package com.ListaFilmesAPI.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHTTP {
	
		public String busca(String url){
			
			
			try {
				
				URI endereco = URI.create(url); 													// Le e armazena o conteudo da API em uma url
				var client = HttpClient.newHttpClient();											// Instancia um cliente
																									// É possivel trocar o HttpClient por var deixando para o java sozinho definir seu tipo
				var request = HttpRequest.newBuilder(endereco).GET().build();						// Faz uma request com a URL
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());		//Gera uma resposta a partir do cliente e request
				return response.body();  															// Retorna o body da reposta em uma variavel
				
			}catch(Exception ex){
				
				throw new HttpException("Erro ao consultar URL");
				
			}

		}
}
