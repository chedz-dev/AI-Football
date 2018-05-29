import java.awt.Image;

public class Field extends Entity {
	Image sprite;
	public Field(int x, int y, ID id, Handler handler) {
		super(x, y, id, SpriteBank.bank.get(Cons.ID_GROUND).getScaledInstance(Cons.CANVAS_X, Cons.CANVAS_Y, Image.SCALE_SMOOTH), handler);
		
	}
	@Override
	public void tick() {
		
	}
}
