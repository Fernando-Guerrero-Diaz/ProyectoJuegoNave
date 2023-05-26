package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tesoro implements Pickup {
	private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private Sprite spr;
    private int exitmargen=50;
    private int entermargen=10;
    private int puntos = 100;
    private boolean activo;

    public Tesoro(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
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
        activo = true;
    }
    public void update() {
        x += getXSpeed();
        y += getySpeed();
        
        /*if ((x+getXSpeed()< -exitmargen))
        	x=Gdx.graphics.getWidth()+entermargen;        	
        else if(x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth()+exitmargen+entermargen)
        	x=-entermargen ;        
        if (y+getySpeed() < -exitmargen +entermargen)
        	y=Gdx.graphics.getHeight()+entermargen;
        if(y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight()+exitmargen+entermargen)
        	y=-entermargen;*/
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
    
	public int getXSpeed() {
		return xSpeed;
	}
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public int getPuntos() {
		activo = false;
		return puntos;
		
	}
	public boolean estaActivo() {
		return activo;
	}


}