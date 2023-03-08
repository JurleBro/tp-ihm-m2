package com.ubo.tp.twitub.view;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ubo.tp.twitub.controller.MainController;
import com.ubo.tp.twitub.controller.TwitsController;
import com.ubo.tp.twitub.datamodel.Twit;

public class TwitsPanel extends JPanel {

  private Map<UUID, TwitPanel> twitsPanelByTwit;

  private final MainController mainController;
  private JPanel filterPanel;
  private JPanel twitListPanel;

  /**
   * Create the panel.
   */
  public TwitsPanel(TwitsController twitsController, MainController mainController) {
    super();
    this.mainController = mainController;
    twitsPanelByTwit = new HashMap<>();
    setLayout(new GridBagLayout());
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    setPreferredSize(new Dimension(screenSize.width/2, screenSize.height));
    twitListPanel = new JPanel();
    twitListPanel.setLayout(new GridBagLayout());
    JScrollPane scrollPane = new JScrollPane(twitListPanel);
    add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    filterPanel = new JPanel();
    filterPanel.setLayout(new GridBagLayout());
    JTextField filterTextArea = new JTextField(20);
    filterPanel.add(filterTextArea, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
    filterPanel.add(new JButton(new AbstractAction("Filtrer") {
      @Override
      public void actionPerformed(ActionEvent e) {
        twitsController.setFilter(filterTextArea.getText());
      }
    }), new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    add(filterPanel, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
  }

  public void addTwit(Twit twit) {
    TwitPanel twitPanel = new TwitPanel(twit, mainController);
    twitsPanelByTwit.put(twit.getUuid(), twitPanel);
  }

  public void refreshList(List<UUID> twitsToShow) {
    twitListPanel.removeAll();
    int i = 0;
    for (UUID twitId : twitsToShow) {
      twitListPanel.add(twitsPanelByTwit.get(twitId), new GridBagConstraints(0, i, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
      i++;
    }
    twitListPanel.revalidate();
    twitListPanel.repaint();
  }

  public void setShowFilter(Boolean showFilter) {
    filterPanel.setVisible(showFilter);
    revalidate();
    repaint();
  }
}
