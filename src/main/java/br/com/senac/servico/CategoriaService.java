package br.com.senac.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Categoria;
import br.com.senac.repositorio.CategoriaRepositorio;
import br.com.senac.servico.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositorio repoCat;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> objCategoria = repoCat.findById(id);
		return objCategoria.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada! id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria inserir(Categoria objCategoria) {
		//estou colocando um objeto novo entao o id precisa ser null
		objCategoria.setId(null);
		return repoCat.save(objCategoria);
	}
	
	public Categoria alterar(Categoria objCategoria) throws ObjectNotFoundException {
		Categoria objCategoriaEncontrado = buscar(objCategoria.getId());
		objCategoriaEncontrado.setName(objCategoria.getName());
		
		return repoCat.save(objCategoriaEncontrado);
				
	}
	
	public void excluir(Integer id) {
		repoCat.deleteById(id);
	}
	
	public List<Categoria> listaCategorias(){
		return repoCat.findAll();
	}

}
