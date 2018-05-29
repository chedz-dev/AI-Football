import java.awt.Image;

public class Ball extends Entity {
	protected boolean grabed = false;
	protected boolean kicked = false;
	private int ticks = 0;
	
	public Ball(int x, int y, ID id, Image sprite, Handler handler) {
		super(x, y, id, sprite, handler);
		maxVel = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void setGrabStatus(Boolean flag) {
		grabed = true;
	}
	
	
	public void kickBall(int x, int y) {
		if (kicked == false) {
			maxVel = rng.nextInt(18) + 1;
			setTarget(x, y);
			kicked = true;
			ticks += 1;
		}
		
	}
	
	public void behaviour () {
		
		if (maxVel > 0) {
			maxVel -= 0.35;
		}
		
		if (maxVel > 1) {
			ticks = 0;
		}
		
		if (maxVel < 3) {
			kicked = false;
		}
		if (maxVel < 0) {
			maxVel = 0;
			redirect = 1;
		}
		
		if (ticks >= 1500) {
			x = Cons.CANVAS_X/2;
			y = Cons.CANVAS_Y/2;
			maxVel = 7;
			ticks = 0;
		}
		
		if (x + bound.getWidth() >= Cons.CANVAS_X  || y + bound.getHeight() >= Cons.CANVAS_Y) {
			setTarget(Cons.CANVAS_X/2, Cons.CANVAS_Y/2);
		} else if (x <= 0 || y <= 0)
			setTarget(Cons.CANVAS_X/2, Cons.CANVAS_Y/2);
	}
}
