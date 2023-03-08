package com.ubo.tp.twitub.component;

import javax.swing.JPanel;

import com.ubo.tp.twitub.controller.Controller;
import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.controller.UserListController;
import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.view.UsersPanel;

public class UsersComponent implements Component {
  UsersPanel usersPanel;
  UserListController userListController;
  @Override
  public Component init(EntityManager entityManager, MainController mainController) {
    userListController = new UserListController(entityManager, mainController);
    usersPanel = new UsersPanel(userListController, mainController);
    userListController.setUsersPanel(usersPanel);
    userListController.subcribeToDb();
    return this;
  }

  public void setShowFilter(boolean show) {
    usersPanel.setShowFilter(show);
  }

  @Override
  public JPanel getView() {
    return usersPanel;
  }

  @Override
  public Controller getController() {
    return userListController;
  }
}
