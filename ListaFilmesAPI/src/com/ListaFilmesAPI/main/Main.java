package com.ListaFilmesAPI.main;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.ListaFilmesAPI.extratores.ExtratorDeConteudo;
import com.ListaFilmesAPI.extratores.ExtratorFilmes;
import com.ListaFilmesAPI.extratores.ExtratorNasa;
import com.ListaFilmesAPI.geradores.GeradorASCIIArt;
import com.ListaFilmesAPI.geradores.GeradorDeFigurinhas;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// Instancia as classes
		var http = new ClienteHTTP();
		
		
		var gerador = new GeradorDeFigurinhas(); 		

// 		API NASA
//		ExtratorDeConteudo extratorNasa = new ExtratorNasa();
//		String url = "SK9plXLYpGPq1T6i3O6xfZM1wlOSP09ldLjb9Bvq";
		
//		API FILMES
		ExtratorDeConteudo extratorFilmes = new ExtratorFilmes();
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
		
		String json = http.busca(url);
		
		List<Conteudo> conteudos = extratorFilmes.extrai(json);
		
		for (int i = 0; i < 3 ; i++) {           
			
			Conteudo conteudo = conteudos.get(i);
			System.out.println("\u001b[1m\u001b[47m\u001b[30m " + conteudo.getTitulo() + " \u001b[m");					
			System.out.println(GeradorASCIIArt.artASCII(conteudo.getImagem()));						
			
			InputStream inputStream = new URL(conteudo.getImagem()).openStream();			// Recebe diretorio da imagem
			String titulo = conteudo.getTitulo().replace(":", "-"); 						// Substitui do titulo : por - para evitar erros do windows									    // Prepara string de rank
			gerador.cria(inputStream, titulo); 												// Envia o diretorio, o titulo e o rank para a classe processar e gerar as imagens
			System.out.println();															// Pula linha
			
			
		}
	}
	
	

}
