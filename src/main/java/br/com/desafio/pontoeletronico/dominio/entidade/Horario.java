package br.com.desafio.pontoeletronico.dominio.entidade;


import br.com.desafio.pontoeletronico.dominio.enums.TipoHorarioEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "horario", schema = "pontoeletronico")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "matricula")
    private Long matricula;
    @Column(name = "hora")
    private String hora;
    @Column(name = "data")
    private LocalDate data;
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoHorarioEnum tipoHorarioEnum;

    public Horario() {}

    public Horario(Long matricula, String hora, LocalDate data, TipoHorarioEnum tipoHorarioEnum) {
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
