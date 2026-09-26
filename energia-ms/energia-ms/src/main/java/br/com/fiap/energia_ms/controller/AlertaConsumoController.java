package br.com.fiap.energia_ms.controller;

import br.com.fiap.energia_ms.model.AlertaConsumo;
import br.com.fiap.energia_ms.repository.AlertaConsumoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaConsumoController {

    private final AlertaConsumoRepository repository;

    public AlertaConsumoController(
            AlertaConsumoRepository repository){

        this.repository = repository;
    }

    @GetMapping
    public List<AlertaConsumo> listar(){

        return repository.findAll();
    }

    @PostMapping
    public AlertaConsumo cadastrar(
            @RequestBody AlertaConsumo alerta){

        return repository.save(alerta);
    }

    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable Long id){

        repository.deleteById(id);
    }
    @PutMapping("/{id}")
    public AlertaConsumo atualizar(
            @PathVariable Long id,
            @RequestBody AlertaConsumo alertaAtualizado) {

        AlertaConsumo alerta = repository.findById(id)
                .orElseThrow();

        alerta.setMensagem(
                alertaAtualizado.getMensagem());

        alerta.setDataAlerta(
                alertaAtualizado.getDataAlerta());

        alerta.setEquipamento(
                alertaAtualizado.getEquipamento());

        return repository.save(alerta);
    }
}