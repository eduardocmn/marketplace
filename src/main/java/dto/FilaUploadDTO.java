package dto;

import java.util.LinkedList;
import java.util.Queue;

public class FilaUploadDTO {
	
public static Queue<ArquivoDTO> fila = new LinkedList<ArquivoDTO>();

public static Queue<ArquivoDTO> arquivosProcessados = new LinkedList<ArquivoDTO>();
	

	public static Queue<ArquivoDTO> getFila() {
		return fila;
	}


	public static void setFila(Queue<ArquivoDTO> fila) {
		FilaUploadDTO.fila = fila;
	}


	public static Queue<ArquivoDTO> getArquivosProcessados() {
		return arquivosProcessados;
	}


	public static void setArquivosProcessados(Queue<ArquivoDTO> arquivosProcessados) {
		FilaUploadDTO.arquivosProcessados = arquivosProcessados;
	}
	
	
}
