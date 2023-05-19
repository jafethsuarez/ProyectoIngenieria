package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Clases.CategoriaRestaurante;
import Clases.Plato;
import Clases.Restaurante;

import Clases.Menu;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerAgregarMenu {

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
    private JFXButton btnAgregar;

    @FXML
    private JFXTextField txtnombreMenu;

    @FXML
    private JFXTextField txtDescripMen;

    @FXML
    private JFXTextField txtPrecioMen;
    
  private Restaurante restaurante;
    
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
       
    }

	@FXML
	void AgregarMenu(ActionEvent event) {
		String nombre = txtnombreMenu.getText();
		String descripcion = txtDescripMen.getText();
		double precio;
		
		
		//Checkea si es null alguno de los campos
		if ((txtnombreMenu.getText().isEmpty()) || (txtDescripMen.getText().isEmpty()) || (txtPrecioMen.getText().isEmpty())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Rellena todos los campos");
			alert.showAndWait();
		} else {
			//Checkea si el precio es un double
			try {
				precio = Double.parseDouble(txtPrecioMen.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Precio inválido, introduce un número");
				alert.showAndWait();
				return;
			}

			PlatoManager platoManager = new PlatoManager();
			List<CategoriaRestaurante> categorias = platoManager.leerCategorias();

			for (CategoriaRestaurante categoria : categorias) {
				for (Restaurante restaurante : categoria.getRestaurantes()) {
					if (this.restaurante.getNombreRestaurante().equals(restaurante.getNombreRestaurante())) {
						Menu menu = restaurante.getMenu();
						// Si el restaurante no tiene un menú, crea uno nuevo
						if (menu == null) {
							menu = new Menu();
							restaurante.setMenu(menu);
						}
						List<Plato> platos = menu.getPlatos();
						// Si el menú no tiene platos, crea una nueva lista de platos
						if (platos == null) {
							platos = new ArrayList<>();
							menu.setPlatos(platos);
						}

						// Aquí debes decidir cómo quieres asignar el ID al nuevo plato
						int id = platos.size() + 1;

						Plato nuevoPlato = new Plato(id, nombre, descripcion, precio);
						platos.add(nuevoPlato);

						platoManager.escribirCategorias(categorias);

						// Mostrar un mensaje emergente indicando que el registro ha sido exitoso.
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Información");
						alert.setHeaderText(null);
						alert.setContentText("Registro realizado con éxito");
						alert.showAndWait();

						// Cerrar la ventana.
						Stage stage = (Stage) txtnombreMenu.getScene().getWindow();
						stage.close();
						return;

					}

				}
			}
		}
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
	    Stage currentStage = (Stage) lblCliente.getScene().getWindow();
	    currentStage.close();
    }

    @FXML
    void initialize() {
        assert lblEditarMenu != null : "fx:id=\"lblEditarMenu\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";
        assert lblReservas != null : "fx:id=\"lblReservas\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";
        assert lblCliente != null : "fx:id=\"lblCliente\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";
        assert gridRestaurante != null : "fx:id=\"gridRestaurante\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";
        assert txtnombreMenu != null : "fx:id=\"txtnombreMenu\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";
        assert txtDescripMen != null : "fx:id=\"txtDescripMen\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";
        assert txtPrecioMen != null : "fx:id=\"txtPrecioMen\" was not injected: check your FXML file 'ViewAgregarMenu.fxml'.";

    }
}
