package PacMan;

import java.awt.*;

public class Fireball extends Rectangle{

	private static final long serialVersionUID = 1L;

	private int speed = 32;
	private int color = 1;
	
	
	public Fireball(int x, int y) {						//get initial position and the space
		setBounds(x,y,32,32);
	}
	
	public void tick() {
	
		Player player = Game.player;

		while(player.shot && canMove(x+player.xfireDir*speed,y+ player.yfireDir*speed)) {
			System.out.println("la fireball veut bouger");
			Game.fireball.x = x + player.xfireDir*speed;
			Game.fireball.y = y + player.yfireDir*speed;
			
		}
		player.xfireDir = -1 * player.xfireDir;
		player.yfireDir = -1 * player.yfireDir;
		System.out.println("ca passe par la");
		
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
		
		return true;
	}
	
	public void render(Graphics g) {
		
		if(color == 1) {
			SpriteSheet sheet = Game.fireball_1;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
			color++;
		}else if(color == 2) {
			SpriteSheet sheet = Game.fireball_2;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
			color++;
		}else if(color == 3) {
			SpriteSheet sheet = Game.fireball_3;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
			color++;
		}else if(color == 4) {
			SpriteSheet sheet = Game.fireball_1;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
			color = 1;
		}
	}
	
	
	
}
