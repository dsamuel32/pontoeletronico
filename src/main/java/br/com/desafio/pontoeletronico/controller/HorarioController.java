package br.com.desafio.pontoeletronico.controller;

import br.com.desafio.pontoeletronico.dominio.dto.HorarioDTO;
import br.com.desafio.pontoeletronico.service.HorarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "registro-horarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class HorarioController {

    private HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> teste() {
        var resposta = new HashMap<String, String>();
        resposta.put("resposta", "Ok");
        return resposta;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioDTO salvar(@RequestBody HorarioDTO horarioDTO) {
        return this.horarioService.salvar(horarioDTO);
    }
}
