package dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


public class ProdutoDTO implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
	
	private long codigo;
	
	private String nome;
		
	private boolean freteGratis;
	
	private String descricao;
	
	private BigDecimal preco;
	
	private long codigoCategoria;
	
	public ProdutoDTO() {
	}
	
	public ProdutoDTO(long codigo,String nome,boolean freteGratis,String descricao,BigDecimal preco,long codigoCategoria) {
		this.codigo = codigo;
		this.nome = nome;
		this.freteGratis = freteGratis;
		this.descricao = descricao;
		this.preco = preco;
		this.codigoCategoria = codigoCategoria;
	}
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isFreteGratis() {
		return freteGratis;
	}
	
	public boolean getFreteGratis() {
		return freteGratis;
	}
	
	public void setFreteGratis(boolean freteGratis) {
		this.freteGratis = freteGratis;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public long getCodigoCategoria() {
		return codigoCategoria;
	}
	
	public void setCodigoCategoria(long codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	
	@Override
	public String toString() {
		return "Viagem [id=" + codigo + ", nome=" + nome + ", descricao=" + descricao+ ", freteGratis=" + freteGratis
				+ ", preco=" + preco + ", codigoCategoria=" + codigoCategoria + "]";
	}
	
}
