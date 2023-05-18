package Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Clases.CategoriaRestaurante;
import Clases.Cliente;
import Clases.Reserva;
import Clases.Restaurante;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ControllerRegistrar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXTextField txtcontrasena;

    @FXML
    private JFXComboBox<String> Ubicacion;

    @FXML
    private JFXTextField txtnombre;

    @FXML
    private JFXTextField txttelefono;

    @FXML
    private JFXButton btnReg;
    
    ObservableList<String> ubicacionList = FXCollections.observableArrayList("Madrid","Pozuelo","Villa-viciosa");

    @FXML
    void Registrar(ActionEvent event) {
    	 // Validar campos de entrada
        if (txtUsuario.getText().isEmpty() || txtcontrasena.getText().isEmpty() || Ubicacion.getSelectionModel().isEmpty()
                || txtnombre.getText().isEmpty() || txttelefono.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Por favor complete todos los campos");
            alert.showAndWait();
            return;
        }
        
        if (txttelefono.getText().length() > 9) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Número de teléfono invalido");
            alert.showAndWait();
            return;
        }
        
     // Obtener la lista de categorías ya cargada
        Cliente cliente = new Cliente();
        ArrayList<CategoriaRestaurante> categorias = cliente.cargarCategorias("Categoria.json"); // Asegúrate de proporcionar la ruta correcta al archivo JSON de las categorías

        // Crear el nuevo cliente y agregarlo a la lista
        Cliente nuevoCliente = new Cliente(txtUsuario.getText(), txtcontrasena.getText(),
                Ubicacion.getSelectionModel().getSelectedItem().toString(), txtnombre.getText(), txttelefono.getText(),
                null, "Categoria.json"); // Asegúrate de proporcionar la ruta correcta al archivo JSON de las categorías

        nuevoCliente.anadirCliente(nuevoCliente);

        
        // Mostrar mensaje de éxito
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Registrado");
        alert.setContentText("Registrado con éxito");
        alert.showAndWait();
        Stage stage = (Stage) btnReg.getScene().getWindow();
    	stage.close();
        
       
    	

    }

    @FXML
    void initialize() {
    	Ubicacion.setItems(ubicacionList);

    }
}
