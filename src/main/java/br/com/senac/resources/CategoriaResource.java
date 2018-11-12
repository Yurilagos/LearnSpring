package br.com.senac.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.senac.dominio.Categoria;
import br.com.senac.servico.CategoriaService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String testar() {
		return "Está Funcionando";
	}

	
	//indica que agora a chamada da url vai incluir um id ex: categoria/1 ou categoria/2
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException{//@PathVariable indica que o que foi mapeado no value do requestMapping vai ser passado para 
		Categoria objCategoria = service.buscar(id);
		return ResponseEntity.ok().body(objCategoria);		
	}//ResponseEntity encapsula varios tratamentos http para o rest e incluir o objeto categoria que encontramos
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Categoria objCategoria) {
		
		objCategoria = service.inserir(objCategoria);
		//Vamos montar a url resposta da categoria inserida
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objCategoria.getId()).toUri();
		// código http de criação de objeto
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Categoria objCategoria, @PathVariable Integer id){
		
		objCategoria.setId(id);
		objCategoria = service.alterar(objCategoria);
		//não queremos que retorne nada
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		
		service.excluir(id);
		
		//não queremos que retorne nada
		return ResponseEntity.noContent().build();
	}
	
	
	
}
