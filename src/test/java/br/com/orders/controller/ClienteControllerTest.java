package br.com.orders.controller;

import br.com.orders.api.MockyApiClient;
import br.com.orders.controller.response.ClienteResponse;
import br.com.orders.controller.response.RecomendacaoResponse;
import br.com.orders.model.Cliente;
import br.com.orders.model.Produto;
import br.com.orders.service.ClienteService;
import br.com.orders.util.BuildMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = ClienteController.class)
class ClienteControllerTest {

    private static final String URL_CLIENTES_FIEIS = "/clientes-fieis";
    private static final String URL_RECOMENDACAO = "/recomendacao/{cpf}/tipo";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private MockyApiClient mockyApiClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetRecomendacao() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        RecomendacaoResponse recomendacaoResponse = BuildMocks.buildRecomendacaoResponse();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.clienteService.getRecomendacoes(BuildMocks.cpf)).thenReturn(recomendacaoResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_RECOMENDACAO, BuildMocks.cpf)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recomendacaoResponse)));

        verify(this.clienteService, times(1)).getRecomendacoes(anyString());
    }

    @Test
    void testGetRecomendacaoNotFound() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.clienteService.getRecomendacoes(BuildMocks.cpf)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_RECOMENDACAO, BuildMocks.cpf)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        verify(this.clienteService, times(1)).getRecomendacoes(anyString());
    }

    @Test
    void testGetClientesFieis() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        List<ClienteResponse> clientesResponse = BuildMocks.buildClientesResponse();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.clienteService.getClientesFieis()).thenReturn(clientesResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_CLIENTES_FIEIS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(clientesResponse)));

        verify(this.clienteService, times(1)).getClientesFieis();
    }

    @Test
    void testGetClientesFieisNotFound() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        List<ClienteResponse> clientesResponse = List.of();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.clienteService.getClientesFieis()).thenReturn(clientesResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_CLIENTES_FIEIS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        verify(this.clienteService, times(1)).getClientesFieis();
    }

}
