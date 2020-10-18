package br.com.desafio.pontoeletronico.negocio.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public final class DataUtil {

    public static final int PRIMEIRO_DIA_MES = 1;

    public static Boolean isFimSemana(LocalDate localDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(localDate.getYear(), (localDate.getMonthValue() - 1), localDate.getDayOfMonth());

        var dia = calendar.get(Calendar.DAY_OF_WEEK);

        return isFimSemana(dia, Calendar.SATURDAY) || isFimSemana(dia, Calendar.SUNDAY) ;
    }

    private static Boolean isFimSemana(int dia, int diaSemana) {
        return diaSemana == dia;
    }

    public static LocalDate getUltimoDiaMes(Integer mes, Integer ano) {
        return LocalDate.of(ano, mes, 1).with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getPrimeiroDiaMes(Integer mes, Integer ano) {
        return LocalDate.of(ano,mes, 1);
    }

}
