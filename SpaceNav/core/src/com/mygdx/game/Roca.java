package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Roca implements Obstaculo{
	private int x;
	private int y;
	private int xSpeed;
	private int ySpeed;
	private Sprite spr;
	private int enYsa=30;
	private int daño = 10;
	
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
    public void update() {
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
    
 /*   public void checkCollision(roca2 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.spr.getBoundingRectangle())){
        	// rebote de asteroides si lo quitas se transpasas
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());
            
            if (getySpeed() ==0) setySpeed(getySpeed() + b2.getySpeed()/2);
            if (b2.getySpeed() ==0) b2.setySpeed(b2.getySpeed() + getySpeed()/2);
            setySpeed(- getySpeed());
            b2.setySpeed(- b2.getySpeed()); 
        }
    }
   */    
    
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
	
	public int getDaño() {
		return daño;
	}
	
	public boolean esDestructible() {
		return false;
	}
	public boolean estaActivo() {
		return true;
	}
}