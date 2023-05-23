package Controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXComboBox;

import Clases.Administrador;
import Clases.CategoriaRestaurante;
import Clases.Cliente;
import Clases.Restaurante;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;


public class controladorRestauranteReg {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Admin;

    @FXML
    private TableView<Restaurante> TableRestaurante;

    @FXML
    private TableColumn<Restaurante, String> ColumnNombre;

    @FXML
    private TableColumn<Restaurante, String> ColumnUbicacion;

    @FXML
    private TableColumn<Restaurante, Integer> ColumnMesa;

    @FXML
    private TableColumn<Restaurante, Integer> ColumnAforo;

    @FXML
    private JFXComboBox<String> cbxCatRest;
    
    @FXML
    private TextField numRestaurantesRegistrados;
    


    @FXML
    void CatRest(ActionEvent event) {
        cargarRestaurantesPorCategoria(cbxCatRest.getSelectionModel().getSelectedItem());
    }

    public void setAdminUsername(String adminUsername) {
        this.Admin.setText(adminUsername);
    }

    ObservableList<Restaurante> lista = FXCollections.observableArrayList();
    String adminUsername = "Admin";
    
    Administrador administrador;
    
    public void setAdminData(Administrador administrador) {
        if (administrador != null) {
            this.administrador = administrador;
            System.out.println("setAdminData: " + administrador.getUsuario());
            actualizarLabelAdmin();
        }
    }

	public void actualizarLabelAdmin() {
		if (adminUsername != null || adminUsername.isEmpty()) {
			Admin.setText(adminUsername);
		} else {
			Admin.setText("Marcos");
		}

	}

    public void cargarCategorias() {
        CategoriaRestaurante categoriaRest = new CategoriaRestaurante();
        ArrayList<CategoriaRestaurante> categorias = categoriaRest.recuperarCategorias();
        ObservableList<String> listaCategorias = FXCollections.observableArrayList();
        for (CategoriaRestaurante cat : categorias) {
            listaCategorias.add(cat.getNombreCategoria());
        }
        cbxCatRest.setItems(listaCategorias);
    }

    public void cargarRestaurantesPorCategoria(String nombreCategoria) {
        lista.clear();
        CategoriaRestaurante categoriaRest = new CategoriaRestaurante();
        ArrayList<CategoriaRestaurante> categorias = categoriaRest.recuperarCategorias();
        for (CategoriaRestaurante cat : categorias) {
            if (cat.getNombreCategoria().equals(nombreCategoria)) {
                lista.addAll(cat.getRestaurantes());
                break;
            }
        }
    }
    
    public int contarRestaurantesEnTotal() {
        cargarCategorias();
        int totalRestaurantes = 0;
        CategoriaRestaurante categoriaRest = new CategoriaRestaurante();
        ArrayList<CategoriaRestaurante> categorias = categoriaRest.recuperarCategorias();
        for (CategoriaRestaurante cat : categorias) {
            totalRestaurantes += cat.getRestaurantes().size();
        }
        return totalRestaurantes;
    }



    public void ingresarDatos() {
        TableRestaurante.setItems(lista);
        ColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombreRestaurante"));
        ColumnUbicacion.setCellValueFactory(new PropertyValueFactory<>("Ubicacion"));
        ColumnMesa.setCellValueFactory(new PropertyValueFactory<>("mesa"));
        ColumnAforo.setCellValueFactory(new PropertyValueFactory<>("aforo"));
    }

    public void eliminarRestaurantePorNombre(String nombreRestaurante) {
        CategoriaRestaurante categoriaRest = new CategoriaRestaurante();
        ArrayList<CategoriaRestaurante> categorias = categoriaRest.recuperarCategorias();
        boolean restauranteEliminado = false;

        for (int i = 0; i < categorias.size() && !restauranteEliminado; i++) {
            CategoriaRestaurante categoria = categorias.get(i);
            ArrayList<Restaurante> restaurantesCategoria = categoria.getRestaurantes();

            for (int j = 0; j < restaurantesCategoria.size(); j++) {
                Restaurante restaurante = restaurantesCategoria.get(j);
                if (restaurante.getNombreRestaurante().equals(nombreRestaurante)) {
                    restaurantesCategoria.remove(j);
                    restauranteEliminado = true;
                    break;
                }
            }
        }

        // Si el restaurante fue eliminado, guarda la lista de categorias actualizada en el JSON
        if (restauranteEliminado) {
            guardarCategoriasEnJson(categorias);
        }
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
    void BtnEliminar(ActionEvent event) {
        Restaurante restauranteSeleccionado = TableRestaurante.getSelectionModel().getSelectedItem();
        if (restauranteSeleccionado != null) {
            eliminarRestaurantePorNombre(restauranteSeleccionado.getNombreRestaurante());
            lista.remove(restauranteSeleccionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No hay ningún restaurante seleccionado");
            alert.showAndWait();
            return;
        }
    }

    @FXML
    void Salir(MouseEvent event) {
	    // Obteniendo la ventana (Stage) actual a través del botón y cerrándola
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
            
            // Pasar el objeto Administrador al controladorClienteReg
            controladorRegCli.setAdminData(administrador);

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

    }
    @FXML
    void RegistrarRest(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistas/RegistrarRestaurante.fxml")); // Reemplaza "/path/to/" con la ruta real del archivo FXML.
            Parent root = fxmlLoader.load(); 
            ControllerRegistrarRestAdmin controller = fxmlLoader.getController();
            // Realiza cualquier configuración adicional en el controlador, si es necesario

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void initialize() {
    	numRestaurantesRegistrados.setText(Integer.toString(contarRestaurantesEnTotal()));
        ingresarDatos();
        cargarCategorias();
    }
    
    
    
    
}
