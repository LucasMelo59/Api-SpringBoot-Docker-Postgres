package com.example.demo.controller;

import com.example.demo.Module.Topico;
import com.example.demo.Module.dto.DetalheTopicoDto;
import com.example.demo.Module.dto.TopicoDto;
import com.example.demo.form.AtualizacaoTopicoForm;
import com.example.demo.form.TopicoForm;
import com.example.demo.services.TopicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {


    @Autowired
    private TopicoServices topicoServices;


    @GetMapping
    public List<TopicoDto> lista() {
        return topicoServices.lista();
    }

    @PostMapping
    @Transactional
    public Topico cadastrar(@Valid @RequestBody TopicoDto dto) {
        return topicoServices.cadastrar(dto);
    }


    @GetMapping("/{id}")
    public DetalheTopicoDto detalhar(@PathVariable Long id) {

        return topicoServices.detalhar(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado"));
    }

    @PutMapping("/{id}")
    @Transactional
    public TopicoDto atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {

        //return topicoServ.orElseThrow(() -> new EntityNotFoundException());

        return topicoServices.atualizar(id, form).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        topicoServices.remover(id);
    }
}

