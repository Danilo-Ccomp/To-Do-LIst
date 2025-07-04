package br.com.SilvaDanilo.gerenciador_tarefas.service;

import br.com.SilvaDanilo.gerenciador_tarefas.model.Tarefa;
import br.com.SilvaDanilo.gerenciador_tarefas.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {
    @Mock
    private TarefaRepository tarefaRepositoryMock;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    public void  deveRetornarTarefa_quandoTarefaExiste(){
        Tarefa tarefaExemplo = new Tarefa();
        tarefaExemplo.setId(1L);
        tarefaExemplo.setTitulo("Tarefa de Teste");

        when(tarefaRepositoryMock.findById(1L)).thenReturn(Optional.of(tarefaExemplo));


        Tarefa tarefaEncontrada = tarefaService.buscarTarefaPorId(1L);


        assertThat(tarefaEncontrada).isNotNull();
        assertThat(tarefaEncontrada.getTitulo()).isEqualTo("Tarefa de Teste");


    }

    @Test
    public void deveLancarExcecao_quandoTarefaNaoExiste(){
        when(tarefaRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> tarefaService.buscarTarefaPorId(1L));

        assertThat(exception.getMessage()).isEqualTo("Tarefa não encontrada com este id");
    }

    @Test
    public void deveSalvarTarefa_quandoCriarNovaTarefa(){
        Tarefa tarefaParaSalvar = new Tarefa();
        tarefaParaSalvar.setTitulo("Nova Tarefa");

        when(tarefaRepositoryMock.save(any(Tarefa.class))).thenReturn(tarefaParaSalvar);

        Tarefa tarefaSalva = tarefaService.criarTarefa(tarefaParaSalvar);

        assertThat(tarefaSalva).isNotNull();
        assertThat(tarefaSalva.getTitulo()).isEqualTo("Nova Tarefa");
        assertThat(tarefaSalva.isConcluida()).isFalse();

        verify(tarefaRepositoryMock, times(1)).save(any(Tarefa.class));
    }
}
