package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

@Service
public class EstudanteServiceImpl implements EstudanteService {

	@Autowired
	private EstudanteRepository repository;

	// @Override
	public Boolean cadastrarEstudante(@Valid Estudante estudante) {

		if (repository.save(estudante) != null) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	// @Override
	public Boolean atualizarEstudante(@Valid Estudante estudante) {

		if (repository.save(estudante) != null) {
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	// @Override
	public List<Estudante> buscarEstudantes() {
		Iterable<Estudante> it = repository.findAll();

		List<Estudante> estudantes = new ArrayList<Estudante>();

		for (Estudante e : it) {
			estudantes.add(e);
		}

		return estudantes;
	}

	// @Override
	public Estudante buscarEstudante(long id) {

		Estudante e = null;

		if (id == 0) {
			throw new IllegalArgumentException("Identificador inválido:" + id);
		}

		return repository.findEstudanteById(id);
	}
	
	public Boolean apagarEstudante(Long id) {
		
		if(repository.deleteByID(id) != null) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
		
	}

}
