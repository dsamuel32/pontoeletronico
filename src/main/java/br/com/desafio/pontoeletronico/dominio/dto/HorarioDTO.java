package br.com.desafio.pontoeletronico.dominio.dto;

import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;

import java.time.LocalDate;

public class HorarioDTO {

    private Long id;
    private Long matricula;
    private String hora;
    private LocalDate data;
    private TipoHorarioEnum tipoHorarioEnum;

    public HorarioDTO() {}
    public HorarioDTO(Long id, Long matricula, String hora, LocalDate data, TipoHorarioEnum tipoHorarioEnum) {
        this.id = id;
        this.matricula = matricula;
        this.hora = hora;
        this.data = data;
        this.tipoHorarioEnum = tipoHorarioEnum;
    }

    public Long getId() {
        return id;
    }

    public Long getMatricula() {
        return matricula;
    }

    public String getHora() {
        return hora;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoHorarioEnum getTipoHorarioEnum() {
        return tipoHorarioEnum;
    }
}
