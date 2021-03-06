package motionless;

import java.awt.*;

import main.Game;


public class Vertical_Bone extends Rectangle{

	private static final long serialVersionUID = 1L;

	public Vertical_Bone(int x, int y) {					//get initial position and the space
		setBounds(x,y,32,32);
	}
	
	public void render (Graphics g) {
		SpriteSheet sheet = Game.vertiBoneSprite;
		g.drawImage(sheet.getSprite(0,0),x,y,null);			// set Wall's graphics POSITION and SIZE
	}
}
