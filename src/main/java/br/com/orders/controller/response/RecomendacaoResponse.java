package br.com.orders.controller.response;

public class RecomendacaoResponse {
    private String nome;
    private String cpf;
    private int quantidadeComprada;
    private ProdutoResponse produto;

    public ProdutoResponse getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResponse produto) {
        this.produto = produto;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
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
        return "ClienteRecomendacaoResponse{" +
                ", nome=" + nome +
                ", cpf=" + cpf +
                ", quantidadeComprada=" + quantidadeComprada +
                ", produto=" + produto +
                '}';
    }
}
