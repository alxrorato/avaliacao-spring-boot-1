package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<Estudante> listarEstudantes() {
		return service.buscarEstudantes();
	}

	@PostMapping("add")
	public HttpStatus adicionarEstudante(@Valid Estudante estudante) {

		if (service.cadastrarEstudante(estudante)) {
			return HttpStatus.OK;
		}

		return HttpStatus.NOT_FOUND;
	}

	@GetMapping("editar/{id}")
	public Estudante exibirEdicaoEstudante(long id, Model model) {

		Estudante estudante = service.buscarEstudante(id);

		return estudante;
	}

	@PostMapping("atualizar/{id}")
	public HttpStatus atualizarEstudante(@Valid Estudante estudante) {

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
