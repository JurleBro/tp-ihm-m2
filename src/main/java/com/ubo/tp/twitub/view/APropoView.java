package com.ubo.tp.twitub.view;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class APropoView extends JFrame {

  public APropoView() {
    super("A propos");

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new GridBagLayout());

    JLabel label = new JLabel("UBO M2 TIIL");
    add(label, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    JLabel label3 = new JLabel("TP Twitub");
    add(label3, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    ImageIcon image = new ImageIcon("src/main/resources/images/logo_50.jpg");
    JLabel label2 = new JLabel(image);
    add(label2, new GridBagConstraints(0, 0, 1, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));

    JButton button = new JButton(new AbstractAction("OK") {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    add(button, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        // Custom de l'affichage
        JFrame frame = APropoView.this;
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getWidth()) / 6,
          (screenSize.height - frame.getHeight()) / 4);

        // Affichage
        APropoView.this.setVisible(true);

        APropoView.this.pack();
      }
    });
  }
}
