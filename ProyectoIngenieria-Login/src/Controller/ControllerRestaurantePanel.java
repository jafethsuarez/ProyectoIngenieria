package Controller;

import java.util.Vector;

import Clases.Restaurante;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerRestaurantePanel extends ControllerLogin {

    @FXML
    private Label PanelControl;

    @FXML
    private Label Ayuda;

    @FXML
    private TextField nombreUsuarioLbl;

    @FXML
    private TextField nombreRestauranteLbl;

    @FXML
    private TextField contrasenaLbl;

    @FXML
    private TextField categoriaLbl;

    @FXML
    private TextField ubicacionLbl;

    @FXML
    private TextField aforoLbl;

    
    //Se instancia eel restaurante de la sesión actual
    Restaurante restauranteActual = new Restaurante();
    Vector<Restaurante> rest = restauranteActual.recuperarRestaurantes();
    
    @FXML
    void ayuda(MouseEvent event) {
    	
    }
    
    @FXML
    void GuardarCambios(MouseEvent event) {

    }
    
    @FXML
    void menu(MouseEvent event) {

    }

    @FXML
    void panelControl(MouseEvent event) {

    }

    @FXML
    void salir(MouseEvent event) {
    	Platform.exit();
    }
    
    void initialize() {
    	restauranteActual = rest.get(ControllerLogin.idRestaurante);
    	nombreUsuarioLbl.setText(restauranteActual.getUsuario());
    	nombreRestauranteLbl.setText(restauranteActual.getNombreRestaurante());
    	contrasenaLbl.setText(restauranteActual.getContrasena());
    	categoriaLbl.setText(restauranteActual.getCategoriaRestaurante().toString());
    	ubicacionLbl.setText(restauranteActual.getUbicacion());
    	aforoLbl.setText(Integer.toString(restauranteActual.getAforo()));
    }

}
