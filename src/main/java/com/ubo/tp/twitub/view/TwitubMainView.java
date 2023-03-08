package com.ubo.tp.twitub.view;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import com.ubo.tp.twitub.controller.MainController;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView extends JFrame {

  JPanel mainPanel;
  JMenuItem quitterMenuItem;
  JMenuItem connexionMenuItem;
  JMenuItem monProfilMenuItem;
  JMenuItem createTwitMenuItem;
  JMenuItem deconnexionMenuItem;
  JMenuItem inscriptionMenuItem;

  public TwitubMainView(MainController mainController) {
    super("Twitter");
    setLayout(new GridBagLayout());

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(800, 600));

    // adding logo
    ImageIcon img = new ImageIcon("src/main/resources/images/logo_20.png");
    setIconImage(img.getImage());

    // adding menu
    JMenuBar menuBar = new JMenuBar();
    quitterMenuItem = new JMenuItem(new AbstractAction("Quitter") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.quitter();
      }
    });
    quitterMenuItem.setIcon(new ImageIcon("src/main/resources/images/exitIcon_20.png"));
    menuBar.add(quitterMenuItem);

    JMenuItem aPropos = new JMenuItem(new AbstractAction("A propos") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.aPropos();
      }
    });
    menuBar.add(aPropos);

    JMenuItem accueil = new JMenuItem(new AbstractAction("Accueil") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.twits();
      }
    });
    menuBar.add(accueil);

    menuBar.add(new JMenuItem(new AbstractAction("Utilisateurs") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.showUsers();
      }
    }));

    connexionMenuItem = new JMenuItem(new AbstractAction("Connexion") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.connexion();
      }
    });
    menuBar.add(connexionMenuItem);

    createTwitMenuItem = new JMenuItem(new AbstractAction("Twitter") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.creatTwit();
      }
    });
    createTwitMenuItem.setVisible(false);
    menuBar.add(createTwitMenuItem);

    monProfilMenuItem = new JMenuItem(new AbstractAction("Mon profil") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.goToUser(mainController.getConnectedUser().getUuid());
      }
    });
    monProfilMenuItem.setVisible(false);
    menuBar.add(monProfilMenuItem);

    deconnexionMenuItem = new JMenuItem(new AbstractAction("Deconnexion") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.deconnexion();
      }
    });
    deconnexionMenuItem.setVisible(false);
    menuBar.add(deconnexionMenuItem);

    inscriptionMenuItem = new JMenuItem(new AbstractAction("Inscription") {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainController.showInscription();
      }
    });
    menuBar.add(inscriptionMenuItem);

    setJMenuBar(menuBar);

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        // Custom de l'affichage
        JFrame frame = TwitubMainView.this;
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(0, 0);

        // Affichage
        TwitubMainView.this.setVisible(true);

        TwitubMainView.this.pack();
      }
    });

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    getContentPane().add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

  public void setCurrentPanel(JPanel panel) {
    mainPanel.removeAll();
    mainPanel.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(50, 150, 50, 150), 0, 0));
   panel.setVisible(true);
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  public void setConnectedUserMenu() {
    connexionMenuItem.setVisible(false);
    createTwitMenuItem.setVisible(true);
    monProfilMenuItem.setVisible(true);
    deconnexionMenuItem.setVisible(true);
    inscriptionMenuItem.setVisible(false);
  }

  public void setDisconnectedUserMenu() {
    connexionMenuItem.setVisible(true);
    createTwitMenuItem.setVisible(false);
    monProfilMenuItem.setVisible(false);
    deconnexionMenuItem.setVisible(false);
    inscriptionMenuItem.setVisible(true);
  }
}
