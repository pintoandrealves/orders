package br.com.orders.util;

import br.com.orders.controller.response.CompraResponse;
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

public class CompraConverter {

    private static final Logger logger = LogManager.getLogger(ClienteConverter.class);

    private CompraConverter() {
    }

    public static List<CompraResponse> convertToCompraResponse(List<Cliente> clientesResult, Map<Integer, Produto> produtosResult) {
        logger.info("Inicio, execução CompraConverter convertToCompraResponse");
        List<CompraResponse> compraResponse = new ArrayList<>();
        clientesResult.stream()
                .flatMap(cliente -> cliente.getCompras().stream()
                        .map(compra ->
                                createCompraResponse(cliente, compra, produtosResult))
                )
                .forEach(compraResponse::add);
        compraResponse.sort(Comparator.comparing(CompraResponse::getValorCompra));
        logger.info("Fim, execução CompraConverter convertToCompraResponse");
        return compraResponse;
    }

    private static CompraResponse createCompraResponse(Cliente cliente, Compra compra, Map<Integer, Produto> produtosResult) {
        logger.info("Inicio, execução CompraResponse createCompraResponse");
        Produto produtoIdentificado = produtosResult.getOrDefault(Integer.parseInt(compra.getCodigo()), null);
        CompraResponse compraResponse = new CompraResponse();
        if (produtoIdentificado != null) {
            compraResponse.setAnoCompra(produtoIdentificado.getAnoCompra());
            compraResponse.setCodigo(produtoIdentificado.getCodigo());
            compraResponse.setPreco(produtoIdentificado.getPreco());
            compraResponse.setSafra(produtoIdentificado.getSafra());
            compraResponse.setTipoVinho(produtoIdentificado.getTipoVinho());
            compraResponse.setValorCompra(produtoIdentificado.getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade())));
            compraResponse.setQuantidade(compra.getQuantidade());
            compraResponse.setNome(cliente.getNome());
            compraResponse.setCpf(cliente.getCpf());
        }
        logger.info("Fim, execução CompraResponse createCompraResponse");
        return compraResponse;
    }

}
