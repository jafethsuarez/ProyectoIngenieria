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
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.lang.reflect.Type;

public class ControllerModificarRes {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCliente;

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private JFXButton Actualizar;

    @FXML
    private JFXDatePicker DatePickerRe;

    @FXML
    private JFXComboBox<String> CbxHoraRes;

    @FXML
    private JFXListView<String> ListMenuRes;

    @FXML
    private JFXComboBox<Integer> cbxCant;

    private Reserva reserva;
    
    private Cliente cliente;
    private Restaurante restaurante;
    
    LocalDateTime now = LocalDateTime.now();  
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        // Cargar los demás datos de la reserva en los controles correspondientes
        DatePickerRe.setValue(LocalDate.of(reserva.getFecha().getYear(), reserva.getFecha().getMonth(), reserva.getFecha().getDay()));
        CbxHoraRes.setValue(reserva.getHora());
        cbxCant.setValue(reserva.getCantidad());

        // Verificar si hay menús seleccionados en la reserva
        if (reserva.getMenuSeleccionado() != null) {
            // Limpiar la lista actual de menús en ListMenuRes
            ListMenuRes.getItems().clear();

            // Agregar cada menú seleccionado a la lista
            for (String menu : reserva.getMenuSeleccionado()) {
                ListMenuRes.getItems().add(menu);
            }
        }
    }



    public void setRestaurante(Restaurante restaurante) {
        if (restaurante == null) {
            throw new IllegalArgumentException("El restaurante no puede ser nulo");
        }

        this.restaurante = restaurante;

        if (restaurante.getMenu() == null) {
            return;
        }

        if (ListMenuRes != null) {
            for (Plato plato : restaurante.getMenu().getPlatos()) {
            	ListMenuRes.getItems().add(plato.getNombre() + " - " + plato.getPrecio() + "€");
            }
        }
    }

    @FXML
    void ActualizarRes(ActionEvent event) {
        if (DatePickerRe.getValue() == null || CbxHoraRes.getValue() == null || cbxCant.getValue() == null || ListMenuRes.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Información incompleta");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, asegúrate de haber completado todos los campos.");
            alert.showAndWait();
            return;
        }
        
        LocalDateTime now = LocalDateTime.now(); 
        String[] hora = CbxHoraRes.getValue().split(":");
        LocalDateTime fechaEscogida = LocalDateTime.of(DatePickerRe.getValue().getYear(), DatePickerRe.getValue().getMonthValue(), 
        		DatePickerRe.getValue().getDayOfMonth(), Integer.parseInt(hora[0]), Integer.parseInt(hora[1]));
        if (fechaEscogida.isBefore(now)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Información incompleta");
            alert.setHeaderText(null);
            alert.setContentText("La fecha no es válida, tiene que ser después de ahora.");
            alert.showAndWait();
            return;
        } 
       
        // Actualizar la reserva con los nuevos valores
        reserva.setFecha(new Fecha(DatePickerRe.getValue().getYear(), DatePickerRe.getValue().getMonthValue(), DatePickerRe.getValue().getDayOfMonth()));
        reserva.setHora(CbxHoraRes.getValue());
        reserva.setCantidad(cbxCant.getValue());
        reserva.setMenuSeleccionado(ListMenuRes.getItems());

        String filename = "ReservasCli_" + cliente.getId() + ".json";
        File file = new File(filename);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<Reserva>>>(){}.getType();
            Map<String, List<Reserva>> data = gson.fromJson(reader, type);

            if (data != null) {
                List<Reserva> reservas = data.get(reserva.getNombreRest());

                if (reservas != null) {
                    for (Reserva r : reservas) {
                        if (r.getId() == reserva.getId()) {
                            // Actualizar la reserva en la lista
                            reservas.set(reservas.indexOf(r), reserva);
                            break;
                        }
                    }

                    // Escribir de nuevo al archivo con pretty printing
                    Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
                    try (FileWriter writer = new FileWriter(filename)) {
                        gsonPretty.toJson(data, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Cerrar la ventana después de actualizar
                    Stage stage = (Stage) Actualizar.getScene().getWindow();
                    stage.close();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reserva Actualizada");
                    alert.setHeaderText(null);
                    alert.setContentText("La reserva con ID " + reserva.getId() + " ha sido actualizada exitosamente.");
                    alert.showAndWait();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    
    @FXML
    void CalendarioRe(ActionEvent event) {

    }

    @FXML
    void MostrarCant(ActionEvent event) {

    }

    @FXML
    void MostrarHora(ActionEvent event) {

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
        	CbxHoraRes.getItems().add(String.format("%02d:00", i));
        }

        // Rellenar el ComboBox de cantidad
        for (int i = 1; i <= 10; i++) {
            cbxCant.getItems().add(i);
        }

        DatePickerRe.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                // Aquí puedes agregar el código que se ejecutará después de seleccionar una fecha
            }
        });

    }
}