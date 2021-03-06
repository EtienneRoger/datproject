package motion;

import java.awt.*;

import main.Game;
import main.Level;
import motionless.SpriteSheet;


public class Player extends Rectangle{

	private static final long serialVersionUID = 1L;
	private int speed = 32;								//set the "speed" of our player who move one case by one case, so 32pixel
	
	public boolean right,left,up,down,shot;				//initialize the action of the player
	
					
	public static int Score = 0;						//initialize the player's score
	public int count = 0;
	
	
	public Player (int x, int y) {						//get initial position and the space
		
		setBounds(x,y,32,32);
		
		
	}
		
	public void tick() {

		Level level = Game.level;
		
		if(right && !up && !down && canMove(x+speed,y))x+=speed;										//set the new position of the player 
		if(left && !up && !down && canMove(x-speed,y))x-=speed;
		if(up && !left && !right && canMove(x,y-speed))y-=speed;
		if(down && !left  && !right && canMove(x,y+speed))y+=speed;
		if(left && down && canMove(x-speed,y+speed)) {x-=speed; y+=speed;}
		if(left && up && canMove(x-speed,y-speed)) {x-=speed; y-=speed;}
		if(right && down && canMove(x+speed,y+speed)) {x+=speed; y+=speed;}
		if(right && up && canMove(x+speed,y-speed)) {x+=speed; y-=speed;}
		

		
		
		for(int i = 0; i < level.crystal.size(); i++) {
			if(this.intersects(level.crystal.get(i))){
				level.crystal.remove(i);							//remove crystal of the list if the player is in the same position
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
		count++;													//add 1 at the counter
		
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
					if(bounds.intersects(level.vertiBones[xx][yy])) {					// if it is a vertical bone 
						return false;													// he can't
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.horiBones.length; xx++) {							
			for(int yy = 0; yy < level.horiBones[0].length; yy++) {
				if(level.horiBones[xx][yy] != null) {									// if it is an horizontal bone
					if(bounds.intersects(level.horiBones[xx][yy])) {					// he can t'
						return false;
					}
				}
			}
		}

		for(int xx = 0; xx < level.gate.length; xx++) {									//if the gate is closed he can t and die on it
			for(int yy = 0; yy < level.gate[0].length; yy++) {
				if(level.gate[xx][yy] != null && !level.crystal.isEmpty()) {			//isEmpty returns true if the list is empty
					if(bounds.intersects(level.gate[xx][yy])) {		
						Score = 0;														//Reset Score
						Game.player = new Player(32,32);								//Initialize a new player
						Game.level = new Level(Game.levelChose);						//Initialize the same map
						Game.fireball = new Fireball(-32,-32);
					}
				}else if(level.gate[xx][yy] != null && level.crystal.isEmpty()) {
					if(bounds.intersects(level.gate[xx][yy])) {
						Score = Score + 50;												//Add 50pts if you finish the level
						if(Game.levelChose < 5) {
							Game.levelChose++;
							Game.player = new Player(32,32);							//Initialize a new player
							Game.level = new Level(Game.levelChose);					//Initialize the next map
							Game.fireball = new Fireball(-32,-32);

						}else {
							System.out.println("Congratulation, you finished the game!");
							Game.levelChose = 1;
							Game.player = new Player(32,32);							//Initialize a new player
							Game.level = new Level(Game.levelChose);					//Initialize the first map
							Game.fireball = new Fireball(-32,-32);
						}
					}
				}
			}
		}

		for(int i = 0; i < level.enemies.size(); i++) {
			if(this.intersects(level.enemies.get(i))){
				Score = 0;														//Reset Score
				Game.player = new Player(32,32);								//Initialize a new player
				Game.level = new Level(Game.levelChose);						//Initialize the same map
				Game.fireball = new Fireball(-32,-32);
			}
		}
		
		return true;															//for all other block, the player can move on
	}
	
	public void render(Graphics g) {					// set Player's graphics POSITION and SIZE with the orientation constraint
		
	
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
			if(count == 0) {											//do a loop if not moving
				SpriteSheet sheet = Game.playerRU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
			}else if(count == 1) {
				SpriteSheet sheet = Game.playerR;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
			}else if(count == 2) {
				SpriteSheet sheet = Game.playerRB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
			}else if(count == 3) {
				SpriteSheet sheet = Game.playerB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
			}else if(count == 4) {
				SpriteSheet sheet = Game.playerLB;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
			}else if(count == 5) {
				SpriteSheet sheet = Game.playerL;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
			}else if(count == 6) {
				SpriteSheet sheet = Game.playerLU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
			}else if(count >= 7) {
				SpriteSheet sheet = Game.playerU;
				g.drawImage(sheet.getSprite(0,0),x,y,null);
				count = 0;												//reset count for another loop
			}
		}	
	}
	
}
