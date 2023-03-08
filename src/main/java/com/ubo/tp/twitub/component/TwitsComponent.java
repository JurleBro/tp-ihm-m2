package com.ubo.tp.twitub.component;

import javax.swing.JPanel;

import java.util.UUID;

import com.ubo.tp.twitub.controller.Controller;
import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.controller.TwitsController;
import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.view.TwitsPanel;

public class TwitsComponent implements Component {
  TwitsPanel twitsPanel;
  TwitsController twitsController;
  @Override
  public Component init(EntityManager entityManager, MainController mainController) {
    twitsController = new TwitsController(entityManager);
    twitsPanel = new TwitsPanel(twitsController, mainController);
    twitsController.setTwitsPanel(twitsPanel);
    twitsController.subcribeToDb();
    return this;
  }

  public void setShowFilter(boolean show) {
    twitsPanel.setShowFilter(show);
  }

  @Override
  public JPanel getView() {
    return twitsPanel;
  }

  @Override
  public Controller getController() {
    return twitsController;
  }

  public void setFilter(UUID id) {
    twitsController.setUserIdFilter(id);
  }
}
