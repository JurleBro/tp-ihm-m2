package main.java.com.ubo.tp.twitub.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import main.java.com.ubo.tp.twitub.component.ConnexionComponent;
import main.java.com.ubo.tp.twitub.component.InscriptionComponent;
import main.java.com.ubo.tp.twitub.component.TwitCreatComponent;
import main.java.com.ubo.tp.twitub.component.TwitsComponent;
import main.java.com.ubo.tp.twitub.component.UserProfilComponent;
import main.java.com.ubo.tp.twitub.component.UsersComponent;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.events.file.ConnexionObserver;
import main.java.com.ubo.tp.twitub.view.APropoView;
import main.java.com.ubo.tp.twitub.view.TwitubMainView;

public class MainController implements ConnexionObserver {
  TwitubMainView mainFrame;
  ConnexionComponent connexionComponent;
  TwitsComponent twitsComponent;
  TwitCreatComponent twitCreatComponent;
  UserProfilComponent userProfilComponent;
  User connectedUser;
  InscriptionComponent inscriptionComponent;
  UsersComponent usersComponent;

  Set<DeconnexionObserver> deconnexionObserverSet;

  public MainController(EntityManager entityManager) {
    deconnexionObserverSet = new HashSet<>();
    this.mainFrame = new TwitubMainView(this);
    connexionComponent = (ConnexionComponent) (new ConnexionComponent()).init(entityManager, this);
    ((ConnexionController) connexionComponent.getController()).addObserver(this);
    twitsComponent = (TwitsComponent) (new TwitsComponent()).init(entityManager, this);
    twitsComponent.setShowFilter(true);
    usersComponent = (UsersComponent) (new UsersComponent()).init(entityManager, this);
    usersComponent.setShowFilter(true);
    deconnexionObserverSet.add((UserListController) usersComponent.getController());
    ((ConnexionController) connexionComponent.getController()).addObserver((UserListController) usersComponent.getController());
    twitCreatComponent = (TwitCreatComponent) (new TwitCreatComponent()).init(entityManager, this);
    userProfilComponent = (UserProfilComponent) (new UserProfilComponent()).init(entityManager, this);
    entityManager.subscribeToDb(new SubscribedTwitNotificationController(this));
    inscriptionComponent = (InscriptionComponent) (new InscriptionComponent()).init(entityManager, this);
    twits();
  }

  public void show() {
    mainFrame.setVisible(true);
  }

  public void quitter() {
    System.exit(0);
  }

  public void connexion() {
    mainFrame.setCurrentPanel(connexionComponent.getView());
  }

  public void aPropos() {
    new APropoView();
  }

  public void twits() {
    mainFrame.setCurrentPanel(twitsComponent.getView());
  }
  public void showUsers() {
    mainFrame.setCurrentPanel(usersComponent.getView());
  }

  public void creatTwit() {
    mainFrame.setCurrentPanel(twitCreatComponent.getView());
  }

  public void showInscription() {
    mainFrame.setCurrentPanel(inscriptionComponent.getView());
  }

  public void goToUser(UUID id) {
    userProfilComponent.setUser(id);
    mainFrame.setCurrentPanel(userProfilComponent.getView());
  }

  @Override
  public void notifyConnexion(User connectedUser) {
    this.connectedUser = connectedUser;
    mainFrame.setConnectedUserMenu();
    twits();
  }

  public User getConnectedUser() {
    return connectedUser;
  }

  public void deconnexion() {
    this.connectedUser = null;
    mainFrame.setDisconnectedUserMenu();
    deconnexionObserverSet.forEach(DeconnexionObserver::notifyDeconnexion);
    twits();
  }
}
