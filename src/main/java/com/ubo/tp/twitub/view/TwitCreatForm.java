package com.ubo.tp.twitub.view;

import javax.swing.JTextField;
import java.util.List;
import java.util.Map;

import com.ubo.tp.twitub.controller.TwitCreatController;
import com.ubo.tp.twitub.view.form.FormPanel;

public class TwitCreatForm extends FormPanel {

  private final TwitCreatController twitCreatController;

  @Override
  public String getSubmitButtonText() {
    return "Twitter";
  }

  @Override
  public List<Integer> onSubmit(Map<String, String> valueByFieldName) {
    return twitCreatController.twitter(valueByFieldName.get("Message"));
  }

  public TwitCreatForm(TwitCreatController twitCreatController) {
    super();
    this.twitCreatController = twitCreatController;

    addErrorLabel(-1, "Ne peux pas être vide");
    addErrorLabel(-2, "Ne peux pas dépasser 250 caractères");

    addInput("Message", new JTextField(20), 250, -1, -2);

    build();
  }

}
