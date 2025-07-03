package br.com.SilvaDanilo.gerenciador_tarefas.repository;

import br.com.SilvaDanilo.gerenciador_tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
