package pickups;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Nave;

public class Remo extends Tesoro {

	public Remo(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int puntos) {
		super(x, y, size, xSpeed, ySpeed, tx, puntos);

	}
	public void getPower() {
		Nave nave = Nave.getNaveInstance();
		nave.nuevaVel(3.0f);
	}
}
