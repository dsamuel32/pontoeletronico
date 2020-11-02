package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import br.com.desafio.pontoeletronico.negocio.utils.DataUtil;
import br.com.desafio.pontoeletronico.negocio.utils.HoraUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class ValidacaoHorario {

    private final Horario horarioAnterior;
    private final String hora;
    private final LocalDate data;
    private final TipoHorarioEnum tipoHorarioEnum;
    private final Map<TipoHorarioEnum, TipoHorarioEnum> mapaOrdemBatidasPermitidas;

    public ValidacaoHorario(Horario horarioAnterior, String hora, LocalDate data, TipoHorarioEnum tipoHorarioEnum) {
        this.horarioAnterior = horarioAnterior;
        this.hora = hora;
        this.data = data;
        this.tipoHorarioEnum = tipoHorarioEnum;
        this.mapaOrdemBatidasPermitidas = this.inicializarMapaOrdemBatidasPermitidas();
    }

    private Map<TipoHorarioEnum, TipoHorarioEnum> inicializarMapaOrdemBatidasPermitidas() {
        Map<TipoHorarioEnum, TipoHorarioEnum> mapaOrdemBatidasPermitidas = new HashMap<>();
        mapaOrdemBatidasPermitidas.put(TipoHorarioEnum.SAIDA_ALMOCO, TipoHorarioEnum.ENTRADA);
        mapaOrdemBatidasPermitidas.put(TipoHorarioEnum.RETORNO_ALMOCO, TipoHorarioEnum.SAIDA_ALMOCO);
        mapaOrdemBatidasPermitidas.put(TipoHorarioEnum.SAIDA, TipoHorarioEnum.RETORNO_ALMOCO);
        return mapaOrdemBatidasPermitidas;
    }

    private void validarHoraInformada() {
        if (this.isHorarioNaoNulo()) {
            var horaValida = HoraUtil.isHoraFinalMaiorHoraInicial(this.horarioAnterior.getHora(), this.hora);

            if (!horaValida) {
                throw new ValidacaoNegocioException("Hora informada é menor que a hora informada anteriror.");
            }
        }
    }

    private void validarTipoHorarioPermitida() {
        var mensagem = "O tipo de horário informado não é permitido, pois não existe um horário do tipo ";
        if (this.horarioAnterior == null && this.tipoHorarioEnum != TipoHorarioEnum.ENTRADA) {
            throw new ValidacaoNegocioException(mensagem + TipoHorarioEnum.ENTRADA.getDescricao());
        } else if (this.isTipoHorarioNaoPermitido()) {
            var tipoEntrada = this.mapaOrdemBatidasPermitidas.get(tipoHorarioEnum);
            throw new ValidacaoNegocioException(mensagem + tipoEntrada.getDescricao());
        }
    }

    private Boolean isTipoHorarioNaoPermitido() {
        return this.isHorarioNaoNulo() && !this.mapaOrdemBatidasPermitidas.get(tipoHorarioEnum).equals(horarioAnterior.getTipoHorarioEnum());
    }

    private Boolean isHorarioNaoNulo() {
        return this.horarioAnterior != null;
    }

    private void validarHoraAlmoco() {
        if (this.isHorarioNaoNulo() && this.horarioAnterior.getTipoHorarioEnum() == TipoHorarioEnum.SAIDA_ALMOCO) {
            var calculoHora = new CalculoHora();
            calculoHora.calcularHorarioAlmocoSegundos(this.horarioAnterior.getHora(), this.hora);
        }
    }

    private void validarFimSemana() {
        if (DataUtil.isFimSemana(this.data)) {
            throw new ValidacaoNegocioException("Não é permitido registrar um ponto no fim de semana");
        }
    }

    public void validar() {
        this.validarFimSemana();
        this.validarTipoHorarioPermitida();
        this.validarHoraInformada();
        this.validarHoraAlmoco();
    }

    public void validar(Long totalAlocadoSegundos, Long totalTrabalhadoSegundos) {
        this.validar();
        this.validarTotalTrabalhadoMaiorTotalAlocado(totalAlocadoSegundos, totalTrabalhadoSegundos);
    }

    private void validarTotalTrabalhadoMaiorTotalAlocado(Long totalAlocadoSegundos, Long totalTrabalhadoSegundos) {
        if (totalAlocadoSegundos > totalTrabalhadoSegundos) {
            throw new ValidacaoNegocioException("Existe um horas alocadas que ultrapassam o novo total de horas trabalhado. Favor ajustar alocação antes de editar o horário.");
        }
    }

}
