package main.java.com.ubo.tp.twitub.controller;

import java.util.UUID;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.view.UserProfilPanel;

public class UserProfilController implements Controller, IDatabaseObserver {
  private final EntityManager entityManager;
  private final MainController mainController;
  private UserProfilPanel userProfilPanel;
  private User currentUser;

  public UserProfilController(EntityManager entityManager, MainController mainController) {
    this.entityManager = entityManager;
    this.mainController = mainController;
  }

  public void setUserProfilPanel(UserProfilPanel userProfilPanel) {
    this.userProfilPanel = userProfilPanel;
  }

  public void setUser(UUID id) {
    currentUser = entityManager.getUser(id);
    userProfilPanel.setUser(currentUser);
    User connectedUser = mainController.getConnectedUser();
    userProfilPanel.setIsFollowed(connectedUser == null || connectedUser.equals(currentUser) ? null : connectedUser.isFollowing(currentUser));
  }

  public void unfollow(User user) {
    mainController.getConnectedUser().removeFollowing(user.getUserTag());
    entityManager.sendUser(mainController.getConnectedUser());
    userProfilPanel.setIsFollowed(false);
  }

  public void follow(User user) {
    mainController.getConnectedUser().addFollowing(user.getUserTag());
    entityManager.sendUser(mainController.getConnectedUser());
    userProfilPanel.setIsFollowed(true);
  }

  @Override
  public void notifyUserModified(User modifiedUser) {
    if(currentUser != null && currentUser.equals(modifiedUser)) {
      userProfilPanel.setUser(modifiedUser);
    }
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit) {}
  @Override
  public void notifyTwitDeleted(Twit deletedTwit) {}
  @Override
  public void notifyTwitModified(Twit modifiedTwit) {}
  @Override
  public void notifyUserAdded(User addedUser) {}
  @Override
  public void notifyUserDeleted(User deletedUser) {}
}
