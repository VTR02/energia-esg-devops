package br.com.fiap.energia_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="TB_DESLIGAMENTO")
public class Desligamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataHora;

    @NotBlank
    private String motivo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
}
