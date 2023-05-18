package Controller;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Clases.CategoriaRestaurante;
import Clases.Cliente;
import Clases.Restaurante;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import com.google.gson.reflect.TypeToken;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
    private JFXComboBox<String> CbxRest;

    private List<CategoriaRestaurante> categorias;

    private static final int NUM_COLUMNS = 2;
    
    private Cliente cliente;
    private Restaurante restaurante;
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @FXML
    void MostrarCategorias(ActionEvent event) {

    }

    @FXML
    void Reservar(ActionEvent event) {

    }

    @FXML
    void Reservas(MouseEvent event) {
        if (cliente == null) {
            System.out.println("Error: cliente is null");
            return;
        }

        try {
            FXMLLoader loaderReservasRegCli = new FXMLLoader(getClass().getResource("/Vistas/ViewMostrarReservaCli.fxml"));
            Parent rootReservasRegCli = loaderReservasRegCli.load();
            
            ControllerReservasRegCli controllerReservasRegCli = loaderReservasRegCli.getController();
            controllerReservasRegCli.setCliente(cliente);
            controllerReservasRegCli.setRestaurante(restaurante);

            Stage stage = new Stage();
            stage.setScene(new Scene(rootReservasRegCli));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void Restaurantes(MouseEvent event) {

    }

    @FXML
    void Salir(MouseEvent event) {

    }
    private String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }

    @FXML
    void initialize() {
    	
    	try {
            String jsonData = readFile("Categoria.json");
            Gson gson = new Gson();
            Type tipoListaCategorias = new TypeToken<List<CategoriaRestaurante>>() {}.getType();
            categorias = gson.fromJson(jsonData, tipoListaCategorias);
            for (CategoriaRestaurante categoria : categorias) {
                CbxRest.getItems().add(categoria.getNombreCategoria());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	
    	CbxRest.setOnAction(e -> {
    		gridRestaurante.getChildren().retainAll(CbxRest);
    	    String categoriaSeleccionada = CbxRest.getValue();

    	    List<Restaurante> restaurantesFiltrados = null;
    	    for (CategoriaRestaurante categoria : categorias) {
    	        if (categoria.getNombreCategoria().equals(categoriaSeleccionada)) {
    	            restaurantesFiltrados = categoria.getRestaurantes();
    	            break;
    	        }
    	    }

    	    if (restaurantesFiltrados != null) {
    	        int row = 2;
    	        int col = 0;
    	        for (Restaurante restaurante : restaurantesFiltrados) {
    	        	
    	            Button btnRestaurante = new Button(restaurante.getNombreRestaurante());
    	            btnRestaurante.setOnAction(event -> showRestaurantDetails(restaurante));
    	            gridRestaurante.add(btnRestaurante, col, row);

    	            col++;
    	            if (col == NUM_COLUMNS) {
    	                col = 0;
    	                row++;
    	            }
    	        }
    	    }
    	});

    }

    private void showRestaurantDetails(Restaurante restaurante) {
        try {
            FXMLLoader loaderDetails = new FXMLLoader(getClass().getResource("/Vistas/ViewRestDetails.fxml"));
            ControllerRestauranteDetails ControlDetails = new ControllerRestauranteDetails();

            loaderDetails.setController(ControlDetails);

            Parent rootAdmin = loaderDetails.load(); // Mueve esta línea hacia arriba
            ControlDetails.setRestaurante(restaurante); // Mueve esta línea hacia abajo
            ControlDetails.setCliente(cliente); // Añade esta línea para establecer el cliente

            Stage stage = new Stage();
            stage.setScene(new Scene(rootAdmin));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    }

    


