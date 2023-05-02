package Controller;

import java.util.Vector;

import Clases.Cliente;
import Clases.Usuario;
import Controller.ControllerLogin;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerClientePanel extends ControllerLogin{
	
    @FXML
    private Label lblRestaurantes;

    @FXML
    private Label lblReservas;


    @FXML
    private TextField nombreUsuarioLbl;
    

    @FXML
    private TextField nombreCompletoLbl;

    @FXML
    private TextField contraseñaLbl;

    @FXML
    private TextField numTelefonoLbl;

    @FXML
    private TextField ubicacionLbl;
    
    @FXML
    private Label lblCliente;
    
    //Se instancia el cliente actual usado en para el login
    Cliente clienteActual = new Cliente();
    Vector<Cliente> client = clienteActual.recuperarCliente();
   
    @FXML
    void Reservas(MouseEvent event) {
        
    }

    @FXML
    void Restaurantes(MouseEvent event) {
    	
    }

    @FXML
    void Salir(MouseEvent event) {
    	Platform.exit();
    }
    

    
    public void initialize () {
    	clienteActual = client.get(ControllerLogin.idCliente);
    	nombreUsuarioLbl.setText(clienteActual.getUsuario());
    	nombreCompletoLbl.setText(clienteActual.getNombre());
    	contraseñaLbl.setText(clienteActual.getContrasena());
    	numTelefonoLbl.setText(clienteActual.getTelefono());
    	ubicacionLbl.setText(clienteActual.getUbicacion());
    }

}

