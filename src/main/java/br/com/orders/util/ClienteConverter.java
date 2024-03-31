package br.com.orders.util;

import br.com.orders.controller.response.ClienteResponse;
import br.com.orders.controller.response.ProdutoResponse;
import br.com.orders.controller.response.RecomendacaoResponse;
import br.com.orders.model.Cliente;
import br.com.orders.model.Compra;
import br.com.orders.model.Produto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ClienteConverter {

    private static final Logger logger = LogManager.getLogger(ClienteConverter.class);

    private ClienteConverter() {
    }

    public static List<ClienteResponse> convertToClienteResponse(List<Cliente> clientesResult, Map<Integer, Produto> produtosResult) {
        logger.info("Inicio, execução ClienteConverter convertToClienteResponse");
        List<ClienteResponse> clientes = new ArrayList<>();
        clientesResult.forEach(itCliente -> {
            ClienteResponse clienteResponse = new ClienteResponse();
            clienteResponse.setNome(itCliente.getNome());
            clienteResponse.setCpf(itCliente.getCpf());
            clienteResponse.setQuantidadeTotalCompras(itCliente.getCompras().size());
            itCliente.getCompras().forEach(itCompra -> {
                Produto produto = produtosResult.get(Integer.parseInt(itCompra.getCodigo()));
                if (produto != null) {
                    clienteResponse.setValorTotalCompras(clienteResponse.getValorTotalCompras().add(produto.getPreco().multiply(BigDecimal.valueOf(itCompra.getQuantidade()))));
                }
            });
            clientes.add(clienteResponse);
        });
        logger.info("Fim, execução ClienteConverter convertToClienteResponse");
        return clientes.stream()
                .sorted(Comparator.comparingInt(ClienteResponse::getQuantidadeTotalCompras)
                        .thenComparingDouble(clienteResponse -> clienteResponse.getValorTotalCompras().doubleValue()).reversed())
                .limit(3)
                .toList();
    }

    public static RecomendacaoResponse convertToClienteRecomendacaoResponse(Cliente clienteResult, Map<Integer, Produto> produtosResult) {
        logger.info("Inicio, execução ClienteConverter convertToClienteRecomendacaoResponse");
        Compra compra = clienteResult.getCompras()
                .stream()
                .max(Comparator.comparingInt(Compra::getQuantidade))
                .orElse(null);

        if (compra == null) {
            logger.warn("Execução ClienteConverter convertToClienteRecomendacaoResponse, compra não existe");
            return new RecomendacaoResponse();
        }

        Produto produto = produtosResult.get(Integer.parseInt(compra.getCodigo()));
        logger.info("Fim, execução ClienteConverter convertToClienteRecomendacaoResponse");
        return getRecomendacaoResponse(clienteResult, produto, compra);

    }

    private static RecomendacaoResponse getRecomendacaoResponse(Cliente clienteResult, Produto produto, Compra compra) {
        logger.info("Inicio, execução ClienteConverter getRecomendacaoResponse");
        ProdutoResponse produtoResponse = new ProdutoResponse();
        if(produto != null){
            produtoResponse.setTipoVinho(produto.getTipoVinho());
            produtoResponse.setSafra(produto.getSafra());
            produtoResponse.setPreco(produto.getPreco());
            produtoResponse.setCodigo(produto.getCodigo());
            produtoResponse.setAnoCompra(produto.getAnoCompra());
        }

        RecomendacaoResponse clienteRecomendacao = new RecomendacaoResponse();
        clienteRecomendacao.setNome(clienteResult.getNome());
        clienteRecomendacao.setCpf(clienteResult.getCpf());
        clienteRecomendacao.setQuantidadeComprada(compra.getQuantidade());
        clienteRecomendacao.setProduto(produtoResponse);
        logger.info("Fim, execução ClienteConverter getRecomendacaoResponse");
        return clienteRecomendacao;
    }

}