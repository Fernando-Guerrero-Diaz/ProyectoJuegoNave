package pickups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Elemento;
import com.mygdx.game.Movimiento;

public class Tesoro extends Pickup implements Movimiento {
    private int puntos;
    private boolean activo;
    private boolean eliminado=false;

 
    public Tesoro(int x, int y, int size, int xSpeed, int ySpeed, Texture tx, int puntos) {
    	setSpr(new Sprite(tx));
    	setX(x); 
    	this.puntos=puntos;
	
    	//validar que borde de esfera no quede fuera
    	if (x-size < 0) setX(x+size);
    	if (x+size > Gdx.graphics.getWidth()) setX(x-size);
    			 
    	setY(y);
    	//validar que borde de esfera no quede fuera
    	if (y-size < 0) setY(y+size);
    	if (y+size > Gdx.graphics.getHeight()) setY(y-size);
    	
    	setSprPosition();
		setxVel(xSpeed);
		setyVel(ySpeed);
    	activo = true;
    }
	
	public void moverse() {
		if (activo) {
	        addX(getxVel());
	        addY(getyVel());
	        
	        if (getX()+getxVel() < 0 || getX()+getxVel()+getSpr().getWidth() > Gdx.graphics.getWidth())
	        	setxVel(getxVel() * -1);
	        if (getY()+getyVel() < 0 || getY()+getyVel()+getSpr().getHeight() > Gdx.graphics.getHeight())
	        	setyVel(getyVel() * -1);
	        setSprPosition();
		}
	}

    

	public int getPuntos() {
		activo = false;
		eliminado=true;
		return puntos;
		
	}
	public boolean estaActivo() {
		return activo;
	}


	public boolean eliminado() {

		return eliminado;
	}

	@Override
	public void colisionBala(int daño) {	}

	@Override
	public boolean esDestructible() {return false;}

	public void checkCollision(Elemento e2) {}

	@Override
	public void getPower() {

		
	}

}
