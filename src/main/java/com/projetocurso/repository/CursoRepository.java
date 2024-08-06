package com.projetocurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetocurso.entities.Curso;


public interface CursoRepository extends JpaRepository<Curso, Long>{}