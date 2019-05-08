package br.com.market.marketplace;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import services.MarketService;
import util.Validacao;
import dto.ProdutoDTO;


public class ProdutoTest{
	
	@Test
	public void validaArquivoNulo(){
		assertTrue(Validacao.isArquivoNulo(null));
	}
	
	@Test
	public void isNullParam(){
		assertTrue(Validacao.isNull(null));
	}
	
	@Test
	public void isIDProdutoNull(){
		assertTrue(Validacao.isIdProdutoNull(null));
	}
	
	@Test
	public void isProdutoNull(){
		assertTrue(Validacao.isIdProdutoNull(new ProdutoDTO()));
	}
	
	@Test
	public void validaBuscaProduto(){
		
		MarketService service = Mockito.mock(MarketService.class);
		ProdutoDTO produto = Mockito.mock(ProdutoDTO.class);
		
		Mockito.when(produto.getCodigo()).thenReturn(Long.valueOf(1));
		Mockito.when(produto.getNome()).thenReturn("Parafusadeira");
		
		Mockito.when(service.buscarProduto(Long.valueOf(1))).thenReturn(produto);
		
	}
	
}
