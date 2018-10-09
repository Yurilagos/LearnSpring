package br.com.senac.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.dominio.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer>{

}
