package br.com.orders.service;

import br.com.orders.api.MockyApiClient;
import br.com.orders.controller.response.ClienteResponse;
import br.com.orders.controller.response.RecomendacaoResponse;
import br.com.orders.model.Cliente;
import br.com.orders.model.Produto;
import br.com.orders.util.BuildMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private MockyApiClient mockyApiClient;

    @Test
    void testGetClientesFieis() {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        List<ClienteResponse> response = BuildMocks.buildClientesResponse();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        List<ClienteResponse> expected = this.clienteService.getClientesFieis();

        assertEquals(expected.get(0).getCpf(), response.get(0).getCpf());
        assertEquals(expected.get(0).getNome(), response.get(0).getNome());
        assertEquals(expected.get(0).getQuantidadeTotalCompras(), response.get(0).getQuantidadeTotalCompras());

        verify(this.mockyApiClient, times(1)).getProdutos();
        verify(this.mockyApiClient, times(1)).getClientes();
    }

    @Test
    void testGetClientesNullValues() {
        List<Cliente> clientes = List.of();
        List<Produto> produtos = BuildMocks.buildProdutos();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        List<ClienteResponse> expected = this.clienteService.getClientesFieis();

        verify(this.mockyApiClient, times(1)).getProdutos();
        verify(this.mockyApiClient, times(1)).getClientes();
        assertTrue(expected.isEmpty());

    }

    @Test
    void testGetRecomendacoes() {

        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        RecomendacaoResponse response = BuildMocks.buildRecomendacaoResponse();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        RecomendacaoResponse expected = this.clienteService.getRecomendacoes(BuildMocks.cpf);

        assertEquals(expected.getProduto().getCodigo(), response.getProduto().getCodigo());
        assertEquals(expected.getNome(), response.getNome());
        verify(this.mockyApiClient, times(1)).getProdutos();
        verify(this.mockyApiClient, times(1)).getClientes();
    }

    @Test
    void testGetRecomendacoesNullValues() {
        List<Cliente> clientes = List.of();
        List<Produto> produtos = BuildMocks.buildProdutos();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        RecomendacaoResponse expected = this.clienteService.getRecomendacoes(BuildMocks.cpf);

        verify(this.mockyApiClient, times(1)).getClientes();

        assertNull(expected.getNome());

    }

}
