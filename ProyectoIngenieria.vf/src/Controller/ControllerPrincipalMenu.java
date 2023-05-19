package Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Clases.Menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerPrincipalMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblEditarMenu;

    @FXML
    private Label lblReservas;

    @FXML
    private Label lblCliente;

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private JFXComboBox<?> CbxMenu;

    @FXML
    private JFXButton btnModificarMenu;

    @FXML
    private JFXButton btnEliminarMenu;

    @FXML
    private TableView<Menu> TableViewMenu;

    @FXML
    private TableColumn<Menu, Integer> ColumnId;

    @FXML
    private TableColumn<Menu, String> ColumnNombre;

    @FXML
    private TableColumn<Menu, String> ColumnDescripcion;

    @FXML
    private TableColumn<Menu, Double> ColumnPrecio;

    @FXML
    void MostrarMenu(ActionEvent event) {

    }

    @FXML
    void PanelCliente(MouseEvent event) {

    }

    @FXML
    void Reservas(MouseEvent event) {

    }

    @FXML
    void Restaurantes(MouseEvent event) {

    }

    @FXML
    void Salir(MouseEvent event) {
	    Stage currentStage = (Stage) lblCliente.getScene().getWindow();
	    currentStage.close();
    }

    @FXML
    void eliminarMenu(ActionEvent event) {

    }

    @FXML
    void modificarMenu(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lblEditarMenu != null : "fx:id=\"lblEditarMenu\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert lblReservas != null : "fx:id=\"lblReservas\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert lblCliente != null : "fx:id=\"lblCliente\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert gridRestaurante != null : "fx:id=\"gridRestaurante\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert CbxMenu != null : "fx:id=\"CbxMenu\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert btnModificarMenu != null : "fx:id=\"btnModificarMenu\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert btnEliminarMenu != null : "fx:id=\"btnEliminarMenu\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert TableViewMenu != null : "fx:id=\"TableViewMenu\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert ColumnId != null : "fx:id=\"ColumnId\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert ColumnNombre != null : "fx:id=\"ColumnNombre\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert ColumnDescripcion != null : "fx:id=\"ColumnDescripcion\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";
        assert ColumnPrecio != null : "fx:id=\"ColumnPrecio\" was not injected: check your FXML file 'ViewMenuPrincipal.fxml'.";

    }
}
