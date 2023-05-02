package Controller;

import java.sql.Time;
import java.util.ArrayList;

import Clases.Cliente;
import Clases.Menu;
import Clases.Reserva;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerPrincipalRestaurante {

    @FXML
    private Label PanelControl;
    
    @FXML
    private Label Ayuda;
    
    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn<Reserva, String> ColumnUsuario;

    @FXML
    private TableColumn<Reserva, String> ColumnTelefo;

    @FXML
    private TableColumn<Reserva, Time> ColumnHora;
    
    @FXML
    private TableColumn<Reserva, String> ColumnFecha;

    @FXML
    private TableColumn<Reserva, String> ColumnMesa;



    @FXML
    void ReservaCancelar(MouseEvent event) {

    }

    @FXML
    void ayuda(MouseEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Para obtener ayuda puede contactarnos en el: \n"
				           + "+34 911-420-6969 [Horario de 9h-14h y 14h-17h]");
		alert.setTitle("Ayuda");
		alert.showAndWait();
    }

    @FXML
    void menu(MouseEvent event) {

    }

    @FXML
    void panelControl(MouseEvent event) {
    	try {
			FXMLLoader loaderRestaurantePanel = new FXMLLoader(getClass().getResource("/Vistas/ViewRestaurantePanel.fxml"));
			ControllerRestaurantePanel controlRestaurantePanel = new ControllerRestaurantePanel();

			loaderRestaurantePanel.setController(controlRestaurantePanel);
			Parent rootRestaurant = loaderRestaurantePanel.load();
			Stage stage = (Stage) PanelControl.getScene().getWindow();

			stage.close();
			stage.setScene(new Scene(rootRestaurant));
			stage.show();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al abrir la ventana");
			alert.showAndWait();
		}
    }

    @FXML
    void salir(MouseEvent event) {
    	Platform.exit();
    }   
    
    void initialize() {



    }
    
    void setColumns() {

        
    }
    

}

