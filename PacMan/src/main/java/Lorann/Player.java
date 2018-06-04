package Lorann;

import java.awt.*;

import PacMan.Game;
import PacMan.Level;
import PacMan.Player;

public class Player extends Rectangle{

	private static final long serialVersionUID = 1L;
	private int speed = 32;								//set the "speed" of our player who move one case by one case, so 32pixel
	
	public boolean right,left,up,down,shot;				//initialize the action of the player
	
	public int xfireDir, yfireDir;						
	
	public static int Score = 0;						//initialize the player's score
	
	
	
	public Player (int x, int y) {						//get initial position and the space
		
		setBounds(x,y,32,32);
		
		
	}
		
	public void tick() {

		Level level = Game.level;
		
		if(right && canMove(x+speed,y))x+=speed;										//set the new position of the player 
		if(left && canMove(x-speed,y))x-=speed;
		if(up && canMove(x,y-speed))y-=speed;
		if(down && canMove(x,y+speed))y+=speed;
		if(left && down && canMove(x-speed,y+speed)) {x-=speed; y+=speed;}
		if(left && up && canMove(x-speed,y-speed)) {x-=speed; y-=speed;}
		if(right && down && canMove(x+speed,y+speed)) {x+=speed; y+=speed;}
		if(right && up && canMove(x+speed,y-speed)) {x+=speed; y-=speed;}
		
		if(shot && Game.fireball.x < 0 ) {
			if(right) {
				Game.fireball.x = x-32;
				Game.fireball.y = y;
				xfireDir = -1;
				yfireDir = 0;
			}
			if(left) {
				Game.fireball.x = x+32;
				Game.fireball.y = y;
				xfireDir = 1;
				yfireDir = 0;
			}
			if(up) {
				Game.fireball.x = x;
				Game.fireball.y = y+32;
				xfireDir = 0;
				yfireDir = 1;
			}
			if(down) {
				Game.fireball.x = x;
				Game.fireball.y = y-32;
				xfireDir = 0;
				yfireDir = -1;
			}
		}
		
		
		for(int i = 0; i < level.crystal.size(); i++) {
			if(this.intersects(level.crystal.get(i))){
				level.crystal.remove(i);							//remove block of the list if the player is in the same position
				break;
			}
		}
		
		for(int i = 0; i < level.purses.size(); i++) {
			if(this.intersects(level.purses.get(i))){
				level.purses.remove(i);
				Score = Score + 10;									//Add score for pick a purse
				break;
			}
		}
		
		if(this.intersects(Game.fireball)) {
			Game.fireball.x = -32;
			Game.fireball.y = -32;
		}
		
	}
	
	public boolean canMove(int nextX, int nextY) {										// Look for collision around the player

		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Level level = Game.level;
		
		for(int xx = 0; xx < level.bones.length; xx++) {								// loop for take all near block
			for(int yy = 0; yy < level.bones[0].length; yy++) {
				if(level.bones[xx][yy] != null) {
					if(bounds.intersects(level.bones[xx][yy])) {						// if it is a bones
						return false;													// he can't
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
						Score = 0;														//Reset Score
						Game.player = new Player(32,32);								//Initialize a new player
						Game.level = new Level("/level/map.png");						//Initialize the same map
					}
				}else if(level.gate[xx][yy] != null && level.crystal.size() == 0) {
					if(bounds.intersects(level.gate[xx][yy])) {
						Score = Score + 50;												//Add 50pts if you finish the level
						Game.player = new Player(32,32);								//Initialize a new player
						Game.level = new Level("/level/map.png");						//Initialize the next map (who doesnt exist actually)
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.enemies.length; xx++) {
			for(int yy = 0; yy < level.enemies[0].length; yy++) {
				if(level.enemies[xx][yy] != null) {
					if(bounds.intersects(level.enemies[xx][yy])) {
						Score = 0;														//Reset Score
						Game.player = new Player(32,32);								//Initialize a new player
						Game.level = new Level("/level/map.png");						//Initialize the same map
					}
				}
			}
		}
		
		return true;																	//for all other block, the player can move on
	}
	
	public void render (Graphics g) {					// set Player's graphics POSITION and SIZE with the orientation "contrainte"
		
		
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
				SpriteSheet sheet = Game.playerL;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
		/*		SpriteSheet sheet = Game.playerLU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				SpriteSheet sheet = Game.playerU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				SpriteSheet sheet = Game.playerRU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				SpriteSheet sheet = Game.playerR;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				SpriteSheet sheet = Game.playerRB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				SpriteSheet sheet = Game.playerB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				SpriteSheet sheet = Game.playerLB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);  */
		}
			
	}
	
}
