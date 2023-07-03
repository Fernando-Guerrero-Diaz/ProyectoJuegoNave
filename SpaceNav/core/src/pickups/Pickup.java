package pickups;

import com.mygdx.game.Elemento;
import com.mygdx.game.Nave;

public abstract class Pickup extends Elemento{
	public void colisionBala(int daño) {	}
	public abstract int getPuntos();
	public abstract void getPower();
	public void colisionNave() {
		getPower();
		Nave nave = Nave.getNaveInstance();
		nave.agregarPuntos(getPuntos());
	}

}
