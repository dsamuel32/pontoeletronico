package br.com.desafio.pontoeletronico.repository;

import br.com.desafio.pontoeletronico.dominio.entidade.AlocacaoHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoHoraRepository extends JpaRepository<AlocacaoHora, Long> {
}
