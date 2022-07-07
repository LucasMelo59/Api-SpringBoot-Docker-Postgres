package com.example.demo.Module.dto;

import com.example.demo.Module.Curso;
import com.example.demo.Module.Topico;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class TopicoDto {

    private Long id;
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;
    @NotNull
    @NotEmpty
    private String nomeCurso;

    private LocalDateTime dataCriacao;

    public TopicoDto(Topico topico) {

        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();

    }

    public TopicoDto() {

    }

    public static TopicoDto converter(Topico topico) {

        return new TopicoDto(topico);
    }


}
