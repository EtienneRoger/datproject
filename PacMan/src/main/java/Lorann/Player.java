package Lorann;

import java.awt.*;


public class Player extends Rectangle{

	private static final long serialVersionUID = 1L;
	private int speed = 32;								//set the "speed" of our player who move one case by one case, so 32pixel
	
	public boolean right,left,up,down,shot;				//initialize the action of the player
	
						
	
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
						Game.fireball = new Fireball(-32,-32);
					}
				}else if(level.gate[xx][yy] != null && level.crystal.size() == 0) {
					if(bounds.intersects(level.gate[xx][yy])) {
						Score = Score + 50;												//Add 50pts if you finish the level
						Game.player = new Player(32,32);								//Initialize a new player
						Game.level = new Level("/level/map.png");						//Initialize the next map (who doesnt exist actually)
						Game.fireball = new Fireball(-32,-32);
					}
				}
			}
		}

		for(int i = 0; i < level.enemies.size(); i++) {
			if(this.intersects(level.enemies.get(i))){
				Score = 0;														//Reset Score
				Game.player = new Player(32,32);								//Initialize a new player
				Game.level = new Level("/level/map.png");						//Initialize the same map
				Game.fireball = new Fireball(-32,-32);
			}
		}
		
		return true;																	//for all other block, the player can move on
	}
	
	public void render(Graphics g) {					// set Player's graphics POSITION and SIZE with the orientation "contrainte"
		
		
		if(right && up) {
			SpriteSheet sheet = Game.playerRU;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(right&& down) {
			SpriteSheet sheet = Game.playerRB;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(left&& up) {
			SpriteSheet sheet = Game.playerLU;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(left && down) {
			SpriteSheet sheet = Game.playerLB;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(right) {
			SpriteSheet sheet = Game.playerR;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(left) {
			SpriteSheet sheet = Game.playerL;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(up) {
			SpriteSheet sheet = Game.playerU;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(down) {
			SpriteSheet sheet = Game.playerB;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else {	
			for(int i = 0; i < 64; i++) {
				if(i >= 0 && i < 7) {
					SpriteSheet sheet = Game.playerL;
					g.drawImage(sheet.getSprite(0,0),x,y,null);
				}else if(i >= 7 && i < 15) {
					SpriteSheet sheet = Game.playerLU;
					g.drawImage(sheet.getSprite(0,0),x,y,null);
				}else if(i >= 15 && i < 23) {
					SpriteSheet sheet = Game.playerU;
					g.drawImage(sheet.getSprite(0,0),x,y,null);
				}else if(i >= 23 && i < 31) {
					SpriteSheet sheet = Game.playerRU;
					g.drawImage(sheet.getSprite(0,0),x,y,null);
				}else if(i >= 31 && i < 39) {
					SpriteSheet sheet = Game.playerR;
					g.drawImage(sheet.getSprite(0,0),x,y,null);
				}else if(i >= 39 && i < 47) {
					SpriteSheet sheet = Game.playerRB;
					g.drawImage(sheet.getSprite(0,0),x,y,null);
				}else if(i >= 47 && i < 55) {
					SpriteSheet sheet = Game.playerB;
					g.drawImage(sheet.getSprite(0,0),x,y,null);
				}else if(i >= 55 && i < 63) {
					SpriteSheet sheet = Game.playerLB;
					g.drawImage(sheet.getSprite(0,0),x,y,null); 
				}
			System.out.println(i);
			}	
		}
			
	}
	
}
