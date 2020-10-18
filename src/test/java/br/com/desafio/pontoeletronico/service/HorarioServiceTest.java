package br.com.desafio.pontoeletronico.service;

import br.com.desafio.pontoeletronico.AppTestComum;
import br.com.desafio.pontoeletronico.dominio.dto.HorarioDTO;
import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;
import br.com.desafio.pontoeletronico.negocio.CalculoHora;
import br.com.desafio.pontoeletronico.repository.HorarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class HorarioServiceTest extends AppTestComum {

    @Mock
    private HorarioRepository horarioRepository;

    @Test
    @DisplayName("Deve retornar horário salvo")
    void deveRetornarHorarioSalvo() {
        doReturn(null).when(horarioRepository).findTopByMatriculaAndDataOrderByIdDesc(anyLong(), any(LocalDate.class));
        doReturn(criarHorario()).when(horarioRepository).save(any(Horario.class));
        var horarioService = new HorarioService(horarioRepository);
        var resposta = horarioService.salvar(new HorarioDTO(1L, 1L, "08:00", LocalDate.now(), TipoHorarioEnum.ENTRADA));
        assertNotNull(resposta);
    }

    @Test
    @DisplayName("Deve calcular o total trabalhado")
    void deveCalcularOTotalTrabalhado() {
        doReturn(criarHorarios()).when(horarioRepository).findByMatriculaAndDataOrderByIdAsc(anyLong(), any(LocalDate.class));
        var horarioService = new HorarioService(horarioRepository);
        var totalTrabalhado = horarioService.calcularTotalTrabalhado(1L, LocalDate.now());
        assertEquals(14400L, totalTrabalhado);

    }

    private Horario criarHorario() {
        return new Horario(1L, "08:00", LocalDate.now(), TipoHorarioEnum.ENTRADA);
    }

    private List<Horario> criarHorarios() {
        return Arrays.asList(new Horario(1L, "08:00", LocalDate.now(), TipoHorarioEnum.ENTRADA),
                             new Horario(1L, "12:00", LocalDate.now(), TipoHorarioEnum.SAIDA_ALMOCO));
    }

    @Test
    @DisplayName("Deve calcular banco de horas com horas a pagar")
    void deveCalcularBancoHorasComHorasPagar() {
        doReturn(criarDiasHorarios()).when(horarioRepository).findByMatriculaBetweenData(anyLong(), any(LocalDate.class), any(LocalDate.class));
        var horarioService = new HorarioService(horarioRepository);
        var bancoHoras = horarioService.calcularBancoHoras(1L, 10, 2020);
        assertNotNull(bancoHoras);
        assertEquals("176:00", bancoHoras.getTotalHoraMesal());
        assertEquals("26:00", bancoHoras.getTotalTrabalhado());
        assertEquals("00:00", bancoHoras.getHorasExtras());
        assertEquals("150:00", bancoHoras.getHorasDevidas());
    }

}