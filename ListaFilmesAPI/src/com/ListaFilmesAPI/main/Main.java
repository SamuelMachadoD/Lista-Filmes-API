package com.ListaFilmesAPI.main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		// fazer conexão HTTP e buscar os top 250 filmes
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; // Armazena o link da API
		URI endereco = URI.create(url); //Le e armazena o conteudo da API em uma url
		var client = HttpClient.newHttpClient();//Instancia um cliente //é possivel trocar o HttpClient por var / deixando para o java sozinho definir como HttpClient ( é preciso esta especificado na direita )
		var request = HttpRequest.newBuilder(endereco).GET().build();//Faz uma request com a URL //o mesmo do var pode ocorrer aqui
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());//Gera uma resposta a partir do cliente e request
		String body = response.body();  // armazena o body da reposta em uma variavel
		
		// extrair só os dados que interessam (titulo, poster, classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
		
		// exibir e manipular os dados
	}

}
