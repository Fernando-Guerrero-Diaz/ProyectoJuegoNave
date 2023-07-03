package estrategiaBalas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GerenteElementos;

public class Shoot {
	private ShootStrategy shootStrategy;
	private int cantDisparos;
	public void setStrategy(ShootStrategy shootStrategy) {
		this.shootStrategy=shootStrategy;
		cantDisparos=shootStrategy.disparos();
	}
	//estrategia para cambiar disparos
	public void shoot(GerenteElementos gerente, float rotacion, Sprite spr, Texture txBala) {
		shootStrategy.shoot(gerente, rotacion, spr, txBala);
		cantDisparos --;
		backLateral();
		
	}
	//Cuando se acaban los disparos especiales se vuelve a los disparos laterales
	public void backLateral() {
		if (cantDisparos ==0) {
			setStrategy(new BalasLaterales());
		}
	}
	public int getCantDisparos() {
		return cantDisparos;
	}
}
