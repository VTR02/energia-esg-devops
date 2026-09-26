package br.com.fiap.energia_ms.repository;

import br.com.fiap.energia_ms.model.ConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Long> {
}