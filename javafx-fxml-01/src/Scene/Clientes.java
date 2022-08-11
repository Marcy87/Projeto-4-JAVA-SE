package Scene;

import Controller.ClientesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Clientes extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderCliente = new FXMLLoader(this.getClass().getResource("layoutClientes.fxml"));

        ClientesController clientesController = new ClientesController();
        loaderCliente.setController(clientesController);

        Parent root = loaderCliente.load();
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void begin(){
        launch();
    }



}
