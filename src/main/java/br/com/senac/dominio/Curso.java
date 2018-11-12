package br.com.senac.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="curso")
public class Curso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String Descricao;
	private Double preco;
	
	/*
	 * Em vez de usar @JsonBackReference para quem eu não quero que apareça no Json eu uso @JsonIgnore
	 * e tiro @JsonManagedReference do metodo que quero que venha no Json
	 */
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="Curso_Tem_Categoria", joinColumns={@JoinColumn(name="curso_id")}, inverseJoinColumns={@JoinColumn(name="categoria_id")})
	private List<Categoria> categorias;
	
	
	
	public Curso(Integer id, String nome, String descricao, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		Descricao = descricao;
		this.preco = preco;
	}
	
	public Curso() {
		super();
	}
	
	@JsonIgnore
	@OneToMany(mappedBy="id.curso")
	private Set<ItemPedido> itens = new HashSet<>();
	
	
	//para saber os pedidos do produto
	@JsonIgnore
	public List<Pedido> getPedido(){
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido i : itens) {
			lista.add(i.getPedido());			
		}
		return lista;
	}
	
	
	public Set<ItemPedido> getItens(){
		return itens;
	}
	
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
	

}
