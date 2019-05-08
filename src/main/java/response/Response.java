package response;

import dto.ProdutoDTO;

public class Response {
	
	private String mensagem;
	
	private ProdutoDTO produto;
	
	public Response(){
	}
	
	public Response(String mensagem, ProdutoDTO produto){
		this.mensagem = mensagem;
		this.produto = produto;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	
	
	
}
