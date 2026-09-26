package br.com.fiap.energia_ms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_LIMITE_CONSUMO")
public class LimiteConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double limiteKwh;

    @OneToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
}