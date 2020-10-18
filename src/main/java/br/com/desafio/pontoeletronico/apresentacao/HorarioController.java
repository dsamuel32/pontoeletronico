package br.com.desafio.pontoeletronico.apresentacao;

import br.com.desafio.pontoeletronico.dominio.dto.BancoHoraDTO;
import br.com.desafio.pontoeletronico.dominio.dto.HorarioDTO;
import br.com.desafio.pontoeletronico.service.HorarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(
        value = Paths.REGISTRO_HORARIOS,
        description = "Registro Horários",
        consumes="application/json",
        produces="application/json"
)
@RestController
@RequestMapping(value = Paths.REGISTRO_HORARIOS, produces = MediaType.APPLICATION_JSON_VALUE)
public class HorarioController {

    private HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @ApiOperation( value = "Calcula horas mensais trabalhadas com diferença positiva ou negativa.")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Retorna o horário salvo"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção inesperada")
    })
    @GetMapping("{matricula}/banco-horas/{mes}/{ano}")
    @ResponseStatus(HttpStatus.OK)
    public BancoHoraDTO calcularBancoHoras(@PathVariable("matricula") Long matricula,
                                           @PathVariable("mes") Integer mes,
                                           @PathVariable("ano") Integer ano) {
        return this.horarioService.calcularBancoHoras(matricula, mes, ano);
    }


    @ApiOperation( value = "Salva um horário de trabalho")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Retorna o horário salvo"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção inesperada")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioDTO salvar(@RequestBody HorarioDTO horarioDTO) {
        return this.horarioService.salvar(horarioDTO);
    }

    @ApiOperation( value = "Edita um horário de trabalho")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Retorna o horário atualizado"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção inesperada")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public HorarioDTO editar(@RequestBody HorarioDTO horarioDTO) {
        return this.horarioService.salvar(horarioDTO);
    }
}
