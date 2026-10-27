package UI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renderer de la tabla de productos: resalta las filas según el nivel de stock.
 *
 * @author kevin
 */
public class ProductTableRenderer extends DefaultTableCellRenderer {

    private static final int COL_STOCK = 4;    
    private static final int COL_STOCK_MIN = 5; 
    private static final double MARGEN_ALERTA = 1.25;
    private static final Color COLOR_CRITICAL = new Color(255, 200, 200);
    private static final Color COLOR_ALERT = new Color(255, 245, 157);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int col) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        if (!isSelected) {
            setBackground(getStockColor(table, row));
        }
        return this;
    }

    private Color getStockColor(JTable table, int row) {
        int stock = Integer.parseInt(table.getValueAt(row, COL_STOCK).toString());
        int stockMin = Integer.parseInt(table.getValueAt(row, COL_STOCK_MIN).toString());

        if (stock <= stockMin) {
            return COLOR_CRITICAL;
        }
        if (stock <= stockMin * MARGEN_ALERTA) {
            return COLOR_ALERT;
        }
        return table.getBackground();
    }
}
