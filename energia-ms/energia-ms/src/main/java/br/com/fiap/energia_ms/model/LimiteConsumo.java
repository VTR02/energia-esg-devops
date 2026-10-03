package br.com.fiap.energia_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull
    @Positive
    private Double limiteKwh;
    
    @OneToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
}
