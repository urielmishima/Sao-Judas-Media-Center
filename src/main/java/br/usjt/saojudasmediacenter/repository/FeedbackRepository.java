package br.usjt.saojudasmediacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

}
