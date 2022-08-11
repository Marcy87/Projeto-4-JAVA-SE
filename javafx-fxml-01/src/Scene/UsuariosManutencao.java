package Scene;

import Controller.LoginController;
import Controller.UsuariosManutencaoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UsuariosManutencao extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderUsuarioManutencao = new FXMLLoader(this.getClass().getResource("layoutUsuarioManutencao.fxml"));

        /*LoginController loginController = new LoginController();
        loaderUsuarioManutencao.setController(loginController);
        */

        UsuariosManutencaoController usuariomanutencaocontroller = new UsuariosManutencaoController();
        loaderUsuarioManutencao.setController(usuariomanutencaocontroller);

        Parent root = loaderUsuarioManutencao.load();
        stage.setTitle("Manutenção de Usuários");
        stage.setScene(new Scene(root));
        stage.show();

    }
}
