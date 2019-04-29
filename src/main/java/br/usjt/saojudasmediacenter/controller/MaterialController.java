package br.usjt.saojudasmediacenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.saojudasmediacenter.model.Material;
import br.usjt.saojudasmediacenter.service.CategoriaService;
import br.usjt.saojudasmediacenter.service.MaterialService;

@Controller
@RequestMapping("/materiais")
public class MaterialController {
	
	@Autowired private CategoriaService categoriaService;
	@Autowired private MaterialService materialService;

	@GetMapping("/upload")
	@Secured("ROLE_ESTAGIARIO")
	public ModelAndView upload() {
		ModelAndView mav = new ModelAndView("upload");
		mav.addObject("categorias", categoriaService.findAll());
		return mav;
	}
	
	@PostMapping("/upload")
	@Secured("ROLE_ESTAGIARIO")
	public String upload(@RequestParam("file") MultipartFile file, Material material) {
		materialService.store(file, material);
		return "redirect:/materiais/upload";
	}
}
