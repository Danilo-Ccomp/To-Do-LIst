package br.com.SilvaDanilo.gerenciador_tarefas.controller;

import br.com.SilvaDanilo.gerenciador_tarefas.model.Tarefa;
import br.com.SilvaDanilo.gerenciador_tarefas.repository.TarefaRepository;
import br.com.SilvaDanilo.gerenciador_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> listartodas() {
        List<Tarefa> tarefas = tarefaService.listarTodasTarefas();
        return ResponseEntity.ok(tarefas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable long id){
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
        return ResponseEntity.ok(tarefa);
    }
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa){
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable long id, @RequestBody Tarefa detalhesTarefa){
            Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, detalhesTarefa);
            return ResponseEntity.ok(tarefaAtualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable long id){
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
