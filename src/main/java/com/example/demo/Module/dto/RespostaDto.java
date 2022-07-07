package com.example.demo.Module.dto;

import com.example.demo.Module.Resposta;

import java.time.LocalDateTime;

public class RespostaDto {

    private Long id;
    private String mensagem;
    private LocalDateTime datacriacao;
    private String nomeAutor;


    public RespostaDto(Resposta resposta){
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.datacriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }
}
