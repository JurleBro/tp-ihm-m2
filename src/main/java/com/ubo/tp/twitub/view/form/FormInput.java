package com.ubo.tp.twitub.view.form;

import javax.swing.JTextField;
import java.util.List;

public class FormInput {
  private String label;
  private JTextField textField;
  private List<Integer> errorCodes;
  private Integer maxLength;

  public FormInput(String label, JTextField textField, Integer maxLength, List<Integer> errorCodes) {
    this.label = label;
    this.textField = textField;
    this.errorCodes = errorCodes;
    this.maxLength = maxLength;
  }

  public String getLabel() {
    return label;
  }

  public JTextField getTextField() {
    return textField;
  }

  public List<Integer> getErrorCodes() {
    return errorCodes;
  }

  public Integer getMaxLength() {
    return maxLength;
  }
}
