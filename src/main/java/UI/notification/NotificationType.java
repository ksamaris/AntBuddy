package UI.notification;

import java.awt.Color;

/**
 *
 * @author kevin
 */

public enum NotificationType {

    SUCCESS(new Color(0x2E7D32), "\u2714"),
    INFO(new Color(0x1565C0), "\u2139"),
    WARNING(new Color(0xEF6C00), "\u26A0"),
    ERROR(new Color(0xC62828), "\u2716");

    private final Color color;
    private final String icon;

    NotificationType(Color color, String icon) {
        this.color = color;
        this.icon = icon;
    }

    public Color getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }
}