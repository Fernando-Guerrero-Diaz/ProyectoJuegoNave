package pickups;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Nave;

import estrategiaBalas.BalasFrontales;

public class Canon extends Tesoro {

	public Canon(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int puntos) {
		super(x, y, size, xSpeed, ySpeed, tx, puntos);
	}
	

	public void getPower() {
		Nave nave= Nave.getNaveInstance();
		nave.changeStrategy(new BalasFrontales());
	}
}
