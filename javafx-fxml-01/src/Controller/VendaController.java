package Controller;

import db.SQLite;
import entities.Cliente;
import entities.Produto;
import entities.VendaProduto;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class VendaController {

    @FXML
    private TextField tfCliente;

    @FXML
    private Label lbClienteSelecionado;

    @FXML
    private TextField tfProduto;

    @FXML
    private Label lbProdutoSelecionado;

    @FXML
    private TableView twprodutos;

    @FXML
    private TableColumn<VendaProduto, Integer> tcCodigoCliente;
    @FXML
    private TableColumn<VendaProduto, Integer> tcCodigoProduto;
    @FXML
    private TableColumn<VendaProduto, String> tcDescricao;
    @FXML
    private TableColumn<VendaProduto, Integer> tcQuantidade;
    @FXML
    private TableColumn<VendaProduto, Double> tcUnitario;
    @FXML
    private TableColumn<VendaProduto, Double> tcTotal;


    public void clickProcurarCliente(Event e) throws SQLException, ClassNotFoundException {
        Alert alertProcurarCliente = new Alert(Alert.AlertType.INFORMATION, "");

        //Captura do Código de Referência do Cliente.
        Integer codigo = Integer.parseInt(tfCliente.getText());

        SQLite db = new SQLite();
        Cliente cliente = db.getClienteforCodigo(codigo);

        if (cliente.getNome() == null) {
            // se ele não encontrar...
            lbClienteSelecionado.setText("...");
            tfCliente.setText("");
            tfCliente.requestFocus();
            alertProcurarCliente.setContentText("Cliente não encontrado !!!");
            alertProcurarCliente.show();
        } else {
            // se ele encontrar...
            lbClienteSelecionado.setText(cliente.getNome());
            tfProduto.requestFocus();
        }
    }


    public void clickProcurarProduto(Event e) throws SQLException, ClassNotFoundException {
        Alert alertProcurarProduto = new Alert(Alert.AlertType.INFORMATION, "");

        Integer codigo = Integer.parseInt(tfProduto.getText());

        SQLite db = new SQLite();
        Produto produto = db.getProdutoForCodigo(codigo);

        if (produto.getDescricao().isEmpty()) {
            lbProdutoSelecionado.setText("...");
            tfProduto.requestFocus();
            tfProduto.setText("");
            alertProcurarProduto.setContentText("Produto não localizado ou inexistente");
            alertProcurarProduto.show();
        } else {
            lbProdutoSelecionado.setText(produto.getDescricao());
        }
    }


    public void clickIncluirProduto(Event e) throws SQLException, ClassNotFoundException {
        Alert alertIncluirProduto = new Alert(Alert.AlertType.INFORMATION, "");

        if (lbProdutoSelecionado.getText().equals("...")) {
            alertIncluirProduto.setContentText("Selecione um produto antes de incluir a venda");
            alertIncluirProduto.show();
            tfProduto.requestFocus();
        } else {
            // Verificar o Produto no Banco de Dados e capturar informações de quantidade...
            VendaProduto vendaProduto = new VendaProduto();

            /*Codigo*/
            vendaProduto.setCodCliente(Integer.valueOf(tfCliente.getText()));
            vendaProduto.setCodProduto(Integer.valueOf(tfProduto.getText()));

            /*Descrição*/
            vendaProduto.setDescricao(lbProdutoSelecionado.getText());

            /*Quantidade, vai comparecer uma janela*/
            TextInputDialog tiQuantidade = new TextInputDialog("1");
            tiQuantidade.setHeaderText("Informe a quantidade desejada de venda ...");
            tiQuantidade.showAndWait();
            vendaProduto.setQuantidade(Integer.valueOf(tiQuantidade.getEditor().getText()));

            SQLite db = new SQLite();
            Produto produtoSelecionado = db.getProdutoForCodigo(Integer.valueOf(tfProduto.getText()));

            /*Valor Unitário*/
            vendaProduto.setUnitario(Double.valueOf(produtoSelecionado.getPreco()));

            /*Total*/
            vendaProduto.setTotal(vendaProduto.calcularTotal());

            /*Atribuindo os valores as celulas na TableView do layoutVenda.fxml*/
            /*Esse codigo vai imprimir os valores na TableView*/
            tcCodigoCliente.setCellValueFactory(new PropertyValueFactory<VendaProduto, Integer>("codCliente"));
            tcCodigoProduto.setCellValueFactory(new PropertyValueFactory<VendaProduto, Integer>("codProduto"));
            tcDescricao.setCellValueFactory(new PropertyValueFactory<VendaProduto, String>("descricao"));
            tcQuantidade.setCellValueFactory(new PropertyValueFactory<VendaProduto, Integer>("quantidade"));
            tcUnitario.setCellValueFactory(new PropertyValueFactory<VendaProduto, Double>("unitario"));
            tcTotal.setCellValueFactory(new PropertyValueFactory<VendaProduto, Double>("total"));

            /*Atribuindo o object de Venda de Produto ao TableView*/
            twprodutos.getItems().add(vendaProduto);
            twprodutos.refresh();

            /*Limpando campos após a inclusão na lista*/
            lbProdutoSelecionado.setText("...");
            tfProduto.clear();
            tfProduto.requestFocus();
        }
    }


    public void clickRemoverProduto(Event e) {
        /*Remover Itens da Lista*/
        twprodutos.getItems().remove(twprodutos.getSelectionModel().getSelectedItem());
        twprodutos.refresh();
    }


    public void clickGravarVenda(Event e) throws SQLException, ClassNotFoundException {
        ObservableList list = twprodutos.getItems();

        VendaProduto vendaproduto = new VendaProduto();
        vendaproduto.setCodCliente(((VendaProduto) list.get(0)).getCodCliente());
        vendaproduto.setCodProduto(((VendaProduto) list.get(0)).getCodProduto());
        vendaproduto.setDescricao(((VendaProduto) list.get(0)).getDescricao());
        vendaproduto.setQuantidade((((VendaProduto) list.get(0)).getQuantidade()));
        vendaproduto.setUnitario((((VendaProduto) list.get(0)).getUnitario()));
        vendaproduto.setTotal((((VendaProduto) list.get(0)).getTotal()));

        SQLite dbvenda = new SQLite();
        dbvenda.insertVendas(vendaproduto);

        twprodutos.getItems().clear();
        lbClienteSelecionado.setText("...");
        tfCliente.clear();
        tfCliente.requestFocus();

        Alert alertGravar = new Alert(Alert.AlertType.INFORMATION, "Salvo com sucesso");
        alertGravar.show();
    }


    public void clickCancelarVenda(Event e) {
        twprodutos.getItems().clear();
        tfCliente.clear();
        tfProduto.clear();
        tfCliente.clear();
        lbProdutoSelecionado.setText("...");
        lbClienteSelecionado.setText("...");
        tfCliente.requestFocus();
    }
}