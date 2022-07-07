package com.example.demo.repositories;

import com.example.demo.Module.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository  extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoNome(String nomeCurso);
}
