import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CharacterObject extends MapObject{

	public int characterCode;
	public boolean[][] pixels;
	
	public CharacterObject(char character) {
		characterCode = (int)character;
		
		// Lowercase -> Uppercase
		if(characterCode >= 97 && characterCode <= 122)
			characterCode -= 32;
		
		// Characters
		if(characterCode >= 65 && characterCode <= 90) {
			y = ((characterCode - 65) / 15) * 8;
			x = ((characterCode - 65) % 15) * 8;
		}
		// Numbers
		else if(characterCode >= 48 && characterCode <= 57) {
			y = 2 * 8;
			x = ((characterCode - 48) % 15) * 8;
		}
		else if (characterCode == 46) { y = 3 * 8; x = 0 * 8; } // . 
		else if (characterCode == 44) { y = 3 * 8; x = 1 * 8; } // ,
		else if (characterCode == 45) { y = 3 * 8; x = 2 * 8; } // -
		else if (characterCode == 43) { y = 3 * 8; x = 3 * 8; } // +
		else if (characterCode == 33) { y = 3 * 8; x = 4 * 8; } // !
		else if (characterCode == 39) { y = 3 * 8; x = 5 * 8; } // '
		else if (characterCode == 58) { y = 3 * 8; x = 6 * 8; } // :
		else if (characterCode == 63) { y = 3 * 8; x = 7 * 8; } // ?
		else if (characterCode == 32) { y = 3 * 8; x = 8 * 8; } // (space
		
		BufferedImage hugeImage = null;
		try {
			hugeImage = ImageIO.read(new File(/*getClass().getResource(*/"Text.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		pixels = convertTo2DUsingGetRGB(hugeImage, x, y);
		color = Color.white;
	         
	}
	public void rotate(int times) {
		for (int i = 0; i < times; i++) {
			pixels = RotateMatrix(pixels, 8);
		}
	}
	static boolean[][] RotateMatrix(boolean[][] matrix, int n) {
		boolean[][] ret = new boolean[n][n];

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				ret[i][j] = matrix[n - j - 1][i];
			}
		}

		return ret;
	}
	 private static boolean[][] convertTo2DUsingGetRGB(BufferedImage image, int x, int y) {

	      boolean[][] result = new boolean[8][8];

	      for (int row = x; row < x + 8; row++) {
	         for (int col = y; col < y + 8; col++) {
	        	 if(image.getRGB(row, col) == Color.white.getRGB())
	        		 result[row - x][col - y] = true;
	        	 else
	        		 result[row - x][col - y] = false;
	         }
	      }

	      return result;
	   }
	 
	void drawTop(int[][] map) {
		for(int i = x; i < x + 8; i++) {
			for(int j = y; j < y + 8; j++) {
				if(i >= x && j >= y)
					if(pixels[(i - x)][j - y])
						map[i][j] = color.getRGB();
			}
		}
	}
}
