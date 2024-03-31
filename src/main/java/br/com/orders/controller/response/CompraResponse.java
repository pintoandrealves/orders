package br.com.orders.controller.response;

import java.math.BigDecimal;

public class CompraResponse {
    private String nome;
    private String cpf;
    private int codigo;
    private String tipoVinho;
    private BigDecimal preco;
    private String safra;
    private int anoCompra;
    private int quantidade;
    private BigDecimal valorCompra = BigDecimal.ZERO;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipoVinho() {
        return tipoVinho;
    }

    public void setTipoVinho(String tipoVinho) {
        this.tipoVinho = tipoVinho;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }

    public int getAnoCompra() {
        return anoCompra;
    }

    public void setAnoCompra(int anoCompra) {
        this.anoCompra = anoCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    @Override
    public String toString() {
        return "CompraResponse{" +
                "codigo='" + codigo + '\'' +
                ", quantidade=" + quantidade +
                ", valorCompra=" + valorCompra +
                ", nome=" + nome +
                ", cpf=" + cpf +
                ", tipoVinho=" + tipoVinho +
                ", preco=" + preco +
                ", safra=" + safra +
                ", anoCompra=" + anoCompra +
                '}';
    }
}
