package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import Clases.Cliente;
import Clases.Plato;
import Clases.Restaurante;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerRestauranteDetails {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private GridPane gridDetails;
    @FXML
    private Label nombreRestaurante;

    @FXML
    private Label Mesas;

    @FXML
    private Label Ubicacion;

    @FXML
    private JFXListView<String> menu;

    @FXML
    private JFXButton Reserva;

    private Restaurante restaurante;
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        if (nombreRestaurante != null) {
            nombreRestaurante.setText(restaurante.getNombreRestaurante());
        }
        if (Mesas != null) {
            Mesas.setText(String.valueOf(restaurante.getMesa()));
        }
        if (Ubicacion != null) {
            Ubicacion.setText(restaurante.getUbicacion());
        }
        if (menu != null) {
            for (Plato plato : restaurante.getMenu().getPlatos()) {
                menu.getItems().add(plato.getNombre() + " - " + plato.getPrecio() + "€");
            }
        }
    }

    @FXML
    void reservar(ActionEvent event) {
        try {
            ControllerReservaCli controllerReservaCli = new ControllerReservaCli();
            FXMLLoader loaderReserva = new FXMLLoader(getClass().getResource("/Vistas/ViewReservaCli.fxml"));
            loaderReserva.setController(controllerReservaCli);
            Parent rootReserva = loaderReserva.load();

            // Pasa la información del cliente a la ventana de reserva
            controllerReservaCli.setCliente(this.cliente);

            // Pasa la información del restaurante a la ventana de reserva
            controllerReservaCli.setRestaurante(this.restaurante);

            Stage stage = new Stage();
            stage.setScene(new Scene(rootReserva));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {

    }

}
