package br.com.desafio.pontoeletronico.dominio.dto;

import java.time.LocalDate;

public class TotalAlocacaoDTO {

    private final String total;
    private final String restantes;
    private final LocalDate data;

    public TotalAlocacaoDTO(String total, String restantes, LocalDate data) {
        this.total = total;
        this.restantes = restantes;
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public String getRestantes() {
        return restantes;
    }

    public LocalDate getData() {
        return data;
    }
}
