package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Roca extends Movimiento implements Obstaculo{
	private int enYsa = 30;
	private int daño = 10;
	private boolean activo;

		
	public Roca(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
		spr = new Sprite(tx);
		this.x = x; 
		
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
	}
	
	public void moverse() {
        x += getXSpeed();
        y += getySpeed();
        Random r = new Random();
        if ((x+getXSpeed()< -enYsa))
        	x=Gdx.graphics.getWidth()+enYsa;        	
        else if(x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth()+enYsa*2)
        	x=-enYsa ;        
        if (y+getySpeed() < -enYsa) {
        	y=Gdx.graphics.getHeight()+enYsa;
        	x=50+r.nextInt((int)Gdx.graphics.getHeight()-50);
        }
        if(y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight()+enYsa*2)
        	y=Gdx.graphics.getHeight();
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
	public void setXSpeed(float xSpeed) {
		this.xVel = xSpeed;
	}
	public float getySpeed() {
		return yVel;
	}
	public void setySpeed(float ySpeed) {
		this.yVel = ySpeed;
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


	public void recibeDaño(int daño) {
	
	}
}
