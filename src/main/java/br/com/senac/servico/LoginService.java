package br.com.senac.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Categoria;
import br.com.senac.dominio.Usuario;
import br.com.senac.repositorio.CategoriaRepositorio;
import br.com.senac.repositorio.LoginRepositorio;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class LoginService {

	
	@Autowired
	private LoginRepositorio repoLogin;
	
	public Usuario buscar(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> objusuario = repoLogin.findById(id);
		return objusuario.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrada! id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
//	public Boolean verificaLogin(Usuario u) throws ObjectNotFoundException {
//		
//		if (condition) {
//			
//		}
//		Usuario usuario = buscar(u.getId());
//		usuario.setName(objCategoria.getName());
//		
//		return repoCat.save(objCategoriaEncontrado);
//				
//	}
	
	
	
	
}
