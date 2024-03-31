package br.com.orders.controller;

import br.com.orders.controller.response.ClienteResponse;
import br.com.orders.controller.response.RecomendacaoResponse;
import br.com.orders.service.ClienteService;
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
@Api(tags = "Clientes", value = "Controlador Rest API Clientes")
public class ClienteController {

    private static final Logger logger = LogManager.getLogger(ClienteController.class);
    @Autowired
    private ClienteService clienteService;

    /**
     * Função responsável por receber uma requisição com o método GET e listar os cliente fieis.
     *
     * @return
     */
    @ApiOperation(value = "Buscar dados de Clientes Fieis")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 404, message = "Não Encontrado"),
            @ApiResponse(code = 500, message = "Erro na aplicação")
    })
    @GetMapping(
            value = "/clientes-fieis",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<ClienteResponse>> getClientesFieis() {
        logger.info("Inicio, execução endpoint /clientes-fieis");
        List<ClienteResponse> clientes = clienteService.getClientesFieis();
        if (clientes.isEmpty()) {
            logger.warn("Execução endpoint /clientes-fieis, lista de clientes vazia");
            return ResponseEntity.notFound().build();
        }
        logger.info("Fim, execução endpoint /clientes-fieis, clientes:{}", clientes);
        return ResponseEntity.ok().body(clientes);
    }

    /**
     * Função responsável por receber uma requisição com o método GET e retornar as recomendacoes.
     *
     * @return
     */
    @ApiOperation(value = "Buscar dados de Recomendações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 404, message = "Não Encontrado"),
            @ApiResponse(code = 500, message = "Erro na aplicação")
    })
    @GetMapping(
            value = "/recomendacao/{cpf}/tipo",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<RecomendacaoResponse> getRecomendacoes(@PathVariable("cpf") String cpf) {
        logger.info("Inicio, execução endpoint /recomendacao/{cpf}/tipo");
        RecomendacaoResponse clienteRecomendacaoResponse = clienteService.getRecomendacoes(cpf);
        if (clienteRecomendacaoResponse == null || clienteRecomendacaoResponse.getNome() == null) {
            logger.warn("Execução endpoint /recomendacao/{cpf}/tipo, recomendacao não existe");
            return ResponseEntity.notFound().build();
        }
        logger.info("Fim, execução endpoint /recomendacao/{cpf}/tipo, recomendacao:{}", clienteRecomendacaoResponse);
        return ResponseEntity.ok().body(clienteRecomendacaoResponse);
    }

}
