import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Porteria extends GameObject{
	Handler handler;
	
	public Porteria(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		bound = new Rectangle (x, y, 20,125);
		this.handler = handler;
	}

	@Override
	public void tick() {
		for (int i = 0 ; i < handler.object.size() ; i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Ball) {
				if (bound.intersects(tempObject.bound)) {
					if (id == ID.PorteriaAzul) {
						Main.golRojo += 1;
					} else
						Main.golAzul += 1;
					Main.createHandler();
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(bound.x, bound.y, (int)bound.getWidth(), (int)bound.getHeight());
	}
	
	

}
