package Controller;

import db.SQLite;
import entities.Cliente;
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

public class ClientesManutencaoController implements Initializable {

    @FXML
    private TableView<Cliente> twcliente;

    @FXML
    private TableColumn<Cliente, String> nomecol;

    @FXML
    private TableColumn<Cliente, String> cidadecol;

    @FXML
    private  TableColumn<Cliente, String> estadocol;

    @FXML
    private TextField tfnome;

    @FXML
    private TextField tfcidade;

    @FXML
    private TextField tfestado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Atribuindo os valores as celulas na TableView do layoutClienteManutecao.fxml*/
        /*Esse codigo vai imprimir os valores na TableView*/
        nomecol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cidadecol.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        estadocol.setCellValueFactory(new PropertyValueFactory<>("estado"));

        try{
            twcliente.setItems(listTodosClientes());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private ObservableList<Cliente> listTodosClientes() throws SQLException, ClassNotFoundException {

        SQLite dbclientes = new SQLite();

        return FXCollections.observableArrayList(dbclientes.getAllClientes());
    }


    public void atualizarTextField(Event e){
        ObservableList clienteselecionado = twcliente.getSelectionModel().getSelectedItems();

        Cliente clienteAtualizar = new Cliente();
        clienteAtualizar.setNome(((Cliente) clienteselecionado.get(0)).getNome());
        clienteAtualizar.setCidade(((Cliente) clienteselecionado.get(0)).getCidade());
        clienteAtualizar.setEstado(((Cliente) clienteselecionado.get(0)).getEstado());

        tfnome.setText(clienteAtualizar.getNome());
        tfcidade.setText(clienteAtualizar.getCidade());
        tfestado.setText(clienteAtualizar.getEstado());
    }


    public void excluirCliente(Event e){
        ObservableList clienteselecionado = twcliente.getSelectionModel().getSelectedItems();

        Cliente clienteEliminar = new Cliente();
        clienteEliminar.setNome(((Cliente) clienteselecionado.get(0)).getNome());
        clienteEliminar.setCidade(((Cliente) clienteselecionado.get(0)).getCidade());
        clienteEliminar.setEstado(((Cliente) clienteselecionado.get(0)).getEstado());

        try{
            SQLite dbExclusaoCliente = new SQLite();
            dbExclusaoCliente.excluirCliente(clienteEliminar);

            twcliente.getItems().clear();
            twcliente.setItems(listTodosClientes());

            Alert alertEliminacao = new Alert(Alert.AlertType.INFORMATION, "Cliente eliminado com sucesso");
            alertEliminacao.show();

        }catch (SQLException | ClassNotFoundException erro){
            erro.printStackTrace();
        }

        tfnome.clear();
        tfcidade.clear();
        tfestado.clear();
    }


    public void atualizarCliente(Event e){
        ObservableList clienteListAntigo = twcliente.getSelectionModel().getSelectedItems();

        Cliente clienteAntigo = new Cliente();
        clienteAntigo.setNome(((Cliente) clienteListAntigo.get(0)).getNome());

        String nome = tfnome.getText();
        String cidade = tfcidade.getText();
        String estado = tfestado.getText();

        Cliente clienteAtualizar = new Cliente();
        clienteAtualizar.setNome(nome);
        clienteAtualizar.setCidade(cidade);
        clienteAtualizar.setEstado(estado);

        try{
            SQLite dbAtualizarCliente = new SQLite();
            dbAtualizarCliente.atualizarCliente(clienteAtualizar, clienteAntigo.getNome());

            twcliente.getItems().clear();
            twcliente.setItems(listTodosClientes());

            Alert alertAtualizacao = new Alert(Alert.AlertType.INFORMATION, "Cliente atualizado com sucesso");
            alertAtualizacao.show();

        }catch (Exception erro){
            erro.printStackTrace();
        }

        tfnome.clear();
        tfcidade.clear();
        tfestado.clear();
    }

}