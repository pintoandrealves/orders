package br.com.orders.controller.response;

import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public class ProdutoResponse {
    private int codigo;
    private String tipoVinho;
    private BigDecimal preco;
    private String safra;
    private int anoCompra;

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

    @Override
    public String toString() {
        return "ProdutoResponse{" +
                "codigo=" + codigo +
                ", tipoVinho='" + tipoVinho + '\'' +
                ", preco=" + preco +
                ", safra='" + safra + '\'' +
                ", ano_compra=" + anoCompra +
                '}';
    }
}
