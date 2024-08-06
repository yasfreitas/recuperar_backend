package com.projetocurso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetocurso.entities.Curso;
import com.projetocurso.services.CursoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/cursos")
public class CursoController {

	private final CursoService cursoService;

	@Autowired
	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> buscaCursoControlId(@PathVariable Long id){
		Curso curso  = cursoService.buscaCursoId(id);
		if(curso != null) {
			return ResponseEntity.ok(curso);
		}
		else {
			return ResponseEntity.notFound().build();
		}

	}
	@GetMapping
	public ResponseEntity<List<Curso>> buscaTodosCursosControl(){
		List<Curso> cursos = cursoService.buscaTodosCursos();
		return ResponseEntity.ok(cursos);
	}

	@PostMapping
	public ResponseEntity<Curso> salvaCursosControl(@RequestBody @Valid Curso curso){
		Curso salvaCurso = cursoService.salvaCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCurso);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Curso> alteraCursoControl(@PathVariable Long id, @RequestBody @Valid Curso curso){
		Curso alteraCurso = cursoService.alterarCurso(id, curso);
		if(alteraCurso != null) {
			return ResponseEntity.ok(curso);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaCursoControl(@PathVariable Long id){
		boolean apagar = cursoService.apagarCurso(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Curso foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}