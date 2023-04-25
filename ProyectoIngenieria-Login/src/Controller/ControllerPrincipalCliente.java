package Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Clases.CategoriaRestaurante;
import Clases.Restaurante;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
    private GridPane gridRestaurante;

    @FXML
    private JFXButton btnReverva;

    @FXML
    private JFXComboBox<CategoriaRestaurante> CbxRest;

    @FXML
    void MostrarCategorias(ActionEvent event) {
    	Restaurante restaurante = new Restaurante();
    
    	  // Cargar restaurantes desde archivo JSON
        Vector<Restaurante> restaurantes = restaurante.recuperarRestaurantes();

        // Mostrar restaurantes en el GridPane
        int col = 0;
        int row = 0;
        for (Restaurante restaurante2 : restaurantes) {
            VBox vbox = new VBox();
            vbox.setSpacing(10);

            Label nombre = new Label(restaurante.getNombreRestaurante());
            vbox.getChildren().add(nombre);

            // Agregar otros elementos al VBox según se necesite (por ejemplo, imágenes, botones, etc.)

            gridRestaurante.add(vbox, col, row);
            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }

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
