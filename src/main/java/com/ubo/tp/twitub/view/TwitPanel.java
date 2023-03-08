package com.ubo.tp.twitub.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.util.Date;

import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.datamodel.Twit;

public class TwitPanel extends JPanel {

  private static final DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
    DateFormat.SHORT,
    DateFormat.SHORT);

  /**
   * Create the panel.
   */
  public TwitPanel(Twit twit, MainController mainController) {
    super();
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    setPreferredSize(new Dimension((screenSize.width/3)-25, 75));
    setLayout(new GridBagLayout());
    setBorder(new LineBorder(Color.BLACK));
    setBackground(Color.WHITE);
    // User
    JPanel userPanel = new JPanel();
    userPanel.setBackground(Color.WHITE);
    userPanel.setBorder(new LineBorder(Color.BLACK));
    userPanel.setLayout(new GridBagLayout());
    userPanel.add(new JLabel(twit.getTwiter().getName()), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(5, 5, 0, 5), 0, 0));
    JLabel comp = new JLabel("@" + twit.getTwiter().getUserTag());
    comp.setForeground(Color.GRAY);
    userPanel.add(
      comp, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(5, 5, 0, 5), 0, 0));
    userPanel.add(new JLabel(shortDateFormat.format(new Date(twit.getEmissionDate()))), new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.EAST, new Insets(5, 5, 0, 5), 0, 0));
    userPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        mainController.goToUser(twit.getTwiter().getUuid());
      }
    });
    add(userPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    // Text
    add(new JLabel(twit.getText()), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(5, 5, 0, 5), 0, 0));
  }

}
