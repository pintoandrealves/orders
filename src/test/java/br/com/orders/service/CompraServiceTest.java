package br.com.orders.service;

import br.com.orders.api.MockyApiClient;
import br.com.orders.controller.response.CompraResponse;
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
class CompraServiceTest {

    @InjectMocks
    private CompraService compraService;

    @Mock
    private MockyApiClient mockyApiClient;

    @Test
    void testGetCompras() {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        List<CompraResponse> response = BuildMocks.buildComprasResponse();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        List<CompraResponse> expected = this.compraService.getCompras();

        assertEquals(expected.get(0).getCpf(), response.get(0).getCpf());
        assertEquals(expected.get(0).getNome(), response.get(0).getNome());
        verify(this.mockyApiClient, times(1)).getProdutos();
        verify(this.mockyApiClient, times(1)).getClientes();
    }

    @Test
    void testGetComprasNullValue() {
        List<Cliente> clientes = List.of();
        List<Produto> produtos = List.of();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        List<CompraResponse> expected = this.compraService.getCompras();

        verify(this.mockyApiClient, times(1)).getProdutos();
        verify(this.mockyApiClient, times(1)).getClientes();
        assertTrue(expected.isEmpty());

    }

    @Test
    void testGetMaiorCompra() {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = BuildMocks.buildProdutos();
        CompraResponse response = BuildMocks.buildCompraResponse();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        CompraResponse expected = this.compraService.getMaiorCompra(BuildMocks.ano);

        assertEquals(expected.getCodigo(), response.getCodigo());
        assertEquals(expected.getTipoVinho(), response.getTipoVinho());
        assertEquals(expected.getNome(), response.getNome());
        assertEquals(expected.getPreco(), response.getPreco());
        verify(this.mockyApiClient, times(1)).getProdutos();
        verify(this.mockyApiClient, times(1)).getClientes();
    }

    @Test
    void testGetMaiorCompraNullValues() {
        List<Cliente> clientes = BuildMocks.buildClientes();
        List<Produto> produtos = List.of();

        when(this.mockyApiClient.getClientes()).thenReturn(clientes);
        when(this.mockyApiClient.getProdutos()).thenReturn(produtos);
        CompraResponse expected = this.compraService.getMaiorCompra(BuildMocks.ano);

        assertNull(expected.getNome());
        verify(this.mockyApiClient, times(1)).getProdutos();
        verify(this.mockyApiClient, times(1)).getClientes();
    }

}
