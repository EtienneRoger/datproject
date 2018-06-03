package PacMan;

import java.awt.*;

public class Horizontal_Bone extends Rectangle{

	private static final long serialVersionUID = 1L;

	public Horizontal_Bone(int x, int y) {
		setBounds(x,y,32,32);
	}
	
	public void render (Graphics g) {
		SpriteSheet sheet = Game.horiBoneSprite;
		g.drawImage(sheet.getSprite(0,0),x,y,null);			// set Wall's graphics POSITION and SIZE
	}
}
