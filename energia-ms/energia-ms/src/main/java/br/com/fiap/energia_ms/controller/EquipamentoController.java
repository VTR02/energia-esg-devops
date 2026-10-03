package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.Equipamento;
import br.com.fiap.energia_ms.repository.EquipamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoRepository repository;

    public EquipamentoController(EquipamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(
            @RequestBody @Valid Equipamento equipamento) {

        Equipamento salvo = repository.save(equipamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid Equipamento equipamentoAtualizado) {

        return repository.findById(id)
                .map(equipamento -> {

                    equipamento.setNome(equipamentoAtualizado.getNome());
                    equipamento.setSetor(equipamentoAtualizado.getSetor());
                    equipamento.setPotenciaWatts(
                            equipamentoAtualizado.getPotenciaWatts());

                    return ResponseEntity.ok(repository.save(equipamento));
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
