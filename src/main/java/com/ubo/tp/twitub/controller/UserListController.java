package main.java.com.ubo.tp.twitub.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.events.file.ConnexionObserver;
import main.java.com.ubo.tp.twitub.view.UsersPanel;

public class UserListController implements Controller, IDatabaseObserver, ConnexionObserver, DeconnexionObserver {
  private final EntityManager entityManager;
  private final MainController mainController;
  private UsersPanel usersPanel;
  private Set<User> allUsers;
  private String filter;

  public UserListController(EntityManager entityManager, MainController mainController) {
    this.entityManager = entityManager;
    this.mainController = mainController;
    this.allUsers = new HashSet<>();
  }

  public void subcribeToDb() {
    entityManager.subscribeToDb(this);
  }

  public void setUsersPanel(UsersPanel usersPanel) {
    this.usersPanel = usersPanel;
  }

  public Set<Twit> getTwits() {
    return entityManager.getTwits();
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit) {
  }

  private List<UUID> getUuidsFiltered() {
    return allUsers
      .stream()
      .filter(user -> filter == null
        || (filter.startsWith("@")
            && user.getUserTag().toLowerCase().contains(filter.substring(1).toLowerCase()))
        || user.getName().toLowerCase().contains(filter.toLowerCase())
      )
      .sorted((e1, e2) -> e2.getName().compareToIgnoreCase(e1.getName()))
      .map(User::getUuid)
      .collect(Collectors.toList());
  }

  @Override
  public void notifyUserAdded(User addedUser) {
    allUsers.add(addedUser);
    usersPanel.addTwit(addedUser);
    List<UUID> users = getUuidsFiltered();
    usersPanel.refreshList(users);
  }

  public void setFilter(String text) {
    this.filter = text.isEmpty() ? null : text;
    usersPanel.refreshList(getUuidsFiltered());
  }

  public void unfollow(User user) {
    mainController.getConnectedUser().removeFollowing(user.getUserTag());
    entityManager.sendUser(mainController.getConnectedUser());
  }

  public void follow(User user) {
    mainController.getConnectedUser().addFollowing(user.getUserTag());
    entityManager.sendUser(mainController.getConnectedUser());
  }

  @Override
  public void notifyConnexion(User connectedUser) {
    usersPanel.refreshAllButtons();
  }

  @Override
  public void notifyDeconnexion() {
    usersPanel.refreshAllButtons();
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit) {}
  @Override
  public void notifyTwitModified(Twit modifiedTwit) {}
  @Override
  public void notifyUserDeleted(User deletedUser) {}
  @Override
  public void notifyUserModified(User modifiedUser) {}
}
