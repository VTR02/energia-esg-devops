package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.LimiteConsumo;
import br.com.fiap.energia_ms.repository.LimiteConsumoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/limites")
public class LimiteConsumoController {

    private final LimiteConsumoRepository repository;

    public LimiteConsumoController(
            LimiteConsumoRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<LimiteConsumo>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<LimiteConsumo> cadastrar(
            @RequestBody @Valid LimiteConsumo limite) {

        LimiteConsumo salvo = repository.save(limite);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LimiteConsumo> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LimiteConsumo limiteAtualizado) {

        return repository.findById(id)
                .map(limite -> {

                    limite.setLimiteKwh(
                            limiteAtualizado.getLimiteKwh());

                    limite.setEquipamento(
                            limiteAtualizado.getEquipamento());

                    return ResponseEntity.ok(repository.save(limite));
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
