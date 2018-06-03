package PacMan;

import java.awt.*;

public class Gate extends Rectangle {

	private static final long serialVersionUID = 1L;

	public Gate(int x, int y) {
		setBounds(x,y,32,32);
	}
	
	public void render(Graphics g) {
		
		Level level = Game.level;		
		
		if(level.crystal.size() != 0) {
			SpriteSheet sheet = Game.gateClosed;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else {
			SpriteSheet sheet = Game.gateOpen;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}
	
	}
	
}