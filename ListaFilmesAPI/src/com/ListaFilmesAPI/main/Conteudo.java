package com.ListaFilmesAPI.main;

import java.net.URL;

public class Conteudo {
	
	private final String titulo;
	private final String urlImagem;
	
	public Conteudo(String titulo, String urlImagem) {
		this.titulo = titulo;
		this.urlImagem = urlImagem;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getImagem() {
		return urlImagem;
	}

}
