package Controller;

import Scene.*;
import db.SQLite;
import entities.Usuario;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    Button btLogar;
    @FXML
    Button btCancelar;
    @FXML
    TextField tfUsuario = null;
    @FXML
    TextField tfSenha = null;
    @FXML
    MenuBar menuPrincipal;
    @FXML
    Label lbUsuario;
    @FXML
    Label lbSenha;
    @FXML
    Label lbUsuarioLogado;

    @FXML
    public void clickLogar(Event e) throws Exception {
        Boolean checkLogin = true;
        String usuario = tfUsuario.getText();
        String senha = tfSenha.getText();
        Alert alertLogar = new Alert(Alert.AlertType.INFORMATION, "Usuário Logado");


        if (usuario.isEmpty()) {
            alertLogar.setContentText("Primeiro Informar o usuário");
            checkLogin = false;
        }else {
            if (senha.isEmpty()) {
                alertLogar.setContentText("Informe a sua senha");
                checkLogin = false;
            }
        }

        if (!checkLogin) {
            alertLogar.show();
        }else{
            Usuario usuarioLogin = new Usuario();
            usuarioLogin.setEmail(usuario);
            usuarioLogin.setSenha(senha);

            SQLite dbsqlite = new SQLite();
            if (dbsqlite.checkLogin(usuarioLogin)){
                // Faz Login
                lbUsuarioLogado.setText("Logado como (" + usuarioLogin.getEmail() + ")");
                tfSenha.setText("");
                menuPrincipal.setDisable(false);
                tfUsuario.setVisible(false);
                tfSenha.setVisible(false);
                btLogar.setVisible(false);
                btCancelar.setVisible(false);
                lbUsuario.setVisible(false);
                lbSenha.setVisible(false);
            }else{
                alertLogar.setContentText("Usuário não cadastrado");
                alertLogar.show();
            }
        }
    }

    @FXML
    public void clickCancelar(Event e){
        tfUsuario.setText("");
        tfSenha.setText("");
    }

    @FXML
    public void clickCadastrarUsuario(Event e) throws Exception {
        Usuarios usuarios = new Usuarios();
        usuarios.start(new Stage());
    }

    @FXML
    public void clickCadastrarProduto(Event e) throws Exception {
        Produtos produtos = new Produtos();
        produtos.start(new Stage());
    }

    @FXML
    public void clickCadastrarCliente(Event e) throws Exception {
        Clientes clientes = new Clientes();
        clientes.start(new Stage());
    }

    @FXML
    public void clickManterUsuario(Event e) throws Exception {
        UsuariosManutencao usuariomanutencao = new UsuariosManutencao();
        usuariomanutencao.start(new Stage());
    }

    @FXML
    public void clickManterProduto(Event e) throws Exception {
        ProdutosManutencao produtosManutencao = new ProdutosManutencao();
        produtosManutencao.start(new Stage());
    }

    @FXML
    public void clickManterCliente(Event e) throws Exception {
        ClientesManutencao clientesManutencao = new ClientesManutencao();
        clientesManutencao.start(new Stage());
    }

    @FXML
    public void clickAbrirVendas(Event e) throws Exception{
        Vendas vendas = new Vendas();
        vendas.start(new Stage());
    }

    @FXML
    public void clickAbrirSistemaVenda(Event e) throws Exception{
        Venda venda = new Venda();
        venda.start(new Stage());
    }

    @FXML
    public void clickRelogar(Event e){
        lbUsuarioLogado.setText("");
        menuPrincipal.setDisable(true);
        tfUsuario.setVisible(true);
        tfSenha.setVisible(true);
        btLogar.setVisible(true);
        btCancelar.setVisible(true);
        lbUsuario.setVisible(true);
        lbSenha.setVisible(true);
    }


}
