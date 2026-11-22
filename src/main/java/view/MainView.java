package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Main application view with a navigation sidebar. */
public class MainView extends JPanel {
  private static final Color SIDEBAR_COLOR = new Color(231, 240, 253);
  private static final Color NAVY = new Color(22, 40, 78);
  private static final Color BLUE = new Color(45, 120, 235);
  private static final String DISCOVER = "discover";
  private static final String PUBLISH = "publish";
  private static final String NOTIFICATIONS = "notifications";
  private static final String REPORTS = "reports";

  private final CardLayout cardLayout = new CardLayout();
  private final JPanel contentPanel = new JPanel(cardLayout);

  /**
   * Constructs the main view.
   *
   * @param browseFoodView the browse food page
   */
  public MainView(JPanel browseFoodView) {
    setLayout(new BorderLayout());
    contentPanel.add(browseFoodView, DISCOVER);
    contentPanel.add(createPlaceholder("Input Food"), PUBLISH);
    contentPanel.add(createPlaceholder("Notifications"), NOTIFICATIONS);
    contentPanel.add(createPlaceholder("Reports"), REPORTS);
    add(createSidebar(), BorderLayout.WEST);
    add(contentPanel, BorderLayout.CENTER);
    cardLayout.show(contentPanel, DISCOVER);
  }

  private JPanel createSidebar() {
    JPanel sidebar = new JPanel();
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
    sidebar.setBackground(SIDEBAR_COLOR);
    sidebar.setPreferredSize(new Dimension(205, 0));
    sidebar.setBorder(BorderFactory.createEmptyBorder(25, 15, 20, 15));

    JLabel appTitle = new JLabel("FreeFood!");
    appTitle.setFont(new Font("SansSerif", Font.BOLD, 25));
    appTitle.setForeground(NAVY);
    appTitle.setAlignmentX(LEFT_ALIGNMENT);
    sidebar.add(appTitle);
    sidebar.add(Box.createVerticalStrut(35));

    JButton discoverButton = createNavButton("Discover", true);
    JButton publishButton = createNavButton("Input Food", false);
    JButton notificationsButton = createNavButton("Notifications", false);
    JButton reportsButton = createNavButton("Reports", false);
    JButton[] buttons = {
        discoverButton, publishButton, notificationsButton, reportsButton
    };

    discoverButton.addActionListener(event -> navigate(DISCOVER, discoverButton, buttons));
    publishButton.addActionListener(event -> navigate(PUBLISH, publishButton, buttons));
    notificationsButton.addActionListener(event ->
        navigate(NOTIFICATIONS, notificationsButton, buttons));
    reportsButton.addActionListener(event -> navigate(REPORTS, reportsButton, buttons));

    for (JButton button : buttons) {
      sidebar.add(button);
      sidebar.add(Box.createVerticalStrut(12));
    }
    return sidebar;
  }

  private JButton createNavButton(String text, boolean selected) {
    JButton button = new JButton(text);
    button.setFont(new Font("SansSerif", Font.BOLD, 14));
    button.setAlignmentX(LEFT_ALIGNMENT);
    button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
    button.setPreferredSize(new Dimension(175, 44));
    button.setFocusPainted(false);
    button.setOpaque(true);
    button.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
    styleNavButton(button, selected);
    return button;
  }

  private void styleNavButton(JButton button, boolean selected) {
    button.setBackground(selected ? BLUE : Color.WHITE);
    button.setForeground(selected ? Color.WHITE : NAVY);
  }

  private void navigate(String page, JButton selectedButton, JButton[] buttons) {
    cardLayout.show(contentPanel, page);
    for (JButton button : buttons) {
      styleNavButton(button, button == selectedButton);
    }
  }

  private JPanel createPlaceholder(String title) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(245, 248, 253));
    JLabel label = new JLabel(title);
    label.setFont(new Font("SansSerif", Font.BOLD, 24));
    label.setForeground(NAVY);
    label.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label, BorderLayout.CENTER);
    return panel;
  }
}
