package br.usjt.saojudasmediacenter.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.saojudasmediacenter.model.Material;
import br.usjt.saojudasmediacenter.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired private MaterialRepository materialRepository;
	
	public Material store(MultipartFile file, Material material) {
		String nome = file.getOriginalFilename();
		if(file.isEmpty()) {
			throw new RuntimeException("Não é possivel guardar um arquivo vazio");
		}
		try(InputStream inputStream = file.getInputStream()){
			fileToMaterial(file, material, nome);
			
			material = materialRepository.save(material);
			
			Path location = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + material.getTipo() + "-dir");
			Files.copy(inputStream, location.resolve(material.getId() + '.' + material.getExtensao()), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Falha ao guardar o arquivo");
		}		
		return material;
	}

	private Material fileToMaterial(MultipartFile file, Material material, String nome) {
		material.setNome(nome.substring(0, nome.lastIndexOf('.') - 1));
		material.setTipo(file.getContentType().split("/")[0]);
		material.setExtensao(nome.substring(nome.lastIndexOf('.') + 1));
		return material;
	}
}
