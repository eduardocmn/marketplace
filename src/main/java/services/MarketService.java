package services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ArquivoDTO;
import dto.FilaUploadDTO;
import dto.ProdutoDTO;
import entidade.Produto;
import repository.Repositorio;

@Service
public class MarketService {

	@Autowired
	private Repositorio repositorio;
	
	public ProdutoDTO buscarProduto(long codigoProduto) {
		
		return converteParaDTO(repositorio.findOne(codigoProduto));
	}
	
	public ProdutoDTO alterarProduto(ProdutoDTO produto) {
		
		return converteParaDTO(repositorio.save(converteParaEntidade(produto)));
	}
	
	public void cadastrarProduto(List<ProdutoDTO> listaProdutos) {
		
		for(ProdutoDTO produto : listaProdutos) {
			repositorio.save(converteParaEntidade(produto));
		}
	}
	
	public void deletarProduto(long codigoProduto) {
		
		repositorio.delete(codigoProduto);
	}
	
	public ProdutoDTO converteParaDTO(Produto produto) {
		
		return new ProdutoDTO(produto.getCodigo(),produto.getNome(),produto.getFreteGratis(),
				produto.getDescricao(),produto.getPreco(),produto.getCodigoCategoria());
	}
	
	public Produto converteParaEntidade(ProdutoDTO produto) {
		
		return new Produto(produto.getCodigo(),produto.getNome(),produto.getFreteGratis(),
				produto.getDescricao(),produto.getPreco(),produto.getCodigoCategoria());
	}
	
	
	public void processarPlanilha() {
		
		//Se possuir planilha na fila executa
		if(FilaUploadDTO.fila.size() > 0) {
			
			ArquivoDTO arquivo = FilaUploadDTO.fila.remove();
			
			List<ProdutoDTO> listaProduto = carregarProdutos(arquivo);
			this.cadastrarProduto(listaProduto);
			
			FilaUploadDTO.arquivosProcessados.add(arquivo);
		}
		
	}
	
	public List<ProdutoDTO> carregarProdutos(ArquivoDTO arquivo) {
		
		List<ProdutoDTO> listaProduto = new ArrayList<ProdutoDTO>();
		ProdutoDTO produtoDTO;
		
		if(arquivo != null && arquivo.getInputStream() != null) {
			
			 try {
				 
				 XSSFWorkbook workbook = new XSSFWorkbook(arquivo.getInputStream());
                 XSSFSheet sheet = workbook.getSheetAt(0);
                 Iterator<Row> rowIterator = sheet.iterator();
                 
                 int numeroDaLinha = 0;
                 long codigoCategoria = 0;
                 while (rowIterator.hasNext()) {
                     
                	 Row linha = rowIterator.next();
                	 
                	 //Primeira linha da planilha para pegar a categoria do produto
                	 if(numeroDaLinha == 0) {
                    	 
                		 Cell celula = linha.getCell(1);
                		 
                		 if (celula.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                			 codigoCategoria = (long) celula.getNumericCellValue();
                		}
                		 
                	 
                	 //Quarta linha da planilha em diante para pegar os produtos
                     }else if(numeroDaLinha >= 3) {
                    	 produtoDTO = new ProdutoDTO();
                    	 
                    	 produtoDTO.setCodigoCategoria(codigoCategoria);
                    	 
                    	 Cell celula = linha.getCell(0);
                		 
                		 if (celula.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                			 produtoDTO.setCodigo((long) celula.getNumericCellValue());
                         }
                		 
                		 celula = linha.getCell(1);
                		 if (celula.getCellType() == Cell.CELL_TYPE_STRING) {
                			 produtoDTO.setNome(celula.getStringCellValue());
                         }
                		 
                		 celula = linha.getCell(2);
                		 if (celula.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                			 long valor = ((long) celula.getNumericCellValue());
                			 
                			 if(valor == 0) {
                				 produtoDTO.setFreteGratis(false);
                			 }else {
                				 produtoDTO.setFreteGratis(true);
                			 }
                         }
                		 
                		 celula = linha.getCell(3);
                		 if (celula.getCellType() == Cell.CELL_TYPE_STRING) {
                			 produtoDTO.setDescricao(celula.getStringCellValue());
                         }
                		 
                		 celula = linha.getCell(4);
                		 if (celula.getCellType() == Cell.CELL_TYPE_STRING) {
                			 produtoDTO.setPreco(new BigDecimal(celula.getStringCellValue()));
                         }
                		 
                		 listaProduto.add(produtoDTO);
                     }
                	 
                	 numeroDaLinha++;
                 }
             } catch (IOException e) {
            	 return null;
     		}
			
		}
		
		return listaProduto;
	}
	
}
