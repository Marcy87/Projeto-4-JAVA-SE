package entities;

public class VendaProduto {

    //Atributos
    public Integer codCliente;
    public Integer codProduto;
    public String  descricao;
    public Integer quantidade;
    public Double  unitario;
    public Double  total;

    //Métodos get e set

    //Codigo Cliente
    public Integer getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }


    //Codigo Produto
    public Integer getCodProduto() {
        return this.codProduto;
    }

    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }


    //Descrição
    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    //Quantidade
    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    //Unitário
    public Double getUnitario() {
        return this.unitario;
    }

    public void setUnitario(Double unitario) {
        this.unitario = unitario;
    }


    //Total
    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }


    //Calcular Total
    public Double calcularTotal(){
        return this.quantidade * this.unitario;
    }
}