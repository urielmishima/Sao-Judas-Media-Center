package br.usjt.saojudasmediacenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.enums.TipoFeedback;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Feedback;
import br.usjt.saojudasmediacenter.model.Usuario;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

	Feedback findByUsuarioAndConteudo(Usuario usuario, Conteudo conteudo);

	List<Feedback> findByUsuarioAndFeedback(Usuario usuario, TipoFeedback feedback);

}
