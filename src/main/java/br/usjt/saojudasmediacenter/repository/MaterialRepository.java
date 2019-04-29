package br.usjt.saojudasmediacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {

	List<Material> findByTipo(String tipo);

}
