package com.ListaFilmesAPI.extratores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ListaFilmesAPI.aula1.JSON.JsonParser;
import com.ListaFilmesAPI.main.Conteudo;

public class ExtratorFilmes implements ExtratorDeConteudo{
	
	public List<Conteudo> extrai(String json){
		
		JsonParser parser = new JsonParser();										// Instancia a classe responsavel pela leitura do JSON
		List<Map<String, String>> listaDeAtributos = parser.parse(json);			// Cria uma list Map, sobre o body do json
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		for (Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");									// Busca do json o titulo do filme e armazena	
			String urlImagem = atributos.get("image");								// Busca url da imagem
			var conteudo = new Conteudo(titulo, urlImagem);							// Cria um novo objeto conteudo
			
			conteudos.add(conteudo);												// Armazena na lista
		}
		
		return conteudos;
	}
}
