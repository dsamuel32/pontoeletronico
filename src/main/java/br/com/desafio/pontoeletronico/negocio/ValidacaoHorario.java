package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.negocio.utils.DataUtil;
import br.com.desafio.pontoeletronico.negocio.utils.HoraUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class ValidacaoHorario {

    private final Horario horario;
    private final String hora;
    private final LocalDate data;
    private final List<String> erros;

    public ValidacaoHorario(Horario horario, String hora, LocalDate data) {
        this.horario = horario;
        this.hora = hora;
        this.data = data;
        this.erros = new ArrayList<>();
    }

    private void validarHoraInformada() {
        if (this.horario != null) {
            var horaValida = HoraUtil.isHoraFinalMaiorHoraInicial(this.horario.getHora(), this.hora);

            if (!horaValida) {
                throw new ValidacaoNegocioException("Hora informada é menor que a hora informada anteriror.");
            }
        }
    }

    private void validarHoraAlmoco() {
        if (this.horario != null && this.horario.getTipoHorarioEnum() == TipoHorarioEnum.SAIDA_MANHA) {
            var calculoHora = new CalculoHora();
            calculoHora.calcularHorarioAlmocoSegundos(this.horario.getHora(), this.hora);
        }
    }

    private void validarFimSemana() {
        if (DataUtil.isFimSemana(this.data)) {
            throw new ValidacaoNegocioException("Não é permitido registrar um ponto no fim de semana");
        }
    }

    public void validar() {
        this.validarFimSemana();
        this.validarHoraInformada();
        this.validarHoraAlmoco();
    }

}
