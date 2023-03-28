package com.ListaFilmesAPI.main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {

	public static void main(String[] args) throws Exception {

		// fazer conexão HTTP e buscar os top 250 filmes
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();//é possivel trocar o HttpClient por var / deixando para o java sozinho definir como HttpClient ( é preciso esta especificado na direita )
		var request = HttpRequest.newBuilder(endereco).GET().build();//o mesmo do var pode ocorrer aqui
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		System.out.println(body);
		
		// extrair só os dados que interessam (titulo, poster, classificação)
		
		// exibir e manipular os dados
	}

}
