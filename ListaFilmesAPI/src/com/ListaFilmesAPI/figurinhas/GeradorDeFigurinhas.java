package com.ListaFilmesAPI.figurinhas;

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
		
		//leitura da imagem
		
		//ARQUIVO
		//InputStream inputStream = 
		//				new FileInputStream(new File("entrada/filme.jpg"));
		//INTERNET
		//InputStream inputStream =	
		//				new URL(url).openStream();
		
		BufferedImage imagemOriginal = ImageIO.read(inputStream);
		
		//cria nova imagem em memória com transparencia e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		
		//copiar a imagem original para a nova em memória
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);
		
		//configurando a fonte
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 130);
		graphics.setFont(fonte);
		graphics.setColor(Color.RED);
		
		//escrever uma frase na nova imagem
		String frase = nomeArquivo + " --- " + rank; 
		graphics.drawString(frase, 0, (altura + 50));
		
		//escrever a nova imagem em um arquivo, cria uma pasta caso não exista
		File file = new File("saida/" + (nomeArquivo + ".png"));
		if (!file.getParentFile().exists()) {
		    file.getParentFile().mkdirs();
		}
		ImageIO.write(novaImagem, "png", new FileOutputStream(file));
		
		
	}
}
