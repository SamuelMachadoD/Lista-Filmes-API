package com.ListaFilmesAPI.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.ListaFilmesAPI.figurinhas.GeradorDeFigurinhas;

public class Main {

	public static void main(String[] args) throws Exception {

		// fazer conexão HTTP e buscar os top 250 filmes 
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json"; // Armazena o link da API
		URI endereco = URI.create(url); //Le e armazena o conteudo da API em uma url
		var client = HttpClient.newHttpClient();//Instancia um cliente //é possivel trocar o HttpClient por var / deixando para o java sozinho definir como HttpClient ( é preciso esta especificado na direita )
		var request = HttpRequest.newBuilder(endereco).GET().build();//Faz uma request com a URL //o mesmo do var pode ocorrer aqui
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());//Gera uma resposta a partir do cliente e request
		String body = response.body();  // armazena o body da reposta em uma variavel
		
		// extrair só os dados que interessam (titulo, poster, classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
		//Cria o gerador de figurinhas
		var gerador = new GeradorDeFigurinhas();
		
		// exibir e manipular os dados e gera ASCII arte
		for (Map<String, String> filme : listaDeFilmes) { //passa por cada filme da listaDeFilmes
			
			String titulo = filme.get("title");
			String rank = filme.get("rank");
			System.out.println("\u001b[1m\u001b[40m Rank: " + rank + " \u001b[m");
			System.out.println("\u001b[1m\u001b[47m\u001b[30m " + titulo + " \u001b[m");
			System.out.println("\u001b[43m\u001b[1m Rating: " + filme.get("imDbRating") + " \u001b[m");
			
			//Gera art ASCII e cria figurinhas
			String img = filme.get("image");
			System.out.println(geraASCII(img));
			
			InputStream inputStream = new URL(img).openStream();
			titulo = titulo.replace(":", "-");
			rank = "Posição : " + rank;
			gerador.cria(inputStream, titulo, rank);
			System.out.println();
			
			// escreve o que esta na chave desejada
		}
	}
	
	public static String geraASCII(String imagem) throws Exception {
		
		//carrega a imagem
		BufferedImage img = ImageIO.read(new URL(imagem));
		
		//redimensiona a imagem
		int newWidth = 125;
		int newHeight = (int)(((double)img.getHeight() / img.getWidth()) * newWidth);
		BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        resizedImg.getGraphics().drawImage(img, 0, 0, newWidth, newHeight, null);
        
        //Mapeia os caracteres ASCII conrrespondentes de acordo com a luminosidade
        String asciiChars = "@#S%=+*:-. ";
        int numChars = asciiChars.length();
        int[] luminanceMap = new int[numChars];
        for (int i = 0; i < numChars; i++) {
            luminanceMap[i] = (int) (255.0 * ((double) i / (numChars - 1)));
        }
        
        // Converte a imagem em ASCII art
        StringBuilder asciiArt = new StringBuilder();
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                Color color = new Color(resizedImg.getRGB(x, y));
                double luminance = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
                int index = 0;
                for (int i = 0; i < numChars; i++) {
                    if (luminance > luminanceMap[i]) {
                        index = i;
                    } else {
                        break;
                    }
                }
                asciiArt.append(asciiChars.charAt(index));
            }
            asciiArt.append("\n");
        }
		return asciiArt.toString();
	}

}
