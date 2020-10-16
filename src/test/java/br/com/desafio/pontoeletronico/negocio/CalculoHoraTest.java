package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}