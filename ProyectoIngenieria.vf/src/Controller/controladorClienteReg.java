package Controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Clases.Administrador;
import Clases.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

public class controladorClienteReg {
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label Admin2;

	    @FXML
	    private TableView<Cliente> TableCliente;
	    
	    @FXML
	    private TableColumn<Cliente, Integer> ColumnId;

	    @FXML
	    private TableColumn<Cliente, Integer> ColumnUbi;

	    @FXML
	    private TableColumn<Cliente, String> ColumnUsuario; // Cambiado de Integer a String
	    
	    private Stage stage;

	    public void setStage(Stage stage) {
	        this.stage = stage;
	    }
	  
	    @FXML
	    private TextField clientesRegistrados;
	    
	    
	    @FXML
	    private TableColumn<Cliente, Integer> ColumnTelefo;
	    
	    public void setAdminUsername(String adminUsername) {
	        this.Admin2.setText(adminUsername);
	    }
	    
	    String adminUsername = "";

	    Administrador administrador;

	    public void setAdminData(Administrador administrador) {
	        if (administrador != null) {
	            this.administrador = administrador;
	            System.out.println("setAdminData: " + administrador.getUsuario());
	            actualizarLabelAdmin();
	        }
	    }



	    public void actualizarLabelAdmin() {
	        if (administrador != null) {
	            Admin2.setText(administrador.getUsuario());
	        } else {
	            Admin2.setText("Marcos");
	        }
	    }



	    ObservableList<Cliente> lista = FXCollections.observableArrayList();

	    public void ingresarDatos() {
	        TableCliente.setItems(lista);
	        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	        ColumnTelefo.setCellValueFactory(new PropertyValueFactory<>("telefono"));
	        ColumnUbi.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
	        ColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

	        // Cambiado controladorClienteReg a ControllerClienteReg
	        controladorClienteReg controlReg = new controladorClienteReg();
	        Vector<Cliente> listaCliente = new Vector<>();
	        listaCliente = controlReg.recuperarCliente();
	        for (int i = 0; i < listaCliente.size(); i++) {
	            Cliente cli = listaCliente.get(i);
	            Cliente nuevoCliente = new Cliente();
	            nuevoCliente.setId(cli.getId());
	            nuevoCliente.setTelefono(cli.getTelefono());
	            nuevoCliente.setUbicacion(cli.getUbicacion());
	            nuevoCliente.setUsuario(cli.getUsuario());
	            lista.add(nuevoCliente);
	        }
	    }
	    
	    
	   
	    @FXML
	    void BtnEliminar(ActionEvent event) {
	    	 Cliente clienteSeleccionado = TableCliente.getSelectionModel().getSelectedItem();
	    	    if (clienteSeleccionado != null) {
	    	    	controladorClienteReg controlReg = new controladorClienteReg();
	    	        controlReg.eliminarClientePorId(clienteSeleccionado.getId());
	    	        lista.remove(clienteSeleccionado);
	    	    } else {
	                Alert alert = new Alert(Alert.AlertType.WARNING);
	                alert.setHeaderText(null);
	                alert.setTitle("Error");
	                alert.setContentText("No hay ningún cliente seleccionado");
	                alert.showAndWait();
	                return;
	    	    }
	    }
	    @FXML
	    void Salir(MouseEvent event) {
	    	 // Obteniendo la ventana (Stage) actual a través del botón y cerrándola
	        Stage currentStage = (Stage) Admin2.getScene().getWindow();
	        currentStage.close();
	    }
	    
	 // ... (El resto del código se mantiene igual)

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
	    	actualizarLabelAdmin();
	        ingresarDatos();
	        clientesRegistrados.setText(Integer.toString(lista.size())); //Numero de clientes registrados a partir del observableList size
	    }
	    
	    //Deserealizar
  	public Vector<Cliente> recuperarCliente(){
  	 	Vector<Cliente> clientes = new Vector<Cliente>();
  		    	
  	  	try(Reader reader = new FileReader("Datos.json")){
  	   		Gson gson = new Gson();
  	   		Type tipoListaClientes = new TypeToken<Vector<Cliente>>() {}.getType();
  	   		clientes = gson.fromJson(reader, tipoListaClientes);
  	   	} catch(IOException e) {
  	   		e.printStackTrace();
  	   	}
  	   	
  	    	return clientes;
  	    }
  	
  	public  void eliminarClientePorId(int id) {
  	    Vector<Cliente> listaCliente = recuperarCliente();
  	    for (int i = 0; i < listaCliente.size(); i++) {
  	        if (listaCliente.get(i).getId() == id) {
  	            listaCliente.remove(i);
  	            break;
  	        }
  	    }
  	  guardarClientesEnJson(listaCliente);
  	}
  	public void guardarClientesEnJson(Vector<Cliente> listaCliente) {
  	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
  	    String json = gson.toJson(listaCliente);
  	    try (Writer writer = new FileWriter("Datos.json")) {
  	        writer.write(json);
  	    } catch (IOException e) {
  	        e.printStackTrace();
  	    }
  	}
  	
}
