package com.example.demo.Module.dto;

import com.example.demo.Module.StatusTopico;
import com.example.demo.Module.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalheTopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDto> respostas;


    public DetalheTopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        if (topico.getAutor() != null) {
            this.nomeAutor = topico.getAutor().getNome();
        }
        if (topico.getStatus() != null) {
            this.status = topico.getStatus();
        }
        if (topico.getRespostas() != null && !topico.getRespostas().isEmpty()) {
            this.respostas = new ArrayList<>();
            this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
        }
    }

    public static DetalheTopicoDto converter(Topico topico) {
        return new DetalheTopicoDto(topico);

    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
