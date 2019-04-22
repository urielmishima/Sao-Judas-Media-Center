package br.usjt.saojudasmediacenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.usjt.saojudasmediacenter.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Usuario findByUsuario(String usuario);

}
