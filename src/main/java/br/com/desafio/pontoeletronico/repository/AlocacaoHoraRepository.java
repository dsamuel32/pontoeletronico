package br.com.desafio.pontoeletronico.repository;

import br.com.desafio.pontoeletronico.dominio.entidade.AlocacaoHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AlocacaoHoraRepository extends JpaRepository<AlocacaoHora, Long> {

    @Query("SELECT SUM(a.totalSegundos) FROM AlocacaoHora a WHERE a.matricula = :matricula AND a.data = :data")
    Optional<Integer> recuperarTotalAlocado(@Param("matricula") Long matricula, @Param("data")LocalDate data);
}
