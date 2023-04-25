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

import javax.lang.model.element.NestingKind;
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
    		try {
				FXMLLoader loaderRegistrar=new FXMLLoader(getClass().getResource("/Vistas/ViewRegistrar.fxml"));
				ControllerRegistrar controlRegistrar=new ControllerRegistrar();
        		
				loaderRegistrar.setController(controlRegistrar);
                Parent rootAdmin=loaderRegistrar.load();
                Stage stage = new Stage();
                
                stage.close();
                stage.setScene(new Scene(rootAdmin));
                stage.show();
    		}catch (Exception e) {
				// TODO: handle exception
    			Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	    alert.setHeaderText(null);
        	    alert.setTitle("error Registro");
        	    alert.setContentText("Datos incorrectos");
        	    alert.showAndWait();
			}
    }

    @FXML
    void ingresar(ActionEvent event)  {
    	 // Comprueba si txtusuario o txtcontrasena son nulos
        if (txtusuario == null || txtcontrasena == null) {
            System.out.println("txtusuario o txtcontrasena son nulos");
            return;
        }

        // Instanciamos Administradores
        Administrador administrador = new Administrador();
        Vector<Administrador> admins = administrador.desserealizarJsonArray();

        // Instanciamos Cliente
        Cliente cliente = new Cliente();
        Vector<Cliente> client = cliente.recuperarCliente();

        Restaurante restaurante = new Restaurante();
        Vector<Restaurante> rest = restaurante.recuperarRestaurantes();

        int l = 0;
        int i = 0;
        int j = 0;

        boolean encontrarRestaurante = false;
        boolean encontrarCli = false;
        boolean encontradoAdmin = false;

        // Revisamos clientes
        while (l < client.size() && !encontrarCli) {
            cliente = client.get(l);
            if (cliente != null && cliente.getUsuario() != null && cliente.getContrasena() != null &&
                    cliente.getUsuario().equals(txtusuario.getText()) &&
                    cliente.getContrasena().equals(txtcontrasena.getText())) {
                encontrarCli = true;
            }
            l++;
        }

        // Revisamos administradores
        while (i < admins.size() && !encontradoAdmin) {
            administrador = admins.get(i);
            if (administrador != null && administrador.getUsuario() != null && administrador.getContrasena() != null &&
                    administrador.getUsuario().equals(txtusuario.getText()) &&
                    administrador.getContrasena().equals(txtcontrasena.getText())) {
                encontradoAdmin = true;
            }
            i++;
        }

        // Revisamos restaurantes
        while (j < client.size() && !encontrarRestaurante) {
            cliente = client.get(j);
            if (cliente != null && cliente.getRestaurantes() != null) {
                int k = 0;
                while (k < cliente.getRestaurantes().size() && !encontrarRestaurante) {
                    Restaurante currentRestaurante = cliente.getRestaurantes().get(k);
                    if (currentRestaurante != null && currentRestaurante.getUsuario() != null &&
                            currentRestaurante.getUsuario().equals(txtusuario.getText())) {
                        encontrarRestaurante = true;
                    }
                    k++;
                }
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
