package br.com.desafio.pontoeletronico.apresentacao;

import br.com.desafio.pontoeletronico.dominio.dto.AlocacaoHoraDTO;
import br.com.desafio.pontoeletronico.service.AlocacaoHoraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
        value = Paths.ALOCACAO_HORAS,
        description = "Registro Horários",
        consumes="application/json",
        produces="application/json"
)
@RestController
@RequestMapping(value = Paths.ALOCACAO_HORAS, produces = MediaType.APPLICATION_JSON_VALUE)
public class AlocacaoHoraController {

    private final AlocacaoHoraService alocacaoHoraService;

    public AlocacaoHoraController(AlocacaoHoraService alocacaoHoraService) {
        this.alocacaoHoraService = alocacaoHoraService;
    }

    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Retorna a alocação salva"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção inesperada")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlocacaoHoraDTO salvar(@RequestBody AlocacaoHoraDTO alocacaoHoraDTO) {
        return this.alocacaoHoraService.alocar(alocacaoHoraDTO);
    }

}
