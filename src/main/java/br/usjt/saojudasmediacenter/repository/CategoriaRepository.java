package br.usjt.saojudasmediacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

	Categoria findByNome(String nome);

}
