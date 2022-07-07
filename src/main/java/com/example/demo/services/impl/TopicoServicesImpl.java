package com.example.demo.services.impl;

import com.example.demo.Module.Curso;
import com.example.demo.Module.Topico;
import com.example.demo.Module.dto.DetalheTopicoDto;
import com.example.demo.Module.dto.TopicoDto;
import com.example.demo.form.AtualizacaoTopicoForm;
import com.example.demo.form.TopicoForm;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.TopicoRepository;
import com.example.demo.services.TopicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoServicesImpl implements TopicoServices {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    public Topico cadastrar(TopicoDto dto) {
        Curso curso = cursoRepository.findByNome(dto.getNomeCurso());
        Topico topico = new Topico(dto.getTitulo(), dto.getMensagem(), curso);
        return topicoRepository.save(topico);
//        Optional<cursor> restrict = Optional.ofNullable(cursorRepository.findByNome(form.getNomeCursor()));
        //if(restrict.isPresent()) {
//        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        //} else{
        //  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        //}
    }

    @Override
    public Optional<DetalheTopicoDto> detalhar(Long id) {
        return topicoRepository.findById(id).map(DetalheTopicoDto::converter);
//        return topicoRepository.findById(id).map(topico -> DetalheTopicoDto.converter(topico));
//        if(topico.isPresent()) {
//            return ResponseEntity.ok(new DetalheTopicoDto(topico.get()));
//        }
//
//        return ResponseEntity.notFound().build();
    }


    @Override
    public Optional<TopicoDto> atualizar(Long id, AtualizacaoTopicoForm form) {
//        return form.actualization(id,topicRepository).map(topic -> TopicDto.converter(topico));
        Optional<Topico> topico = topicoRepository.findById(id);
        topico.ifPresent(t -> {
            t.setTitulo(form.getTitulo());
            t.setMensagem(form.getMensagem());
            topicoRepository.saveAndFlush(t);
        });

        return topico.map(TopicoDto::converter);
    }
//        } else{
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//        if(optional.isPresent()) {
//            Topic topic = form.actualization(id, topicRepository);
//            return ResponseEntity.ok(new TopicDto(topic));
//        }
//
//        return ResponseEntity.notFound().build();


    @Override
    public void remover(Long id) {
        Optional<Topico> remover = topicoRepository.findById(id);
        if (remover.isPresent()) {
            topicoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public List<TopicoDto> lista() {
        return topicoRepository.findAll().stream().map(TopicoDto::new).collect(Collectors.toList());
        // lizard o method converter em vez de new
    }
}



