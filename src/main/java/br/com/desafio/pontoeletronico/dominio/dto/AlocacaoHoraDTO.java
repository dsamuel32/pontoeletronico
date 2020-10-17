package br.com.desafio.pontoeletronico.dominio.dto;

import java.time.LocalDate;

public class AlocacaoHoraDTO {

    private Long id;
    private Long matricula;
    private Long codigoProjeto;
    private String horas;
    private LocalDate data;

    public AlocacaoHoraDTO() { }

    public AlocacaoHoraDTO(Long matricula, Long codigoProjeto, String horas, LocalDate data) {
        this.matricula = matricula;
        this.codigoProjeto = codigoProjeto;
        this.horas = horas;
        this.data = data;
    }

    public AlocacaoHoraDTO(Long id, Long matricula, Long codigoProjeto, String horas, LocalDate data) {
        this.id = id;
        this.matricula = matricula;
        this.codigoProjeto = codigoProjeto;
        this.horas = horas;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Long getMatricula() {
        return matricula;
    }

    public Long getCodigoProjeto() {
        return codigoProjeto;
    }

    public String getHoras() {
        return horas;
    }

    public LocalDate getData() {
        return data;
    }
}
