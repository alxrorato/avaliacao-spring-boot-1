package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

@RestController
@RequestMapping("/estudantes")
public class EstudanteRestController {

	@Autowired
	private EstudanteService service;

	// Inicializa��o do Repository em EstudanteSeeviceImpl
	//
	// @GetMapping("criar")
	// public String iniciarCastrado(Estudante estudante) {
	// return "cadastrar-estudante";
	// }

	@GetMapping("listar")
	public ResponseEntity<?> listarEstudantes() {
		List<Estudante> estudantes = service.buscarEstudantes();
		return new ResponseEntity<>(estudantes, HttpStatus.OK);	
	}

	@PostMapping("add")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> adicionarEstudante(@Valid @RequestBody Estudante estudante) {
		return new ResponseEntity<>(service.cadastrarEstudante(estudante), HttpStatus.CREATED);
	}
	
	@GetMapping("editar/{id}")
	public ResponseEntity<?> exibirEdicaoEstudante(@PathVariable("id") long id, Model model) {

		Estudante estudante = service.buscarEstudante(id);
		if (estudante != null ) {
			return new ResponseEntity<>(estudante, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	//@PostMapping("atualizar/{id}")
	@PutMapping("atualizar")
	public ResponseEntity<?> atualizarEstudante(@Valid @RequestBody Estudante estudante) {
		service.atualizarEstudante(estudante);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("apagar/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> apagarEstudante(@PathVariable("id") Long id) {
		Estudante estudante = service.buscarEstudante(id);
		if (estudante == null ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		service.apagarEstudante(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
