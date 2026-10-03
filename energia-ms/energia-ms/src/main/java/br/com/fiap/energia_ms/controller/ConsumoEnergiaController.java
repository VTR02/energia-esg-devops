package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.ConsumoEnergia;
import br.com.fiap.energia_ms.repository.ConsumoEnergiaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumo")
public class ConsumoEnergiaController {

    private final ConsumoEnergiaRepository repository;

    public ConsumoEnergiaController(
            ConsumoEnergiaRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ConsumoEnergia>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<ConsumoEnergia> cadastrar(
            @RequestBody @Valid ConsumoEnergia consumo) {

        ConsumoEnergia salvo = repository.save(consumo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoEnergia> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ConsumoEnergia consumoAtualizado) {

        return repository.findById(id)
                .map(consumo -> {

                    consumo.setConsumoKwh(
                            consumoAtualizado.getConsumoKwh());

                    consumo.setDataRegistro(
                            consumoAtualizado.getDataRegistro());

                    consumo.setEquipamento(
                            consumoAtualizado.getEquipamento());

                    return ResponseEntity.ok(repository.save(consumo));
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
