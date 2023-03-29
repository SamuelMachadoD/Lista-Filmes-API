package com.ListaFilmesAPI.base.main;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import com.ListaFilmesAPI.aula1.JSON.GeraASCII;
import com.ListaFilmesAPI.aula1.JSON.JsonParser;
import com.ListaFilmesAPI.aula2.gerador.GeradorDeFigurinhas;

public class Main {

	public static void main(String[] args) throws Exception {

		// --------------------------------------------------Faz conexão HTTP e buscar os top 250 filmes---------------------------------------------------
		
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json"; 	// Armazena o link da API
		URI endereco = URI.create(url); 										// Le e armazena o conteudo da API em uma url
		var client = HttpClient.newHttpClient();								// Instancia um cliente
																				// É possivel trocar o HttpClient por var deixando para o java sozinho definir seu tipo
		var request = HttpRequest.newBuilder(endereco).GET().build();			// Faz uma request com a URL
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());		//Gera uma resposta a partir do cliente e request
		String body = response.body();  										// Armazena o body da reposta em uma variavel
		
		
		// ---------------------------------------Extrair só os dados que interessam (titulo, poster, classificação)---------------------------------------
		
		JsonParser parser = new JsonParser();									// Instancia a classe responsavel pela leitura do JSON
		List<Map<String, String>> listaDeFilmes = parser.parse(body);			// Cria uma list Map, sobre o body do json
		
		
		// ----------------------------------------------------------Cria o gerador de figurinhas----------------------------------------------------------
		
		var gerador = new GeradorDeFigurinhas(); 								// Instancia a classe responsavel pelas figurinhas
		
		
		
		// --------------------------------------------------Exibir e manipular os dados e gera ASCII arte-------------------------------------------------
		
		for (Map<String, String> filme : listaDeFilmes) {                       // Passa por cada filme da listaDeFilmes
			
			String titulo = filme.get("title");									// Busca do json o titulo do filme e armazena	
			String rank = filme.get("rank");									// Busca do json o rank do filme e armazena
			System.out.println("\u001b[1m\u001b[40m Rank: " + rank + " \u001b[m");              // Escreve no console, o rank do filme com cores alteradas
			System.out.println("\u001b[1m\u001b[47m\u001b[30m " + titulo + " \u001b[m");		// Escreve no console, o titulo com cores alteradas
			System.out.println("\u001b[43m\u001b[1m Rating: " + filme.get("imDbRating") + " \u001b[m");  // Busca o rating no json e escreve ele com cores alteradas
			
			
			
			// ------------------------------------------------------Gera art ASCII e cria figurinhas------------------------------------------------------
			
			String img = filme.get("image"); 									// Recebe a url da imagem
			System.out.println(GeraASCII.artASCII(img));						// Imprime o retorno da classe responsavel por gerar o artASCII
			
			
			
			// ----------------------------------------Gera figurinha e exporta imagens para diretorio local (saida)---------------------------------------
			
			InputStream inputStream = new URL(img).openStream();				// Recebe diretorio da imagem
			titulo = titulo.replace(":", "-"); 								    // Substitui do titulo : por - para evitar erros do windows									    // Prepara string de rank
			gerador.cria(inputStream, titulo, rank); 							// Envia o diretorio, o titulo e o rank para a classe processar e gerar as imagens
			System.out.println();												// Pula linha
			
			
		}
	}
	
	

}
