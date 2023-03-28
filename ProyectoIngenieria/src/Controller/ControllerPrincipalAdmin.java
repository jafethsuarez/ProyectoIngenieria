package Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class ControllerPrincipalAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Admin;

    @FXML
    private TableColumn<String, String> ColumnUsuario;

    @FXML
    private TableColumn<String,String> ColumnTelefo;

    @FXML
    void Salir(MouseEvent event) {

    }



	@FXML
    void clientes(MouseEvent event) {

    }

    @FXML
    void restaurantes(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert Admin != null : "fx:id=\"Admin\" was not injected: check your FXML file 'ViewPrincipalAdmin.fxml'.";
        assert ColumnUsuario != null : "fx:id=\"ColumnUsuario\" was not injected: check your FXML file 'ViewPrincipalAdmin.fxml'.";
        assert ColumnTelefo != null : "fx:id=\"ColumnTelefo\" was not injected: check your FXML file 'ViewPrincipalAdmin.fxml'.";

    }
}
