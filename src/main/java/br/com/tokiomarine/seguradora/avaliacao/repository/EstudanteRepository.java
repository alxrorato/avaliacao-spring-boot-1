package br.com.tokiomarine.seguradora.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

@Repository
public interface EstudanteRepository extends CrudRepository<Estudante, Long> {

	List<Estudante> findByName(String name);

	@Query(value = "select * from Estudante e where e.id = ?1", nativeQuery = true)
	Estudante findEstudanteById(long id);
	
	@Query(value = "delete from Estudante e where e.id = ?1", nativeQuery = true)
	Object deleteByID(Long id);
	
	@Query(nativeQuery = true) 
    public List<Estudante> findEstudantesNative(String arrayMatriculasText);

}
