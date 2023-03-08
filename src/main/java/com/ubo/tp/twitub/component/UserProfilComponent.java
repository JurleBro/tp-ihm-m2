package com.ubo.tp.twitub.component;

import javax.swing.JPanel;

import java.util.UUID;

import com.ubo.tp.twitub.controller.Controller;
import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.controller.UserProfilController;
import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.view.TwitsPanel;
import com.ubo.tp.twitub.view.UserProfilPanel;

public class UserProfilComponent implements Component {
  UserProfilPanel userProfilPanel;
  UserProfilController userProfilController;
  TwitsComponent twitsComponent;
  @Override
  public Component init(EntityManager entityManager, MainController mainController) {
    userProfilController = new UserProfilController(entityManager, mainController);
    entityManager.subscribeToDb(userProfilController);
    twitsComponent = (TwitsComponent) (new TwitsComponent()).init(entityManager, mainController);
    twitsComponent.setShowFilter(false);
    userProfilPanel = new UserProfilPanel((TwitsPanel) twitsComponent.getView(), userProfilController);
    userProfilController.setUserProfilPanel(userProfilPanel);
    return this;
  }

  public void setUser(UUID id) {
    twitsComponent.setFilter(id);
    userProfilController.setUser(id);
  }

  @Override
  public JPanel getView() {
    return userProfilPanel;
  }

  @Override
  public Controller getController() {
    return userProfilController;
  }
}
