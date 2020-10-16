package br.com.desafio.pontoeletronico.repository;

import br.com.desafio.pontoeletronico.dominio.entidade.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query("SELECT h FROM Horario h WHERE h.matricula = :matricula AND h.data = :data ORDER BY h.id DESC")
    public Horario recuperarUltimoHorario(@Param("matricula") Long matricula,
                                          @Param("data") LocalDate data);

    public Horario findTopByMatriculaAndDataOrderByIdDesc(Long matricula, LocalDate data);

}
