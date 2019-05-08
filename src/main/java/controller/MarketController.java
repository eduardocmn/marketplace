package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import response.Response;
import services.MarketService;
import util.Validacao;
import dto.ArquivoDTO;
import dto.FilaUploadDTO;
import dto.ProdutoDTO;

@RestController
@RequestMapping("/leroy")
public class MarketController {
	
	private MarketService service = new MarketService();
	
	@GetMapping(path = "/consultarProcessamentoPlanilhas")
	public ResponseEntity<List<String>> consultarPlanilhasEnviadas() {
		
		List<String> listaArquivosEnviados = new ArrayList<String>();
		
		//Verifica quais arquivos foram processados
		for(ArquivoDTO arquivo : FilaUploadDTO.arquivosProcessados){
			
			listaArquivosEnviados.add("Arquivo pocessado: "+arquivo.getNome());
		}
		
		//Verifica quais arquivos estão na fila para serem processados
		for(ArquivoDTO arquivo : FilaUploadDTO.fila){
			
			listaArquivosEnviados.add("Arquivo na fila para processamento:"+arquivo.getNome());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listaArquivosEnviados);
	}
	
	@PostMapping(path = "/upload")
	public ResponseEntity<Response> uploadPlanilha(MultipartFile file) {
		
		try {
			
			if(!Validacao.isArquivoNulo(file)){
				
				FilaUploadDTO.fila.add(new ArquivoDTO(file.getOriginalFilename(),file.getInputStream()));
				
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Arquivo não enviado",null));
			}
			
			
			
		} catch (IOException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Erro ao carregar planilha",null));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Planilha enviada para fila de processamento",null));
	}
	
	@GetMapping(path = "/buscarProduto/{id}")
	public ResponseEntity<Response> buscarProduto(@PathVariable Long id) {
		
		try {
			
			if(!Validacao.isNull(id)){
				
				ProdutoDTO produto = service.buscarProduto(id);
				return ResponseEntity.status(HttpStatus.OK).body(new Response("Produto encontrado",produto));
				
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Informe o id do produto",null));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Erro ao buscar produto",null));
		}
	}
	
	@PutMapping(path = "/alterarProduto")
	public ResponseEntity<Response> alterarProduto(@RequestBody ProdutoDTO produto) {
		
		try {
			
			if(!Validacao.isIdProdutoNull(produto)){
				
				produto = service.alterarProduto(produto);
				
				return ResponseEntity.status(HttpStatus.OK).body(new Response("Produto alterado",produto));
				
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Informe o id do produto",produto));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Erro ao alterar o produto",produto));
		}
	}
	
	@DeleteMapping(path = "/deletarProduto/{id}")
	public ResponseEntity<Response> deletarProduto(@PathVariable Long id) {
		
		try {
			if(!Validacao.isNull(id)){
				
				service.deletarProduto(id);
				
				return ResponseEntity.status(HttpStatus.OK).body(new Response("Produto removido",null));
				
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Informe o id do produto",null));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Erro ao deletar o produto",null));
		}
	}
	
}
