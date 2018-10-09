package br.com.senac.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.dominio.Estado;

public interface EstadoRepositorio extends JpaRepository<Estado, Integer>{

}
