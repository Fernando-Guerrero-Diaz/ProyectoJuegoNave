package pickups;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Nave;

public class Polvora extends Tesoro {

	public Polvora(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int puntos) {
		super(x, y, size, xSpeed, ySpeed, tx, puntos);

	}
	
	public void getPower() {
		Nave nave = Nave.getNaveInstance();
		nave.nuevoCooldown(90);
	}
}
