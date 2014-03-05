package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
	
	
	public GamePanel () {
		super();
		setPreferredSize(
				new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
		
	} // end method
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		} // end if
	} // end method
	
	private void init() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
	}
	
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = (System.nanoTime() - start);
			
			wait = targetTime - elapsed / 1000000;
			
			try { 
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			} // end try
			
		} // end while
	} // end method
	
	
	private void update() {
		gsm.update();
	} // end method
	
	
	private void draw() {
		gsm.draw(g);
	} // end method
	
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	} // end method
	
	
	public void keyTyped(KeyEvent key) {
		
	}
	
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	} // end method
	
	
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	} // end method
	
	
} // end class
