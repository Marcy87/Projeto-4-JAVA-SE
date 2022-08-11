package Controller;

import Scene.Clientes;
import db.SQLite;
import entities.Cliente;
import entities.Estado;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ClientesController {

    @FXML
    TextField tfNomeCliente;

    @FXML
    TextField tfCidadeCliente;

    @FXML
    ComboBox cbEstadoCliente;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        SQLite db = new SQLite();
        for (Estado cadEstado : db.getEstados()){
            cbEstadoCliente.getItems().add(cadEstado.getUf());
        }
        //cbEstadoCliente.getItems().addAll("PR", "RJ", "SP");
    }

    @FXML
    public void clickCadastrarCliente(Event e) throws Exception {
        Clientes clientes = new Clientes();
        clientes.start(new Stage());
    }


    public void clickSalvarCliente(Event e) throws SQLException, ClassNotFoundException {
        Alert alertSalvarCliente = new Alert(Alert.AlertType.INFORMATION, "");

        if (tfNomeCliente.getText().isEmpty()){
            alertSalvarCliente.setContentText("Nome do Cliente não informado");
        }else{
            if(tfCidadeCliente.getText().isEmpty()){
                alertSalvarCliente.setContentText("Cidade do cliente não informada");
            }else{
                if (cbEstadoCliente.getSelectionModel().getSelectedItem() == null){
                    alertSalvarCliente.setContentText("Estado do cliente não informado");
                }else{
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome(tfNomeCliente.getText());
                    novoCliente.setCidade(tfCidadeCliente.getText());
                    novoCliente.setEstado(cbEstadoCliente.getValue().toString());

                    SQLite db = new SQLite();
                    db.insertCliente(novoCliente);

                    alertSalvarCliente.setContentText("Cliente salvo com sucesso");
                    limparCampos();

                }
            }
        }

        alertSalvarCliente.show();


    }

    public void clickCancelarCliente(Event e){
        limparCampos();
    }

    public void limparCampos(){
        tfNomeCliente.setText("");
        tfCidadeCliente.setText("");
    }




}
