package br.com.SilvaDanilo.gerenciador_tarefas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    private boolean concluida;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        dataModificacao = LocalDateTime.now();
    }
}

