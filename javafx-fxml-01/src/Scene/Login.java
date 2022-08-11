package Scene;

import Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderLogin = new FXMLLoader(this.getClass().getResource("layoutLogin.fxml"));

        LoginController controllerLogin = new LoginController();
        loaderLogin.setController(controllerLogin);

        Parent root = loaderLogin.load();
        stage.setTitle("Programa JavaFX FXML 01");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void begin(){
        launch();
    }
}
