package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.negocio.utils.HoraUtil;

import java.time.Duration;

public final class CalculoHora {

    private static final int HORA_SEGUNDOS = 3600;

    public Long calcularHorarioAlmocoSegundos(String horaSaida, String horaEntrada) {
        var entrada = HoraUtil.converter(horaEntrada);
        var saida = HoraUtil.converter(horaSaida);
        var segundos = Duration.between(saida, entrada).getSeconds();

        if (segundos < HORA_SEGUNDOS) {
            throw new ValidacaoNegocioException("O horário de almoço não pode ser menor que uma hora");
        }

        return segundos;
    }

}
