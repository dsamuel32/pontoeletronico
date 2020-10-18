package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.dominio.dto.BancoHoraDTO;
import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.negocio.utils.DataUtil;
import br.com.desafio.pontoeletronico.negocio.utils.HoraUtil;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.desafio.pontoeletronico.negocio.utils.DataUtil.PRIMEIRO_DIA_MES;

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

    private Long calcularTotalTrabalhadoMesSegundos(List<Horario> horarios) {
        var datas = horarios.stream().map(Horario::getData).collect(Collectors.toSet());
        var totalSegundos = datas.stream().map(it -> {
            var horariosAtual = horarios.stream().filter(i -> i.getData().equals(it)).collect(Collectors.toList());
            return this.calcular(horariosAtual);
        }).reduce((it, total) -> total + it).orElse(0L);

        return totalSegundos;
    }

    private Long calcularTotalHorasMesSegundos(Integer mes, Integer ano) {
        var dataInicial = LocalDate.of(ano, mes, PRIMEIRO_DIA_MES);
        var ultimoDiaMes = DataUtil.getUltimoDiaMes(mes, ano);
        var total = 0;

        while (dataInicial.isBefore(ultimoDiaMes)) {
            if (!DataUtil.isFimSemana(dataInicial)) {
                total += 8;
            }
            dataInicial = dataInicial.plusDays(1);
        }

        return HoraUtil.converterSegundos(total);
    }

    public BancoHoraDTO calcularBancoHoras(List<Horario> horarios, Integer mes, Integer ano) {
        var totalTrabalhadoSegundos = this.calcularTotalTrabalhadoMesSegundos(horarios);
        var totalMesSegundos = this.calcularTotalHorasMesSegundos(mes, ano);

        var bancoHoraDTO = new BancoHoraDTO(formatarSegundosHora(totalMesSegundos),
                                            formatarSegundosHora(totalTrabalhadoSegundos),
                                 "00:00",
                                  "00:00");

        if (totalMesSegundos > totalTrabalhadoSegundos) {
            var total = this.calcularDiferencaSegundos(totalMesSegundos, totalTrabalhadoSegundos);
            bancoHoraDTO.setHorasDevidas(formatarSegundosHora(total));
        } else if (totalMesSegundos < totalTrabalhadoSegundos) {
            var total = this.calcularDiferencaSegundos(totalTrabalhadoSegundos, totalMesSegundos);
            bancoHoraDTO.setHorasExtras(formatarSegundosHora(total));
        }

        return bancoHoraDTO;
    }

    private String formatarSegundosHora(Long segundos) {
        return HoraUtil.converterParaString(segundos);
    }

    private Long calcularDiferencaSegundos(Long valor1, Long valor2) {
        return valor1 - valor2;
    }

}
