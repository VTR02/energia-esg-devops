package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.Equipamento;
import br.com.fiap.energia_ms.repository.EquipamentoRepository;
import jakarta.validation.Valid;
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
    public List<Equipamento> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Equipamento cadastrar(@RequestBody @Valid Equipamento equipamento) {
        return repository.save(equipamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable Long id,
                                 @RequestBody Equipamento equipamentoAtualizado) {

        Equipamento equipamento = repository.findById(id)
                .orElseThrow();

        equipamento.setNome(equipamentoAtualizado.getNome());
        equipamento.setSetor(equipamentoAtualizado.getSetor());
        equipamento.setPotenciaWatts(
                equipamentoAtualizado.getPotenciaWatts());

        return repository.save(equipamento);
    }
}


