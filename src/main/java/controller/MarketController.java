package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import dto.Arquivo;
import dto.FilaUpload;
import dto.ProdutoDTO;
import services.MarketService;

@RestController
@RequestMapping("/leroy")
public class MarketController {
	
	@GetMapping(path = "/consultarProcessamentoPlanilhas")
	public ResponseEntity<List<String>> teste() {
		
		List<String> listaArquivosProcessados = new ArrayList<String>();
		
		
		for(Arquivo arquivo : FilaUpload.arquivosProcessados){
			
			listaArquivosProcessados.add("Planilha Processada:"+arquivo.getNome());
		}
		
		for(Arquivo arquivo : FilaUpload.fila){
			
			listaArquivosProcessados.add("Planilha na fila para ser processada:"+arquivo.getNome());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listaArquivosProcessados);
	}
	
	@PostMapping(path = "/upload")
	public ResponseEntity<String> uploadPlanilha(MultipartFile file) {
		
		try {
			
			FilaUpload.fila.add(new Arquivo(file.getOriginalFilename(),file.getInputStream()));
			
		} catch (IOException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao carregar planilha");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("Planilha na fila de processamento");
	}
	
	@GetMapping(path = "/buscarProduto/{id}")
	public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable Long id) {
		
		try {
			MarketService service = new MarketService();
			ProdutoDTO produto = service.buscarProduto(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(produto);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping(path = "/alterarProduto")
	public ResponseEntity<ProdutoDTO> alterarProduto(@RequestBody ProdutoDTO produto) {
		
		try {
			MarketService service = new MarketService();
			produto = service.alterarProduto(produto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
	
	@DeleteMapping(path = "/deletarProduto/{id}")
	public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
		
		try {
			MarketService service = new MarketService();
			service.deletarProduto(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar produto");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Produto removido");
	}
	
}
