package br.usjt.saojudasmediacenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.saojudasmediacenter.service.MaterialService;

@RestController
@RequestMapping("/materiais")
public class MaterialController {
	
	@Autowired private MaterialService materialService;

	@GetMapping("/load_images")
	@Secured("ROLE_ESTAGIARIO")
	public List<Map<String, String>>loadImages() {
		List<Map<String, String>> listJson = new ArrayList<>();
		materialService.findByTipo("image").forEach((material) -> {
			Map<String, String> map = new HashMap<String, String>();
			map.put("url", material.getSource());
			map.put("thumb", material.getSource());
			listJson.add(map);
		});
		return listJson;
	}
	
	@PostMapping("/upload")
	@Secured("ROLE_ESTAGIARIO")
	public Map<String, String> upload(MultipartFile file) {
		Map<String, String> json = new HashMap<String, String>();
		json.put("link", materialService.store(file).getSource());
		System.out.println(json.toString());
		return json;
	}
	
	@Secured("ROLE_ESTAGIARIO")
	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(String src) {
		materialService.delete(src);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
