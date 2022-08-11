package Controller;

import db.SQLite;
import entities.Usuario;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class UsuariosController {

    @FXML
    TextField tfNomeCompleto;

    @FXML
    TextField tfEmail;

    @FXML
    TextField tfSenha;

    @FXML
    TextField tfConfirmarSenha;


    public void clickSalvarUsuario(Event e) throws SQLException, ClassNotFoundException {
        Alert alertSalvarUsuario = new Alert(Alert.AlertType.INFORMATION, "Clicou no salvar do usuário");

        String nomecompleto = tfNomeCompleto.getText();
        if (nomecompleto.isEmpty()){
            alertSalvarUsuario.setContentText("Preencha o campo nome + sobrenome");
        }else {
            String[] nomesobrenome = nomecompleto.split(" ");
            int elementonomesobrenome = nomesobrenome.length;
            if (elementonomesobrenome == 1) {
                alertSalvarUsuario.setContentText("Preenche seu sobrenome");
                tfNomeCompleto.requestFocus();
                tfNomeCompleto.selectRange(tfNomeCompleto.getText().length(), tfNomeCompleto.getText().length());
            }else{
                String email = tfEmail.getText();
                if (!email.contains("@")){
                    alertSalvarUsuario.setContentText("E-mail incorreto, favor informar um e-mail valído");
                    tfEmail.requestFocus();
                    tfEmail.selectRange(tfEmail.getText().length(), tfEmail.getText().length());
                }else{
                    String senha = tfSenha.getText();
                    String senharConfirmar = tfConfirmarSenha.getText();

                    if (senha.isEmpty() || senharConfirmar.isEmpty()){
                        alertSalvarUsuario.setContentText("Preencha as senhas");
                        tfSenha.requestFocus();
                        tfSenha.selectRange(tfSenha.getText().length(), tfSenha.getText().length());
                    }else {

                        if (!senha.equals(senharConfirmar)) {
                            alertSalvarUsuario.setContentText("As senhas não Combinam");
                            tfSenha.requestFocus();
                            tfSenha.selectRange(tfSenha.getText().length(), tfSenha.getText().length());
                        } else {
                            Usuario cadusuario = new Usuario();
                            cadusuario.setEmail(tfEmail.getText());
                            cadusuario.setNome(tfNomeCompleto.getText());
                            cadusuario.setSenha(tfSenha.getText());

                            SQLite dbsqlite = new SQLite();
                            if (dbsqlite.checkEmail(cadusuario)) {
                                alertSalvarUsuario.setContentText("O usuário " + cadusuario.getEmail() + " já existe");
                            } else {
                                dbsqlite.insertUsuario(cadusuario);
                                alertSalvarUsuario.setContentText("Usuário salvo com sucesso");
                                tfNomeCompleto.setText("");
                                tfEmail.setText("");
                                tfConfirmarSenha.setText("");
                                tfSenha.setText("");
                                tfNomeCompleto.requestFocus();
                            }


                        }
                    }
                }
            }
        }

        alertSalvarUsuario.show();
    }

    public void clickCancelarUsuario(Event e){
        tfNomeCompleto.setText("");
        tfEmail.setText("");
        tfSenha.setText("");
        tfConfirmarSenha.setText("");
        tfNomeCompleto.requestFocus();
    }



}
