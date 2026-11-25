package UI;

/**
 *
 * @author kevin
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.Node;

public class BaseMenuController {

    @FXML
    private Button btnCategorias;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnMovimientos;

    @FXML
    private Button btnSalir;

    @FXML
    private StackPane contentArea;

    @FXML
    private Label lblLogo;
    
    private List<Button> menuButtons;

    private static final String BASE_STYLE =
            "-fx-shape: 'M 24,12 L 204,12 A 12,12 0 0 0 216,0 L 216,72 A 12,12 0 0 0 204,60 "
            + "L 24,60 A 24,24 0 0 1 0,36 A 24,24 0 0 1 24,12 Z'; "
            + "-fx-alignment: CENTER-LEFT; -fx-padding: 0 16 0 24; "
            + "-fx-font-size: 14px; -fx-cursor: hand; ";

    private static final String STYLE_ACTIVE =
            BASE_STYLE + "-fx-background-color: white; -fx-text-fill: #0B3A66; -fx-font-weight: bold;";

    private static final String STYLE_NORMAL =
            BASE_STYLE + "-fx-background-color: transparent; -fx-text-fill: #BFDBFE; -fx-font-weight: normal;";

    @FXML
    void handleCategorias(ActionEvent event) {
        openSection(btnCategorias, "Categorias");
    }

    @FXML
    void handleInventario(ActionEvent event) {
        openSection(btnInventario, "Inventario");
    }

    @FXML
    void handleMovimientos(ActionEvent event) {
        openSection(btnMovimientos, "Movimientos");
    }

    @FXML
    void handleSalir(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void initialize() {
        assert btnCategorias != null : "fx:id=\"btnCategorias\" was not injected: check your FXML file 'BaseMenu.fxml'.";
        assert btnInventario != null : "fx:id=\"btnInventario\" was not injected: check your FXML file 'BaseMenu.fxml'.";
        assert btnMovimientos != null : "fx:id=\"btnMovimientos\" was not injected: check your FXML file 'BaseMenu.fxml'.";
        assert btnSalir != null : "fx:id=\"btnSalir\" was not injected: check your FXML file 'BaseMenu.fxml'.";
        assert contentArea != null : "fx:id=\"contentArea\" was not injected: check your FXML file 'BaseMenu.fxml'.";
        assert lblLogo != null : "fx:id=\"lblLogo\" was not injected: check your FXML file 'BaseMenu.fxml'.";

        menuButtons = List.of(btnInventario, btnCategorias, btnMovimientos);
        openSection(btnInventario, "Inventario");
    }
    
    private void openSection(Button button, String name) {
        setActive(button);
        showView(new Label(name + ": pantalla pendiente"));
    }

    private void setActive(Button active) {
        for (Button button : menuButtons) {
            button.setStyle(STYLE_NORMAL);
        }
        active.setStyle(STYLE_ACTIVE);
    }

    private void showView(Node view) {
        contentArea.getChildren().setAll(view);
    }

}
