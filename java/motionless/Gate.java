package motionless;

import java.awt.*;

import main.Game;
import main.Level;


public class Gate extends Rectangle {

	private static final long serialVersionUID = 1L;

	public Gate(int x, int y) {							//get initial position and the space
		setBounds(x,y,32,32);
	}
	
	public void render(Graphics g) {					// set Gate's graphics POSITION and SIZE when it is open or close
		
		Level level = Game.level;		
		
		if(!level.crystal.isEmpty()) {					//Look if the list of crystal is empty for put the right sprite
			SpriteSheet sheet = Game.gateClosed;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else {
			SpriteSheet sheet = Game.gateOpen;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}
	
	}
	
}