package br.com.desafio.pontoeletronico;

import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AppTestComum {

    protected List<Horario> criarDiasHorarios() {
        var dataDiaUm = LocalDate.of(2020, 10, 15);
        var dataDiaDois = LocalDate.of(2020, 10, 16);
        var dataDiaTres = LocalDate.of(2020, 10, 19);
        var horarios = Arrays.asList(
                criarHorario(1L,"08:00", TipoHorarioEnum.ENTRADA, dataDiaUm),
                criarHorario(2L, "12:00", TipoHorarioEnum.SAIDA_ALMOCO, dataDiaUm),
                criarHorario(3L, "13:00", TipoHorarioEnum.RETORNO_ALMOCO, dataDiaUm),
                criarHorario(4L, "17:00", TipoHorarioEnum.SAIDA, dataDiaUm),
                criarHorario(5L, "08:00", TipoHorarioEnum.ENTRADA, dataDiaDois),
                criarHorario(6L, "12:00", TipoHorarioEnum.SAIDA_ALMOCO, dataDiaDois),
                criarHorario(7L, "13:00", TipoHorarioEnum.RETORNO_ALMOCO, dataDiaDois),
                criarHorario(8L, "17:00", TipoHorarioEnum.SAIDA, dataDiaDois),
                criarHorario(9L,"08:00", TipoHorarioEnum.ENTRADA, dataDiaTres),
                criarHorario(10L, "12:00", TipoHorarioEnum.SAIDA_ALMOCO, dataDiaTres),
                criarHorario(11L, "13:00", TipoHorarioEnum.RETORNO_ALMOCO, dataDiaTres),
                criarHorario(12L, "19:00", TipoHorarioEnum.SAIDA, dataDiaTres));
        return horarios;
    }

    protected Horario criarHorario(Long id, String hora, TipoHorarioEnum tipoHorarioEnum, LocalDate data) {
        return new Horario(id, 1L, hora, data, tipoHorarioEnum);
    }


}
