package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Temporary preview for the main application view.
 */
public class MainViewPreview {

  /**
   * Launches the UI preview.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("FreeFood Tracker");

      JPanel browsePlaceholder = new JPanel(new BorderLayout());

      MainView mainView = new MainView(browsePlaceholder);

      frame.setContentPane(mainView);
      frame.setSize(1000, 650);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}