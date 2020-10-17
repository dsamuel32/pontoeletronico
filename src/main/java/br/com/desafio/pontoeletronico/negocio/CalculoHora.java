package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.negocio.utils.DataUtil;
import br.com.desafio.pontoeletronico.negocio.utils.HoraUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public Long calcular(List<Horario> horarios) {
        var horariosProcessados = this.removerUltimoElemento(horarios).stream()
                                                                                    .map(it -> HoraUtil.converter(it.getHora()))
                                                                                    .collect(Collectors.toList());
        var entradas = IntStream.range(0, horariosProcessados.size())
                                             .filter(i -> (i % 2) == 0)
                                             .mapToObj(horariosProcessados::get)
                                             .collect(Collectors.toList());

        var saidas = IntStream.range(0, horariosProcessados.size())
                                            .filter(i -> (i % 2) != 0)
                                            .mapToObj(horariosProcessados::get)
                                            .collect(Collectors.toList());

        var segundos = 0L;
        for (var i = 0; i < entradas.size(); i++) {
            segundos += Duration.between(entradas.get(i), saidas.get(i)).getSeconds();
        }

        return segundos;
    }

    private List<Horario> removerUltimoElemento(List<Horario> horarios) {
        List<Horario> novosHorarios = new ArrayList<>();
        if ((horarios.size() % 2) != 0 ) {
            var ultimoIndice = horarios.size() - 1;
            horarios.remove(ultimoIndice);
        }
        novosHorarios.addAll(horarios);
        return novosHorarios;
    }

}
