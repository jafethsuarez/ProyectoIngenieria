package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Clases.Plato;
import Clases.Restaurante;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Clases.CategoriaRestaurante;

public class ControllerActualizarMenu {

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
    private JFXTextField txtnombre;

    @FXML
    private JFXTextField txtDescrip;

    @FXML
    private JFXTextField txtPrecio;
    private Plato plato;
    private Restaurante restaurante;
    
    
    public void setRestaurante(Restaurante restaurante)
    {
    	this.restaurante = restaurante;
    	
    }
    public void setPlato(Plato plato) {
        this.plato = plato;
        txtnombre.setText(plato.getNombre());
        txtDescrip.setText(plato.getDescripcion());
        txtPrecio.setText(String.valueOf(plato.getPrecio()));
    }

    @FXML
    void ActualizarMenu(ActionEvent event) {
        // Actualizar la información del Plato.
        plato.setNombre(txtnombre.getText());
        plato.setDescripcion(txtDescrip.getText());
        plato.setPrecio(Double.valueOf(txtPrecio.getText()));

        // Leer todas las Categorias del archivo JSON.
        PlatoManager manager = new PlatoManager();
        List<CategoriaRestaurante> categorias = manager.leerCategorias();

        // Buscar el Plato que coincida con el Plato que estamos editando.
        outerloop:
        for (CategoriaRestaurante categoria : categorias) {
            for (Restaurante restauranteDeLista : categoria.getRestaurantes()) {
                // Verificar si estamos en el restaurante correcto.
                if (restauranteDeLista.getNombreRestaurante().equals(restaurante.getNombreRestaurante())) {
                    for (int i = 0; i < restauranteDeLista.getMenu().getPlatos().size(); i++) {
                        if (restauranteDeLista.getMenu().getPlatos().get(i).getId() == plato.getId()) {
                            // Si se encuentra el Plato, reemplazarlo con la versión actualizada.
                            restauranteDeLista.getMenu().getPlatos().set(i, plato);
                            break outerloop;
                        }
                    }
                }
            }
        }

     // Guardar todas las Categorias de vuelta en el archivo JSON.
        manager.escribirCategorias(categorias);

        // Mostrar un mensaje emergente indicando que la actualización ha sido exitosa.
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Actualización realizada con éxito");
        alert.showAndWait();

        // Cerrar la ventana.
        Stage stage = (Stage) btnModificar.getScene().getWindow();
        stage.close();
    }


    @FXML
    void PanelCliente(MouseEvent event) {

    }

    @FXML
    void Reservas(MouseEvent event) {

    }

    @FXML
    void Restaurantes(MouseEvent event) {

    }

    @FXML
    void Salir(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert lblEditarMenu != null : "fx:id=\"lblEditarMenu\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";
        assert lblReservas != null : "fx:id=\"lblReservas\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";
        assert lblCliente != null : "fx:id=\"lblCliente\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";
        assert gridRestaurante != null : "fx:id=\"gridRestaurante\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";
        assert btnModificar != null : "fx:id=\"btnModificar\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";
        assert txtnombre != null : "fx:id=\"txtnombre\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";
        assert txtDescrip != null : "fx:id=\"txtDescrip\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";
        assert txtPrecio != null : "fx:id=\"txtPrecio\" was not injected: check your FXML file 'ViewActualizarMenu.fxml'.";

    }
}
