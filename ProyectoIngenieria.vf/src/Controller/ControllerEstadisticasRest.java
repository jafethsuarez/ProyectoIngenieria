package Controller;

import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import Clases.Cliente;
import Clases.Restaurante;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;

import Clases.Reserva;

public class ControllerEstadisticasRest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblCliente;

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private Label TotalReservas;

    @FXML
    private Label Menumaselegido;
    private Restaurante restaurante;

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        System.out.println("nombre "+ restaurante.getNombreRestaurante());
        actualizarEstadisticas();
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
    void Estadisticas(MouseEvent event) {

    }

    @FXML
    void Reservas(MouseEvent event) {
		 try {
	            // Obtener la referencia al objeto Stage de la pantalla actual
	            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            
             // Crea el controlador
             ControllerPrincipalRest controlRest = new ControllerPrincipalRest();
             controlRest.setRestaurante(restaurante);

             // Crea el FXMLLoader
             FXMLLoader loaderRestaurante = new FXMLLoader();

             // Asigna el controlador al FXMLLoader
             loaderRestaurante.setController(controlRest);

             // Asigna el archivo FXML al FXMLLoader
             loaderRestaurante.setLocation(getClass().getResource("/Vistas/ViewPrincipalRestaurante.fxml"));

             // Carga la vista desde el archivo FXML
             Parent rootRest = loaderRestaurante.load();

             // Cierra la ventana actual y muestra la nueva vista en un escenario
             Stage stage = new Stage();
             stage.setScene(new Scene(rootRest));
             stage.show();
             stageActual.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    @FXML
    void Salir(MouseEvent event) {
	    Stage currentStage = (Stage) lblCliente.getScene().getWindow();
	    currentStage.close();
    }

    @FXML
    void initialize() {
    	

    }
    
    public void actualizarEstadisticas() {
        int totalReservas = calcularTotalReservas();
        String platoMasPopular = calcularPlatoMasPopular();

        TotalReservas.setText(String.valueOf(totalReservas));
        Menumaselegido.setText(platoMasPopular);
    }

   

    private int calcularTotalReservas() {
        Gson gson = new Gson();
        AtomicInteger totalReservas = new AtomicInteger(0);
        File folder = new File(".");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().startsWith("ReservasCli_") && fileEntry.getName().endsWith(".json")) {
                try {
                    FileReader reader = new FileReader(fileEntry);
                    Type reservationType = new TypeToken<Map<String, List<Reserva>>>() {}.getType();
                    Map<String, List<Reserva>> reservas = gson.fromJson(reader, reservationType);

                    if (reservas != null) {
                        List<Reserva> reservasRestaurante = reservas.get(restaurante.getNombreRestaurante());
                        if (reservasRestaurante != null) {
                            totalReservas.addAndGet(reservasRestaurante.size());
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalReservas.get();
    }

    private String calcularPlatoMasPopular() {
        Gson gson = new Gson();
        Map<String, Integer> platoCount = new HashMap<>();
        File folder = new File(".");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().startsWith("ReservasCli_") && fileEntry.getName().endsWith(".json")) {
                try {
                    FileReader reader = new FileReader(fileEntry);
                    Type reservationType = new TypeToken<Map<String, List<Reserva>>>() {}.getType();
                    Map<String, List<Reserva>> reservas = gson.fromJson(reader, reservationType);

                    if (reservas != null) {
                        List<Reserva> reservasRestaurante = reservas.get(restaurante.getNombreRestaurante());
                        if (reservasRestaurante != null) {
                            for (Reserva reserva : reservasRestaurante) {
                                for (String menu : reserva.getMenuSeleccionado()) {
                                    platoCount.put(menu, platoCount.getOrDefault(menu, 0) + 1);
                                }
                            }
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return platoCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No hay platos reservados.");
    }


}
