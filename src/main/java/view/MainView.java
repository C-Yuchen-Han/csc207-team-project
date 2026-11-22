package view;

import java.awt.*;
import javax.swing.*;

/**
 * Main view containing navigation and use case pages.
 */
public class MainView extends JPanel {

  private final CardLayout cardLayout = new CardLayout();
  private final JPanel contentPanel = new JPanel(cardLayout);

  /**
   * Constructs the main application view.
   *
   * @param browseFoodView the browse food page
   */
  public MainView(JPanel browseFoodView) {
    setLayout(new BorderLayout());

    JPanel sidebar = new JPanel();
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

    sidebar.setPreferredSize(new Dimension(190, 0));
    sidebar.setBackground(new Color(240, 242, 245));
    sidebar.setBorder(BorderFactory.createEmptyBorder(20, 12, 20, 12));

    JButton discoverButton = new JButton("Discover");
    JButton publishButton = new JButton("Input Food");
    JButton notificationsButton = new JButton("Notifications");
    JButton reportsButton = new JButton("Reports");

    Dimension buttonSize = new Dimension(160, 40);

    discoverButton.setMaximumSize(buttonSize);
    publishButton.setMaximumSize(buttonSize);
    notificationsButton.setMaximumSize(buttonSize);
    reportsButton.setMaximumSize(buttonSize);

    JLabel appTitle = new JLabel("FreeFood!");
    appTitle.setFont(new Font("SansSerif", Font.BOLD, 20));

    sidebar.add(appTitle);
    sidebar.add(Box.createVerticalStrut(25));

    sidebar.add(discoverButton);
    sidebar.add(Box.createVerticalStrut(12));

    sidebar.add(publishButton);
    sidebar.add(Box.createVerticalStrut(12));

    sidebar.add(notificationsButton);
    sidebar.add(Box.createVerticalStrut(12));

    sidebar.add(reportsButton);

    add(sidebar, BorderLayout.WEST);

    contentPanel.add(browseFoodView, "DISCOVER");

    // Temporary placeholders for unfinished use cases.
    contentPanel.add(new JPanel(), "PUBLISH");
    contentPanel.add(new JPanel(), "NOTIFICATIONS");
    contentPanel.add(new JPanel(), "REPORTS");

    add(contentPanel, BorderLayout.CENTER);

    discoverButton.addActionListener(event ->
        cardLayout.show(contentPanel, "DISCOVER"));

    publishButton.addActionListener(event ->
        cardLayout.show(contentPanel, "PUBLISH"));

    notificationsButton.addActionListener(event ->
        cardLayout.show(contentPanel, "NOTIFICATIONS"));

    reportsButton.addActionListener(event ->
        cardLayout.show(contentPanel, "REPORTS"));
  }
}