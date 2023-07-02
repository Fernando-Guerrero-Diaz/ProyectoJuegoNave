package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Roca extends Obstaculo implements Movimiento{
	private int margenEntradaSalida = 30;
	private int daño = 10;
	private boolean activo;

		
	public Roca(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
		setSpr(new Sprite(tx));
		setX(x); 
		
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
	}
	
	public void moverse() {
        addX(getxVel());
        addY(getyVel());
        Random r = new Random();
        if ((getX()+getxVel()< -margenEntradaSalida))
        	setX(Gdx.graphics.getWidth()+margenEntradaSalida);        	
        else if(getX()+getxVel()+getSpr().getWidth() > Gdx.graphics.getWidth()+margenEntradaSalida*2)
        	setX(-margenEntradaSalida) ;        
        if (getY()+getyVel() < -margenEntradaSalida) {
        	setY(Gdx.graphics.getHeight()+margenEntradaSalida);
        	setX(50+r.nextInt((int)Gdx.graphics.getHeight()-50));
        }
        if(getY()+getyVel()+getSpr().getHeight() > Gdx.graphics.getHeight()+margenEntradaSalida*2)
        	setY(Gdx.graphics.getHeight());
        setSprPosition();
    }
 
	
	public int getDaño() {
		return daño;
	}
	
	public boolean esDestructible() {
		return false;
	}
	public boolean estaActivo() {
		return true;
	}

	public boolean eliminado() {
		return false;
	}


	public void recibeDaño(int daño) {  }

	@Override
	public void colisionBala(int daño) {	}

	@Override
	public void checkCollision(Elemento e2) {
		// TODO Auto-generated method stub
		
	}
}
