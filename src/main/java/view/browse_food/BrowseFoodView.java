package view.browse_food;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/** View for discovering free food events. */
public class BrowseFoodView extends JPanel {
  private static final Color BACKGROUND = new Color(245, 248, 253);
  private static final Color NAVY = new Color(22, 40, 78);
  private static final Color MUTED = new Color(99, 113, 135);
  private static final Color BLUE = new Color(45, 120, 235);

  private final DefaultListModel<String> eventListModel = new DefaultListModel<>();
  private final JList<String> eventList = new JList<>(eventListModel);
  private final JPanel detailsContent = new JPanel();

  /** Constructs the browse food view. */
  public BrowseFoodView() {
    setLayout(new BorderLayout(0, 20));
    setBackground(BACKGROUND);
    setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    add(createFilterPanel(), BorderLayout.NORTH);
    add(createResultsPanel(), BorderLayout.CENTER);
    loadPreviewEvents();
  }

  /**
   * Creates the search and filter area.
   *
   * @return the filter panel
   */
  private JPanel createFilterPanel() {
    JPanel filterPanel = new JPanel(new BorderLayout(0, 20));
    filterPanel.setOpaque(false);

    JLabel title = new JLabel("Discover FREE FOOD!");
    title.setFont(new Font("SansSerif", Font.BOLD, 27));
    title.setForeground(NAVY);
    title.setHorizontalAlignment(JLabel.CENTER);
    filterPanel.add(title, BorderLayout.NORTH);

    JPanel controlsPanel = new JPanel(new GridLayout(2, 1, 0, 14));
    controlsPanel.setOpaque(false);
    JPanel searchRow = new JPanel(new BorderLayout(15, 0));
    searchRow.setOpaque(false);

    JTextField searchField = new JTextField();
    searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
    searchField.setToolTipText("Search food, events, or organizations");
    searchField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(205, 216, 232)),
        BorderFactory.createEmptyBorder(8, 12, 8, 12)));
    searchRow.add(searchField, BorderLayout.CENTER);

    JComboBox<String> sortByBox = new JComboBox<>(new String[] {
        "Newest First", "Nearest First", "Location (A-Z)"
    });
    sortByBox.setPreferredSize(new Dimension(180, 40));
    searchRow.add(sortByBox, BorderLayout.EAST);
    controlsPanel.add(searchRow);

    JPanel optionsRow = new JPanel(new GridLayout(1, 3, 14, 0));
    optionsRow.setOpaque(false);
    JComboBox<String> foodTypeBox = new JComboBox<>(new String[] {
        "All Food Types", "Pizza", "Snacks", "Drinks"
    });
    optionsRow.add(foodTypeBox);

    JComboBox<String> buildingBox = new JComboBox<>(new String[] {
        "All Buildings", "Bahen Centre", "Robarts Library", "Sidney Smith Hall"
    });
    optionsRow.add(buildingBox);

    JButton searchButton = new JButton("Search");
    searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
    searchButton.setForeground(Color.WHITE);
    searchButton.setBackground(BLUE);
    searchButton.setOpaque(true);
    searchButton.setBorderPainted(false);
    searchButton.setFocusPainted(false);
    optionsRow.add(searchButton);
    controlsPanel.add(optionsRow);
    filterPanel.add(controlsPanel, BorderLayout.CENTER);
    return filterPanel;
  }

  /**
   * Creates the event list and details panels.
   *
   * @return the results panel
   */
  private JPanel createResultsPanel() {
    JPanel resultsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
    resultsPanel.setOpaque(false);

    JPanel listPanel = new JPanel(new BorderLayout());
    listPanel.setBackground(Color.WHITE);
    listPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(215, 222, 232)),
        BorderFactory.createEmptyBorder(0, 0, 10, 0)));
    listPanel.add(createSectionTitle("  Available Food"), BorderLayout.NORTH);

    eventList.setBackground(Color.WHITE);
    eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    eventList.setCellRenderer(new FoodEventCardRenderer());
    eventList.setFixedCellHeight(125);
    JScrollPane listScroll = new JScrollPane(eventList);
    listScroll.setBorder(BorderFactory.createEmptyBorder());
    listPanel.add(listScroll, BorderLayout.CENTER);

    JPanel detailsPanel = new JPanel(new BorderLayout());
    detailsPanel.setBackground(Color.WHITE);
    detailsPanel.setBorder(BorderFactory.createLineBorder(new Color(215, 222, 232)));
    detailsPanel.add(createSectionTitle("  Event Details"), BorderLayout.NORTH);

    detailsContent.setLayout(new BoxLayout(detailsContent, BoxLayout.Y_AXIS));
    detailsContent.setBackground(Color.WHITE);
    detailsContent.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
    JScrollPane detailsScroll = new JScrollPane(detailsContent);
    detailsScroll.setBorder(BorderFactory.createEmptyBorder());
    detailsPanel.add(detailsScroll, BorderLayout.CENTER);

    resultsPanel.add(listPanel);
    resultsPanel.add(detailsPanel);
    return resultsPanel;
  }

  private JLabel createSectionTitle(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("SansSerif", Font.BOLD, 18));
    label.setForeground(NAVY);
    label.setPreferredSize(new Dimension(100, 58));
    label.setBorder(BorderFactory.createMatteBorder(
        0, 0, 1, 0, new Color(225, 230, 238)));
    return label;
  }

  private void loadPreviewEvents() {
    eventListModel.addElement("Free Pizza - Bahen Centre");
    eventListModel.addElement("Free Snacks - Robarts Library");
    eventListModel.addElement("Free Coffee - Sidney Smith Hall");
    eventList.addListSelectionListener(event -> {
      if (!event.getValueIsAdjusting()) {
        showEventDetails(eventList.getSelectedValue());
      }
    });
    eventList.setSelectedIndex(0);
  }

  private void showEventDetails(String selectedEvent) {
    detailsContent.removeAll();
    if (selectedEvent == null) {
      addDetailText("Select an event to view details.", false);
    } else {
      String[] parts = selectedEvent.split(" - ", 2);
      String title = parts[0];
      JPanel heading = new JPanel();
      heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
      heading.setBackground(new Color(232, 242, 255));
      heading.setBorder(BorderFactory.createEmptyBorder(18, 15, 18, 15));
      heading.setAlignmentX(LEFT_ALIGNMENT);

      JLabel eventTitle = new JLabel(title);
      eventTitle.setFont(new Font("SansSerif", Font.BOLD, 23));
      eventTitle.setForeground(NAVY);
      heading.add(eventTitle);
      heading.add(Box.createVerticalStrut(10));

      String building = parts.length > 1 ? parts[1] : "";
      JLabel location = new JLabel("⌖  " + building);
      location.setForeground(MUTED);
      heading.add(location);
      detailsContent.add(heading);
      detailsContent.add(Box.createVerticalStrut(25));

      addDetailText("Description", true);
      addDetailText("Free food available!", false);
      detailsContent.add(Box.createVerticalStrut(22));
      addDetailText("Food Types", true);
      addDetailText(getFoodType(title), false);
      detailsContent.add(Box.createVerticalStrut(22));
      addDetailText("Registration", true);
      addDetailText(title.contains("Coffee")
          ? "Registration required" : "Not required", false);
    }
    detailsContent.revalidate();
    detailsContent.repaint();
  }

  private void addDetailText(String text, boolean heading) {
    JLabel label = new JLabel(text);
    label.setAlignmentX(LEFT_ALIGNMENT);
    label.setForeground(heading ? NAVY : MUTED);
    label.setFont(new Font("SansSerif", heading ? Font.BOLD : Font.PLAIN,
        heading ? 15 : 14));
    detailsContent.add(label);
    detailsContent.add(Box.createVerticalStrut(6));
  }

  private String getFoodType(String title) {
    if (title.contains("Pizza")) {
      return "Pizza";
    }
    if (title.contains("Coffee")) {
      return "Drinks";
    }
    return "Snacks";
  }
}
