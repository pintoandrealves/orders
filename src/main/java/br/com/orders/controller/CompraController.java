package br.com.orders.controller;

import br.com.orders.controller.response.CompraResponse;
import br.com.orders.service.CompraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Compras", value = "Controlador Rest API Compras")
public class CompraController {

    private static final Logger logger = LogManager.getLogger(ClienteController.class);
    @Autowired
    private CompraService compraService;

    /**
     * Função responsável por receber uma requisição com o método GET e retornar as compras realizadas.
     *
     * @return
     */
    @ApiOperation(value = "Buscar dados de Compras Realizadas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 404, message = "Não Encontrado"),
            @ApiResponse(code = 500, message = "Erro na aplicação")
    })
    @GetMapping(
            value = "/compras",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<CompraResponse>> getCompras() {
        logger.info("Inicio, execução endpoint /compras");
        List<CompraResponse> compras = compraService.getCompras();
        if (compras.isEmpty()) {
            logger.warn("Execução endpoint /compras, lista vazia");
            return ResponseEntity.notFound().build();
        }
        logger.info("Fim, execução endpoint /compras, compras:{}", compras);
        return ResponseEntity.ok().body(compras);
    }

    /**
     * Função responsável por receber uma requisição com o método GET e retornar as compras realizadas por ano.
     *
     * @return
     */
    @ApiOperation(value = "Buscar dados de Maiores Compras Realizadas por ano")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 404, message = "Não Encontrado"),
            @ApiResponse(code = 500, message = "Erro na aplicação")
    })
    @GetMapping(
            value = "/maior-compra/{ano}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CompraResponse> getMaiorCompra(@PathVariable("ano") int ano) {
        logger.info("Inicio, execução endpoint /maior-compra, ano:{}", ano);
        CompraResponse compra = compraService.getMaiorCompra(ano);
        if (compra == null || compra.getCpf() == null) {
            logger.warn("Execução endpoint /maior-compra/{ano}, ano:{}, maior compra não localizada", ano);
            return ResponseEntity.notFound().build();
        }
        logger.info("Fim, execução endpoint /maior-compra/{ano}, ano:{}, compra:{}", ano, compra);
        return ResponseEntity.ok().body(compra);
    }

}
