package Controller;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Clases.Cliente;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;

public class ControllerCambioContrasena {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtusuario;

    @FXML
    private JFXPasswordField txtContrasena;

    @FXML
    private JFXPasswordField txtxconfirmarCont;

    @FXML
    private JFXPasswordField txtantiguacont;
    public void usuario(String usuario) {
    	txtusuario.setText(usuario);
    }
    @FXML
    void CambiarContrasena(ActionEvent event) {
        Cliente cliente = new Cliente();
        Vector<Cliente> clientes = cliente.recuperarCliente();
        boolean encontrar = false;
        boolean esCliente = false;
        boolean informacionIncorrecta = false;
        int i = 0;
        int indice = -1;
        while (i < clientes.size() && !encontrar) {
            if (clientes.get(i).getUsuario().equals(txtusuario.getText())) {
                indice = i;
                encontrar = true;
                if (clientes.get(i).getContrasena().equals(txtantiguacont.getText())) {
                    informacionIncorrecta = true;
                }
            }
            i++;
        }

        if (encontrar && informacionIncorrecta) {
            if (!esCliente) {
                if (txtContrasena.getText().equals(txtxconfirmarCont.getText())) {
                    clientes.get(indice).setContrasena(txtContrasena.getText());
                    Cliente.guardarCliente(clientes);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Cambio de contraseña");
                    alert.setContentText("La contraseña ha sido cambiada correctamente");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Error al cambiar contraseña");
                    alert.setContentText("Las contraseñas no coinciden");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error de identificacion");
            alert.setContentText("Informacion incorrecta");
            alert.showAndWait();
        }
    }
    @FXML
    void initialize() {
        assert txtusuario != null : "fx:id=\"txtusuario\" was not injected: check your FXML file 'ViewCambioCont.fxml'.";
        assert txtContrasena != null : "fx:id=\"txtContrasena\" was not injected: check your FXML file 'ViewCambioCont.fxml'.";

    }
}
