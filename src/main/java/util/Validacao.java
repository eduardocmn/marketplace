package util;

import org.springframework.web.multipart.MultipartFile;

import dto.ProdutoDTO;

public class Validacao {
	
	public static boolean isArquivoNulo(MultipartFile file){
		
		if(file == null){
			return true;
		}
		
		return false;
	}
	
	public static boolean isNull(Long parametro){
		return parametro == null;
	}
	
	public static boolean isIdProdutoNull(ProdutoDTO produtoDTO){
		boolean validacao = false;
		
		if(produtoDTO == null || produtoDTO.getCodigo() == 0){
			validacao = true;
		}
		return validacao;
	}

}
