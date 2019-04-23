package br.usjt.saojudasmediacenter.config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.enums.TipoUsuario;
import br.usjt.saojudasmediacenter.model.Categoria;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Tag;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.repository.CategoriaRepository;
import br.usjt.saojudasmediacenter.repository.ConteudoRepository;
import br.usjt.saojudasmediacenter.repository.TagRepository;
import br.usjt.saojudasmediacenter.repository.UsuarioRepository;
import br.usjt.saojudasmediacenter.service.UsuarioService;
import de.svenjacobs.loremipsum.LoremIpsum;

@Configuration
public class Init implements CommandLineRunner {

	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private TagRepository tagRepository;
	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private ConteudoRepository conteudoRepository;
	@Autowired private UsuarioService usuarioService;

	@Override
	public void run(String... args) throws Exception {
		if (usuarioRepository.findByUsuario("admin") == null) {
			for (String tipo : Arrays.asList("image", "video", "text", "audio")) {
				Files.createDirectories(Paths.get("src/main/resources/static/" + tipo + "-dir"));
			}

			List<Usuario> usuarios = Arrays.asList(
					new Usuario().setUsuario("admin").setSenha("admin").setTipo(TipoUsuario.ADMINISTRADOR),
					new Usuario().setUsuario("estagiario").setSenha("estagiario").setTipo(TipoUsuario.ESTAGIARIO),
					new Usuario().setUsuario("comum").setSenha("comum").setTipo(TipoUsuario.COMUM));

			for (Usuario usuario : usuarios) {
				usuarioRepository.save(usuarioService.criptografarSenha(usuario));
			}

			List<Tag> tags = new ArrayList<>();
			for (String nome : Arrays.asList("software", "statistics", "teaching", "technology", "tips", "tool",
					"toread", "travel", "tutorial", "tv", "typography", "ubuntu", "video", "visualization", "web",
					"USJT", "Bolsa de Valores", "Dólar", "Bolsonaro", "PT")) {
				Tag tag = new Tag().setNome(nome);
				tagRepository.save(tag);
				tags.add(tag);
			}

			List<Categoria> categorias = new ArrayList<>();
			for (String nome : Arrays.asList("Economia", "Filosofia", "Programação", "Esportes", "Mundo", "Cotidiano",
					"Geral", "Brasil")) {
				Categoria categoria = new Categoria().setNome(nome);
				categoriaRepository.save(categoria);
				categorias.add(categoria);
			}

			LoremIpsum loremIpsum = new LoremIpsum();

			List<TipoAcesso> acessos = Arrays.asList(TipoAcesso.PUBLICO, TipoAcesso.PROTEGIDO);

			Conteudo conteudo;
			for (int i = 0; i < 2; i++) {
				for (Categoria categoria : categorias) {
					for (Tag tag : tags) {
						Calendar data = Calendar.getInstance();
						data.setTimeInMillis(
								(long) (System.currentTimeMillis() - 432000000 + Math.random() * 864000000));

						conteudo = new Conteudo()
								.setTitulo(categoria.getNome() + " " + tag.getNome() + " " + acessos.get(i))
								.setDescricao(loremIpsum.getParagraphs(1)).setConteudo(loremIpsum.getParagraphs(1))
								.setTipo(acessos.get(0)).setData(data).setCategorias(Arrays.asList(categoria))
								.setTags(Arrays.asList(tag))
								.setVisualizacoes((int) (Math.random() * 100));

						conteudoRepository.save(conteudo);
					}
				}
			}
		}
	}

}
