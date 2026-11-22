package view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import view.browse_food.BrowseFoodView;

/** Runs the Swing UI without compiling unfinished team features. */
public final class MainViewPreview {
  private MainViewPreview() {
  }

  /**
   * Starts the preview.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("FreeFood Tracker");
      frame.setContentPane(new MainView(new BrowseFoodView()));
      frame.setSize(1000, 650);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}
