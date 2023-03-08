package com.ubo.tp.twitub.view;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import com.ubo.tp.twitub.controller.UserProfilController;
import com.ubo.tp.twitub.datamodel.User;

public class UserProfilPanel extends JPanel {

  JLabel usernameLabel;
  JLabel tagLabel;
  JLabel followersLabel;
  JButton followButton;
  JButton unfollowButton;

  User user;

  /**
   * Create the panel.
   */
  public UserProfilPanel(TwitsPanel twitsPanel, UserProfilController userProfilController) {
    super();
    setLayout(new GridBagLayout());
    JPanel userPanel = new JPanel();
    userPanel.setLayout(new GridBagLayout());
    usernameLabel = new JLabel("");
    userPanel.add(
      usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    tagLabel = new JLabel("");
    userPanel.add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    followersLabel = new JLabel("Abonnements : ");
    userPanel.add(
      followersLabel, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    followButton = new JButton(new AbstractAction("S'abonner") {
      @Override
      public void actionPerformed(ActionEvent e) {
        userProfilController.follow(user);
      }
    });
userPanel.add(
      followButton, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    unfollowButton = new JButton(new AbstractAction("Se désabonner") {
      @Override
      public void actionPerformed(ActionEvent e) {
        userProfilController.unfollow(user);
      }
    });
    userPanel.add(
      unfollowButton, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    add(userPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    add(twitsPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

  public void setUser(User user) {
    this.user = user;
    usernameLabel.setText(user.getName());
    tagLabel.setText("@"+user.getUserTag());
    followersLabel.setText("Abonnements : " + user.getFollows().size());
    revalidate();
    repaint();
  }

  public void setIsFollowed(Boolean following) {
    if (following == null) {
      unfollowButton.setVisible(false);
      followButton.setVisible(false);
    }else {
      followButton.setVisible(!following);
      unfollowButton.setVisible(following);
    }
    revalidate();
    repaint();
  }
}
