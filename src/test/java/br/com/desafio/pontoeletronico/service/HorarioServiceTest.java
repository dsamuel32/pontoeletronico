package br.com.desafio.pontoeletronico.service;

import br.com.desafio.pontoeletronico.AppTestComum;
import br.com.desafio.pontoeletronico.dominio.dto.HorarioDTO;
import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;
import br.com.desafio.pontoeletronico.negocio.CalculoHora;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.repository.AlocacaoHoraRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class HorarioServiceTest extends AppTestComum {

    @Mock
    private HorarioRepository horarioRepository;
    @Mock
    private AlocacaoHoraRepository alocacaoHoraRepository;

    @Test
    @DisplayName("Deve retornar horário salvo")
    void deveRetornarHorarioSalvo() {
        doReturn(null).when(horarioRepository).findTopByMatriculaAndDataOrderByIdDesc(anyLong(), any(LocalDate.class));
        doReturn(criarHorario("08:00")).when(horarioRepository).save(any(Horario.class));
        var horarioService = new HorarioService(horarioRepository, alocacaoHoraRepository);
        var resposta = horarioService.salvar(new HorarioDTO(1L, 1L, "08:00", LocalDate.of(2020, 10, 15), TipoHorarioEnum.ENTRADA));
        assertNotNull(resposta);
        assertEquals(1L, resposta.getId());
        assertEquals("08:00", resposta.getHora());
        assertEquals(TipoHorarioEnum.ENTRADA, resposta.getTipoHorarioEnum());
    }

    @Test
    @DisplayName("Deve retornar horário editado")
    void deveRetornarHorarioEditado() {
        var horarios = criarDiasHorarios().stream().filter(it -> it.getData().equals(LocalDate.of(2020, 10, 15))).collect(Collectors.toList());
        doReturn(horarios).when(horarioRepository).findByMatriculaAndDataOrderByIdAsc(anyLong(), any(LocalDate.class));
        doReturn(criarHorario("07:50")).when(horarioRepository).save(any(Horario.class));
        doReturn(Optional.of(28800)).when(alocacaoHoraRepository).recuperarTotalAlocado(anyLong(), any(LocalDate.class));
        var horarioService = new HorarioService(horarioRepository, alocacaoHoraRepository);
        var resposta = horarioService.editar(new HorarioDTO(1L, 1L, "07:50", LocalDate.of(2020, 10, 15), TipoHorarioEnum.ENTRADA));
        assertNotNull(resposta);
        assertEquals(1L, resposta.getId());
        assertEquals("07:50", resposta.getHora());
        assertEquals(TipoHorarioEnum.ENTRADA, resposta.getTipoHorarioEnum());
    }

    @Test
    @DisplayName("Deve retornar ValidacaoNegocioException quanto total trabalhado menor que total alocado")
    void deveRetornarValidacaoNegocioExceptionQuantoTotalTrabalhadoMenorQueTotalAlocado() {
        var horarios = criarDiasHorarios().stream().filter(it -> it.getData().equals(LocalDate.of(2020, 10, 15))).collect(Collectors.toList());
        doReturn(horarios).when(horarioRepository).findByMatriculaAndDataOrderByIdAsc(anyLong(), any(LocalDate.class));
        doReturn(Optional.of(28800)).when(alocacaoHoraRepository).recuperarTotalAlocado(anyLong(), any(LocalDate.class));
        var horarioService = new HorarioService(horarioRepository, alocacaoHoraRepository);
        assertThrows(ValidacaoNegocioException.class, () -> horarioService.editar(new HorarioDTO(1L, 1L, "09:50", LocalDate.of(2020, 10, 15), TipoHorarioEnum.ENTRADA)));
    }

    @Test
    @DisplayName("Deve calcular o total trabalhado")
    void deveCalcularOTotalTrabalhado() {
        doReturn(criarHorarios()).when(horarioRepository).findByMatriculaAndDataOrderByIdAsc(anyLong(), any(LocalDate.class));
        var horarioService = new HorarioService(horarioRepository, alocacaoHoraRepository);
        var totalTrabalhado = horarioService.calcularTotalTrabalhado(1L, LocalDate.of(2020, 10, 15));
        assertEquals(14400L, totalTrabalhado);

    }

    private Horario criarHorario(String horario) {
        return new Horario(1L, 1L, horario, LocalDate.now(), TipoHorarioEnum.ENTRADA);
    }

    private List<Horario> criarHorarios() {
        return Arrays.asList(new Horario(1L, "08:00", LocalDate.now(), TipoHorarioEnum.ENTRADA),
                             new Horario(1L, "12:00", LocalDate.now(), TipoHorarioEnum.SAIDA_ALMOCO));
    }

    @Test
    @DisplayName("Deve calcular banco de horas com horas a pagar")
    void deveCalcularBancoHorasComHorasPagar() {
        doReturn(criarDiasHorarios()).when(horarioRepository).findByMatriculaBetweenData(anyLong(), any(LocalDate.class), any(LocalDate.class));
        var horarioService = new HorarioService(horarioRepository, alocacaoHoraRepository);
        var bancoHoras = horarioService.calcularBancoHoras(1L, 10, 2020);
        assertNotNull(bancoHoras);
        assertEquals("176:00", bancoHoras.getTotalHoraMesal());
        assertEquals("26:00", bancoHoras.getTotalTrabalhado());
        assertEquals("00:00", bancoHoras.getHorasExtras());
        assertEquals("150:00", bancoHoras.getHorasDevidas());
    }

}