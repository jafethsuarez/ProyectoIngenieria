package Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;

import Clases.Cliente;
import Clases.Fecha;
import Clases.Plato;
import Clases.Reserva;
import Clases.Restaurante;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
public class ControllerReservaCli {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCliente;

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private JFXButton btnReserva;

    @FXML
    private JFXDatePicker DatePicker;

    @FXML
    private JFXComboBox<String> CbxHora;

    @FXML
    private JFXListView<String> ListMenu;

    @FXML
    private JFXComboBox<Integer> cbxCant;
    
    private Restaurante restaurante;
    private Cliente cliente;
    
    
    
    private int lastId = 0;


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setRestaurante(Restaurante restaurante) {
        if (restaurante == null) {
            throw new IllegalArgumentException("El restaurante no puede ser nulo");
        }

        this.restaurante = restaurante;

        if (restaurante.getMenu() == null) {
            return;
        }

        if (ListMenu != null) {
            for (Plato plato : restaurante.getMenu().getPlatos()) {
                ListMenu.getItems().add(plato.getNombre() + " - " + plato.getPrecio() + "€");
            }
        }
    }


    @FXML
    void Calendario(ActionEvent event) {

    }

    @FXML
    void MostrarCant(ActionEvent event) {

    }


    
    @FXML
    void ReservarRest(ActionEvent event) {
        // Verificar si todos los campos están llenos
        if (DatePicker.getValue() == null || CbxHora.getValue() == null || cbxCant.getValue() == null || ListMenu.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, llena todos los campos.");
            alert.showAndWait();
            return;
        }
        
        
        LocalDateTime now = LocalDateTime.now(); 
        String[] hora = CbxHora.getValue().split(":");
        LocalDateTime fechaEscogida = LocalDateTime.of(DatePicker.getValue().getYear(), DatePicker.getValue().getMonthValue(), 
        		                                       DatePicker.getValue().getDayOfMonth(), Integer.parseInt(hora[0]), Integer.parseInt(hora[1]));
        if (fechaEscogida.isBefore(now)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Información incompleta");
            alert.setHeaderText(null);
            alert.setContentText("La fecha no es válida, tiene que ser después de ahora.");
            alert.showAndWait();
            return;
        } 

        // Crear una reserva con los datos del formulario
        Reserva reserva = new Reserva();
        reserva.setId(lastId + 1);
        reserva.setFecha(new Fecha(DatePicker.getValue().getYear(), DatePicker.getValue().getMonthValue(), DatePicker.getValue().getDayOfMonth()));
        reserva.setHora(CbxHora.getValue());
        reserva.setCantidad(cbxCant.getValue());
        reserva.setMenuSeleccionado(ListMenu.getSelectionModel().getSelectedItems());
        reserva.setNombreRest(restaurante.getNombreRestaurante());

        // Incrementar lastId en 1
        lastId++;

        String filename = "ReservasCli_" + cliente.getId() + ".json";
        Map<String, Object> data = new HashMap<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (Files.exists(Paths.get(filename))) {
            try {
            	 String existingContent = new String(Files.readAllBytes(Paths.get(filename)));
                 Type type = new TypeToken<Map<String, Object>>(){}.getType();
                 Map<String, Object> existingData = gson.fromJson(existingContent, type);

                 List<Reserva> reservas = (List<Reserva>) existingData.get(restaurante.getNombreRestaurante());
                 if (reservas == null) {
                     reservas = new ArrayList<>();
                 }

                 reserva.setId(reservas.size() + 1);
                 reservas.add(reserva);
                 existingData.put(restaurante.getNombreRestaurante(), reservas);

                 // Convertir el JSON al formato deseado sin decimales
                 String json = gson.toJson(existingData, type);

                 // Eliminar los decimales de los campos id y cantidad en el JSON
                 json = json.replaceAll("\"id\": (\\d+)\\.0", "\"id\": $1");
                 json = json.replaceAll("\"cantidad\": (\\d+)\\.0", "\"cantidad\": $1");

                 try (FileWriter writer = new FileWriter(filename)) {
                     writer.write(json);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             } catch (IOException e) {
                 e.printStackTrace();
             }
         } else {
             List<Reserva> reservas = new ArrayList<>();
             reserva.setId(reservas.size() + 1);
             reservas.add(reserva);
             data.put(restaurante.getNombreRestaurante(), reservas);
             String json = gson.toJson(data);

             // Eliminar los decimales de los campos id y cantidad en el JSON
             json = json.replaceAll("\"id\": (\\d+)\\.0", "\"id\": $1");
             json = json.replaceAll("\"cantidad\": (\\d+)\\.0", "\"cantidad\": $1");

             try (FileWriter writer = new FileWriter(filename)) {
                 writer.write(json);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }

         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Reserva Exitosa");
         alert.setHeaderText(null);
         alert.setContentText("¡Reserva realizada con éxito con ID " + reserva.getId() + "!");
         alert.showAndWait();

         Stage stage = (Stage) btnReserva.getScene().getWindow();
         stage.close();
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
    void initialize() {
        // Rellenar el ComboBox de horas
        for (int i = 9; i <= 23; i++) {
            CbxHora.getItems().add(String.format("%02d:00", i));
        }

        // Rellenar el ComboBox de cantidad
        for (int i = 1; i <= 10; i++) {
            cbxCant.getItems().add(i);
        }

        DatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                // Aquí puedes agregar el código que se ejecutará después de seleccionar una fecha
            }
        });
    }

	
}

