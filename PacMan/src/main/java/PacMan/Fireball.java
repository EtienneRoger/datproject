package PacMan;

import java.awt.*;

public class Fireball extends Rectangle{

	private static final long serialVersionUID = 1L;

	private int speed = 32;
	
	
	public Fireball(int x, int y) {						//get initial position and the space
		setBounds(x,y,32,32);
	}
	
	public void tick() {
		
		/*Player player = Game.player;
		Level level = Game.level;

		while(canMove(x,y) == true) {
			System.out.println("la fierball veut bouger");
			x = x + player.xfireDir;
			y = y + player.yfireDir;
			level.fireball.remove(0);
			level.crystal.add(new Crystal(x*32,y*32));
		}
		player.xfireDir = -1 * player.xfireDir;
		player.yfireDir = -1 * player.yfireDir;
		System.out.println("ca passe par la");*/
		
		
	}
	
	public boolean canMove(int nextX, int nextY) {

		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Level level = Game.level;
		
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
		
		for(int xx = 0; xx < level.enemies.length; xx++) {
			for(int yy = 0; yy < level.enemies[0].length; yy++) {
				if(level.enemies[xx][yy] != null) {
					if(bounds.intersects(level.enemies[xx][yy])) {
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
		
	}
	
	
	
}
