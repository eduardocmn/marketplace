package dto;

import java.util.LinkedList;
import java.util.Queue;

public class FilaUpload {
	
public static Queue<Arquivo> fila = new LinkedList<Arquivo>();

public static Queue<Arquivo> arquivosProcessados = new LinkedList<Arquivo>();
	

	public static Queue<Arquivo> getFila() {
		return fila;
	}


	public static void setFila(Queue<Arquivo> fila) {
		FilaUpload.fila = fila;
	}


	public static Queue<Arquivo> getArquivosProcessados() {
		return arquivosProcessados;
	}


	public static void setArquivosProcessados(Queue<Arquivo> arquivosProcessados) {
		FilaUpload.arquivosProcessados = arquivosProcessados;
	}
	
	
}
