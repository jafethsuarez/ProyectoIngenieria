package Controller;

import com.google.gson.Gson;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import Clases.Cliente;
import Clases.Menu;
import Clases.Reserva;
import Clases.Restaurante;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;

public class ControllerPrincipalRest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCliente;

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private JFXDatePicker DatePickerMenu;

    @FXML
    private TableView<Reserva> TableReservasCli;

    @FXML
    private TableColumn<Reserva, String> ColumnIdCliente;

    @FXML
    private TableColumn<Reserva, LocalDate> columnFecha;

    @FXML
    private TableColumn<Reserva, String> columnHora;

    @FXML
    private TableColumn<Reserva, Integer> columncant;
    @FXML
    private TableColumn<Menu, String> columnMenu;

    private Restaurante restaurante;
    @FXML
    private JFXButton btnExportar;

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        System.out.println("Restaurante establecido: " + this.restaurante);
        System.out.println("Nombre del restaurante: " + this.restaurante.getNombreRestaurante());
    }
    
    @FXML
    void EditarMenu(MouseEvent event) {
        try {
            // Crea el FXMLLoader
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loaderRestaurante = new FXMLLoader();

            // Asigna el archivo FXML al FXMLLoader
            loaderRestaurante.setLocation(getClass().getResource("/Vistas/ViewModificarMenu.fxml"));

            // Carga la vista desde el archivo FXML
            Parent rootRest = loaderRestaurante.load();

            // Obtén el controlador y asigna el restaurante
            ControllerModificarMenu controModifi = loaderRestaurante.getController();
            controModifi.setRestaurante(restaurante);

            // Crea un nuevo Stage para mostrar la vista
            Stage stage = new Stage();
            stage.setScene(new Scene(rootRest));
            stage.show();
            
            stageActual.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Reservas(MouseEvent event) {

    }
    @FXML
    void ExportarDatos(ActionEvent event) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("reservas.csv");

            fileWriter.append("ID Cliente, Fecha, Hora, Cantidad, Menú \n");

            for (Reserva reserva : TableReservasCli.getItems()) {
                fileWriter.append(String.valueOf(reserva.getId()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(reserva.getFecha()));
                fileWriter.append(",");
                fileWriter.append(reserva.getHora());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(reserva.getCantidad()));
                fileWriter.append(",");
                fileWriter.append(String.join(",", reserva.getMenuSeleccionado()));
                fileWriter.append("\n");
            }

            // Muestra un mensaje de éxito después de la exportación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("¡Datos exportados con éxito!");
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println("Error al escribir CSV!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error al limpiar/cerrar el FileWriter!");
                e.printStackTrace();
            }
        }

        // Cierra la ventana actual
        Stage stage = (Stage) btnExportar.getScene().getWindow();
        stage.close();
    }



    @FXML
    void etadisticas(MouseEvent event) {
        try {
        	
            // Crea el FXMLLoader
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Crea el FXMLLoader
            FXMLLoader loader = new FXMLLoader();

            // Asigna el archivo FXML al FXMLLoader
            loader.setLocation(getClass().getResource("/Vistas/ViewEstadisticasRest.fxml"));

            // Carga la vista desde el archivo FXML
            Parent root = loader.load();

            // Obtén el controlador y asigna el restaurante
            ControllerEstadisticasRest controller = loader.getController();
            controller.setRestaurante(restaurante);

            // Crea un nuevo Stage para mostrar la vista
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            stageActual.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Salir(MouseEvent event) {
	    Stage currentStage = (Stage) lblCliente.getScene().getWindow();
	    currentStage.close();
    }
    
    private void loadReservations() {
        Gson gson = new Gson();
        Cliente cliente = new Cliente();
        List<Cliente> clientes = cliente.recuperarCliente();

        if (restaurante == null) {
            System.out.println("Restaurante es null");
            return;
        }
        System.out.println("Nombre del restaurante: " + restaurante.getNombreRestaurante());

        List<Reserva> todasLasReservas = new ArrayList<>();

        clientes.forEach(clienteObj -> {
            String filename = "ReservasCli_" + clienteObj.getId() + ".json";

            try {
                FileReader reader = new FileReader(filename);
                Type reservationType = new TypeToken<Map<String, List<Reserva>>>() {}.getType();
                Map<String, List<Reserva>> reservas = gson.fromJson(reader, reservationType);

                if (reservas == null) {
                    System.out.println("Reservas es null para el archivo " + filename);
                    return;
                }

                List<Reserva> reservasRestaurante = reservas.get(restaurante.getNombreRestaurante());
                if (reservasRestaurante == null) {
                    System.out.println("No hay reservas para el restaurante " + restaurante.getNombreRestaurante() + " en el archivo " + filename);
                } else {
                	List<Reserva> reservasFiltradas = reservasRestaurante.stream()
                		    .filter(reserva -> reserva.getNombreRest().equals(restaurante.getNombreRestaurante()))
                		    .collect(Collectors.toList());

                    todasLasReservas.addAll(reservasFiltradas);
                }

                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Archivo " + filename + " no encontrado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ObservableList<Reserva> reservasObservableList = FXCollections.observableArrayList(todasLasReservas);
        TableReservasCli.setItems(reservasObservableList);
    }



    @FXML
    void initialize() {
    	 ColumnIdCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
         columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
         columnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
         columncant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
         columnMenu.setCellValueFactory(new PropertyValueFactory<>("menuSeleccionado"));
         showReservations();
        
    }
    
    public void showReservations() {
        if(restaurante != null) {
            loadReservations();
        }
    }
    
}
