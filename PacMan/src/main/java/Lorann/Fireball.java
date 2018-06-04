package Lorann;

import java.awt.*;
import java.util.Random;

public class Fireball extends Rectangle{

	private static final long serialVersionUID = 1L;

	public int xfireDir, yfireDir;
	
	private int speed = 32;
	public int color;
	
	public Random random;
	
	
	public Fireball(int x, int y) {						//get initial position and the space
		setBounds(x,y,32,32);

	}
	
	public void tick() {
	
		Player player = Game.player;
		Level level = Game.level;
		
		for(int i = 0; i < level.enemies.size(); i++) {
			if(this.intersects(level.enemies.get(i))){
				level.enemies.remove(i);							//remove monster of the list if the fireball is in the same position
				Game.fireball.x = -32;
				Game.fireball.y = -32;
				break;
			}
		}
		
		if(Game.fireball.x > 0) {
			if(canMove(x+xfireDir*speed,y+yfireDir*speed)) {
				Game.fireball.x = x + xfireDir*speed;
				Game.fireball.y = y + yfireDir*speed;	
			}else{
			xfireDir = -1 * xfireDir;
			yfireDir = -1 * yfireDir;
			}	
		}
		
		if(player.shot && Game.fireball.x < 0 ) {
			if(player.right) {
				x = player.x-32;
				y = player.y;
				xfireDir = -1;
				yfireDir = 0;
			}
			if(player.left) {
				x = player.x+32;
				y = player.y;
				xfireDir = 1;
				yfireDir = 0;
			}
			if(player.up) {
				x = player.x;
				y = player.y+32;
				xfireDir = 0;
				yfireDir = 1;
			}
			if(player.down) {
				x = player.x;
				y = player.y-32;
				xfireDir = 0;
				yfireDir = -1;
			}
		}
		
		for(int i = 0; i < level.enemies.size(); i++) {
			if(this.intersects(level.enemies.get(i))){
				level.enemies.remove(i);							//remove monster of the list if the fireball is in the same position
				Game.fireball.x = -32;
				Game.fireball.y = -32;
				break;
			}
		}
		
	}
	
	public boolean canMove(int nextX, int nextY) {

		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Level level = Game.level;
		
		if(Game.player.x == nextX && Game.player.y == nextY) {
			Game.fireball.x = -32;
			Game.fireball.y = -32;
			return false;	
		}else if(Game.player.x+32*xfireDir == nextX && Game.player.y+32*yfireDir == nextY) {
			Game.fireball.x = -32;
			Game.fireball.y = -32;
			return false;	
		}
		
		
		for(int xx = 0; xx < level.bones.length; xx++) {										//collision with a bone
			for(int yy = 0; yy < level.bones[0].length; yy++) {
				if(level.bones[xx][yy] != null) {
					if(bounds.intersects(level.bones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.vertiBones.length; xx++) {									//collision with a vert_bone
			for(int yy = 0; yy < level.vertiBones[0].length; yy++) {
				if(level.vertiBones[xx][yy] != null) {
					if(bounds.intersects(level.vertiBones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.horiBones.length; xx++) {									//collision with an hori_bone
			for(int yy = 0; yy < level.horiBones[0].length; yy++) {
				if(level.horiBones[xx][yy] != null) {
					if(bounds.intersects(level.horiBones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.gate.length; xx++) {											//collision with the gate
			for(int yy = 0; yy < level.gate[0].length; yy++) {
				if(level.gate[xx][yy] != null) {
					if(bounds.intersects(level.gate[xx][yy])) {
						return false;
					
					}
				}
			}
		}

		for(int i = 0; i < level.crystal.size(); i++) {											//collision with a crystal
			if(bounds.intersects(level.crystal.get(i))){
				return false;
			}
		}
		
		for(int i = 0; i < level.purses.size(); i++) {											//collision with a purse
			if(bounds.intersects(level.purses.get(i))){
				return false;
			}
		}
		
		return true;
	}
	
	public void render(Graphics g) {
		
		random = new Random();
		color = random.nextInt(5);
		
		if(color == 0){
			SpriteSheet sheet = Game.fireball_1;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
		}else if(color == 1) {
			SpriteSheet sheet = Game.fireball_2;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
		}else if(color == 2) {
			SpriteSheet sheet = Game.fireball_3;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
		}else if(color == 3) {
			SpriteSheet sheet = Game.fireball_4;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
		}else if(color == 4) {
			SpriteSheet sheet = Game.fireball_5;
			g.drawImage(sheet.getSprite(0,0),x,y,null);	
		}
	}
	
}
