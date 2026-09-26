package br.com.fiap.energia_ms.model;

import jakarta.persistence.*;
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

    private LocalDateTime dataHora;

    private String motivo;

    @ManyToOne
    @JoinColumn(name="equipamento_id")
    private Equipamento equipamento;
}