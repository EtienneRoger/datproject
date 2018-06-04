package Lorann;

import java.awt.*;

public class Fireball extends Rectangle{

	private static final long serialVersionUID = 1L;

	private int speed = 8;
	
	
	public Fireball(int x, int y) {						//get initial position and the space
		setBounds(x,y,32,32);
	}
	
	public void tick() {
	
		Player player = Game.player;
		
		while(Game.fireball.x > 0) {
			while(canMove(x+player.xfireDir*speed,y+ player.yfireDir*speed)) {
				System.out.println("Fireball Moved");
				Game.fireball.x = x + player.xfireDir*speed;
				Game.fireball.y = y + player.yfireDir*speed;
			}
			
			player.xfireDir = -1 * player.xfireDir;
			player.yfireDir = -1 * player.yfireDir;
			System.out.println("Fireball Blocked");
			
			
		}
	}
	
	public boolean canMove(int nextX, int nextY) {

		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Level level = Game.level;
		
		if(Game.player.x == Game.fireball.x && Game.player.y == Game.fireball.y) {
			Game.fireball.x = -32;
			Game.fireball.y = -32;
			return false;	
		}
		
		for(int xx = 0; xx < level.bones.length; xx++) {
			for(int yy = 0; yy < level.bones[0].length; yy++) {
				if(level.bones[xx][yy] != null) {
					if(bounds.intersects(level.bones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.vertiBones.length; xx++) {
			for(int yy = 0; yy < level.vertiBones[0].length; yy++) {
				if(level.vertiBones[xx][yy] != null) {
					if(bounds.intersects(level.vertiBones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.horiBones.length; xx++) {
			for(int yy = 0; yy < level.horiBones[0].length; yy++) {
				if(level.horiBones[xx][yy] != null) {
					if(bounds.intersects(level.horiBones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.gate.length; xx++) {
			for(int yy = 0; yy < level.gate[0].length; yy++) {
				if(level.gate[xx][yy] != null) {
					if(bounds.intersects(level.gate[xx][yy])) {
						return false;
					
					}
				}
			}
		}

		return true;
	}
	
	public void render(Graphics g) {
			
		SpriteSheet sheet = Game.fireball_1;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
/*			SpriteSheet sheet = Game.fireball_2;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
			SpriteSheet sheet = Game.fireball_3;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
			SpriteSheet sheet = Game.fireball_1;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	 */

	}
	
}
