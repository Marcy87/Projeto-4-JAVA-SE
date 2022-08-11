package Scene;

import Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Vendas extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderCliente = new FXMLLoader(this.getClass().getResource("layoutVendas.fxml"));

        LoginController loginController = new LoginController();
        loaderCliente.setController(loginController);

        Parent root = loaderCliente.load();
        stage.setTitle("Vendas");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void begin(){
        launch();
    }



}
