package main.java.com.ubo.tp.twitub.component;

import javax.swing.JPanel;

import main.java.com.ubo.tp.twitub.controller.Controller;
import main.java.com.ubo.tp.twitub.controller.MainController;
import main.java.com.ubo.tp.twitub.core.EntityManager;

public interface Component {
  public Component init(EntityManager entityManager, MainController mainController);
  public JPanel getView();
  public Controller getController();
}
