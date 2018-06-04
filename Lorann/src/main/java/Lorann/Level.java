package Lorann;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Level{

	public int width = 20, height = 13;
	
	public Bone[][] bones;
	public Vertical_Bone[][] vertiBones;
	public Horizontal_Bone[][] horiBones;
	public Gate[][] gate;
	
	public List<Purse> purses;
	public List<Crystal> crystal;
	public List<Enemy> enemies;
	
	public Level(int level) {
		
		
		
		purses = new ArrayList<Purse>();
		crystal = new ArrayList<Crystal>();
		enemies = new ArrayList<Enemy>();
		
		bones = new Bone[width][height];
		vertiBones = new Vertical_Bone[width][height];
		horiBones = new Horizontal_Bone[width][height];
		gate = new Gate[width][height];

		for(int xx = 0; xx < width; xx++) {
			for(int yy = 0; yy < height; yy++) {
				String val = sqlConnection.saveInBase(xx, yy, level);
				
				if(val == "bone") {
					bones[xx][yy] = new Bone(xx*32,yy*32);
				}else if(val == "vertical_bone") {
					vertiBones[xx][yy] = new Vertical_Bone(xx*32,yy*32);
				}else if(val == "horizontal_bone") {
					horiBones[xx][yy] = new Horizontal_Bone(xx*32,yy*32);
				}else if (val == "purse") {
					purses.add(new Purse(xx*32,yy*32));
				}else if (val == "crystal_ball") {
					crystal.add(new Crystal(xx*32,yy*32));
				}else if (val == "gate_closed") {
					gate[xx][yy] = new Gate(xx*32,yy*32);
				}else if (val == "monster") {
					enemies.add(new Enemy(xx*32,yy*32));

				}
				
			}
		}
		
	}

	public void tick() {
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
		
	}
	
	public void render(Graphics g) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(bones[x][y] != null) bones[x][y].render(g);
			}
		}
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(vertiBones[x][y] != null) vertiBones[x][y].render(g);
			}
		}
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(horiBones[x][y] != null) horiBones[x][y].render(g);
			}
		}
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(gate[x][y] != null) gate[x][y].render(g);
			}
		}
		
		for(int i = 0; i < purses.size(); i++) {
			purses.get(i).render(g);
		}
	
		for(int i = 0; i < crystal.size(); i++) {
			crystal.get(i).render(g);
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g, i);
		}
	}
}
