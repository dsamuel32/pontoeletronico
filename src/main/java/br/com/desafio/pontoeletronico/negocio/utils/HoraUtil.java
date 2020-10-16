package br.com.desafio.pontoeletronico.negocio.utils;

import java.time.LocalTime;

public final class HoraUtil {

    public static LocalTime converter(String hora) {
        var horaMinuto = hora.split(":");
        return LocalTime.of(converterParaInteger(horaMinuto[0]), converterParaInteger(horaMinuto[1]));
    }

    public static Boolean isHoraFinalMaiorHoraInicial(String horaInicial, String horaFinal) {
        var inicio = converter(horaInicial);
        var fim = converter(horaFinal);
        return inicio.isBefore(fim);
    }

    private static Integer converterParaInteger(String valor) {
        return Integer.valueOf(valor);
    }
}
