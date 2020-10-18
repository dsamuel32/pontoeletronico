package br.com.desafio.pontoeletronico.negocio.utils;

import java.time.*;

public final class HoraUtil {

    private static final long UMA_HORA_SEGUNDOS = 3600;

    public static LocalTime converter(String hora) {
        var horaMinuto = hora.split(":");
        return LocalTime.of(converterParaInteger(horaMinuto[0]), converterParaInteger(horaMinuto[1]));
    }

    public static Long converterSegundos(String hora) {
        var localTime = converter(hora);
        return Long.valueOf(localTime.toSecondOfDay());
    }

    public static Long converterSegundos(Integer hora) {
        return hora * UMA_HORA_SEGUNDOS;
    }

    public static Boolean isHoraFinalMaiorHoraInicial(String horaInicial, String horaFinal) {
        var inicio = converter(horaInicial);
        var fim = converter(horaFinal);
        return inicio.isBefore(fim);
    }

    private static Integer converterParaInteger(String valor) {
        return Integer.valueOf(valor);
    }

    public static String converterParaString(Long segundos) {
        var horas = segundos / 3600;
        var minutos = (segundos - horas * 3600) / 60;
        return StringUtil.padEsquerdaZeros(String.valueOf(horas), 2) + ":" + StringUtil.padEsquerdaZeros(String.valueOf(minutos), 2);
    }

}
