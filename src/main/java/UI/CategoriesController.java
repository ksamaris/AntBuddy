package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author kevin
 */
public class CategoriesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane apCategoriesBase;

    @FXML
    private AnchorPane apCategoriesCards;

    @FXML
    private Button btnAddCategory;

    @FXML
    private Button btnCategoryFilter;

    @FXML
    private FlowPane fpCategoriesCards;

    @FXML
    private Pane pnCategoryCardsBase;

    @FXML
    private Pane pnTopCategoryMenuBar;

    @FXML
    private ScrollPane scpCategoriesCards;

    @FXML
    private TextField txtSearchCategories;

    @FXML
    void handleAddProduct(ActionEvent event) {

    }

    @FXML
    void handleFilter(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert apCategoriesBase != null : "fx:id=\"apCategoriesBase\" was not injected: check your FXML file 'Categories.fxml'.";
        assert apCategoriesCards != null : "fx:id=\"apCategoriesCards\" was not injected: check your FXML file 'Categories.fxml'.";
        assert btnAddCategory != null : "fx:id=\"btnAddCategory\" was not injected: check your FXML file 'Categories.fxml'.";
        assert btnCategoryFilter != null : "fx:id=\"btnCategoryFilter\" was not injected: check your FXML file 'Categories.fxml'.";
        assert fpCategoriesCards != null : "fx:id=\"fpCategoriesCards\" was not injected: check your FXML file 'Categories.fxml'.";
        assert pnCategoryCardsBase != null : "fx:id=\"pnCategoryCardsBase\" was not injected: check your FXML file 'Categories.fxml'.";
        assert pnTopCategoryMenuBar != null : "fx:id=\"pnTopCategoryMenuBar\" was not injected: check your FXML file 'Categories.fxml'.";
        assert scpCategoriesCards != null : "fx:id=\"scpCategoriesCards\" was not injected: check your FXML file 'Categories.fxml'.";
        assert txtSearchCategories != null : "fx:id=\"txtSearchCategories\" was not injected: check your FXML file 'Categories.fxml'.";

    }

}
