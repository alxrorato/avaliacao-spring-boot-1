package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

public interface EstudanteService {

	List<Estudante> buscarEstudantes();

	Boolean cadastrarEstudante(@Valid Estudante estudante);

	Estudante buscarEstudante(long id);

	Boolean atualizarEstudante(@Valid Estudante estudante);
	
	Boolean apagarEstudante(Long id);
}
