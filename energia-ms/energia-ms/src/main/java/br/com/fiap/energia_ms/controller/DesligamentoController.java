package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.Desligamento;
import br.com.fiap.energia_ms.repository.DesligamentoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desligamentos")
public class DesligamentoController {

    private final DesligamentoRepository repository;

    public DesligamentoController(
            DesligamentoRepository repository){

        this.repository = repository;
    }

    @GetMapping
    public List<Desligamento> listar(){

        return repository.findAll();
    }

    @PostMapping
    public Desligamento cadastrar(
            @RequestBody Desligamento desligamento){

        return repository.save(desligamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable Long id){

        repository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Desligamento atualizar(
            @PathVariable Long id,
            @RequestBody Desligamento desligamentoAtualizado) {

        Desligamento desligamento = repository.findById(id)
                .orElseThrow();

        desligamento.setDataHora(
                desligamentoAtualizado.getDataHora());

        desligamento.setMotivo(
                desligamentoAtualizado.getMotivo());

        desligamento.setEquipamento(
                desligamentoAtualizado.getEquipamento());

        return repository.save(desligamento);
    }
}