package br.com.desafio.pontoeletronico.negocio;

import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;

public final class ValidacaoAlocacaoHora {

    private final Long totalTrabalhado;
    private final Long totalAlocado;

    public ValidacaoAlocacaoHora(Long totalTrabalhado, Long totalAlocado) {
        this.totalTrabalhado = totalTrabalhado;
        this.totalAlocado = totalAlocado;
    }

    public void validar() {
        if (totalTrabalhado < totalAlocado) {
            throw new ValidacaoNegocioException("O total alocado é maior que o total trabalhado");
        }
    }
}
