package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public HttpStatus listarEstudantes() {
		List<Estudante> estudantes = service.buscarEstudantes();
		if (estudantes != null && !estudantes.isEmpty()) {
			return HttpStatus.OK;
		}
		return HttpStatus.NOT_FOUND;
	}

	@PostMapping("add")
	public HttpStatus adicionarEstudante(@Valid @RequestBody Estudante estudante) {

		Estudante e = service.buscarEstudante(estudante.getId());
		if (e != null && e.getId() != null) {
			if (service.cadastrarEstudante(estudante)) {
				return HttpStatus.CREATED;
			}
		}

		return HttpStatus.BAD_REQUEST;
	}

	@GetMapping("editar/{id}")
	public ResponseEntity<?> exibirEdicaoEstudante(long id, Model model) {

		Estudante estudante = service.buscarEstudante(id);
		if (estudante != null ) {
			return new ResponseEntity<>(estudante, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@PostMapping("atualizar/{id}")
	public HttpStatus atualizarEstudante(@Valid @RequestBody Estudante estudante) {

		if (service.atualizarEstudante(estudante)) {
			return HttpStatus.OK;
		}

		return HttpStatus.NOT_MODIFIED;
	}

	@GetMapping("apagar/{id}")
	public HttpStatus apagarEstudante(@PathVariable("id") Long id) {

		if (service.apagarEstudante(id)) {
			return HttpStatus.OK;
		}

		return HttpStatus.NOT_MODIFIED;
	}

}
