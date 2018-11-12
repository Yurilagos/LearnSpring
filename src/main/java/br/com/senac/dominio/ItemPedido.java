package br.com.senac.dominio;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ItemPedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double desconto;
	private Integer quantidade;
	private double valor;
	
	@JsonIgnore //O ItemPedido não precisa saber que é o pedido para o JSON, Isso vai acontecer na classe Pedido
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	public ItemPedido(Pedido pedido, Curso curso, Double desconto, Integer quantidade, Double valor) {
		super();
		id.setPedido(pedido);
		id.setCurso(curso);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	public ItemPedido() {
		super();
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}
	
	@JsonIgnore // Esse método ajuda a ter acesso ao pedido e ao produto sem ter que acessar a classe ItenPedidoPK, 
	//Mão precisa saber em ItemPedido mas precisa saber em pedido
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Curso getCurso() {
		return id.getCurso();
	}
	
	

}
