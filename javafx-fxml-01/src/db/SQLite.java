package db;

import entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite {
    /*
     * Responsável pela comunicação entre o Banco e Aplicação
     * */
    private Connection conn;
    private Statement stm;


    public SQLite() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
        this.stm = this.conn.createStatement();
    }


    public boolean checkLogin(Usuario usuario){
        List<Usuario> listUsuarioLogin = new ArrayList<>();
        ResultSet resultUsuarioLogin;

        try{
            resultUsuarioLogin = this.stm.executeQuery("select * from usuario " +
                                                       "where email = '"+usuario.getEmail()+"'  and senha = '"+usuario.getSenha()+"'");

            while(resultUsuarioLogin.next()){
                resultUsuarioLogin.close();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean checkEmail(Usuario usuario){
        List<Usuario> listUsuarioLogin = new ArrayList<>();
        ResultSet resultUsuarioLogin;

        try{
            resultUsuarioLogin = this.stm.executeQuery("select * from usuario " +
                                                       "where email = '"+usuario.getEmail()+"'");

            while(resultUsuarioLogin.next()){
                resultUsuarioLogin.close();
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public List<Estado> getEstados(){
        List<Estado> listEstado = new ArrayList<>();
        ResultSet rsEstado;

        try{
            rsEstado = this.stm.executeQuery("select codigouf, nome, uf from estado order by uf");

            while(rsEstado.next()){
                Estado novoEstado = new Estado();
                novoEstado.setCodigoUf(rsEstado.getInt("codigouf"));
                novoEstado.setNome(rsEstado.getString("nome"));
                novoEstado.setUf(rsEstado.getString("uf"));
                listEstado.add(novoEstado);
            }

            rsEstado.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return listEstado;
    }


    //USUÁRIO

    public void insertUsuario(Usuario cadastro){
        try {
            this.stm = this.conn.createStatement();
            String sqlInsertUsuario = "insert into usuario (nome, email, senha) " +
                    "values ('"+cadastro.getNome()+"', '"+cadastro.getEmail()+"', '"+cadastro.getSenha()+"')";
            this.stm.executeUpdate(sqlInsertUsuario);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public List<Usuario> getAllUsuarios(){
        List<Usuario> listUsuario = new ArrayList<>();
        ResultSet rsUsuario;

        try{
            String sql = "select nome, email from usuario order by nome";
            rsUsuario = this.stm.executeQuery(sql);

            while(rsUsuario.next()) {
                Usuario usuarioCadastrado = new Usuario();
                usuarioCadastrado.setNome(rsUsuario.getString("nome"));
                usuarioCadastrado.setEmail(rsUsuario.getString("email"));
                listUsuario.add(usuarioCadastrado);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listUsuario;
    }


    public void excluirUsuario(Usuario usuario){
        try {
            this.stm = this.conn.createStatement();
            String exclusao = "delete from usuario where email = '"+usuario.getEmail()+"'";
            this.stm.executeUpdate(exclusao);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void atualizarUsuario(Usuario usuario, String emailAntigo){
        try {
            this.stm = this.conn.createStatement();
            String update = "update usuario " +
                    "        set nome  = '"+usuario.getNome()+"', " +
                    "            email = '"+usuario.getEmail()+"' " +
                    "        where email = '"+emailAntigo+"'";
            this.stm.executeUpdate(update);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    //CLIENTE

    public void insertCliente(Cliente cliente){
        try {
            this.stm = this.conn.createStatement();
            String sqlInsertCliente = "insert into cliente " +
                    "(nome, cidade, estado) " +
                    "values ('"+cliente.getNome()+"', '"+cliente.getCidade()+"', '"+cliente.getEstado()+"')";
            this.stm.executeUpdate(sqlInsertCliente);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public List<Cliente> getAllClientes(){
        List<Cliente> listCliente = new ArrayList<>();
        ResultSet rsCliente;

        try{
            String sql = "select nome, cidade, estado from cliente order by nome";
            rsCliente = this.stm.executeQuery(sql);

            while(rsCliente.next()){
                Cliente clienteCadastrado = new Cliente();
                clienteCadastrado.setNome(rsCliente.getString("nome"));
                clienteCadastrado.setCidade(rsCliente.getString("cidade"));
                clienteCadastrado.setEstado(rsCliente.getString("estado"));
                listCliente.add(clienteCadastrado);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listCliente;
    }

    public void excluirCliente(Cliente cliente){
        try {
            this.stm = this.conn.createStatement();
            String exclusao = "delete from cliente where nome = '"+cliente.getNome()+"'";
            this.stm.executeUpdate(exclusao);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void atualizarCliente(Cliente cliente, String nomeAntigo){
        try {
            this.stm = this.conn.createStatement();
            String update = "update cliente " +
                    "        set nome  = '"+cliente.getNome()+"', " +
                    "            cidade = '"+cliente.getCidade()+"', " +
                    "            estado = '"+cliente.getEstado()+"'" +
                    "        where nome = '"+nomeAntigo+"'";
            this.stm.executeUpdate(update);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public Cliente getClienteforCodigo(Integer codigo){
        Cliente clienteCadastrado = new Cliente();
        ResultSet rsCliente;

        try{
            String sql="select codigo, nome, cidade, estado from cliente where codigo = "+codigo+"";
            rsCliente = this.stm.executeQuery(sql);

            while(rsCliente.next()){
                clienteCadastrado.setNome(rsCliente.getString("nome"));
                clienteCadastrado.setEstado(rsCliente.getString("estado"));
                clienteCadastrado.setCidade(rsCliente.getString("cidade"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clienteCadastrado;
    }


    //PRODUTO

    public void insertProduto(Produto produto){
        try {
            this.stm = this.conn.createStatement();
            String sqlInsertProduto = "insert into produto " +
                    "(descricao, custo, preco) " +
                    "values " +
                    "('"+produto.getDescricao()+"', '"+produto.getCusto()+"', '"+produto.getPreco()+"')";
            this.stm.executeUpdate(sqlInsertProduto);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public List<Produto> getAllProdutos(){
        List<Produto> listProduto = new ArrayList<>();
        ResultSet rsProduto;

        try{
            String sql = "select descricao, custo, preco from Produto order by descricao";
            rsProduto = this.stm.executeQuery(sql);

            while(rsProduto.next()){
                Produto produtoCadastrado = new Produto();
                produtoCadastrado.setDescricao(rsProduto.getString("descricao"));
                produtoCadastrado.setCusto(rsProduto.getString("custo"));
                produtoCadastrado.setPreco(rsProduto.getString("preco"));
                listProduto.add(produtoCadastrado);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listProduto;
    }


    public void excluirProduto(Produto produto){
        try {
            this.stm = this.conn.createStatement();
            String exclusao = "delete from Produto where descricao = '"+produto.getDescricao()+"'";
            this.stm.executeUpdate(exclusao);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizarProduto(Produto produto, String descricaoAntigo){
        try {
            this.stm = this.conn.createStatement();
            String update = "update Produto " +
                    "        set descricao  = '"+produto.getDescricao()+"', " +
                    "            custo = '"+produto.getCusto()+"', " +
                    "            preco = '"+produto.getPreco()+"' " +
                    "        where descricao = '"+descricaoAntigo+"'";
            this.stm.executeUpdate(update);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public Produto getProdutoForCodigo(Integer cod){
        Produto produtoCadastrado = new Produto();
        ResultSet rsProduto;

        try{
            String sql = "select codigo, descricao, custo, preco from produto where codigo = "+cod+"";
            rsProduto = this.stm.executeQuery(sql);

            while(rsProduto.next()){
                produtoCadastrado.setDescricao(rsProduto.getString("descricao"));
                produtoCadastrado.setCusto(String.valueOf(rsProduto.getDouble("custo")));
                produtoCadastrado.setPreco(String.valueOf(rsProduto.getDouble("preco")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produtoCadastrado;
    }


    //VENDAS

    public void insertVendas(VendaProduto venda){
        try {
            this.stm = this.conn.createStatement();
            String sqlInsertVenda = "insert into vendas " +
                    "(codCliente, codProduto, descricao, qtd, unitario, total) " +
                    "values " +
                    "('"+venda.getCodCliente()+"','"+venda.getCodProduto()+"', '"+venda.getDescricao()+"', '"+venda.getQuantidade()+"', '"+venda.getUnitario()+"', '"+venda.getTotal()+"')";
            this.stm.executeUpdate(sqlInsertVenda);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}