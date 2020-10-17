package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.negocio.utils.HoraUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.rowset.CachedRowSet;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CalculoHoraTest {

    @Test
    @DisplayName("Deve deve calcular horario de almoco de uma hora")
    void deveCalcularHorarioAlmocoDeUmaHora() {
        var calculoHora = new CalculoHora();
        var segundos = calculoHora.calcularHorarioAlmocoSegundos("12:00", "13:00");
        assertEquals(3600, segundos);
    }

    @Test
    @DisplayName("Deve deve retornar ValidacaoNegocioException quando hora de almoco menor que uma hora")
    void deveRetornarValidacaoNegocioExceptionQuandoHoraAlmocoMenorUmaHora() {
        var calculoHora = new CalculoHora();
        assertThrows(ValidacaoNegocioException.class, () -> calculoHora.calcularHorarioAlmocoSegundos("12:00", "12:20"));
    }

    @Test
    @DisplayName("Deve calcular o total trabalhado e retornar o valor")
    void deveCalcularOTotalTrabalhado() {
        var horarios = Arrays.asList(
                            criarHorario("08:00", TipoHorarioEnum.ENTRADA),
                            criarHorario("12:00", TipoHorarioEnum.SAIDA_ALMOCO),
                            criarHorario("13:00", TipoHorarioEnum.RETORNO_ALMOCO),
                            criarHorario("17:00", TipoHorarioEnum.SAIDA));
        var calculo = new CalculoHora();
        var total = calculo.calcular(horarios);
        assertEquals(28800L, total);
    }

    private Horario criarHorario(String hora, TipoHorarioEnum tipoHorarioEnum) {
        return new Horario(1L, hora, LocalDate.now(), tipoHorarioEnum);
    }

}