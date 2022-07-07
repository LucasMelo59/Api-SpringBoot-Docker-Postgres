package com.example.demo.services.impl;

import com.example.demo.Module.Curso;
import com.example.demo.Module.Topico;
import com.example.demo.Module.dto.DetalheTopicoDto;
import com.example.demo.Module.dto.TopicoDto;
import com.example.demo.form.AtualizacaoTopicoForm;
import com.example.demo.form.TopicoForm;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.TopicoRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class TopicoServicesImplTest {
    private static final Long ID = 1L;


    @InjectMocks
    private TopicoServicesImpl service;
    @Mock
    private TopicoRepository repository;
    @Mock
    private CursoRepository cursoRepository;

    @Test
    void mockito() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void detalhar() { // procurando id e retornando detalhetopicoDto

        Optional<DetalheTopicoDto> response;
        response = service.detalhar(ID);
        Assertions.assertTrue(response.isPresent());
    }


    @Test
    void whenFindByidNotFound(){ // detalhar...
        when(repository.findById(Mockito.anyLong())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

    try{
        service.detalhar(ID);
    } catch(Exception ex){
        assertEquals(ResponseStatusException.class, ex.getClass());
        //assertEquals(ResponseStatusException.class,"Objeto não encontrado", ex.getMessage());

    }

    }


    @Test
    void cadastrar(){
        String TITULO = "ola";
        String MENSAGEM = "esta funcional";
        Curso CURSO = new Curso("pedro");
        Topico topico = new Topico(TITULO, MENSAGEM, CURSO);
        TopicoDto topicoDto = new TopicoDto(topico);
        Topico response = service.cadastrar(topicoDto);
        assertNotNull(response);
        assertEquals(Topico.class, response.getClass());
        //assertEquals(ID, response.getId());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(MENSAGEM, response.getMensagem());
    }

    @Test
    void atualizar(){
       String TITULO = "ola";
     String MENSAGEM = "esta funcional";
       Curso CURSO = new Curso("pedro");
       Topico topico = new Topico(TITULO, MENSAGEM, CURSO);
      TopicoDto topicoDto = new TopicoDto(topico);
      AtualizacaoTopicoForm form = new AtualizacaoTopicoForm();
       Optional<TopicoDto> response = service.atualizar(ID, form);
        assertNotNull(response);
      Assertions.assertTrue(response.isPresent());
    }

    @Test
    void atualizarExcept(){
        when(repository.saveAndFlush(any())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        try{
            AtualizacaoTopicoForm form = new AtualizacaoTopicoForm();
            service.atualizar(ID, form );
        } catch(Exception ex){
            assertEquals(ResponseStatusException.class, ex.getClass());
        }

    }

    @Test
    void remover() {
    doNothing().when(repository).deleteById(anyLong());
    service.remover(ID);
    verify(repository, times(1)).deleteById(anyLong());
    }
    @Test
    void removerExcept(){
        when(repository.findById(anyLong())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        try{
            service.remover(ID);
        } catch (Exception ex){
        assertEquals(ResponseStatusException.class, ex.getClass());
        }

    }



    @Test
    void lista() {
    List<TopicoDto> response = service.lista();

    assertNotNull(response);
    assertEquals(1, response.size());
    assertEquals(TopicoDto.class, response.get(0).getClass());
//   assertEquals(ID, response.get(0).getId());
    }

    @BeforeEach
    private void startUser() {
        String TITULO = "ola";
         String MENSAGEM = "esta funcional";
         Curso CURSO = new Curso("pedro");
        Topico topico = new Topico(TITULO, MENSAGEM, CURSO);
        topico.setId(ID);
        TopicoDto topicoDto = new TopicoDto(topico);
        Optional<Topico> optionalTopico = Optional.of(topico);
        when(repository.findById(Mockito.anyLong())).thenReturn(optionalTopico);
        when(repository.findAll()).thenReturn(List.of(topico));
        when(repository.save(any())).thenReturn(topico);

    }


}