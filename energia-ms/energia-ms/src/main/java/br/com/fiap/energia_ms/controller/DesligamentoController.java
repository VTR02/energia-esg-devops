package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.Desligamento;
import br.com.fiap.energia_ms.repository.DesligamentoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desligamentos")
public class DesligamentoController {

    private final DesligamentoRepository repository;

    public DesligamentoController(
            DesligamentoRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Desligamento>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Desligamento> cadastrar(
            @RequestBody @Valid Desligamento desligamento) {

        Desligamento salvo = repository.save(desligamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Desligamento> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid Desligamento desligamentoAtualizado) {

        return repository.findById(id)
                .map(desligamento -> {

                    desligamento.setDataHora(
                            desligamentoAtualizado.getDataHora());

                    desligamento.setMotivo(
                            desligamentoAtualizado.getMotivo());

                    desligamento.setEquipamento(
                            desligamentoAtualizado.getEquipamento());

                    return ResponseEntity.ok(
                            repository.save(desligamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
