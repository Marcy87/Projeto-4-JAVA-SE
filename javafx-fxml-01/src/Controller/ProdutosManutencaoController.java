package Controller;

import db.SQLite;
import entities.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProdutosManutencaoController implements Initializable {

    @FXML
    private TableView<Produto> twproduto;

    @FXML
    private TableColumn<Produto, String> descricaocol;

    @FXML
    private TableColumn<Produto, String> custocol;

    @FXML
    private  TableColumn<Produto, String> precocol;

    @FXML
    private TextField tfdescricao;

    @FXML
    private TextField tfcusto;

    @FXML
    private TextField tfpreco;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Atribuindo os valores as celulas*/
        descricaocol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        custocol.setCellValueFactory(new PropertyValueFactory<>("custo"));
        precocol.setCellValueFactory(new PropertyValueFactory<>("preco"));

        try{
            twproduto.setItems(listTodosProdutos());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private ObservableList<Produto> listTodosProdutos() throws SQLException, ClassNotFoundException {

        SQLite dbprodutos = new SQLite();

        return FXCollections.observableArrayList(dbprodutos.getAllProdutos());
    }


    public void atualizarTextField(Event e){
        ObservableList produtoselecionado = twproduto.getSelectionModel().getSelectedItems();

        Produto produtoAtualizar = new Produto();
        produtoAtualizar.setDescricao(((Produto) produtoselecionado.get(0)).getDescricao());
        produtoAtualizar.setCusto(((Produto) produtoselecionado.get(0)).getCusto());
        produtoAtualizar.setPreco(((Produto) produtoselecionado.get(0)).getPreco());

        tfdescricao.setText(produtoAtualizar.getDescricao());
        tfcusto.setText(produtoAtualizar.getCusto());
        tfpreco.setText(produtoAtualizar.getPreco());
    }


    public void excluirProduto(Event e){
        ObservableList produtoselecionado = twproduto.getSelectionModel().getSelectedItems();

        Produto produtoEliminar = new Produto();
        produtoEliminar.setDescricao(((Produto) produtoselecionado.get(0)).getDescricao());
        produtoEliminar.setCusto(((Produto) produtoselecionado.get(0)).getCusto());
        produtoEliminar.setPreco(((Produto) produtoselecionado.get(0)).getPreco());

        try{
            SQLite dbExclusaoProduto = new SQLite();
            dbExclusaoProduto.excluirProduto(produtoEliminar);

            twproduto.getItems().clear();
            twproduto.setItems(listTodosProdutos());

            Alert alertEliminacao = new Alert(Alert.AlertType.INFORMATION, "Produto eliminado com sucesso");
            alertEliminacao.show();

        }catch (SQLException | ClassNotFoundException erro){
            erro.printStackTrace();
        }

        tfdescricao.clear();
        tfcusto.clear();
        tfpreco.clear();
    }


    public void atualizarProduto(Event e){
        ObservableList produtoListAntigo = twproduto.getSelectionModel().getSelectedItems();

        Produto produtoAntigo = new Produto();
        produtoAntigo.setDescricao(((Produto) produtoListAntigo.get(0)).getDescricao());

        String descricao = tfdescricao.getText();
        String custo = tfcusto.getText();
        String preco = tfpreco.getText();

        Produto produtoAtualizar = new Produto();
        produtoAtualizar.setDescricao(descricao);
        produtoAtualizar.setCusto(custo);
        produtoAtualizar.setPreco(preco);

        try{
            SQLite dbAtualizarProduto = new SQLite();
            dbAtualizarProduto.atualizarProduto(produtoAtualizar, produtoAntigo.getDescricao());

            twproduto.getItems().clear();
            twproduto.setItems(listTodosProdutos());

            Alert alertAtualizacao = new Alert(Alert.AlertType.INFORMATION, "Produto atualizado com sucesso");
            alertAtualizacao.show();

        }catch (Exception erro){
            erro.printStackTrace();
        }

        tfdescricao.clear();
        tfcusto.clear();
        tfpreco.clear();
    }

}