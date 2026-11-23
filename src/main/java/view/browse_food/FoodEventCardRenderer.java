package view.browse_food;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/** Renders preview food events as rounded cards. */
public class FoodEventCardRenderer implements ListCellRenderer<String> {
  private static final Color NAVY = new Color(22, 40, 78);
  private static final Color MUTED = new Color(99, 113, 135);
  private static final Color BLUE = new Color(45, 120, 235);

  @Override
  public Component getListCellRendererComponent(
      JList<? extends String> list, String value, int index,
      boolean isSelected, boolean cellHasFocus) {
    JPanel wrapper = new JPanel(new BorderLayout());
    wrapper.setOpaque(false);
    wrapper.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    RoundedPanel card = new RoundedPanel(isSelected);
    card.setLayout(new BorderLayout(12, 0));
    card.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

    String[] parts = value.split(" - ", 2);
    String title = parts[0];
    JLabel icon = new JLabel(getFoodIcon(title));
    icon.setFont(new Font("SansSerif", Font.PLAIN, 26));
    card.add(icon, BorderLayout.WEST);

    JPanel information = new JPanel();
    information.setOpaque(false);
    information.setLayout(new BoxLayout(information, BoxLayout.Y_AXIS));

    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    titleLabel.setForeground(NAVY);
    information.add(titleLabel);
    information.add(Box.createVerticalStrut(6));

    String building = parts.length > 1 ? parts[1] : "";
    JLabel locationLabel = new JLabel("⌖  " + building);
    locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
    locationLabel.setForeground(MUTED);
    information.add(locationLabel);
    information.add(Box.createVerticalStrut(8));

    JLabel tagsLabel = new JLabel(
        getFoodType(title) + "    •    " + getRegistrationText(title));
    tagsLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
    tagsLabel.setForeground(BLUE);
    information.add(tagsLabel);
    card.add(information, BorderLayout.CENTER);

    JLabel arrow = new JLabel("›");
    arrow.setFont(new Font("SansSerif", Font.PLAIN, 26));
    arrow.setForeground(MUTED);
    card.add(arrow, BorderLayout.EAST);
    wrapper.add(card, BorderLayout.CENTER);
    return wrapper;
  }

  private String getFoodIcon(String title) {
    if (title.contains("Pizza")) {
      return "🍕";
    }
    if (title.contains("Coffee")) {
      return "☕";
    }
    return "🍪";
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

  private String getRegistrationText(String title) {
    if (title.contains("Coffee")) {
      return "Registration required";
    }
    return "No registration required";
  }

  /** Rounded background and border for an event card. */
  private static class RoundedPanel extends JPanel {
    private final boolean selected;

    RoundedPanel(boolean selected) {
      this.selected = selected;
      setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
      super.paintComponent(graphics);
      Graphics2D g2 = (Graphics2D) graphics.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      if (selected) {
        g2.setColor(new Color(232, 242, 255));
      } else {
        g2.setColor(Color.WHITE);
      }
      g2.fillRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 16, 16);
      g2.setColor(selected ? BLUE : new Color(215, 222, 232));
      g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 16, 16);
      g2.dispose();
    }
  }
}
