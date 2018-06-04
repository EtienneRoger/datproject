package Lorann;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class Level{

	public int width, height;
	
	public Bone[][] bones;
	public Vertical_Bone[][] vertiBones;
	public Horizontal_Bone[][] horiBones;
	public Gate[][] gate;
	
	public List<Purse> purses;
	public List<Crystal> crystal;
	public List<Enemy> enemies;
	
	public Level(String path) {
		
		
		
		purses = new ArrayList<Purse>();
		crystal = new ArrayList<Crystal>();
		enemies = new ArrayList<Enemy>();
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.height = map.getHeight();
			int[] pixels = new int[width*height];
			bones = new Bone[width][height];
			vertiBones = new Vertical_Bone[width][height];
			horiBones = new Horizontal_Bone[width][height];
			gate = new Gate[width][height];
			
			map.getRGB(0, 0, width, height, pixels, 0, width);
			
			for(int xx = 0; xx < width; xx++) {
				for(int yy = 0; yy < height; yy++) {
					int val = pixels[xx + (yy*width)];
					
					if(val == 0xFFC8C8C8) {
						bones[xx][yy] = new Bone(xx*32,yy*32);
					}else if(val == 0xFFC86464) {
						vertiBones[xx][yy] = new Vertical_Bone(xx*32,yy*32);
					}else if(val == 0xFFC8C864) {
						horiBones[xx][yy] = new Horizontal_Bone(xx*32,yy*32);
					}else if (val == 0xFFFFFF00) {
						purses.add(new Purse(xx*32,yy*32));
					}else if (val == 0xFF00FFFF) {
						crystal.add(new Crystal(xx*32,yy*32));
					}else if (val == 0xFF646464) {
						gate[xx][yy] = new Gate(xx*32,yy*32);
					}else if (val == 0xFFFF0000) {
						enemies.add(new Enemy(xx*32,yy*32));

					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
