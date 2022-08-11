package Scene;

import Controller.ClientesManutencaoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientesManutencao extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderClienteManutencao = new FXMLLoader(this.getClass().getResource("layoutClienteManutencao.fxml"));

        ClientesManutencaoController clientesManutencaoController = new ClientesManutencaoController();
        loaderClienteManutencao.setController(clientesManutencaoController);

        Parent root = loaderClienteManutencao.load();
        stage.setTitle("Manutenção de Clientes");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
