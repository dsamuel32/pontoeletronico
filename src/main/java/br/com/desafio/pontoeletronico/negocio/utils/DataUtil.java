package br.com.desafio.pontoeletronico.negocio.utils;

import java.time.LocalDate;
import java.util.Calendar;

public final class DataUtil {

    public static Boolean isFimSemana(LocalDate localDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(localDate.getYear(), (localDate.getMonthValue() - 1), localDate.getDayOfMonth());

        var dia = calendar.get(Calendar.DAY_OF_WEEK);

        return isFimSemana(dia, Calendar.SATURDAY) || isFimSemana(dia, Calendar.SUNDAY) ;
    }

    private static Boolean isFimSemana(int dia, int diaSemana) {
        return diaSemana == dia;
    }

}
