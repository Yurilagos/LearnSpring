package br.com.senac.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Pedido;
import br.com.senac.repositorio.PedidoRepositorio;
import br.com.senac.servico.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepositorio repoPedido;

	
	public Pedido buscar(Integer id) {
		Optional<Pedido> objPedido = repoPedido.findById(id);
		return objPedido.orElseThrow(() -> new ObjectNotFoundException("Peddio não encontrado! id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
