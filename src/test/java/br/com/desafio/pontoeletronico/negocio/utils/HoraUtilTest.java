package br.com.desafio.pontoeletronico.negocio.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class HoraUtilTest {

    @Test
    @DisplayName("Deve converter hora String para LocalTime")
    void deveConverterHoraStringParaLocalTime() {
        var hora = HoraUtil.converter("08:00");
        var ok = hora instanceof LocalTime;
        assertTrue(ok);
    }

    @Test
    @DisplayName("Deve verificar se hora inicial maior que a final")
    void deveVerificarHoraInicialMaiorHoraFinal() {
        var ok = HoraUtil.isHoraFinalMaiorHoraInicial("08:00", "12:00");
        assertTrue(ok);
    }

    @Test
    @DisplayName("Deve retornar hora em segundos")
    void deveRetornarHoraEmSegundos() {
        var segundos = HoraUtil.converterSegundos("01:00");
        assertEquals(3600L, segundos);
    }

}