package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.scene.Node;
import Clases.Administrador;
import Clases.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerPrincipalAdmin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Admin;

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
    String adminUsername  = "";
    
    
    
    @FXML
    private TableColumn<Cliente, Integer> ColumnTelefo;
    
    
    Administrador administrador;

    public void setAdminData(Administrador administrador) {
        if (administrador != null) {
            this.administrador = administrador;
            System.out.println("setAdminData: " + administrador.getUsuario());
            setAdminUsername(administrador.getUsuario());
            }
        }
    public void setAdminUsername(String adminUsername) {
            this.Admin.setText(adminUsername);
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
    	        // Muestra un mensaje de error si no hay ningún cliente seleccionado
    	        System.out.println("No hay ningún cliente seleccionado");
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
            // Cambiado controladorClienteReg a ControllerClienteReg
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
            // Cambiado controladorRestauranteReg a ControllerRestauranteReg
            controladorRestauranteReg controladorRegRest = new controladorRestauranteReg();

            loaderRegRest.setController(controladorRegRest);
          
            Parent rootRegRest = loaderRegRest.load();
           
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
        ingresarDatos();
    }
}