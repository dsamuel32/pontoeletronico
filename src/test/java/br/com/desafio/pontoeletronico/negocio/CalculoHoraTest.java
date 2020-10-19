package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.AppTestComum;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CalculoHoraTest extends AppTestComum {

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
        var data = LocalDate.of(2020, 10, 15);
        var horarios = Arrays.asList(
                            criarHorario(null, "08:00", TipoHorarioEnum.ENTRADA, data),
                            criarHorario(null, "12:00", TipoHorarioEnum.SAIDA_ALMOCO, data),
                            criarHorario(null, "13:00", TipoHorarioEnum.RETORNO_ALMOCO, data),
                            criarHorario(null, "17:00", TipoHorarioEnum.SAIDA, data));
        var calculo = new CalculoHora();
        var total = calculo.calcular(horarios);
        assertEquals(28800L, total);
    }

    @Test
    @DisplayName("Deve calcular banco de horas com horas a pagar")
    void deveCalcularBancoHorasComHorasPagar() {
        var calculo = new CalculoHora();
        var bancoHoras = calculo.calcularBancoHoras(criarDiasHorarios(), 10, 2020);
        assertNotNull(bancoHoras);
        assertEquals("176:00", bancoHoras.getTotalHoraMesal());
        assertEquals("26:00", bancoHoras.getTotalTrabalhado());
        assertEquals("00:00", bancoHoras.getHorasExtras());
        assertEquals("150:00", bancoHoras.getHorasDevidas());
    }

}