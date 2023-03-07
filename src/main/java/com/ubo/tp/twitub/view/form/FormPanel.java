package main.java.com.ubo.tp.twitub.view.form;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FormPanel extends JPanel {
  private Map<Integer, JLabel> errorLabelsByCode;
  private List<FormInput> formInputs;

  public FormPanel() {
    super();
    setLayout(new GridBagLayout());
    formInputs = new ArrayList<>();
    errorLabelsByCode = new HashMap<>();
  }

  public void build() {

    for (int i = 0; i < formInputs.size(); i++) {
      addFormInput(i, formInputs.get(i));
    }

    addSubmitButton();
  }

  private void addSubmitButton() {
    JButton button = new JButton(new AbstractAction(getSubmitButtonText()) {
      @Override
      public void actionPerformed(ActionEvent e) {
        Map<String, String> valueByFieldName = formInputs.stream()
          .collect(HashMap::new, (map, input) -> map.put(input.getLabel(), input.getTextField().getText()),
            HashMap::putAll);
        List<Integer> errorCodes = onSubmit(valueByFieldName);
        errorLabelsByCode.values().forEach(label -> label.setVisible(false));
        if (errorCodes == null) {
          formInputs.forEach(input -> input.getTextField().setText(""));
        } else {
          errorCodes.forEach(code -> errorLabelsByCode.get(code).setVisible(true));
        }
      }
    });
    add(button,
      new GridBagConstraints(0, formInputs.size() + 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
  }

  private void addFormInput(Integer position, FormInput formInput) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.add(new JLabel(formInput.getLabel()),
      new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0),
        0, 0));
    panel.add(formInput.getTextField(),
      new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0),
        0, 0));
    formInput.getErrorCodes().forEach(code -> {
      JLabel errorLabel = errorLabelsByCode.get(code);
      panel.add(errorLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    });
    if (formInput.getMaxLength() != null) {
      JLabel maxLengthLabel = new JLabel("0/" + formInput.getMaxLength());
      panel.add(maxLengthLabel,
        new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE,
          new Insets(0, 0, 0, 0), 0, 0));
      formInput.getTextField().getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
          updtCharCount();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
          updtCharCount();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
          updtCharCount();
        }

        private void updtCharCount() {
          int length = formInput.getTextField().getText().length();
          maxLengthLabel.setText(length + "/" + formInput.getMaxLength());
          if (length > 250) {
            maxLengthLabel.setForeground(Color.RED);
          } else {
            maxLengthLabel.setForeground(Color.BLACK);
          }
        }

      });
    }
    add(panel, new GridBagConstraints(0, position, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
      new Insets(0, 0, 0, 0), 0, 0));
  }

  public abstract String getSubmitButtonText();

  public abstract List<Integer> onSubmit(Map<String, String> valueByFieldName);

  public void addErrorLabel(int code, String message) {
    JLabel label = new JLabel(message);
    label.setVisible(false);
    label.setForeground(Color.RED);
    errorLabelsByCode.put(code, label);
  }

  public void addInput(String label, JTextField textField, Integer maxLength, Integer... errorCodes) {
    List<Integer> errorCodesList = errorCodes == null ? Collections.emptyList() : Arrays.asList(errorCodes);
    formInputs.add(new FormInput(label, textField, maxLength, errorCodesList));
  }
}
