package main.java.com.ubo.tp.twitub.component;

import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.controller.Controller;
import main.java.com.ubo.tp.twitub.controller.MainController;
import main.java.com.ubo.tp.twitub.controller.TwitCreatController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.view.TwitCreatForm;

public class TwitCreatComponent implements Component {
  TwitCreatForm twitCreatForm;
  TwitCreatController twitCreatController;
  @Override
  public Component init(EntityManager entityManager, MainController mainController) {
    twitCreatController = new TwitCreatController(entityManager, mainController);
    twitCreatForm = new TwitCreatForm(twitCreatController);
    return this;
  }

  @Override
  public JPanel getView() {
    return twitCreatForm;
  }

  @Override
  public Controller getController() {
    return twitCreatController;
  }
}
