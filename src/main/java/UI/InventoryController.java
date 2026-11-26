package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Controller of Inventory.fxml. Shows the products of the inventory
 * and handles the search, add, modify and delete actions.
 *
 * @author kevin
 */
public class InventoryController {

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFilter;

    @FXML
    private Button btnModify;

    @FXML
    private FlowPane fpProducts;

    @FXML
    private Pane pnDetail;

    @FXML
    private ScrollPane scpProducts;

    @FXML
    private TextField txtSearchProducts;
    
    @FXML
    void handleAddProduct(ActionEvent event) {

    }

    @FXML
    void handleDelete(ActionEvent event) {

    }

    @FXML
    void handleFilter(ActionEvent event) {

    }

    @FXML
    void handleModify(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAddProduct != null : "fx:id=\"btnAddProduct\" was not injected: check your FXML file 'Inventory.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'Inventory.fxml'.";
        assert btnFilter != null : "fx:id=\"btnFilter\" was not injected: check your FXML file 'Inventory.fxml'.";
        assert btnModify != null : "fx:id=\"btnModify\" was not injected: check your FXML file 'Inventory.fxml'.";
        assert fpProducts != null : "fx:id=\"fpProducts\" was not injected: check your FXML file 'Inventory.fxml'.";
        assert pnDetail != null : "fx:id=\"pnDetail\" was not injected: check your FXML file 'Inventory.fxml'.";
        assert scpProducts != null : "fx:id=\"scpProducts\" was not injected: check your FXML file 'Inventory.fxml'.";
        assert txtSearchProducts != null : "fx:id=\"txtSearchProducts\" was not injected: check your FXML file 'Inventory.fxml'.";
    }
}
