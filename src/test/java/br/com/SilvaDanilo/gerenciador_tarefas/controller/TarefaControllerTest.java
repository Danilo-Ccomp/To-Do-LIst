package br.com.SilvaDanilo.gerenciador_tarefas.controller;


import br.com.SilvaDanilo.gerenciador_tarefas.model.Tarefa;
import br.com.SilvaDanilo.gerenciador_tarefas.service.TarefaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaController.class)
public class TarefaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig{

        @Bean
        public TarefaService tarefaService(){
            return Mockito.mock(TarefaService.class);
        }
    }

    @Test
    public void deveCriarNovaTarefa_quandoDadosValidos() throws Exception {
        Tarefa tarefaParaCriar = new Tarefa();
        tarefaParaCriar.setTitulo("Minha nova tarefa");

        Tarefa tarefaSalva = new Tarefa();
        tarefaSalva.setId(1L);
        tarefaSalva.setTitulo("Minha nova tarefa");
        tarefaSalva.setConcluida(false);

        when(tarefaService.criarTarefa(any(Tarefa.class))).thenReturn(tarefaSalva);

        mockMvc.perform(post("/tarefa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarefaParaCriar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Minha nova tarefa"));
    }

    @Test
    public void deveRetornarListaDeTarefas() throws Exception {
        Tarefa tarefaExemplo = new Tarefa();
        tarefaExemplo.setId(1L);
        tarefaExemplo.setTitulo("Tarefa de Teste");

        when(tarefaService.listarTodasTarefas()).thenReturn(Collections.singletonList(tarefaExemplo));

        mockMvc.perform(get("/tarefas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Tarefa de Teste"));
    }

}
