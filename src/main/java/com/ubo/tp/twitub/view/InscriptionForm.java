package main.java.com.ubo.tp.twitub.view;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.List;
import java.util.Map;

import main.java.com.ubo.tp.twitub.controller.InscriptionController;
import main.java.com.ubo.tp.twitub.view.form.FormPanel;

public class InscriptionForm extends FormPanel {

  private final InscriptionController inscriptionController;

  @Override
  public String getSubmitButtonText() {
    return "Inscription";
  }

  @Override
  public List<Integer> onSubmit(Map<String, String> valueByFieldName) {
    return inscriptionController.inscription(valueByFieldName.get("Nom"), valueByFieldName.get("Tag"),
      valueByFieldName.get("Mot de passe"));
  }

  public InscriptionForm(InscriptionController inscriptionController) {
    super();
    this.inscriptionController = inscriptionController;
    addErrorLabel(-1, "Ne peux pas être vide");
    addErrorLabel(-2, "Ne peux pas être vide");
    addErrorLabel(-3, "Déjà utilisé");
    addErrorLabel(-4, "Ne peux pas être vide");

    addInput("Nom", new JTextField(20), null,  -1);
    addInput("Tag", new JTextField(20), null, -2, -3);
    addInput("Mot de passe", new JPasswordField(20), null, -4);

    build();
  }

}
