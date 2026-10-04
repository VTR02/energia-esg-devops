package br.com.fiap.energia_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TB_CONSUMO_ENERGIA")
public class ConsumoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Double consumoKwh;
    
    @NotNull
    private LocalDate dataRegistro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
}
