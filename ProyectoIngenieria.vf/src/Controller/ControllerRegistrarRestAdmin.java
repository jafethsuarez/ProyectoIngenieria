package Controller;

import Clases.CategoriaRestaurante;
import Clases.Menu;
import Clases.Reserva;
import Clases.Restaurante;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerRegistrarRestAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Admin;

    @FXML
    private JFXComboBox<String> cbxCatRest;

    @FXML
    private JFXTextField txtnombreRest;

    @FXML
    private JFXTextField txtusuario;

    @FXML
    private JFXTextField txtContraseña;

    @FXML
    private JFXTextField txtaforo;

    @FXML
    private JFXTextField txtmesa;

    @FXML
    private JFXTextField txtubicacion;

    @FXML
    void CatRest(ActionEvent event) {

    }

    @FXML
    void RegistrarRest(ActionEvent event) {
        String categoriaSeleccionada = cbxCatRest.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada == null || txtusuario.getText().isEmpty() || txtContraseña.getText().isEmpty() || txtubicacion.getText().isEmpty() 
        		|| txtnombreRest.getText().isEmpty() || txtmesa.getText().isEmpty() || txtaforo.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Rellene todos los campos");
            alert.showAndWait();
            return;
        } else {
        	try {
        	Menu menu = new Menu();
         // Asume que la clase Menu tiene un constructor sin parámetros
        	ArrayList<Reserva> reservas = new ArrayList<>();

        	Restaurante nuevoRestaurante = new Restaurante(txtusuario.getText(), txtContraseña.getText(), txtubicacion.getText(),
        	                    txtnombreRest.getText(), menu, reservas, Integer.parseInt(txtmesa.getText()), Integer.parseInt(txtaforo.getText()));


            agregarRestauranteACategoria(categoriaSeleccionada, nuevoRestaurante);
            
            // Muestra un cuadro de diálogo de éxito
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Restaurante registrado con éxito.");
            alert.showAndWait();
            
            // Cierra la ventana actual
            Stage stage = (Stage) cbxCatRest.getScene().getWindow();
            stage.close();
        	} catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Dato no válido introducido");
                alert.showAndWait();
                return;
        	}
        	

        }
    }

    public void agregarRestauranteACategoria(String nombreCategoria, Restaurante nuevoRestaurante) {
        ArrayList<CategoriaRestaurante> categorias = recuperarCategorias();
        for (CategoriaRestaurante cat : categorias) {
            if (cat.getNombreCategoria().equals(nombreCategoria)) {
                cat.getRestaurantes().add(nuevoRestaurante);
                break;
            }
        }
        guardarCategoriasEnJson(categorias);
    }

    public ArrayList<CategoriaRestaurante> recuperarCategorias() {
        Gson gson = new Gson();
        ArrayList<CategoriaRestaurante> categorias = new ArrayList<>();
        try (Reader reader = new FileReader("Categoria.json")) {
            Type tipoCategoria = new TypeToken<ArrayList<CategoriaRestaurante>>() {}.getType();
            categorias = gson.fromJson(reader, tipoCategoria);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categorias;
    }
    public void guardarCategoriasEnJson(ArrayList<CategoriaRestaurante> categorias) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(categorias);
        try (Writer writer = new FileWriter("Categoria.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Salir(MouseEvent event) {
	    Stage currentStage = (Stage) Admin.getScene().getWindow();
	    currentStage.close();
    }

    @FXML
    void clientes(MouseEvent event) {
        try {
            // Obtener la referencia al objeto Stage de la pantalla actual
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loaderRegCli = new FXMLLoader(getClass().getResource("/Vistas/ViewClientesReg.fxml"));
            controladorClienteReg controladorRegCli = new controladorClienteReg();
            

            loaderRegCli.setController(controladorRegCli);
            Parent rootRegCli = loaderRegCli.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(rootRegCli));
            stage.show();

            // Cerrar la pantalla actual
            stageActual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void restaurantes(MouseEvent event) {
        try {
            // Obtener la referencia al objeto Stage de la pantalla actual
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loaderRegRest = new FXMLLoader(getClass().getResource("/Vistas/ViewRestaurantesRegistrados.fxml"));
            controladorRestauranteReg controladorRegRest = new controladorRestauranteReg();

            loaderRegRest.setController(controladorRegRest);
            Parent rootRegRest = loaderRegRest.load();
            
            controladorRegRest.actualizarLabelAdmin();

            Stage stage = new Stage();
            stage.setScene(new Scene(rootRegRest));
            stage.show();

            // Cerrar la pantalla actual
            stageActual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        cargarCategorias();
    }

    public void cargarCategorias() {
        ArrayList<CategoriaRestaurante> categorias = recuperarCategorias();
        ObservableList<String> listaCategorias = FXCollections.observableArrayList();
        for (CategoriaRestaurante cat : categorias) {
            listaCategorias.add(cat.getNombreCategoria());
        }
        cbxCatRest.setItems(listaCategorias);
    }
}
