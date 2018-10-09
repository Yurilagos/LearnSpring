package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.dominio.Curso;
import br.com.senac.servico.CategoriaService;
import br.com.senac.servico.CursoService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/listarCurso")
	public ModelAndView listaCursos() {
		ModelAndView mv = new ModelAndView("/paginaCursos");
		mv.addObject("cursos", cursoService.listaCursos());
		return mv;
	}

	@GetMapping("/adicionar")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("/paginaAdicionarCurso");
		mv.addObject("curso", new Curso());
		mv.addObject("categorias", categoriaService.listaCategorias());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView inserir(Curso curso) {
		cursoService.inserir(curso);
		return listaCursos();
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {
		cursoService.excluir(id);
		return listaCursos();
	}
	
	@GetMapping("/alterarCurso/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("/paginaAlterar");
		mv.addObject("curso", cursoService.buscar(id));
		mv.addObject("categorias", categoriaService.listaCategorias());
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Curso curso) throws ObjectNotFoundException {
		cursoService.alterar(curso);
		return listaCursos();
	}

}
