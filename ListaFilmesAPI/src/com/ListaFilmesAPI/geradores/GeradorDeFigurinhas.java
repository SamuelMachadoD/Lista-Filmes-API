package com.ListaFilmesAPI.geradores;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
	
	public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
		
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
		var fonte = new Font("Impact", Font.BOLD, 100);									// Cria uma fonte e a armazena
		graphics.setFont(fonte);														// Configura a fonte do Graphics2D para minha variavel
		
//		switch(rank) {																	// Configurar a cor de acordo com o rank
//			case "1":
//				graphics.setColor(Color.YELLOW);
//				break;
//			case "2":
//				graphics.setColor(Color.LIGHT_GRAY);
//				break;
//			case "3":
//				graphics.setColor(Color.magenta);
//				break;
//			default:
//				graphics.setColor(Color.WHITE);
//				break;
//		}
														
		
		
		// Escrevendo uma frase
		String frase = nomeArquivo; 									// Concatena e armazena os dados na variavel
		FontMetrics fontMetrics = graphics.getFontMetrics();							// Pega as medidas do texto (altura, largura)
		Rectangle2D retanguloFrase = fontMetrics.getStringBounds(frase, graphics);		// Cria um retangulo baseado na altura e largura do texto
		int larguraTexto = (int) retanguloFrase.getWidth();								// Armazena a posição X do retangulo
		int posicaoTextoX = (largura - larguraTexto)/2;									// Ajusta a posição X do texto
		int posicaoTextoY =  (altura + (int)(retanguloFrase.getHeight())) + 10;			// Ajusta posição Y do texto
		graphics.drawString(frase, posicaoTextoX, posicaoTextoY);						// Escreve na imagem recebendo respectivamente: String, x, y
		
		// Fazendo o contorno dessa frase
		FontRenderContext fontRenderContext = graphics.getFontRenderContext();			
		var textLayout = new TextLayout(frase, fonte, fontRenderContext);				
		Shape outline = textLayout.getOutline(null);
		AffineTransform transform = graphics.getTransform();
		transform.translate(posicaoTextoX, posicaoTextoY);
		graphics.setTransform(transform);
		
		var outlineStroke = new BasicStroke(largura * 0.004f);
		graphics.setStroke(outlineStroke);
		
		graphics.setColor(Color.BLACK);
		graphics.draw(outline);
		graphics.setClip(outline);
		
		// Escrever a nova imagem em um arquivo, cria uma pasta caso não exista																				
		File file = new File("saida/" + (nomeArquivo + ".png")); 						// Armazena diretorio para qual a imagem ira, e seu titulo
		if (!file.getParentFile().exists()) {											// Verifica se diretorio não é existente
		    file.getParentFile().mkdirs();												// Caso true, cria o mesmo
		}
		ImageIO.write(novaImagem, "png", new FileOutputStream(file));					// Imprime a imagem recebendo : A imagem, o tipo, e o diretorio
		
	}
}
