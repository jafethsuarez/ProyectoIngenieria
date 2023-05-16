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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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

    }

    @FXML
    void Estadisticas(MouseEvent event) {

    }

    @FXML
    void Reservas(MouseEvent event) {

    }

    @FXML
    void Salir(MouseEvent event) {

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
