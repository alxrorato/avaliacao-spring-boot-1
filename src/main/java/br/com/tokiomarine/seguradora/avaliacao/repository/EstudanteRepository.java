package br.com.tokiomarine.seguradora.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

@Repository
public interface EstudanteRepository extends CrudRepository<Estudante, Long> {

	List<Estudante> findByNome(String nome);

	@Query(value = "select * from Estudante e where e.id = ?1", nativeQuery = true)
	Estudante findEstudanteById(long id);
	
	@Modifying
	@Query(value = "delete from Estudante e where e.id = ?1", nativeQuery = true)
	//Object deleteByID(Long id);
	void deleteByID(Long id);
	
	@Query(nativeQuery = true) 
    public List<Estudante> findEstudantesNative(String arrayMatriculasText);

}
