package Controller;

import Clases.Cliente;
import Clases.Fecha;
import Clases.Reserva;
import Clases.Restaurante;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.application.Platform;

public class ControllerReservasRegCli {

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private JFXButton btnModificar;

    @FXML
    private JFXButton BtnCancelar;

    @FXML
    private TableView<Reserva> ReservasTableView;
    @FXML
    private TableColumn<Reserva, Integer> columnId;

    @FXML
    private TableColumn<Reserva, Fecha> ColumnFecha;

    @FXML
    private TableColumn<Reserva, String> ColumnHora;

    @FXML
    private TableColumn<Reserva, Integer> ColumnCantidad;

    @FXML
    private TableColumn<Reserva, String> ColumnMenu;
    @FXML
    private TableColumn<Reserva, String> columnNombreRest;

    private Cliente cliente;
    private Restaurante restaurante;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
      
        System.out.println("El ID del cliente es: " + cliente.getId());
       
        loadReservations();
    }
    public void setRestaurante(Restaurante restaurante)
    {
    	  this.restaurante = restaurante;
    	  System.out.println("El restaurante es: " + restaurante.getNombreRestaurante());
    }

    @FXML
    void Cancelar(ActionEvent event) {
        Reserva reservaSeleccionada = ReservasTableView.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nada seleccionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una reserva para cancelar.");
            alert.showAndWait();
            return;
        }

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
                List<Reserva> reservas = data.get(reservaSeleccionada.getNombreRest());

                if (reservas != null) {
                    reservas.removeIf(reserva -> reserva.getId() == reservaSeleccionada.getId());

                    // Escribir de nuevo al archivo con pretty printing
                    Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
                    try (FileWriter writer = new FileWriter(filename)) {
                        gsonPretty.toJson(data, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Actualizar la vista de la tabla
                    loadReservations();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reserva Cancelada");
                    alert.setHeaderText(null);
                    alert.setContentText("La reserva con ID " + reservaSeleccionada.getId() + " ha sido cancelada exitosamente.");
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
    void Modificar(ActionEvent event) {
        Reserva reservaSeleccionada = ReservasTableView.getSelectionModel().getSelectedItem();

        if (reservaSeleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nada seleccionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona una reserva para modificar.");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/ViewModificarReserva.fxml")); // reemplaza esto con la ruta a tu archivo fxml
            Parent root = loader.load();

            ControllerModificarRes controllerModificarRes = loader.getController();
            controllerModificarRes.setCliente(cliente);
            controllerModificarRes.setRestaurante(restaurante);
            controllerModificarRes.setReserva(reservaSeleccionada);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
        
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    void Reservas(MouseEvent event) {

    }

    @FXML
    void Restaurantes(MouseEvent event) {
		  try {
	           // Obtener la referencia al objeto Stage de la pantalla actual
	            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            
	            FXMLLoader loaderCli = new FXMLLoader(getClass().getResource("/Vistas/ViewPrincipalCliente.fxml"));
	            ControllerPrincipalCliente controlCli = new ControllerPrincipalCliente();
	            controlCli.setCliente(cliente);
	            controlCli.setRestaurante(restaurante);
	            loaderCli.setController(controlCli);
	            Parent rootCli = loaderCli.load();

	            Stage stage = new Stage();
	            stage.setScene(new Scene(rootCli));
	            stage.show();
	            stageActual.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
    }

    @FXML
    void Salir(MouseEvent event) {
	    Stage currentStage = (Stage) BtnCancelar.getScene().getWindow();
	    currentStage.close();
    }

    @FXML
    void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ColumnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        ColumnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        ColumnMenu.setCellValueFactory(new PropertyValueFactory<>("menuSeleccionado"));
        columnNombreRest.setCellValueFactory(new PropertyValueFactory<>("nombreRest"));
    }

    private void loadReservations() {
        if (cliente == null) {
            System.out.println("Advertencia: cliente es nulo.");
            return;
        }

        int clientId = cliente.getId();
        String filename = "ReservasCli_" + clientId + ".json";
        File file = new File(filename);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<Reserva>>>(){}.getType();
            Map<String, List<Reserva>> data = gson.fromJson(reader, type);

            if (data != null) {
                List<Reserva> reservations = data.values().stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

                if (!reservations.isEmpty()) {
                    ObservableList<Reserva> reservationList = FXCollections.observableArrayList();
                    reservationList.addAll(reservations);
                    ReservasTableView.setItems(reservationList);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}

