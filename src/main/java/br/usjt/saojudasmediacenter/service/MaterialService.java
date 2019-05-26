package br.usjt.saojudasmediacenter.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.saojudasmediacenter.model.Material;
import br.usjt.saojudasmediacenter.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired private MaterialRepository materialRepository;
	private final String directoryPrefix = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static";
	
	public Material store(MultipartFile file) {
		Material material = new Material();
		String nome = file.getOriginalFilename();
		if(file.isEmpty()) {
			throw new RuntimeException("Não é possivel guardar um arquivo vazio");
		}
		try(InputStream inputStream = file.getInputStream()){
			fileToMaterial(file, material, nome);
			
			material = materialRepository.save(material);
			
			Path location = Paths.get(directoryPrefix + File.separator + material.getTipo() + "-dir");
			Files.copy(inputStream, location.resolve(material.getId() + '.' + material.getExtensao()), StandardCopyOption.REPLACE_EXISTING);
			TimeUnit.SECONDS.sleep(5);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException("Falha ao guardar o arquivo");
		}	
		return material;
	}

	private Material fileToMaterial(MultipartFile file, Material material, String nome) {
		material.setNome(nome.substring(0, nome.lastIndexOf('.')));
		material.setTipo(file.getContentType().split("/")[0]);
		material.setExtensao(nome.substring(nome.lastIndexOf('.') + 1));
		return material;
	}

	public List<Material> findByTipo(String tipo){ 
		return materialRepository.findByTipo(tipo);
	}
	
	public boolean delete(String src) {
		try {
			new File(directoryPrefix + src.replaceAll("%2F", "/")).delete();
			System.out.println(src);
			System.out.println(src.substring(src.lastIndexOf("%2F") + 1, src.indexOf('.')));
			materialRepository.deleteById(src.substring(src.lastIndexOf("%2F") + 3, src.indexOf('.')));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
