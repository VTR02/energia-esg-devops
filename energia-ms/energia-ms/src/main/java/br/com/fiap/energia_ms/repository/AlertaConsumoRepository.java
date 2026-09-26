package br.com.fiap.energia_ms.repository;

import br.com.fiap.energia_ms.model.AlertaConsumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaConsumoRepository
        extends JpaRepository<AlertaConsumo, Long> {

}