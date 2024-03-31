package br.com.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Compra {

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("quantidade")
    private int quantidade;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "codigo='" + codigo + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
