package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.LimiteConsumo;
import br.com.fiap.energia_ms.repository.LimiteConsumoRepository;
import jakarta.validation.Valid;
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
    public List<LimiteConsumo> listar() {
        return repository.findAll();
    }

    @PostMapping
    public LimiteConsumo cadastrar(
            @RequestBody @Valid LimiteConsumo limite) {

        return repository.save(limite);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public LimiteConsumo atualizar(
            @PathVariable Long id,
            @RequestBody LimiteConsumo limiteAtualizado) {

        LimiteConsumo limite = repository.findById(id)
                .orElseThrow();

        limite.setLimiteKwh(
                limiteAtualizado.getLimiteKwh());

        limite.setEquipamento(
                limiteAtualizado.getEquipamento());

        return repository.save(limite);
    }
}