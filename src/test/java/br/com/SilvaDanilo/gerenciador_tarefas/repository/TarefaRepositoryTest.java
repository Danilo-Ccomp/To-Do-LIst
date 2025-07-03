package br.com.SilvaDanilo.gerenciador_tarefas.repository;

import br.com.SilvaDanilo.gerenciador_tarefas.model.Tarefa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class TarefaRepositoryTest {
    @Autowired
    private TarefaRepository tarefaRepository;
    @Test
    public void quandoSalvarTarefa_entaoEncontrarPeloId() {
        //Arrange(Preparar)
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Estudar Spring Boot");
        tarefa.setConcluida(false);

        //Act(Agir)
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        //Assert(Assegurar)
        assertThat(tarefaSalva).isNotNull(); // Verifica se o objeto salvo não é nulo
        assertThat(tarefaSalva.getId()).isNotNull(); // Verifica se um ID foi gerado
        assertThat(tarefaSalva.getTitulo()).isEqualTo("Estudar Testes em Spring Boot");
        assertThat(tarefaSalva.isConcluida()).isFalse();
        assertThat(tarefaSalva.getDataCriacao()).isNotNull(); // Verifica se a data de criação foi setada pelo @PrePersist
    }
}
