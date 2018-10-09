package br.com.senac.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.dominio.Pagamento;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Integer>{

}
