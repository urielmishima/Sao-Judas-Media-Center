package br.usjt.saojudasmediacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Sugestao;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, String> {

	List<Sugestao> findByFeedbackIsNull();

}
