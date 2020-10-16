package br.com.desafio.pontoeletronico.dominio.enums;

public enum TipoHorarioEnum {

    ENTRADA ("Inicio do expediente"),
    SAIDA_ALMOCO ("Saída para o almoço"),
    RETORNO_ALMOCO ("Retorno para o almoço"),
    SAIDA ("Fim do expediente");

    private final String descricao;

    TipoHorarioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
