package Controller;

import db.SQLite;
import entities.Produto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ProdutosController {

    @FXML
    TextField tfProdutoDescricao;

    @FXML
    TextField tfProdutoCusto;

    @FXML
    TextField tfProdutoPreco;

    public void clickSalvarProduto(Event e) throws SQLException, ClassNotFoundException {
        Alert alertSalvarProduto = new Alert(Alert.AlertType.INFORMATION, "");

        if (tfProdutoDescricao.getText().isEmpty()){
            alertSalvarProduto.setContentText("Preencha a descrição do produto");
            tfProdutoDescricao.requestFocus();
        }else{
            if(tfProdutoCusto.getText().isEmpty()){
                alertSalvarProduto.setContentText("Preencha o custo do produto");
                tfProdutoCusto.requestFocus();
            }else{
                if(tfProdutoPreco.getText().isEmpty()){
                    alertSalvarProduto.setContentText("Preencha o preço da mercadoria");
                    tfProdutoPreco.requestFocus();
                }else{
                    /*Salvar*/
                    Produto produtoCadastro = new Produto();
                    produtoCadastro.setDescricao(tfProdutoDescricao.getText());
                    produtoCadastro.setCusto(tfProdutoCusto.getText());
                    produtoCadastro.setPreco(tfProdutoPreco.getText());


                    SQLite dbsqlite = new SQLite();
                    dbsqlite.insertProduto(produtoCadastro);
                    alertSalvarProduto.setContentText("Produto registrado com sucesso");
                    limparCampos();
                }
            }
        }

        alertSalvarProduto.show();
    }


    public void keyPressOnlyNumber(Event e){
        // Expressões Regulares.. REGEX
        tfProdutoCusto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*[\\.|\\,]\\d*")){
                    tfProdutoCusto.setText(newValue.replaceAll("[^\\d]", ""));
                }

            }
        });

        tfProdutoPreco.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*[\\.|\\,]\\d*")){
                    tfProdutoPreco.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void clickCancelarProduto(Event e){
        limparCampos();
    }

    public void limparCampos(){
        tfProdutoDescricao.setText("");
        tfProdutoCusto.setText("");
        tfProdutoPreco.setText("");
        tfProdutoDescricao.requestFocus();
    }

}
