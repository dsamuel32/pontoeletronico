package br.com.desafio.pontoeletronico.service;

import br.com.desafio.pontoeletronico.dominio.dto.AlocacaoHoraDTO;
import br.com.desafio.pontoeletronico.dominio.entidade.AlocacaoHora;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.repository.AlocacaoHoraRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AlocacaoHoraServiceTest {

    @Mock
    private AlocacaoHoraRepository alocacaoHoraRepository;
    @Mock
    private HorarioService horarioService;

    @Test
    @DisplayName("Deve retornar alocacao hora")
    void deveRetornarAlocacaoHora() {
        doReturn(0).when(this.alocacaoHoraRepository).recuperarTotalAlocado(anyLong(), any(LocalDate.class));
        doReturn(criarAlocacaoHora()).when(this.alocacaoHoraRepository).save(any(AlocacaoHora.class));
        doReturn(7200L).when(this.horarioService).calcularTotalTrabalhado(anyLong(), any(LocalDate.class));
        var alocacaoService = new AlocacaoHoraService(alocacaoHoraRepository, horarioService);
        var resposta = alocacaoService.alocar(new AlocacaoHoraDTO(1L, 1L, "01:50", LocalDate.now()));
        assertEquals("01:50", resposta.getHoras());
        assertEquals(1L, resposta.getId());
        assertEquals(1L, resposta.getCodigoProjeto());
    }

    @Test
    @DisplayName("Deve lancar ValidacaoNegocioException quando tempo alocacao ultrapassar total trabalhado")
    void deveLancarValidacaoNegocioExceptionQuandoTempoAlocacaoUltrapassarTotalTrabalhado() {
        doReturn(0).when(this.alocacaoHoraRepository).recuperarTotalAlocado(anyLong(), any(LocalDate.class));
        doReturn(7200L).when(this.horarioService).calcularTotalTrabalhado(anyLong(), any(LocalDate.class));
        var alocacaoService = new AlocacaoHoraService(alocacaoHoraRepository, horarioService);
        assertThrows(ValidacaoNegocioException.class, () -> alocacaoService.alocar(new AlocacaoHoraDTO(1L, 1L, "03:50", LocalDate.now())));
    }

    private AlocacaoHora criarAlocacaoHora() {
        return new AlocacaoHora( 1L, 1L, 1L, LocalDate.now(), 6600L);
    }

}