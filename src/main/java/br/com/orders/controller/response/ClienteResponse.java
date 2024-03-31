package br.com.orders.controller.response;

import java.math.BigDecimal;

public class ClienteResponse {
    private String nome;
    private String cpf;
    private int quantidadeTotalCompras;
    private BigDecimal valorTotalCompras = BigDecimal.ZERO;

    public int getQuantidadeTotalCompras() {
        return quantidadeTotalCompras;
    }

    public void setQuantidadeTotalCompras(int quantidadeTotalCompras) {
        this.quantidadeTotalCompras = quantidadeTotalCompras;
    }

    public BigDecimal getValorTotalCompras() {
        return valorTotalCompras;
    }

    public void setValorTotalCompras(BigDecimal valorTotalCompras) {
        this.valorTotalCompras = valorTotalCompras;
    }

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

    @Override
    public String toString() {
        return "ClienteResponse{" +
                ", nome=" + nome +
                ", cpf=" + cpf +
                ", quantidadeTotalCompras=" + quantidadeTotalCompras +
                ", valorTotalCompras=" + valorTotalCompras +
                '}';
    }
}
