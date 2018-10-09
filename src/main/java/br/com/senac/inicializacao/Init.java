package br.com.senac.inicializacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.dominio.Aluno;
import br.com.senac.dominio.Categoria;
import br.com.senac.dominio.Cidade;
import br.com.senac.dominio.Curso;
import br.com.senac.dominio.Endereco;
import br.com.senac.dominio.Estado;
import br.com.senac.dominio.Pagamento;
import br.com.senac.dominio.PagamentoComBoleto;
import br.com.senac.dominio.Pedido;
import br.com.senac.dominio.enums.StatusPagamento;
import br.com.senac.repositorio.AlunoRepositorio;
import br.com.senac.repositorio.CategoriaRepositorio;
import br.com.senac.repositorio.CidadeRepositorio;
import br.com.senac.repositorio.CursoRepositorio;
import br.com.senac.repositorio.EnderecoRepositorio;
import br.com.senac.repositorio.EstadoRepositorio;
import br.com.senac.repositorio.PagamentoRepositorio;
import br.com.senac.repositorio.PedidoRepositorio;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoRepositorio alunoRepositorio;
	
	@Autowired
	PedidoRepositorio pedidoRepositorio;
	
	@Autowired
	PagamentoRepositorio pagamentoRepositorio;
	
	@Autowired
	EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	EstadoRepositorio estadoRepositorio;
	
	@Autowired
	CidadeRepositorio cidadeRepositorio;
	
	@Autowired
	CursoRepositorio cursoRepositorio;
	
	@Autowired
	CategoriaRepositorio categoriaRepositorio;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Aluno aluno1 = new Aluno();
		aluno1.setNome("Lucas");
		aluno1.setEmail("lucas@gmail.com");
		
//		Aluno aluno2 = new Aluno();
//		aluno2.setNome("Yuri");
//		aluno2.setEmail("Yuri@gmail.com");
		
		alunoRepositorio.save(aluno1);
		
		
		Aluno alunoGravado = alunoRepositorio.findByEmail("lucas@gmail.com");
//		Aluno alunoGravado1 = alunoRepositorio.findByEmail("Yuri@gmail.com");
		
		
		Estado estado1 = new Estado();
		estado1.setNome("Rio de Janeiro");
		
		Estado estado2 = new Estado();
		estado2.setNome("São Paulo");
		
		Cidade cidade1 = new Cidade();
		cidade1.setNome("Angra dos reis");
		cidade1.setEstado(estado1);
		
		Cidade cidade2 = new Cidade();
		cidade2.setNome("Cabo Frio");
		cidade2.setEstado(estado1);
		
		Cidade cidade3 = new Cidade();
		cidade3.setNome("Mongi das Cruzes");
		cidade3.setEstado(estado2);
		
		estadoRepositorio.saveAll(Arrays.asList(estado1,estado2));
		
		cidadeRepositorio.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		
		
		Endereco end1 = new Endereco();
		end1.setRua("Rua dos Andradas");
		end1.setNumero("10");
		end1.setBairro("Centro");
		end1.setComplemento("Bloco B");
		end1.setCep("22341-175");
		end1.setCidade(cidade1);
		end1.setAluno(aluno1);
		

		
		aluno1.getTelefones().addAll(Arrays.asList("232323243", "232323234"));
		
		
		alunoRepositorio.save(aluno1);
		
		enderecoRepositorio.saveAll(Arrays.asList(end1));
		
		
		//criado o pedido
		Pedido ped1 = new Pedido();
		ped1.setAluno(aluno1);
		ped1.setEnderecoDeEntrega(end1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		
		try {
			//fez o pedido nesta data
			ped1.setDataPedido(sdf.parse("27/06/2018 09:08"));
			
			Pagamento pag1 = new PagamentoComBoleto(null, StatusPagamento.QUITADO, ped1, sdf.parse("30/06/2018 00:00"), sdf.parse("29/06/2018 00:00"));
			
			ped1.setPagamento(pag1);
			
			//salvando o pedido
			pedidoRepositorio.save(ped1);
			
			//salvando o pagamento
			pagamentoRepositorio.save(pag1);
			
		}catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		Categoria categoria1 = new Categoria();
		Categoria categoria2 = new Categoria();
		
		categoria1.setName("Web");
		categoria2.setName("Web Front");
		
		List categorias = new ArrayList<Categoria>();
		categorias.add(categoria1);
		categorias.add(categoria2);
		
		Curso curso1 = new Curso();
		Curso curso2 = new Curso();
		
		curso1.setNome("Java");
		curso1.setPreco(122.10);
		curso1.setDescricao("Curso de Java");
		curso1.setCategorias(categorias);
		
		
		curso2.setNome("JSF");
		curso2.setPreco(159.90);
		curso2.setDescricao("Curso de JSF");
		curso2.setCategorias(categorias);
		
	
		categoriaRepositorio.saveAll(Arrays.asList(categoria1, categoria2));
		cursoRepositorio.saveAll(Arrays.asList(curso1, curso2));
		
		
		
	}
	

}
