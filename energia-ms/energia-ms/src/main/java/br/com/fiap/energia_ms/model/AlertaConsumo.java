package br.com.fiap.energia_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="TB_ALERTA_CONSUMO")
public class AlertaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@NotBlank
private String mensagem;

@NotNull
private LocalDate dataAlerta;

@NotNull
@ManyToOne
@JoinColumn(name="equipamento_id")
private Equipamento equipamento;
}
