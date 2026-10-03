package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.AlertaConsumo;
import br.com.fiap.energia_ms.repository.AlertaConsumoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaConsumoController {

    private final AlertaConsumoRepository repository;

    public AlertaConsumoController(
            AlertaConsumoRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<AlertaConsumo>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<AlertaConsumo> cadastrar(
            @RequestBody @Valid AlertaConsumo alerta) {

        AlertaConsumo salvo = repository.save(alerta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaConsumo> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AlertaConsumo alertaAtualizado) {

        return repository.findById(id)
                .map(alerta -> {

                    alerta.setMensagem(
                            alertaAtualizado.getMensagem());

                    alerta.setDataAlerta(
                            alertaAtualizado.getDataAlerta());

                    alerta.setEquipamento(
                            alertaAtualizado.getEquipamento());

                    return ResponseEntity.ok(repository.save(alerta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
