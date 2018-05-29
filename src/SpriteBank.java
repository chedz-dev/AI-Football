import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Clase que se encarga de cargar los sprites que están en el disco duro a la memoria RAM.
 * Se utiliza un mapa de sprites con un ID como llave y un buffer de imagen como valor.
 * @author Exequetor
 *
 */
public class SpriteBank {
	public static Map <Integer, BufferedImage> bank = new HashMap <Integer, BufferedImage> ();
	
	public static void loadSprites () {
		bank.put(Cons.ID_GROUND, loadSprite ("sprites" + File.separator + "cancha.jpg"));
		bank.put(Cons.ID_PLAYER_RED, loadSprite ("sprites" + File.separator + "PlayerRojo1.png"));
		bank.put(Cons.ID_PLAYER_BLUE, loadSprite ("sprites" + File.separator + "PlayerAzul1.png"));
		bank.put(Cons.ID_BALL, loadSprite ("sprites" + File.separator + "ball.png"));
	}
	
	public static BufferedImage loadSprite (String path) {
		try {
			BufferedImage loadedImage = ImageIO.read(Main.class.getResource(path));
			return loadedImage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
