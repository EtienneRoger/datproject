package motion;

import java.awt.*;
import java.util.Random;

import main.Game;
import main.Level;
import motionless.SpriteSheet;


public class Enemy extends Rectangle{

	private static final long serialVersionUID = 1L;
	private int speed = 32;						//declaration the number of pixel who move each monster at each frame 
	
	public int enemyDir;						//declaration the direction of the monster
	
	public Random random;						//declaration a random variable

	
	

	public Enemy(int x, int y){					//get initial position and the space
		
		setBounds(x,y,32,32);
		random = new Random();					//initialize a a new random
		enemyDir = random.nextInt(8);			//get a random number between 0 - 7
	}
	
	public void tick() {
		
		if(Game.player.x == x && Game.player.y == y) {
			Player.Score = 0;												//Reset Score
			Game.player = new Player(32,32);								//Initialize a new player
			Game.level = new Level(Game.levelChose);						//Initialize the same map
			Game.fireball = new Fireball(-32,-32);
		}	

		
		if(enemyDir == 0) {						//move to the right
			if(canMove(x+speed,y)) {
				x+=speed;
			}else {
				enemyDir = random.nextInt(8);
			}
		}else if(enemyDir == 1) {				//move to the down right
			if(canMove(x+speed,y+speed)) {
				x+=speed;
				y+=speed;
			}else {
				enemyDir = random.nextInt(8);
			}
		}else if(enemyDir == 2) {				//move to the down
			if(canMove(x,y+speed)) {
				y+=speed;
			}else {
				enemyDir = random.nextInt(8);
			}	
		}else if(enemyDir == 3) {				//move to the down left
			if(canMove(x-speed,y+speed)) {
				x-=speed;
				y+=speed;
			}else {
				enemyDir = random.nextInt(8);
			}	
		}else if(enemyDir == 4) {				//move to the left
			if(canMove(x-speed,y)) {
				x-=speed;
			}else {
				enemyDir = random.nextInt(8);
			}	
		}else if(enemyDir == 5) {				//move to the up left
			if(canMove(x-speed,y-speed)) {
				x-=speed;
				y-=speed;
			}else {
				enemyDir = random.nextInt(8);
			}	
		}else if(enemyDir == 6) {				//move to the up
			if(canMove(x,y-speed)) {
				y-=speed;
			}else {
				enemyDir = random.nextInt(8);
			}
		}else if(enemyDir == 7) {				//move to the up right
			if(canMove(x+speed,y-speed)) {
				x+=speed;
				y-=speed;
			}else {
				enemyDir = random.nextInt(8);
			}	
		}

		if(Game.player.x == x && Game.player.y == y) {
			Player.Score = 0;												//Reset Score
			Game.player = new Player(32,32);								//Initialize a new player
			Game.level = new Level(Game.levelChose);						//Initialize the same map
			Game.fireball = new Fireball(-32,-32);							//initialize a fireball outside the window
		}	

	}
	
	public boolean canMove(int nextX, int nextY) {

		Rectangle bounds = new Rectangle(nextX, nextY, width, height);		//initialize a rectangle who will be a ghost of the next position of a monster
		Level level = Game.level;
		
	
		
		for(int xx = 0; xx < level.bones.length; xx++) {					//collision with a bone
			for(int yy = 0; yy < level.bones[0].length; yy++) {
				if(level.bones[xx][yy] != null) {
					if(bounds.intersects(level.bones[xx][yy])) {
						return false;
					}
				}
			}
		}
				
		for(int xx = 0; xx < level.vertiBones.length; xx++) {				//collision with a verti_bone
			for(int yy = 0; yy < level.vertiBones[0].length; yy++) {
				if(level.vertiBones[xx][yy] != null) {
					if(bounds.intersects(level.vertiBones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.horiBones.length; xx++) {				//collision with an hori_bone
			for(int yy = 0; yy < level.horiBones[0].length; yy++) {
				if(level.horiBones[xx][yy] != null) {
					if(bounds.intersects(level.horiBones[xx][yy])) {
						return false;
					}
				}
			}
		}
		
		for(int xx = 0; xx < level.gate.length; xx++) {						//collision with the gate
			for(int yy = 0; yy < level.gate[0].length; yy++) {
				if(level.gate[xx][yy] != null) {
					if(bounds.intersects(level.gate[xx][yy])) {
						return false;

					}
				}
			}
		}
		
		for(int i = 0; i < level.crystal.size(); i++) {						//collision with a crystal
			if(bounds.intersects(level.crystal.get(i))){
				return false;
			}
		}
		
		for(int i = 0; i < level.purses.size(); i++) {						//collision with a purse
			if(bounds.intersects(level.purses.get(i))){
				return false;
			}
		}
		
		for(int i = 0; i < level.enemies.size(); i++) {						//collision with another monster
			if(bounds.intersects(level.enemies.get(i))){
				return false;
			}
		}
		
		return true;
	}
	
	public void render(Graphics g, int i) {									//out a different Sprite according to is position in the list
		
		if(i == 0) {
			SpriteSheet sheet = Game.monster_1;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(i == 1) {
			SpriteSheet sheet = Game.monster_2;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(i == 2) {
			SpriteSheet sheet = Game.monster_3;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}else if(i == 3) {
			SpriteSheet sheet = Game.monster_4;
			g.drawImage(sheet.getSprite(0,0),x,y,null);
		}
	}
}