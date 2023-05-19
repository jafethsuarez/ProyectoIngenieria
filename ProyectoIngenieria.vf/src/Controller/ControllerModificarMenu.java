package Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;

import Clases.CategoriaRestaurante;
import Clases.Menu;
import Clases.Plato;
import Clases.Restaurante;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerModificarMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblEditarMenu;

    @FXML
    private Label lblReservas;

    @FXML
    private Label lblCliente;

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private JFXButton btnModificar;

    @FXML
    private TableView<Plato> TableViewMenu;

    @FXML
    private TableColumn<Plato, Integer> columnid;

    @FXML
    private TableColumn<Plato, String> columnNombre;

    @FXML
    private TableColumn<Plato, String> columnDescripcion;

    @FXML
    private TableColumn<Plato, Double> columnPrecio;

    @FXML
    private JFXButton btnAgregar;

    @FXML
    private JFXButton btnEliminar;
    private Restaurante restaurante;
    
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        loadMenu();
    }
    @FXML
    void AgregarMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/ViewAgregarMenu.fxml"));
            Parent root = loader.load();

            ControllerAgregarMenu controller = loader.getController();
            // Aquí puedes pasar cualquier dato que necesites a la nueva vista, por ejemplo:
            controller.setRestaurante(restaurante);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void EliminarMenu(ActionEvent event) {
        Plato platoSeleccionado = TableViewMenu.getSelectionModel().getSelectedItem();

        if (platoSeleccionado != null) {
            TableViewMenu.getItems().remove(platoSeleccionado);

            // Leer el archivo JSON
            try (Reader reader = new FileReader("Categoria.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                // Definir el tipo de la estructura de datos
                Type listType = new TypeToken<List<CategoriaRestaurante>>(){}.getType();

                // Leer y convertir el archivo JSON a una lista de categorias
                List<CategoriaRestaurante> categorias = gson.fromJson(reader, listType);

                // Buscar el restaurante correcto y eliminar el plato seleccionado
                for (CategoriaRestaurante categoria : categorias) {
                    for (Restaurante restaurante : categoria.getRestaurantes()) {
                        if (this.restaurante.getNombreRestaurante().equals(restaurante.getNombreRestaurante())) {
                            List<Plato> platos = restaurante.getMenu().getPlatos();
                            for (int i = 0; i < platos.size(); i++) {
                                if (platos.get(i).getId() == platoSeleccionado.getId()) {
                                    platos.remove(i);
                                    break;
                                }
                            }
                        }
                    }
                }

                // Convertir la lista de categorias de nuevo a JSON
                String json = gson.toJson(categorias);

                // Escribir el JSON de vuelta al archivo
                try (FileWriter writer = new FileWriter("Categoria.json")) {
                    writer.write(json);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void ModificarMenu(ActionEvent event) {
        Plato platoSeleccionado = TableViewMenu.getSelectionModel().getSelectedItem();
        if (platoSeleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/ViewActualizarMenu.fxml"));
                Parent root = loader.load();
                ControllerActualizarMenu controller = loader.getController();
                controller.setPlato(platoSeleccionado);
                controller.setRestaurante(restaurante);
                
                // Aquí se abre la nueva vista
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





    @FXML
    void PanelCliente(MouseEvent event) {
    	
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
            
            // Cerrar la pantalla actual
            stageActual.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    @FXML
    void EditarMenu(MouseEvent event) {
        //no hace nada porque ya esta en el menu
    }

    @FXML
    void Salir(MouseEvent event) {
	    Stage currentStage = (Stage) lblCliente.getScene().getWindow();
	    currentStage.close();
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
    void initialize() {
    	 columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
    	    columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	    columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    	    columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

    }
    
    private void loadMenu() {
        if (restaurante != null) {
            if (restaurante.getMenu() == null) {
                restaurante.setMenu(new Menu());  // Crear un nuevo menú si no existe
            } else if (restaurante.getMenu().getPlatos() == null) {
                restaurante.getMenu().setPlatos(new ArrayList<>());  // Crear una nueva lista de platos si no existe
            }
            ObservableList<Plato> menuObservableList = FXCollections.observableArrayList(restaurante.getMenu().getPlatos());
            TableViewMenu.setItems(menuObservableList);
        }
    }

}


