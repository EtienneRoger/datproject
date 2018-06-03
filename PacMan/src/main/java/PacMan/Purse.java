package PacMan;

import java.awt.*;

public class Purse extends Rectangle {

	private static final long serialVersionUID = 1L;

	public Purse(int x, int y) {							//get initial position and the space
		setBounds(x,y,32,32);
	}
	
	public void render(Graphics g) {
		SpriteSheet sheet = Game.purseSprite;
		g.drawImage(sheet.getSprite(0,0),x,y,null); 		// set Purses's graphics POSITION and SIZE
	}
}