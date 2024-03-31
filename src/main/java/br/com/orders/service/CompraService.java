package br.com.orders.service;

import br.com.orders.api.MockyApiClient;
import br.com.orders.controller.response.CompraResponse;
import br.com.orders.model.Cliente;
import br.com.orders.model.Produto;
import br.com.orders.util.CompraConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private static final Logger logger = LogManager.getLogger(CompraService.class);
    @Autowired
    private MockyApiClient mockyApiClient;

    public List<CompraResponse> getCompras() {
        logger.info("Inicio, execução CompraService getCompras");
        List<Cliente> clientesResult = mockyApiClient.getClientes();
        Map<Integer, Produto> produtosResult = mockyApiClient.getProdutos()
                .stream()
                .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));
        logger.info("Fim, execução CompraService getCompras");
        return CompraConverter.convertToCompraResponse(clientesResult, produtosResult);
    }

    public CompraResponse getMaiorCompra(int ano) {
        logger.info("Inicio, execução CompraService getMaiorCompra, ano:{}", ano);
        List<Cliente> clientesResult = mockyApiClient.getClientes();
        Map<Integer, Produto> produtosResult = mockyApiClient.getProdutos()
                .stream()
                .filter(produto -> produto.getAnoCompra() == ano)
                .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));
        List<CompraResponse> compras = CompraConverter.convertToCompraResponse(clientesResult, produtosResult);
        logger.info("Fim, execução CompraService getMaiorCompra, ano:{}", ano);
        return compras.get(compras.size() - 1);
    }

}
