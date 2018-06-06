package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import motion.Fireball;
import motion.Player;
import motionless.SpriteSheet;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;			//Variable to know if the game is running
	private boolean restart = false;			//Variable for restart the level when we want
			
	public static final int X_SIZE = 20, Y_SIZE = 12;										// Number of Sprite per Axe
	public static final int SPRITE_SIZE = 32;												// Size of a Sprite
	public static final int WIDTH = X_SIZE * SPRITE_SIZE, HEIGHT = Y_SIZE * SPRITE_SIZE;	// Size of the Window
	public static final String TITLE = "Lorann";											// Title of the window
	
	private Thread thread;
	
	public static Player player;				// the player
	public static Level level;					// the level
	public static Fireball fireball;			// the fireball

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
	public static SpriteSheet fireball_5;
	
	public static int levelChose = 1;

	public int count = 0;
	
	public Game() {
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);		//Dimension of the window
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		
		
		addKeyListener(this);
		player = new Player(32,32);											//add a player and his position
		level = new Level(levelChose);										//Choose the map to load
		fireball = new Fireball(-32,-32);									//add the fireball outside the window in the right POSITION when we press space		
		
		boneSprite = new SpriteSheet("/sprite/bone.png");					//Initialize all sprite
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
		fireball_5 = new SpriteSheet("/sprite/fireball_5.png");

	}
	
	
	
	
	public synchronized void start() {		//Method to start the game								
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
		fireball.tick();
		
	}			
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);						//Set the color of the background	
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);		//Set the size of the background
		player.render(g);
		level.render(g);
		fireball.render(g);
		g.dispose();
		bs.show();
		
	}
	
	public void run() {								//Method used to control the frame rate of the game
		requestFocus();								//get the focus on the opened window
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
//				System.out.println("FPS : " + fps);										//Show the actual fps
				fps = 0;
				timer+=1000;
			}
			System.out.println("Your Score: " + Player.Score + " points");	//Show your actual score
			
			if(restart) {												//Restart the game if the Back_Space button is pressed
				Player.Score = 0;										//Reset Score
				player = new Player(32,32);								//Initialize a new player
				level = new Level(levelChose);					//Initialize the same map
				fireball = new Fireball(-32,-32);				//Initialize a new fireball
			}
			
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
	
	public void keyPressed(KeyEvent e) {								//Method needed to take the keyboard information (when you press)
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = true;
		if(e.getKeyCode() == KeyEvent.VK_SPACE) player.shot = true;
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) restart = true;

		
	}
	
	public void keyReleased(KeyEvent e) {								//Method needed to take the keyboard information (when you release)
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
		if(e.getKeyCode() == KeyEvent.VK_SPACE) player.shot = false;
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) restart = false;

		
	}
	
	public void keyTyped(KeyEvent e) {}

}
