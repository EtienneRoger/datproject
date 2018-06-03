package PacMan;

import java.awt.*;

public class Player extends Rectangle{

	private static final long serialVersionUID = 1L;

	
	public boolean right,left,up,down,shot;
	private int speed = 32;
	public int xfireDir, yfireDir;
	public static int Score = 0;
	
	public Player (int x, int y) {
		
		setBounds(x,y,32,32);
		
		
	}
		
	public void tick() {
		
		Level level = Game.level;

		if(right && canMove(x+speed,y))x+=speed;
		if(left && canMove(x-speed,y))x-=speed;
		if(up && canMove(x,y-speed))y-=speed;
		if(down && canMove(x,y+speed))y+=speed;
		if(left && down && canMove(x-speed,y+speed)) {x-=speed; y+=speed;}
		if(left && up && canMove(x-speed,y-speed)) {x-=speed; y-=speed;}
		if(right && down && canMove(x+speed,y+speed)) {x+=speed; y+=speed;}
		if(right && up && canMove(x+speed,y-speed)) {x+=speed; y-=speed;}
		
		
		if(level.fireball.size() == 0){
			if(shot && right) {
				level.fireball.add(new Fireball(x-speed,y));
				xfireDir = -1;
				yfireDir = 0;
			}
			if(shot && left) {
				level.fireball.add(new Fireball(x+speed,y));
				xfireDir = 1;
				yfireDir = 0;
			}
			if(shot && up) {
				level.fireball.add(new Fireball(x,y+speed));
				xfireDir = 0;
				yfireDir = 1;
			}
			if(shot && down) {
				level.fireball.add(new Fireball(x,y-speed));
				xfireDir = 0;
				yfireDir = -1;
			}
		}else {System.out.println("la fireball est lance");}
		
		
		for(int i = 0; i < level.crystal.size(); i++) {
			if(this.intersects(level.crystal.get(i))){
				level.crystal.remove(i);
				break;
			}
		}
		
		for(int i = 0; i < level.purses.size(); i++) {
			if(this.intersects(level.purses.get(i))){
				level.purses.remove(i);
				Score = Score + 10;
				break;
			}
		}
		
		for(int i = 0; i < level.fireball.size(); i++) {
			if(this.intersects(level.fireball.get(i))){
				level.fireball.remove(i);
				break;
			}
		}
		
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
					if(bounds.intersects(level.gate[xx][yy])) {						//Si on veut differencier le cas ou la porte est ferme et l on meurt ou ouverte et l on gagne
						Score = 0;
						Game.player = new Player(32,32);
						Game.level = new Level("/level/map.png");
					}
				}else if(level.gate[xx][yy] != null && level.crystal.size() == 0) {
					if(bounds.intersects(level.gate[xx][yy])) {
						Score = Score + 50;
						Game.player = new Player(32,32);
						Game.level = new Level("/level/map.png");
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.enemies.length; xx++) {
			for(int yy = 0; yy < level.enemies[0].length; yy++) {
				if(level.enemies[xx][yy] != null) {
					if(bounds.intersects(level.enemies[xx][yy])) {
						Score = 0;
						Game.player = new Player(32,32);
						Game.level = new Level("/level/map.png");
					}
				}
			}
		}
		
		return true;
	}
	
	public void render (Graphics g, int c) {
		
		
		
		if(right == true && up == true) {
			SpriteSheet sheet = Game.playerRU;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(right == true && down == true) {
			SpriteSheet sheet = Game.playerRB;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(left == true && up == true) {
			SpriteSheet sheet = Game.playerLU;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(left == true && down == true) {
			SpriteSheet sheet = Game.playerLB;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(right ==true) {
			SpriteSheet sheet = Game.playerR;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(left ==true) {
			SpriteSheet sheet = Game.playerL;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(up ==true) {
			SpriteSheet sheet = Game.playerU;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(down ==true) {
			SpriteSheet sheet = Game.playerB;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else {

			if(c == 0) {
				SpriteSheet sheet = Game.playerL;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c++;
			}else if(c == 1) {
				SpriteSheet sheet = Game.playerLU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c++;
			}else if(c == 2) {
				SpriteSheet sheet = Game.playerU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c++;
			}else if(c == 3) {
				SpriteSheet sheet = Game.playerRU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c++;
			}else if(c == 4) {
				SpriteSheet sheet = Game.playerR;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c++;
			}else if(c == 4) {
				SpriteSheet sheet = Game.playerRB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c++;
			}else if(c == 6) {
				SpriteSheet sheet = Game.playerB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c++;
			}else if(c == 7) {
				SpriteSheet sheet = Game.playerLB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				c = 0;
			}
		}
			
	}
	
}
