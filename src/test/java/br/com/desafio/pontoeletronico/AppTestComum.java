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
                criarHorario("08:00", TipoHorarioEnum.ENTRADA, dataDiaUm),
                criarHorario("12:00", TipoHorarioEnum.SAIDA_ALMOCO, dataDiaUm),
                criarHorario("13:00", TipoHorarioEnum.RETORNO_ALMOCO, dataDiaUm),
                criarHorario("17:00", TipoHorarioEnum.SAIDA, dataDiaUm),
                criarHorario("08:00", TipoHorarioEnum.ENTRADA, dataDiaDois),
                criarHorario("12:00", TipoHorarioEnum.SAIDA_ALMOCO, dataDiaDois),
                criarHorario("13:00", TipoHorarioEnum.RETORNO_ALMOCO, dataDiaDois),
                criarHorario("17:00", TipoHorarioEnum.SAIDA, dataDiaDois),
                criarHorario("08:00", TipoHorarioEnum.ENTRADA, dataDiaTres),
                criarHorario("12:00", TipoHorarioEnum.SAIDA_ALMOCO, dataDiaTres),
                criarHorario("13:00", TipoHorarioEnum.RETORNO_ALMOCO, dataDiaTres),
                criarHorario("19:00", TipoHorarioEnum.SAIDA, dataDiaTres));
        return horarios;
    }

    protected Horario criarHorario(String hora, TipoHorarioEnum tipoHorarioEnum, LocalDate data) {
        return new Horario(1L, hora, data, tipoHorarioEnum);
    }


}
