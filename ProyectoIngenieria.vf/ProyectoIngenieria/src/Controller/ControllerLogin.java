package Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Clases.Administrador;
import Clases.Cliente;
import Clases.Restaurante;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.sound.midi.Soundbank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ControllerLogin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtusuario;


    @FXML
    private JFXPasswordField txtcontrasena;


    @FXML
    private JFXButton btnIngresar;

    @FXML
    void RecuperarCont(MouseEvent event) {
    	
    }	

    @FXML
    void Registrar(MouseEvent event) {
    	
    }

    @FXML
    void ingresar(ActionEvent event)  {
    	//instanciamosAdministradores
    	Administrador administrador = new Administrador();
    	Vector<Administrador> admins= administrador.desserealizarJsonArray();
    	//intanciamos Cliente
    	Cliente cliente = new Cliente();
    	Vector<Cliente> client = cliente.recuperarCliente();
    	int l= 0;
    	int i = 0;
    	int j = 0;
    	
    	boolean encontrarRestaurante= true;
    	boolean encontrarCli= false;
    	boolean encontradoAdmin = false;
    	
    	//encontrarCliente
    	while(l< client.size() && !encontrarCli) {
    		if (client.get(l).getUsuario().equals(txtusuario.getText())&& client.get(l).getContrasena().equals(txtcontrasena.getText())) {
				encontrarCli = true;
			}
    		l++;
    	}
    	
    	while(i < admins.size()&& !encontradoAdmin) {
    		
    		if(admins.get(i).getUsuario().equals(txtusuario.getText()) && admins.get(i).getContrasena().equals(txtcontrasena.getText())) {
    			encontradoAdmin =true;
    		}
    		i++;
    		
    	}
    	//encontrarRestaurante
    	while(j < client.size() && !encontrarRestaurante ) {
    	    int k = 0;
    	    while(k < client.get(j).getRestaurantes().size()&& !encontrarRestaurante) {
    	        if(client.get(j).getRestaurantes().get(k).getUsuario().equals(txtusuario.getText())) {
    	            encontrarRestaurante = true;
    	            
    	        } 
    	        k++;
    	    }
    	    j++;
    	}
    	
    	if (encontradoAdmin) {
    		try {
				FXMLLoader loaderAdmin=new FXMLLoader(getClass().getResource("/Vistas/ViewPrincipalAdmin.fxml"));
				ControllerPrincipalAdmin controlAdmin=new ControllerPrincipalAdmin();
        		
        		loaderAdmin.setController(controlAdmin);
                Parent rootAdmin=loaderAdmin.load();
                Stage stage = (Stage) btnIngresar.getScene().getWindow();
                
                stage.close();
                stage.setScene(new Scene(rootAdmin));
                stage.show();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if (encontrarCli) {
			try {
			FXMLLoader loaderCli=new FXMLLoader(getClass().getResource("/Vistas/ViewPrincipalCliente.fxml"));
			ControllerPrincipalCliente controlCli=new ControllerPrincipalCliente();
    		
			loaderCli.setController(controlCli);
            Parent rootCli=loaderCli.load();
            Stage stage = (Stage) btnIngresar.getScene().getWindow();
            
            stage.close();
            stage.setScene(new Scene(rootCli));
            stage.show();
			} catch (Exception e) {
    		}
		}else if (encontrarRestaurante) {
			try {
			FXMLLoader loaderRestaurante=new FXMLLoader(getClass().getResource("/Vistas/ViewPrincipalRestaurante.fxml"));
			ControllerPrincipalRestaurante controlRest=new ControllerPrincipalRestaurante();
    		
			loaderRestaurante.setController(controlRest);
            Parent rootRest=loaderRestaurante.load();
            Stage stage = (Stage) btnIngresar.getScene().getWindow();
            
            stage.close();
            stage.setScene(new Scene(rootRest));
            stage.show();
			} catch (Exception e) {
    		}
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error de identificacion");
    	    alert.setContentText("Usuario o contrasena incorrectos");
    	    alert.showAndWait();
			
		}
    }

    @FXML
    void initialize() {
        

    }
}
