package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */

/**
 * Shows a short message in the top-right corner of the screen
 * and closes it automatically after a few seconds.
 */
public class Notification {

    private static final int DISPLAY_MS = 2500;
    private static final int MARGIN = 20;

    /**
     * Shows a temporary message.
     *
     * @param message the text to show.
     */
    public static void show(String message) {
        JLabel label = new JLabel(message);
        label.setOpaque(true);
        label.setBackground(new Color(51, 51, 51));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JWindow window = new JWindow();
        window.add(label);
        window.pack();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(screen.width - window.getWidth() - MARGIN, MARGIN);
        window.setAlwaysOnTop(true);
        window.setVisible(true);

        Timer timer = new Timer(DISPLAY_MS, e -> window.dispose());
        timer.setRepeats(false);
        timer.start();
    }
}
