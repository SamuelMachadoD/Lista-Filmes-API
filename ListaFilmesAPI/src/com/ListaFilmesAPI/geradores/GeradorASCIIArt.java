package com.ListaFilmesAPI.geradores;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeradorASCIIArt {
	
	public static String artASCII(String imagem) throws Exception {
		
		BufferedImage img = ImageIO.read(new URL(imagem)); 													// Le a url da imagem recebida
		
		int newWidth = 125;																					// Redimensiona a imagem
		int newHeight = (int)(((double)img.getHeight() / img.getWidth()) * newWidth);						// |
		BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);		// |
        resizedImg.getGraphics().drawImage(img, 0, 0, newWidth, newHeight, null);							// |
        																									// <->
        
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
