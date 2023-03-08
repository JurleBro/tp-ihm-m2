package com.ubo.tp.twitub.component;

import javax.swing.JPanel;

import com.ubo.tp.twitub.controller.Controller;
import com.ubo.tp.twitub.controller.InscriptionController;
import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.core.EntityManager;
import com.ubo.tp.twitub.view.InscriptionForm;

public class InscriptionComponent implements Component {
  InscriptionForm inscriptionForm;
  InscriptionController inscriptionController;
  @Override
  public Component init(EntityManager entityManager, MainController mainController) {
    inscriptionController = new InscriptionController(entityManager, mainController);
    inscriptionForm = new InscriptionForm(inscriptionController);
    return this;
  }

  @Override
  public JPanel getView() {
    return inscriptionForm;
  }

  @Override
  public Controller getController() {
    return inscriptionController;
  }
}
