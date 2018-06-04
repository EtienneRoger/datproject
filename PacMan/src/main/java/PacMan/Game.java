package PacMan;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;			//Variable to know if the game is running
			
	public static final int X_SIZE = 20, Y_SIZE = 13;										// Number of Sprite per Axe
	public static final int SPRITE_SIZE = 32;												// Size of a Sprite
	public static final int WIDTH = X_SIZE * SPRITE_SIZE, HEIGHT = Y_SIZE * SPRITE_SIZE;	//Size of the Window
	public static final String TITLE = "Pac Man";
	
	private Thread thread;
	
	public static Player player;				// the player
	public static Level level;					// the level
	
	public static SpriteSheet boneSprite;		// the different type of sprite
	public static SpriteSheet vertiBoneSprite;	
	public static SpriteSheet horiBoneSprite;
	public static SpriteSheet purseSprite;
	public static SpriteSheet crystalSprite;
	public static SpriteSheet playerL;
	public static SpriteSheet playerR;
	public static SpriteSheet playerU;
	public static SpriteSheet playerB;
	public static SpriteSheet playerLU;
	public static SpriteSheet playerLB;
	public static SpriteSheet playerRU;
	public static SpriteSheet playerRB;
	public static SpriteSheet gateOpen;
	public static SpriteSheet gateClosed;
	public static SpriteSheet monster_1;
	public static SpriteSheet monster_2;
	public static SpriteSheet monster_3;
	public static SpriteSheet monster_4;
	public static SpriteSheet fireball_1;
	public static SpriteSheet fireball_2;
	public static SpriteSheet fireball_3;
	public static SpriteSheet fireball_4;
	
	public int count = 0;
	
	public Game() {
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);		//Dimention of the window
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		
		
		addKeyListener(this);
		player = new Player(32,32);											//add a player with his position
		level = new Level("/level/map.png");								//Show the root of each sprite used
		boneSprite = new SpriteSheet("/sprite/bone.png");
		vertiBoneSprite = new SpriteSheet("/sprite/vertical_bone.png");
		horiBoneSprite = new SpriteSheet("/sprite/horizontal_bone.png");
		purseSprite = new SpriteSheet("/sprite/purse.png");
		crystalSprite = new SpriteSheet("/sprite/crystal_ball.png");
		playerL = new SpriteSheet("/sprite/lorann_l.png");
		playerR = new SpriteSheet("/sprite/lorann_r.png");
		playerU = new SpriteSheet("/sprite/lorann_u.png");
		playerB = new SpriteSheet("/sprite/lorann_b.png");
		playerLU = new SpriteSheet("/sprite/lorann_ul.png");
		playerLB = new SpriteSheet("/sprite/lorann_bl.png");
		playerRU = new SpriteSheet("/sprite/lorann_ur.png");
		playerRB = new SpriteSheet("/sprite/lorann_br.png");
		gateOpen = new SpriteSheet("/sprite/gate_open.png");
		gateClosed = new SpriteSheet("/sprite/gate_closed.png");
		monster_1 = new SpriteSheet("/sprite/monster_1.png");
		monster_2 = new SpriteSheet("/sprite/monster_2.png");
		monster_3 = new SpriteSheet("/sprite/monster_3.png");
		monster_4 = new SpriteSheet("/sprite/monster_4.png");
		fireball_1 = new SpriteSheet("/sprite/fireball_1.png");
		fireball_2 = new SpriteSheet("/sprite/fireball_2.png");
		fireball_3 = new SpriteSheet("/sprite/fireball_3.png");
		fireball_4 = new SpriteSheet("/sprite/fireball_4.png");


	}
	
	
	
	
	public synchronized void start() {		//Method for start the game								
		if(isRunning) return;				//look for the status of isRunning
		isRunning = true;					
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {		//Method for stop the game
		if(!isRunning)return;				//look for the status of isRunning
		isRunning = false;
		
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

	private void tick() {
		player.tick();
		level.tick();
	}			
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);							
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		player.render(g, count);
		level.render(g);
		g.dispose();
		bs.show();
		
	}
	
	public void run() {								//Method for control the frame rate of the game
		requestFocus();								//for get the focus on the opened window
		int fps = 0;
		double timer = System.currentTimeMillis();	
		long lastTime = System.nanoTime();
		double targetTick = 8.0;					
		double delta = 0;
		double ns = 1000000000 / targetTick;
		
		 
		while(isRunning) {
			long now = System.nanoTime();
			delta+=(now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
			//	System.out.println(fps);										//Show the actual fps
				fps = 0;
				timer+=1000;
			}
			//System.out.println("Your Score: " + Player.Score + " points");	//Show your actual score

		}
		stop();
	}

	public static void main( String[] args )
    {
		Game game = new Game();									//initialize a game
		JFrame frame = new JFrame();							//initialize and set the window
		frame.setTitle(Game.TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();											//launch game
		
    }
	
	public void keyPressed(KeyEvent e) {								//Method for take the keyboard information (when you press)
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = true;
		if(e.getKeyCode() == KeyEvent.VK_SPACE) player.shot = true;
		
	}
	
	public void keyReleased(KeyEvent e) {								//Method for take the keyboard information (when you release)
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
		if(e.getKeyCode() == KeyEvent.VK_SPACE) player.shot = false;
		
	}
	
	public void keyTyped(KeyEvent e) {}

}
