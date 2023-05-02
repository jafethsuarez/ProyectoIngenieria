package Controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class ControllerPrincipalRestaurante {

    @FXML
    private Label PanelControl;

    @FXML
    private TableColumn<String, String> ColumnUsuario;

    @FXML
    private TableColumn<String, String> ColumnTelefo;

    @FXML
    private TableColumn<String, String> ColumnHora;

    @FXML
    private TableColumn<String, String> ColumnMesa;

    @FXML
    void menu(MouseEvent event) {

    }

    @FXML
    void salir(MouseEvent event) {
    	Platform.exit();
    	System.exit(0);
    }

}

