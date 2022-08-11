package entities;

public class Cliente {

    //Atributos
    private Integer codigo;
    private String nome;
    private String cidade;
    private String estado;

    //Metodo get e set

    //Codigo
    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    //Nome
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }


    //Cidade
    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade.toUpperCase();
    }


    //Estado
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}