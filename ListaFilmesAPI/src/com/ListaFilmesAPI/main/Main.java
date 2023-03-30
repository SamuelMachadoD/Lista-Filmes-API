package com.ListaFilmesAPI.main;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.ListaFilmesAPI.extratores.ExtratorDeConteudo;
import com.ListaFilmesAPI.extratores.ExtratorFilmes;
import com.ListaFilmesAPI.extratores.ExtratorNasa;
import com.ListaFilmesAPI.geradores.GeradorASCIIArt;
import com.ListaFilmesAPI.geradores.GeradorDeFigurinhas;
import com.ListaFilmesAPI.http.ClienteHTTP;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// Instancia as classes
		var http = new ClienteHTTP();
		var gerador = new GeradorDeFigurinhas(); 		
		

		API api = API.NASA_APOD;															//	Escolhe a api, baseada em um ENUM
		String url = api.getUrl();															// 	Pega a url da api
		ExtratorDeConteudo extrator = api.getExtrator();									// 	Pega a classe extratora de conteudo da respectiva api
		String json = http.busca(url);														// 	Estabelece conexão HTTP
		
		List<Conteudo> conteudos = extrator.extrai(json);									//	Recebe uma lista com as informações extraidas do json
		
		for (int i = 0; i < conteudos.size() ; i++) {           
			
			Conteudo conteudo = conteudos.get(i);
			System.out.println("\u001b[1m\u001b[47m\u001b[30m " + conteudo.titulo() + " \u001b[m");					
			System.out.println(GeradorASCIIArt.artASCII(conteudo.urlImagem()));				//	Gera a ASCII art		
			
			InputStream inputStream = new URL(conteudo.urlImagem()).openStream();			// 	Recebe diretorio da imagem
			String titulo = conteudo.titulo().replace(":", "-"); 							// 	Substitui do titulo : por - para evitar erros do windows									    // Prepara string de rank
			gerador.cria(inputStream, titulo); 												//	Envia o diretorio, o titulo e o rank para a classe processar e gerar as imagens
			System.out.println();															// 	Pula linha
			
			
		}
	}
	
	

}
