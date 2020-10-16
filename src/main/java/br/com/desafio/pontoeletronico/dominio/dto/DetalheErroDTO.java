package br.com.desafio.pontoeletronico.dominio.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class DetalheErroDTO {

    private final LocalDateTime localDateTime;
    private final List<String> erros;

    public DetalheErroDTO(LocalDateTime localDateTime, String mensagem) {
        this.localDateTime = localDateTime;
        this.erros = new ArrayList<>();
        this.erros.add(mensagem);
    }

    public DetalheErroDTO(LocalDateTime localDateTime, List<String> erros) {
        this.localDateTime = localDateTime;
        this.erros = erros;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public List<String> getErros() {
        return erros;
    }

}
