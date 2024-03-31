package br.com.orders.controller;

import br.com.orders.api.MockyApiClient;
import br.com.orders.controller.response.CompraResponse;
import br.com.orders.model.Cliente;
import br.com.orders.model.Produto;
import br.com.orders.service.CompraService;
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
@WebMvcTest(value = CompraController.class)
class CompraControllerTest {

    private static final String URL_COMPRAS = "/compras";
    private static final String URL_MAIOR_COMPRA = "/maior-compra/{ano}";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CompraService compraService;
    @MockBean
    private MockyApiClient mockyApiClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetMaiorCompra() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        CompraResponse compraResponse = BuildMocks.buildCompraResponse();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.compraService.getMaiorCompra(BuildMocks.ano)).thenReturn(compraResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_MAIOR_COMPRA, BuildMocks.ano)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(compraResponse)));

        verify(this.compraService, times(1)).getMaiorCompra(anyInt());
    }

    @Test
    void testGetMaiorCompraNotFound() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.compraService.getMaiorCompra(BuildMocks.ano)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_MAIOR_COMPRA, BuildMocks.ano)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        verify(this.compraService, times(1)).getMaiorCompra(anyInt());
    }

    @Test
    void testGetCompras() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        List<CompraResponse> comprasResponse = List.of(BuildMocks.buildCompraResponse());

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.compraService.getCompras()).thenReturn(comprasResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_COMPRAS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(comprasResponse)));

        verify(this.compraService, times(1)).getCompras();
    }

    @Test
    void testGetComprasNotFound() throws Exception {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        List<CompraResponse> comprasResponse = List.of();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        when(this.compraService.getCompras()).thenReturn(comprasResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(URL_COMPRAS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        verify(this.compraService, times(1)).getCompras();
    }

}
