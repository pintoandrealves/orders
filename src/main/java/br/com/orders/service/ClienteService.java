package br.com.orders.service;

import br.com.orders.api.MockyApiClient;
import br.com.orders.controller.ClienteController;
import br.com.orders.controller.response.ClienteResponse;
import br.com.orders.controller.response.RecomendacaoResponse;
import br.com.orders.model.Cliente;
import br.com.orders.model.Produto;
import br.com.orders.util.ClienteConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private static final Logger logger = LogManager.getLogger(ClienteController.class);
    @Autowired
    private MockyApiClient mockyApiClient;

    public List<ClienteResponse> getClientesFieis() {
        logger.info("Inicio, execução ClienteService getClientesFieis");
        List<Cliente> clientesResult = mockyApiClient.getClientes();
        Map<Integer, Produto> produtosResult = mockyApiClient.getProdutos()
                .stream()
                .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));

        logger.info("Fim, execução ClienteService getClientesFieis");
        return ClienteConverter.convertToClienteResponse(clientesResult, produtosResult);
    }

    public RecomendacaoResponse getRecomendacoes(String cpf) {
        logger.info("Inicio, ClienteService getRecomendacoes cpf:{}", cpf);
        Cliente clienteResult = mockyApiClient.getClientes()
                .stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (clienteResult == null) {
            return new RecomendacaoResponse();
        }

        Map<Integer, Produto> produtosResult = mockyApiClient.getProdutos()
                .stream()
                .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));

        logger.info("Fim, execução ClienteService getRecomendacoes cpf:{}", cpf);
        return ClienteConverter.convertToClienteRecomendacaoResponse(clienteResult, produtosResult);
    }

}
