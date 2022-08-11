package Scene;

import Controller.LoginController;
import Controller.ProdutosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Produtos extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderProduto = new FXMLLoader(this.getClass().getResource("layoutProdutos.fxml"));

        /*LoginController loginController = new LoginController();
        loaderProduto.setController(loginController);
        */

        ProdutosController produtosController = new ProdutosController();
        loaderProduto.setController(produtosController);

        Parent root = loaderProduto.load();
        stage.setTitle("Cadastro de Produto");
        stage.setScene(new Scene(root));
        stage.show();


    }

    public void begin(){
        launch();
    }

}
