package application;
	
import javax.sound.midi.ControllerEventListener;


import Controller.ControllerLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
		@Override
		public void start(Stage primaryStage) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/ViewLogin.fxml"));
				
				ControllerLogin controlLogin = new ControllerLogin();
				
				loader.setController(controlLogin);

				Parent root = loader.load();
				
				primaryStage.setScene(new Scene(root));
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
