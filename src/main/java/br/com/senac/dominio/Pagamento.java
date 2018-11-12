package br.com.senac.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.senac.dominio.enums.StatusPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)// para cada subclasse sera criada uma tabela nova
public class Pagamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private StatusPagamento statusPagamento;
	
	
	@JsonIgnore // O pagamento não precisa saber que é o pedido para o JSON, Isso vai acontecer na classe Pedido
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId // para garantir que o id do pedido seja o do mapeamento
	private Pedido pedido;
	
	
	
	//Defoult Construtor
	public Pagamento() {
		super();
	}


	public Pagamento(Integer id, StatusPagamento status, Pedido pedido) {
		super();
		this.id = id;
		this.statusPagamento = status;
		this.pedido = pedido;
	}
	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
