/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.notification;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */

/**
 * Floating window that shows a temporary message in the top-right corner
 * of its parent window. It fades in, stays visible for a few seconds and
 * fades out. Several notifications are stacked one below the other.
 * <p>
 * Usage (from the Swing thread):
 * <pre>
 * Notification.show(parentWindow, NotificationType.SUCCESS, "Saved");
 * </pre>
 */
public class Notification extends JWindow {

    private static final List<Notification> ACTIVE = new ArrayList<>();

    private static final int MARGIN = 20;
    private static final int GAP = 10;
    private static final int TOP_OFFSET = 50;
    private static final int DISPLAY_MS = 3000;
    private static final int FADE_STEP_MS = 20;
    private static final float FADE_STEP = 0.08f;
    private static final int CORNER_RADIUS = 16;
    private static final int STRIPE_WIDTH = 8;

    private final Window owner;

    private Notification(Window owner, NotificationType type, String message) {
        super(owner);
        this.owner = owner;
        setBackground(new Color(0, 0, 0, 0));
        setContentPane(buildContent(type, message));
        pack();
    }

    /**
     * Creates and shows a notification. Must be called from the Swing thread.
     *
     * @param owner   window where the notification will appear
     * @param type    kind of notification (color and symbol)
     * @param message text to display
     */
    public static void show(Window owner, NotificationType type, String message) {
        new Notification(owner, type, message).display();
    }

    private JPanel buildContent(NotificationType type, String message) {
        JPanel panel = new JPanel(new BorderLayout(12, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                paintCard((Graphics2D) g.create(), getWidth(), getHeight(), type.getColor());
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(14, 22, 14, 18));
        panel.add(buildIcon(type), BorderLayout.WEST);
        panel.add(buildText(message), BorderLayout.CENTER);
        return panel;
    }

    private void paintCard(Graphics2D g2, int width, int height, Color stripeColor) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(stripeColor);
        g2.fillRoundRect(0, 0, width, height, CORNER_RADIUS, CORNER_RADIUS);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(STRIPE_WIDTH, 0, width - STRIPE_WIDTH, height, CORNER_RADIUS, CORNER_RADIUS);
        g2.fillRect(STRIPE_WIDTH, 0, CORNER_RADIUS, height);
        g2.dispose();
    }

    private JLabel buildIcon(NotificationType type) {
        JLabel icon = new JLabel(type.getIcon());
        icon.setForeground(type.getColor());
        icon.setFont(icon.getFont().deriveFont(Font.BOLD, 20f));
        return icon;
    }

    private JLabel buildText(String message) {
        JLabel text = new JLabel(message);
        text.setForeground(new Color(0x333333));
        return text;
    }

    private void display() {
        Rectangle bounds = owner.getBounds();
        int y = bounds.y + TOP_OFFSET;
        for (Notification other : ACTIVE) {
            y += other.getHeight() + GAP;
        }
        ACTIVE.add(this);
        setLocation(bounds.x + bounds.width - getWidth() - MARGIN, y);
        setOpacity(0f);
        setVisible(true);
        fadeIn();
    }

    private void fadeIn() {
        Timer timer = new Timer(FADE_STEP_MS, null);
        timer.addActionListener(e -> {
            float next = Math.min(1f, getOpacity() + FADE_STEP);
            setOpacity(next);
            if (next >= 1f) {
                timer.stop();
                scheduleFadeOut();
            }
        });
        timer.start();
    }

    private void scheduleFadeOut() {
        Timer wait = new Timer(DISPLAY_MS, e -> fadeOut());
        wait.setRepeats(false);
        wait.start();
    }

    private void fadeOut() {
        Timer timer = new Timer(FADE_STEP_MS, null);
        timer.addActionListener(e -> {
            float next = Math.max(0f, getOpacity() - FADE_STEP);
            setOpacity(next);
            if (next <= 0f) {
                timer.stop();
                dispose();
            }
        });
        timer.start();
    }

    @Override
    public void dispose() {
        ACTIVE.remove(this);
        super.dispose();
    }
}
