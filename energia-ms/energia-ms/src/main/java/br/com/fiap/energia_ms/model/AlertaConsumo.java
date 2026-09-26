package br.com.fiap.energia_ms.model;

import jakarta.persistence.*;
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

    private String mensagem;

    private LocalDate dataAlerta;

    @ManyToOne
    @JoinColumn(name="equipamento_id")
    private Equipamento equipamento;
}