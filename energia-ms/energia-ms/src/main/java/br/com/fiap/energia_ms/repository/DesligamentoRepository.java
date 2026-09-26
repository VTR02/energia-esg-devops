package br.com.fiap.energia_ms.repository;

import br.com.fiap.energia_ms.model.Desligamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesligamentoRepository
        extends JpaRepository<Desligamento, Long> {

}