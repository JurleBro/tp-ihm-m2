package main.java.com.ubo.tp.twitub.controller;

import main.java.com.ubo.tp.twitub.datamodel.IDatabaseObserver;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.view.SubcribedTwitNotification;

public class SubscribedTwitNotificationController implements IDatabaseObserver {
  private final MainController mainController;

  public SubscribedTwitNotificationController(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit) {
    if (mainController.getConnectedUser() != null &&
      mainController.getConnectedUser().getFollows().contains(addedTwit.getTwiter().getUserTag())) {
      new SubcribedTwitNotification(addedTwit.getTwiter());
    }
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit) {

  }

  @Override
  public void notifyTwitModified(Twit modifiedTwit) {

  }

  @Override
  public void notifyUserAdded(User addedUser) {

  }

  @Override
  public void notifyUserDeleted(User deletedUser) {

  }

  @Override
  public void notifyUserModified(User modifiedUser) {

  }
}
