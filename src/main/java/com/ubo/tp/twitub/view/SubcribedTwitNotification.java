package com.ubo.tp.twitub.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import com.ubo.tp.twitub.datamodel.User;

public class SubcribedTwitNotification extends JFrame {

  public SubcribedTwitNotification(User user) {
    super("Nouveau twt");
    setLayout(new GridBagLayout());

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setPreferredSize(new Dimension(200, 100));

    JPanel mainPanel = new JPanel();
    mainPanel.add(new JLabel("Nouveau twt de @" + user.getUserTag()));
    getContentPane().add(mainPanel);
    pack();
    setVisible(true);
    new Timer(3000, e -> dispose()).start();
  }
}
