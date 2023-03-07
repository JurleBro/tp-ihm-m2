package main.java.com.ubo.tp.twitub.controller;

import java.util.Collections;
import java.util.List;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Twit;

public class TwitCreatController implements Controller {

  private final EntityManager mEntityManager;
  private final MainController mainController;

  public TwitCreatController(EntityManager entityManager, MainController mainController) {
    this.mEntityManager = entityManager;
    this.mainController = mainController;
  }
  public List<Integer> twitter(String message) {
    if (message == null || message.isEmpty()) {
      return Collections.singletonList(-1);
    } else if (message.length() > 250) {
      return Collections.singletonList(-2);
    }
    Twit twit = new Twit(mainController.getConnectedUser(), message);
    mEntityManager.sendTwit(twit);
    mainController.twits();
    return null;
  }

}
