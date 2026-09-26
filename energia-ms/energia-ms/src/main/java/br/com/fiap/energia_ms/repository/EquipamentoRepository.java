package br.com.fiap.energia_ms.repository;

import br.com.fiap.energia_ms.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}