package br.com.orders.util;

import br.com.orders.controller.response.ClienteResponse;
import br.com.orders.controller.response.CompraResponse;
import br.com.orders.controller.response.ProdutoResponse;
import br.com.orders.controller.response.RecomendacaoResponse;
import br.com.orders.model.Cliente;
import br.com.orders.model.Compra;
import br.com.orders.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BuildMocks {

    public static String cpf = "32045874585";
    public static int ano = 2016;

    public static List<Produto> buildProdutos() {

        Produto produtoTinto = new Produto();
        produtoTinto.setCodigo(1);
        produtoTinto.setTipoVinho("Tinto");
        produtoTinto.setPreco(new BigDecimal("50.00"));
        produtoTinto.setSafra("2013");
        produtoTinto.setAnoCompra(2016);

        Produto produtoSuave = new Produto();
        produtoSuave.setCodigo(2);
        produtoSuave.setTipoVinho("Suave");
        produtoSuave.setPreco(new BigDecimal("100.00"));
        produtoSuave.setSafra("2005");
        produtoSuave.setAnoCompra(2021);

        return List.of(produtoSuave, produtoTinto);
    }

    public static List<Cliente> buildClientes() {
        Cliente cliente = new Cliente();
        cliente.setNome("João da Silva");
        cliente.setCpf("32045874585");

        List<Compra> compras = new ArrayList<>();
        Compra compra1 = new Compra();
        compra1.setCodigo("1");
        compra1.setQuantidade(8);
        compras.add(compra1);

        Compra compra2 = new Compra();
        compra2.setCodigo("2");
        compra2.setQuantidade(6);
        compras.add(compra2);

        cliente.setCompras(compras);

        return List.of(cliente);
    }

    public static CompraResponse buildCompraResponse() {
        CompraResponse compraResponse = new CompraResponse();
        compraResponse.setNome("João da Silva");
        compraResponse.setCpf("12345678900");
        compraResponse.setCodigo(1);
        compraResponse.setTipoVinho("Tinto");
        compraResponse.setPreco(new BigDecimal("50.00"));
        compraResponse.setSafra("2020");
        compraResponse.setAnoCompra(2021);
        compraResponse.setQuantidade(2);
        compraResponse.setValorCompra(new BigDecimal("100.00"));

        return compraResponse;
    }

    public static List<CompraResponse> buildComprasResponse() {
        CompraResponse compraResponse = new CompraResponse();
        compraResponse.setNome("João da Silva");
        compraResponse.setCpf("32045874585");
        compraResponse.setCodigo(1);
        compraResponse.setTipoVinho("Tinto");
        compraResponse.setPreco(new BigDecimal("100.00"));
        compraResponse.setSafra("2020");
        compraResponse.setAnoCompra(2021);
        compraResponse.setQuantidade(2);
        compraResponse.setValorCompra(new BigDecimal("200.00"));

        return List.of(compraResponse);
    }

    public static List<ClienteResponse> buildClientesResponse() {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setCpf("32045874585");
        clienteResponse.setQuantidadeTotalCompras(2);
        clienteResponse.setValorTotalCompras(new BigDecimal("700.00"));
        clienteResponse.setNome("João da Silva");
        return List.of(clienteResponse);
    }

    public static RecomendacaoResponse buildRecomendacaoResponse() {
        ProdutoResponse produto = new ProdutoResponse();
        produto.setCodigo(1);
        produto.setTipoVinho("Tinto");
        produto.setPreco(new BigDecimal("50.00"));
        produto.setSafra("2013");
        produto.setAnoCompra(2016);

        RecomendacaoResponse recomendacao = new RecomendacaoResponse();
        recomendacao.setNome("João da Silva");
        recomendacao.setCpf("32045874585");
        recomendacao.setQuantidadeComprada(2);
        recomendacao.setProduto(produto);

        return recomendacao;
    }
}