package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tesoro extends Movimiento implements Pickup {
    private int puntos;
    private boolean activo;
    private boolean eliminado=false;

 
    public Tesoro(int x, int y, int size, int xSpeed, int ySpeed, Texture tx,int puntos) {
    	spr = new Sprite(tx);
    	this.x = x; 
    	this.puntos=puntos;
	
    	//validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
    	this.y = y;
    	//validar que borde de esfera no quede fuera
		if (y-size < 0) this.y = y+size;
		if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	
    	spr.setPosition(x, y);
    	this.setXSpeed(xSpeed);
    	this.setySpeed(ySpeed);
    	activo = true;
    }
	
	public void moverse() {
        x += getXSpeed();
        y += getySpeed();
        
        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	setXSpeed(getXSpeed() * -1);
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        spr.setPosition(x, y);
	}

	   
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
    
	public float getXSpeed() {
		return xVel;
	}
	public void setXSpeed(float xVel) {
		this.xVel = xVel;
	}
	public float getySpeed() {
		return yVel;
	}
	public void setySpeed(float yVel) {
		this.yVel = yVel;
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

}
