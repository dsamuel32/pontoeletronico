package br.com.desafio.pontoeletronico.negocio.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilTest {


    @Test
    @DisplayName("Deve verificar se a data é fim de semana")
    void deveVerificarDataFimSemana() {
        var ok = DataUtil.isFimSemana(LocalDate.of(2020, 10, 17));
        assertTrue(ok);
    }

    @Test
    @DisplayName("Deve retornar a data do ultimo dia do mes")
    void deveRetornarDataUltimoDiaMes() {
        var dataEsperada = LocalDate.of(2020, 10, 31);
        var ultimoDiaMes = DataUtil.getUltimoDiaMes(10, 2020);
        assertEquals(dataEsperada, ultimoDiaMes);
    }

    @Test
    @DisplayName("Deve retornar primeiro dia do mes")
    void deveRetornarPrimeiroDiaMes() {
        var data = DataUtil.getPrimeiroDiaMes(10, 2020);
        assertEquals(LocalDate.of(2020, 10, 1), data);
    }
}