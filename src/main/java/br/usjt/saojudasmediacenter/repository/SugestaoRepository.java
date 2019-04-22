package br.usjt.saojudasmediacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Sugestao;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, String> {

}
