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
}