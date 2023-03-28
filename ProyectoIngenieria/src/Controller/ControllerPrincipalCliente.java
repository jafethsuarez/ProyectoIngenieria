package Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControllerPrincipalCliente {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCliente;

    @FXML
    private ImageView logorest;

    @FXML
    private Label nombreRest;

    @FXML
    private JFXButton btnReverva;

    @FXML
    private JFXComboBox<?> CbxRest;

    @FXML
    void MostrarCategorias(ActionEvent event) {

    }

    @FXML
    void Reservar(ActionEvent event) {

    }

    @FXML
    void Reservas(MouseEvent event) {

    }

    @FXML
    void Restaurantes(MouseEvent event) {

    }

    @FXML
    void Salir(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert lblCliente != null : "fx:id=\"lblCliente\" was not injected: check your FXML file 'ViewPrincipalCliente.fxml'.";
        assert logorest != null : "fx:id=\"logorest\" was not injected: check your FXML file 'ViewPrincipalCliente.fxml'.";
        assert nombreRest != null : "fx:id=\"nombreRest\" was not injected: check your FXML file 'ViewPrincipalCliente.fxml'.";
        assert btnReverva != null : "fx:id=\"btnReverva\" was not injected: check your FXML file 'ViewPrincipalCliente.fxml'.";
        assert CbxRest != null : "fx:id=\"CbxRest\" was not injected: check your FXML file 'ViewPrincipalCliente.fxml'.";

    }
}
