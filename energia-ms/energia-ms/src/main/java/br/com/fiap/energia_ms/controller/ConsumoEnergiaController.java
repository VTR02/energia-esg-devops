package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.ConsumoEnergia;
import br.com.fiap.energia_ms.repository.ConsumoEnergiaRepository;
import jakarta.validation.Valid;
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
    public List<ConsumoEnergia> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ConsumoEnergia cadastrar(
            @RequestBody @Valid ConsumoEnergia consumo) {

        return repository.save(consumo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PutMapping("/{id}")
    public ConsumoEnergia atualizar(
            @PathVariable Long id,
            @RequestBody ConsumoEnergia consumoAtualizado) {

        ConsumoEnergia consumo = repository.findById(id)
                .orElseThrow();

        consumo.setConsumoKwh(
                consumoAtualizado.getConsumoKwh());

        consumo.setDataRegistro(
                consumoAtualizado.getDataRegistro());

        consumo.setEquipamento(
                consumoAtualizado.getEquipamento());

        return repository.save(consumo);
    }
}