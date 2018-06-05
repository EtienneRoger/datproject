package motionless;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage sheet;
	
	
	public SpriteSheet(String path) {
		
		try {
			sheet = ImageIO.read(getClass().getResource(path));		//read the sprite of the path
		} catch (IOException e) {
			System.out.println("failed to load");
		}	
	}
	
		
	public BufferedImage getSprite(int xx, int yy) {				//get a part of a sprite
		return sheet.getSubimage(xx, yy, 32, 32);
	}
		
}
