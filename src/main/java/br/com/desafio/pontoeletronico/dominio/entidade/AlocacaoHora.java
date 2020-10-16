package br.com.desafio.pontoeletronico.dominio.entidade;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "alocacao_hora", schema = "pontoeletronico")
public class AlocacaoHora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "matricula")
    private Long matricula;
    @Column(name = "total_horas")
    private Long totalHoras;
    @Column(name = "data")
    private LocalDate data;
    @Column(name = "codigo_projeto")
    private Long codigoProjeto;

    public AlocacaoHora() { }

    public AlocacaoHora(Long matricula, Long totalHoras, LocalDate data, Long codigoProjeto) {
        this.matricula = matricula;
        this.totalHoras = totalHoras;
        this.data = data;
        this.codigoProjeto = codigoProjeto;
    }

    public Long getId() {
        return id;
    }

    public Long getMatricula() {
        return matricula;
    }

    public Long getTotalHoras() {
        return totalHoras;
    }

    public LocalDate getData() {
        return data;
    }

    public Long getCodigoProjeto() {
        return codigoProjeto;
    }
}
