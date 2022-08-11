package entities;

public class Produto {

    //Atributos
    private Integer codigo;
    private String descricao;
    private String custo;
    private String preco;

    //Método get e set

    //Codigo
    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    //Descrição
    public String getDescricao() {
        return this.descricao.toUpperCase();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase();
    }


    //Custo
    public String getCusto() {
        return this.custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }


    //Preço
    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}