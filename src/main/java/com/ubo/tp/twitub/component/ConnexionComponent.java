package com.ubo.tp.twitub.component;

import javax.swing.JPanel;

import com.ubo.tp.twitub.controller.ConnexionController;
import com.ubo.tp.twitub.controller.Controller;
import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.view.ConnexionForm;

public class ConnexionComponent implements Component {
  ConnexionForm connexionPanel;
  ConnexionController connexionController;
  @Override
  public Component init(EntityManager entityManager, MainController mainController) {
    connexionController = new ConnexionController(entityManager);
    connexionPanel = new ConnexionForm(connexionController);
    return this;
  }

  @Override
  public JPanel getView() {
    return connexionPanel;
  }

  @Override
  public Controller getController() {
    return connexionController;
  }
}
