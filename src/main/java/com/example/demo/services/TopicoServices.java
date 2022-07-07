package com.example.demo.services;

import com.example.demo.Module.Topico;
import com.example.demo.Module.dto.DetalheTopicoDto;
import com.example.demo.Module.dto.TopicoDto;
import com.example.demo.form.AtualizacaoTopicoForm;
import com.example.demo.form.TopicoForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

public interface TopicoServices {

    public Topico cadastrar(TopicoDto dto);

    public Optional<DetalheTopicoDto> detalhar(Long id);

    public Optional<TopicoDto> atualizar(Long id, AtualizacaoTopicoForm form);

    public void remover(Long id);

    public List<TopicoDto> lista();

}
