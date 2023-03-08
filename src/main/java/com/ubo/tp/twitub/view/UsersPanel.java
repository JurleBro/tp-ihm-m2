package com.ubo.tp.twitub.view;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.controller.UserListController;
import com.ubo.tp.twitub.datamodel.User;

public class UsersPanel extends JPanel {

  private Map<UUID, UserPanel> userPanelByUserUUID;
  private final MainController mainController;
  private JPanel filterPanel;
  private JPanel userListPanel;

  private UserListController userListController;

  /**
   * Create the panel.
   */
  public UsersPanel(UserListController userListController, MainController mainController) {
    super();
    this.userListController = userListController;
    this.mainController = mainController;
    userPanelByUserUUID = new HashMap<>();
    setLayout(new GridBagLayout());
    userListPanel = new JPanel();
    userListPanel.setLayout(new GridBagLayout());
    JScrollPane scrollPane = new JScrollPane(userListPanel);
    scrollPane.setLayout(new ScrollPaneLayout());
    add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    filterPanel = new JPanel();
    filterPanel.setLayout(new GridBagLayout());
    JTextField filterTextArea = new JTextField(20);
    filterPanel.add(filterTextArea, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
    filterPanel.add(new JButton(new AbstractAction("Filtrer") {
      @Override
      public void actionPerformed(ActionEvent e) {
        userListController.setFilter(filterTextArea.getText());
      }
    }), new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    add(filterPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
  }

  public void addTwit(User user) {
    UserPanel userPanel = new UserPanel(user, mainController, userListController);
    userPanelByUserUUID.put(user.getUuid(), userPanel);
  }

  public void refreshList(List<UUID> usersToShow) {
    userListPanel.removeAll();
    int i = 0;
    for (UUID userId : usersToShow) {
      userListPanel.add(userPanelByUserUUID.get(userId), new GridBagConstraints(0, i, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
      i++;
    }
    userListPanel.revalidate();
    userListPanel.repaint();
  }

  public void setShowFilter(Boolean showFilter) {
    filterPanel.setVisible(showFilter);
    revalidate();
    repaint();
  }

  public void refreshAllButtons() {
    for (UserPanel userPanel : userPanelByUserUUID.values()) {
      userPanel.refreshButtons();
    }
  }
}
