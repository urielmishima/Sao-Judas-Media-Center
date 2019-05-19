package br.usjt.saojudasmediacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

	Tag findByNome(String nome);

	List<Tag> findByNomeIn(List<String> tags);

}
