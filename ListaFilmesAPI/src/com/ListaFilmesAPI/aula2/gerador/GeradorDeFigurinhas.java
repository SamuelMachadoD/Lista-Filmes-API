package com.ListaFilmesAPI.aula2.gerador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
	
	public void cria(InputStream inputStream, String nomeArquivo, String rank) throws Exception {
		
		/*
		* LEITURA DE IMAGENS
		 *
		 *	ATRAVÉS DE ARQUIVOS
		 *	InputStream inputStream = 
		 *				new FileInputStream(new File("entrada/filme.jpg"));
		 *
		 *	POR URL DA INTERNET
		 *	InputStream inputStream =	
		 *				new URL(url).openStream();
		*/
		
		BufferedImage imagemOriginal = ImageIO.read(inputStream);						// Recebe a url/endereço local da imagem
		
		int largura = imagemOriginal.getWidth();										// Le e armazena a largura da imagem
		int altura = imagemOriginal.getHeight();										// Le e armazena a altura da imagem
		int novaAltura = altura + 200;													// Cria uma nova altura baseada na original para criar um espaço em baixo da imagem
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT); // Cria uma nova imagem com transparencia, com a nova altura e largura
		
		// Copiando a imagem original para a nova em memória
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics(); 					// Instancia a classe graphics utilizando a imagem nova de referencia
		graphics.drawImage(imagemOriginal, 0, 0, null);									// Desenha a imagem com graphics
		
		// Configurando o Graphics2D
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);							// Cria uma fonte e a armazena
		graphics.setFont(fonte);														// Configura a fonte do Graphics2D para minha variavel
		graphics.setColor(Color.RED);													// Configurar a cor para vermelho
		
		// Escrevendo uma frase na nova imagem
		String frase = nomeArquivo + " --- " + rank; 									// Concatena e armazena os dados na variavel
		graphics.drawString(frase, 0, (altura + 50));									// Escreve na imagem recebendo respectivamente: String, x, y
		
		// Escrever a nova imagem em um arquivo, cria uma pasta caso não exista
		File file = new File("saida/" + (nomeArquivo + ".png")); 						// Armazena diretorio para qual a imagem ira, e seu titulo
		if (!file.getParentFile().exists()) {											// Verifica se diretorio não é existente
		    file.getParentFile().mkdirs();												// Caso true, cria o mesmo
		}
		ImageIO.write(novaImagem, "png", new FileOutputStream(file));					// Imprime a imagem recebendo : A imagem, o tipo, e o diretorio
		
	}
}
