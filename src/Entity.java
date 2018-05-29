import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class Entity extends GameObject{
	protected Image sprite;
	protected double accel = 0, maxVel = 0, stopFactor = 1;
	protected int targetX, targetY;
	protected double direction;
	private double dx, dy, tarX, tarY;
	protected Handler handler;
	protected int redirect = 1;
	protected Random rng;
	
	public Entity(int x, int y, ID id, Image sprite, Handler handler) {
		super(x, y, id);
		this.sprite = sprite;
		bound = new Rectangle (x, y, sprite.getWidth(null), sprite.getHeight(null));
		accelX = accelY = stopFactor;
		this.handler = handler;
		rng = new Random ();
		//rng.setSeed(this.toString().hashCode());
	}

	@Override
	public void tick() {
		
		behaviour ();
		
		setNext();
		collision ();
		x = Utility.clamp((int)x, 0, (int)(Cons.CANVAS_X - bound.getWidth()));
		y = Utility.clamp((int)y, 0, (int)(Cons.CANVAS_Y - bound.getHeight()));

		bound.setLocation((int)x, (int)y);
	}
	
	public void setTarget(int x, int y)
	{
		tarX = x;
		tarY = y;
	}

	public void setNext()
	{
		if (!((x <= tarX + 5 && x >= tarX -5) || (y <= tarY + 5 && y >= tarY -5))) {
	        dx = tarX - this.x;
	        dy = tarY - this.y;
	        if (dx != 0) {
		        direction = Math.atan2(dy, dx); // Math.atan2(deltaY, deltaX) does the same thing but checks for deltaX being zero to prevent divide-by-zero exceptions
			    x += (maxVel * Math.cos(direction)) * redirect;
			    y += (maxVel * Math.sin(direction)) * redirect;
	        }
		} else {
			maxVel = 0;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
	}
	
	public void collision () {
		
	}
	
	public void behaviour () {
		
	}
}
