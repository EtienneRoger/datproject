package Lorann;

import java.awt.*;
import java.util.Random;

import PacMan.Game;
import PacMan.Level;

public class Enemy extends Rectangle{

	private static final long serialVersionUID = 1L;

	public Enemy(int x, int y){					//get initial position and the space
		setBounds(x,y,32,32);
		
	}
	
	public void tick() {
		
/*		if(6)x+=speed;
		if(4)x-=speed;
		if(8)y-=speed;
		if(2)y+=speed;
		if(1) {x-=speed; y+=speed;}
		if(7) {x-=speed; y-=speed;}
		if(3) {x+=speed; y+=speed;}
		if(9) {x+=speed; y-=speed;} */
	
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
				if(level.gate[xx][yy] != null && level.crystal.size() != 0) {
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
		
		SpriteSheet sheet = Game.monster_3;
		g.drawImage(sheet.getSprite(0,0),x,y,null);
		
/*		if(Level.randomEnemies == 0) {
		SpriteSheet sheet = Game.monster_1;
		g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(Level.randomEnemies == 1) {
			SpriteSheet sheet = Game.monster_2;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(Level.randomEnemies == 2) {
			SpriteSheet sheet = Game.monster_3;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(Level.randomEnemies == 3) {
			SpriteSheet sheet = Game.monster_4;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}	*/
	}
	
}
 