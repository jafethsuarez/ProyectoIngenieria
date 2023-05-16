package Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Clases.Administrador;
import Clases.CategoriaRestaurante;
import Clases.Cliente;
import Clases.Restaurante;
import Clases.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    
    String adminUsername  = ""; // Asegúrate de inicializarla con un valor predeterminado
    
    private Restaurante restaurante;
    
    @FXML
    void RecuperarCont(MouseEvent event) {
    	try {
			FXMLLoader loaderContra=new FXMLLoader(getClass().getResource("/Vistas/ViewCambiocont.fxml"));
			ControllerCambioContrasena controlContrasena=new ControllerCambioContrasena();
    		
			loaderContra.setController(controlContrasena);
            Parent rootContra=loaderContra.load();
            Stage stage = new Stage();
            
            stage.close();
            stage.setScene(new Scene(rootContra));
            stage.show();
		}catch (Exception e) {
			// TODO: handle exception
		
		}
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
        
        CategoriaRestaurante categorias = new CategoriaRestaurante();
        ArrayList<CategoriaRestaurante> cat = categorias.recuperarCategorias();
        
        int l = 0;
        int i = 0;
        int j = 0;
        

        boolean encontrarRestaurante = false;
        boolean encontrarCli = false;
        boolean encontradoAdmin = false;
     // Verificar las credenciales del administrador
     // ...

     // Suponiendo que 'administrador' es el objeto que contiene la información del administrador
        adminUsername = administrador.getUsuario();

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

        int c = 0;
        while (c < cat.size() && !encontrarRestaurante) {
            CategoriaRestaurante categoria = cat.get(c);
            if (categoria != null && categoria.getRestaurantes() != null) {
                int k = 0;
                // Revisamos restaurantes
                while (k < categoria.getRestaurantes().size() && !encontrarRestaurante) {
                    Restaurante currentRestaurante = categoria.getRestaurantes().get(k);
                    if (currentRestaurante != null && currentRestaurante.getUsuario() != null &&
                            currentRestaurante.getUsuario().equals(txtusuario.getText()) &&
                            currentRestaurante.getContrasena().equals(txtcontrasena.getText())) {
                        encontrarRestaurante = true;
                        
                     // Establecer el objeto Restaurante
                        restaurante = currentRestaurante;
                    }
                    k++;
                }
            }
            c++;
        }

    	if (encontradoAdmin) {
    		try {
				FXMLLoader loaderAdmin=new FXMLLoader(getClass().getResource("/Vistas/ViewPrincipalAdmin.fxml"));
				ControllerPrincipalAdmin controlAdmin=new ControllerPrincipalAdmin();
				

        		loaderAdmin.setController(controlAdmin);
        		
                Parent rootAdmin=loaderAdmin.load();
                controlAdmin.setAdminUsername(administrador.getUsuario());
                Stage stage = (Stage) btnIngresar.getScene().getWindow();
                
                stage.close();
                stage.setScene(new Scene(rootAdmin));
                stage.show();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if (encontrarCli) {
			  try {
		            FXMLLoader loaderCli = new FXMLLoader(getClass().getResource("/Vistas/ViewPrincipalCliente.fxml"));
		            ControllerPrincipalCliente controlCli = new ControllerPrincipalCliente();
		            controlCli.setCliente(cliente);
		            controlCli.setRestaurante(restaurante);
		            loaderCli.setController(controlCli);
		            Parent rootCli = loaderCli.load();
		            // Pasa el objeto Cliente al controlador
		            Stage stage = (Stage) btnIngresar.getScene().getWindow();

		            stage.close();
		            stage.setScene(new Scene(rootCli));
		            stage.show();
		        } catch (Exception e) {
		        }
		}else if (encontrarRestaurante) {
			 try {
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
	                Stage stage = (Stage) btnIngresar.getScene().getWindow();
	                stage.close();
	                stage.setScene(new Scene(rootRest));
	                stage.show();
	            } catch (Exception e) {
	                e.printStackTrace();
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
