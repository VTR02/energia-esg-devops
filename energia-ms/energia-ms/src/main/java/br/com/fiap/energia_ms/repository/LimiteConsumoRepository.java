package br.com.fiap.energia_ms.repository;

import br.com.fiap.energia_ms.model.LimiteConsumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimiteConsumoRepository
        extends JpaRepository<LimiteConsumo, Long> {
}