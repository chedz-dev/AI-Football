import java.awt.Image;

public class DefensaRojo extends Entity {

	protected boolean collidePlayer = false;
	private int ticks = 0;
	private int origenX, origenY;
	public DefensaRojo(int x, int y, ID id, Image sprite, Handler handler) {
		super(x, y, id, sprite, handler);
		origenX = x;
		origenY = y;
	}
	@Override
	public void behaviour () {
		if (ticks == 25) {
			switch (rng.nextInt(1)) {
				case 0:
					maxVel = rng.nextInt(5);
					for (int i = 0 ; i < handler.object.size() ; i++) {
						GameObject tempObject = handler.object.get(i);
						if (tempObject.getId() == ID.Ball) {
							if (tempObject.x >= Cons.CANVAS_X/2) {
								maxVel = rng.nextInt(4) + 2; 
								setTarget((int)tempObject.x + (rng.nextInt(100) - 50), (int)tempObject.y + (rng.nextInt(100) - 50));
							}
							else
								setTarget(origenX  + (rng.nextInt(100) - 50), origenY + (rng.nextInt(100) - 50));
						}
					}
					ticks = 0;
					break;
				default: maxVel = 0;
			}
			
		}
		ticks++;
	}
	
	public void collision () {
		for (int i = 0 ; i < handler.object.size() ; i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Ball) {
				Ball ball = (Ball)tempObject;
				if (bound.intersects(tempObject.bound)) {
					ball.setGrabStatus(true);
					GameObject delantero;
					while((delantero = handler.object.get(rng.nextInt(handler.object.size()))).getId() != ID.DelanteroRojo);
					ball.kickBall((int)delantero.x, (int)delantero.y);
				}
			}
			
		}
	}
}
