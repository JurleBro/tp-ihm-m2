package com.ubo.tp.twitub.view;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.controller.UserListController;
import com.ubo.tp.twitub.datamodel.User;

public class UserPanel extends JPanel {

  private JButton followButton;
  private JButton unfollowButton;

  private final MainController mainController;
  private final User user;

  /**
   * Create the panel.
   */
  public UserPanel(User user, MainController mainController, UserListController userListController) {
    super();
    this.user = user;
    this.mainController = mainController;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setPreferredSize(new Dimension((screenSize.width / 3) - 25, 50));
    setLayout(new GridBagLayout());
    setBorder(new LineBorder(Color.BLACK));
    setBackground(Color.WHITE);
    // User
    JPanel userPanel = new JPanel();
    userPanel.setBackground(Color.WHITE);
    userPanel.setBorder(new LineBorder(Color.BLACK));
    userPanel.setLayout(new GridBagLayout());
    userPanel.add(new JLabel(user.getName()),
      new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(5, 5, 0, 5),
        0, 0));
    JLabel comp = new JLabel("@" + user.getUserTag());
    comp.setForeground(Color.GRAY);
    userPanel.add(comp,
      new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(5, 5, 0, 5),
        0, 0));
    userPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        mainController.goToUser(user.getUuid());
      }
    });
    add(userPanel,
      new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0),
        0, 0));

    followButton = new JButton(new AbstractAction("S'abonner") {
      @Override
      public void actionPerformed(ActionEvent e) {
        userListController.follow(user);
        followButton.setVisible(false);
        unfollowButton.setVisible(true);
      }
    });

    followButton.setVisible(mainController.getConnectedUser() != null
      && !mainController.getConnectedUser().getUuid().equals(user.getUuid())
      && !mainController.getConnectedUser().getFollows().contains(user.getUserTag())
    );

    unfollowButton = new JButton(new AbstractAction("Se désabonner") {
      @Override
      public void actionPerformed(ActionEvent e) {
        userListController.unfollow(user);
        followButton.setVisible(true);
        unfollowButton.setVisible(false);
      }
    });

    unfollowButton.setVisible(mainController.getConnectedUser() != null
      && !mainController.getConnectedUser().getUuid().equals(user.getUuid())
      && mainController.getConnectedUser().getFollows().contains(user.getUserTag())
    );

    add(followButton,
      new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0),
        0, 0));

    add(unfollowButton,
      new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0),
        0, 0));
  }

  public void refreshButtons() {
    unfollowButton.setVisible(mainController.getConnectedUser() != null
      && !mainController.getConnectedUser().getUuid().equals(user.getUuid())
      && mainController.getConnectedUser().getFollows().contains(user.getUserTag())
    );

    followButton.setVisible(mainController.getConnectedUser() != null
      && !mainController.getConnectedUser().getUuid().equals(user.getUuid())
      && !mainController.getConnectedUser().getFollows().contains(user.getUserTag())
    );
  }
}
