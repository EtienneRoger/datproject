package PacMan;

import java.awt.*;

public class Crystal extends Rectangle {

	private static final long serialVersionUID = 1L;

	public Crystal(int x, int y) {							//get initial position and the space
		setBounds(x,y,32,32);
	}
	
	public void render(Graphics g) {
		SpriteSheet sheet = Game.crystalSprite;
		g.drawImage(sheet.getSprite(0,0),x,y,null);			// set Crystal's graphics POSITION and SIZE
	}
}