package br.com.senac.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Aluno;
import br.com.senac.repositorio.AlunoRepositorio;
import br.com.senac.servico.exception.ObjectNotFoundException;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepositorio repoAluno;
	
	public Aluno buscar(Integer id) {
		Optional<Aluno> objAluno = repoAluno.findById(id);
		return objAluno.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()));
	}

}
