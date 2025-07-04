package br.com.SilvaDanilo.gerenciador_tarefas.service;

import br.com.SilvaDanilo.gerenciador_tarefas.model.Tarefa;
import br.com.SilvaDanilo.gerenciador_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {


    @Autowired
    TarefaRepository tarefaRepository;


    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setConcluida(false);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
    }

    public Tarefa atualizarTarefa(long id, Tarefa detalhesTarefa) {
        Tarefa tarefaAtual = buscarTarefaPorId(detalhesTarefa.getId());

        tarefaAtual.setTitulo(detalhesTarefa.getTitulo());
        tarefaAtual.setConcluida(detalhesTarefa.isConcluida());
        return tarefaRepository.save(tarefaAtual);
    }

    public void excluirTarefa(long id){
        buscarTarefaPorId(id);
        tarefaRepository.deleteById(id);
    }
}
