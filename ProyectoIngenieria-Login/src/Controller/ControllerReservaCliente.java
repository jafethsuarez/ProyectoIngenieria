package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerReservaCliente {

    @FXML
    private GridPane gridRestaurante;

    @FXML
    private JFXButton confirmarReservaBtn;

    @FXML
    private JFXDatePicker fechaPicker;

    @FXML
    private JFXTimePicker horaPicker;

    @FXML
    private JFXTextField numPersonasLbl;

    @FXML
    void confirmarReserva(MouseEvent event) {
    	
    }

}

