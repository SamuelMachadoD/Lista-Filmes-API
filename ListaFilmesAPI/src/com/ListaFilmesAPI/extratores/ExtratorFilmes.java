package com.ListaFilmesAPI.extratores;

import java.util.List;
import java.util.Map;

import com.ListaFilmesAPI.aula1.JSON.JsonParser;
import com.ListaFilmesAPI.main.Conteudo;

public class ExtratorFilmes implements ExtratorDeConteudo{
	
	public List<Conteudo> extrai(String json){
		
		JsonParser parser = new JsonParser();										// Instancia a classe responsavel pela leitura do JSON
		List<Map<String, String>> listaDeAtributos = parser.parse(json);			// Cria uma list Map, sobre o body do json
		
		return listaDeAtributos.stream().map(atributos ->  new Conteudo(atributos.get("title"), atributos.get("image"))).toList(); // Mapeia e busca os atributos presentes na Map
	}
}
