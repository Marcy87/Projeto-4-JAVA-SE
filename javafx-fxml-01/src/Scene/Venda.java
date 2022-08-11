package Scene;

import Controller.VendaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Venda extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loaderVenda = new FXMLLoader(this.getClass().getResource("layoutVenda.fxml"));

        VendaController vendaController = new VendaController();
        loaderVenda.setController(vendaController);

        Parent root = loaderVenda.load();
        stage.setTitle("Sistema de Vendas");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
