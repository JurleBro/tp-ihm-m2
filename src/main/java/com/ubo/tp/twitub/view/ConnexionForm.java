package com.ubo.tp.twitub.view;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.List;
import java.util.Map;

import com.ubo.tp.twitub.controller.ConnexionController;
import com.ubo.tp.twitub.view.form.FormPanel;

public class ConnexionForm extends FormPanel {


  private final ConnexionController connexionController;

  public ConnexionForm(ConnexionController connexionController) {
    super();
    this.connexionController = connexionController;
    addErrorLabel(-1, "Ne peux pas être vide");
    addErrorLabel(-2, "Ne peux pas être vide");
    addErrorLabel(-3, "Nom ou mdp incorrect");

    addInput("Nom", new JTextField(20),null,  -1);
    addInput("Mot de passe", new JPasswordField(20),null,  -2, -3);

    build();
  }

  @Override
  public String getSubmitButtonText() {
    return "Connexion";
  }

  @Override
  public List<Integer> onSubmit(Map<String, String> valueByFieldName) {
    return connexionController.connexion(valueByFieldName.get("Nom"), valueByFieldName.get("Mot de passe"));
  }
}
