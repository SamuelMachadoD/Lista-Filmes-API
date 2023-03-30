package com.ListaFilmesAPI.extratores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ListaFilmesAPI.aula1.JSON.JsonParser;
import com.ListaFilmesAPI.main.Conteudo;

public class ExtratorNasa implements ExtratorDeConteudo{
	
	public List<Conteudo> extrai(String json){
		
		JsonParser parser = new JsonParser();										// Instancia a classe responsavel pela leitura do JSON
		List<Map<String, String>> listaDeAtributos = parser.parse(json);			// Cria uma list Map, sobre o body do json
		
		List<Conteudo> conteudos = new ArrayList<>();
		
		return listaDeAtributos.stream().map(atributos ->  new Conteudo(atributos.get("title"), atributos.get("url"))).toList(); // Mapeia, busca e lista os atributos presentes no Json
		
	}
	
}
