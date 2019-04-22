package br.usjt.saojudasmediacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

}
