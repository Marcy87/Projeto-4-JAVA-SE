package Scene;

import Controller.ProdutosManutencaoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProdutosManutencao extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderProdutoManutencao = new FXMLLoader(this.getClass().getResource("layoutProdutoManutencao.fxml"));

        ProdutosManutencaoController produtosManutencaoController = new ProdutosManutencaoController();
        loaderProdutoManutencao.setController(produtosManutencaoController);

        Parent root = loaderProdutoManutencao.load();
        stage.setTitle("Manutenção de Produtos");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
