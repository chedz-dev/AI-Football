import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JLabel;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 3628156543984317078L;

	private boolean running = false;
	
	JLabel field;
	
	Random rng = new Random ();
	
	public static Integer golAzul = 0;
	public static Integer golRojo = 0;
	
	private static Handler handler;
	
	public Main () {
		this.setPreferredSize(new Dimension (Cons.CANVAS_X, Cons.CANVAS_Y));
		new Window (this);
		SpriteBank.loadSprites();
		createHandler();
		createBufferStrategy(Cons.BUFFER_STRATEGY_SIZE);
		render();
	}
	
	public static void createHandler () {
		handler = new Handler ();
		
		handler.addObject(new Field (0, 0, ID.Field, handler));
		handler.addObject(new Ball(Cons.CANVAS_X/2 - 25, Cons.CANVAS_Y/2 - 25, ID.Ball, SpriteBank.bank.get(Cons.ID_BALL), handler));
		
		handler.addObject(new PorteroRojo (Cons.CANVAS_X - 100, Cons.CANVAS_Y/2, ID.Player, SpriteBank.bank.get(Cons.ID_PLAYER_RED), handler));
		handler.addObject(new PorteroAzul (100, Cons.CANVAS_Y/2, ID.Player, SpriteBank.bank.get(Cons.ID_PLAYER_BLUE), handler));
		
		handler.addObject(new DelanteroAzul (Cons.CANVAS_X/3 + 100, Cons.CANVAS_Y/5, ID.DelanteroAzul, SpriteBank.bank.get(Cons.ID_PLAYER_BLUE), handler));
		handler.addObject(new DelanteroAzul (Cons.CANVAS_X/3 + 100, Cons.CANVAS_Y/4 * 3, ID.DelanteroAzul, SpriteBank.bank.get(Cons.ID_PLAYER_BLUE), handler));
		handler.addObject(new DefensaAzul (Cons.CANVAS_X/5, Cons.CANVAS_Y/5, ID.Player, SpriteBank.bank.get(Cons.ID_PLAYER_BLUE), handler));
		handler.addObject(new DefensaAzul (Cons.CANVAS_X/5, Cons.CANVAS_Y/4 * 3, ID.Player, SpriteBank.bank.get(Cons.ID_PLAYER_BLUE), handler));
		
		
		handler.addObject(new DelanteroRojo (Cons.CANVAS_X - (Cons.CANVAS_X/3 + 200), Cons.CANVAS_Y/5, ID.DelanteroRojo, SpriteBank.bank.get(Cons.ID_PLAYER_RED), handler));
		handler.addObject(new DelanteroRojo (Cons.CANVAS_X - (Cons.CANVAS_X/3 + 200), Cons.CANVAS_Y/4 * 3, ID.DelanteroRojo, SpriteBank.bank.get(Cons.ID_PLAYER_RED), handler));
		handler.addObject(new DefensaRojo (Cons.CANVAS_X - Cons.CANVAS_X/4, Cons.CANVAS_Y/5, ID.Player, SpriteBank.bank.get(Cons.ID_PLAYER_RED), handler));
		handler.addObject(new DefensaRojo (Cons.CANVAS_X - Cons.CANVAS_X/4, Cons.CANVAS_Y/4 * 3, ID.Player, SpriteBank.bank.get(Cons.ID_PLAYER_RED), handler));
		
		handler.addObject(new Porteria (0, 295, ID.PorteriaAzul, handler));
		handler.addObject(new Porteria (Cons.CANVAS_X - 30, 295, ID.PorteriaRojo, handler));
	}
	
	public synchronized void start () {
		Thread loop = new Thread (this);
		running = true;
		loop.start();
	}
	
	@Override
	public void run() {
		
		long currentTime;
		long previousTime = System.nanoTime();
		long passedTime;
		int frames = 0;
		double unprocessedSeconds = 0;
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;     

		while (running) {
		    currentTime = System.nanoTime();
		    passedTime = currentTime - previousTime;
		    previousTime = currentTime;
		    unprocessedSeconds += passedTime / 1000000000.0;

		    while (unprocessedSeconds > secondsPerTick) {
		        tick();
		        unprocessedSeconds -= secondsPerTick;
		        ticked = true;
		        tickCount++;
		        if (tickCount % 60 == 0) {
		            System.out.println(frames + " FPS");
		            previousTime += 1000;
		            frames = 0;
		        }
		    }
		    if (ticked) {
		        render();
		        frames++;
		    }         
		    frames++;
		}
	}

	private void tick() {
		handler.tick();
	}
	
	private void render () {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(Cons.BUFFER_STRATEGY_SIZE);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(g instanceof Graphics2D)
	      {
			Font f = new Font("Dialog", Font.PLAIN, 40);
	        Graphics2D g2 = (Graphics2D)g;
	        
	        g2.setColor(Color.white);
	        g2.fillRect(Cons.CANVAS_X/2 - 60, 0, 120, 50);
	        
	        g2.setFont(f);
	        
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(Color.BLACK);
	        g2.drawString(" - ",Cons.CANVAS_X/2 - 20, 40);
	        g2.setColor(Color.BLUE);
	        g2.drawString(golAzul.toString(),Cons.CANVAS_X/2 - 50,40);
	        g2.setColor(Color.RED);
	        g2.drawString(golRojo.toString(),Cons.CANVAS_X/2 + 25,40); 
	       }
		handler.render(g);
		bs.show();
	}
	
	
	public static void main(String[] args) {
		new Main ();
	}
}
