package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
public class Enemigo extends Movimiento implements Obstaculo{
	private int exitmargen=50;
	private int entermargen=10;
	private int daño=5;
	private int spawnWait;
    private boolean activo;
	
    public Enemigo(int x, int y, int size, float xVel, float yVel, Texture tx, int wait) {
    	spr = new Sprite(tx);
    	Random r = new Random();
    	x=r.nextInt((int)Gdx.graphics.getWidth());
    	y=Gdx.graphics.getHeight()+entermargen;
 	
        //validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
	         
        this.y = y;
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
	    	
        spr.setPosition(x, y);
        this.setXSpeed(xVel);
        this.setySpeed(yVel);
        spawnWait = wait;
        activo=false;
    }		

	public void moverse() {
		if (activo) {
        x += getXSpeed();
        y += getySpeed();
        
        if ((x+getXSpeed()< -exitmargen))
        	x=Gdx.graphics.getWidth()+entermargen;        	
        else if(x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth()+exitmargen+entermargen)
        	x=-entermargen ;        
        if (y+getySpeed() < -exitmargen +entermargen)
        	y=Gdx.graphics.getHeight()+entermargen;
        if(y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight()+exitmargen+entermargen)
        	y=-entermargen;
        spr.setPosition(x, y);
		}
        else {
    		spawnWait--;
    		if (spawnWait<=0)activo=true;
    	}
    }
		
/*	public void update() {
        x += getXSpeed();
        y += getySpeed();
        
        if ((x+getXSpeed()< -exitmargen))
        	x=Gdx.graphics.getWidth()+entermargen;        	
        else if(x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth()+exitmargen+entermargen)
        	x=-entermargen ;        
        if (y+getySpeed() < -exitmargen +entermargen)
        	y=Gdx.graphics.getHeight()+entermargen;
        if(y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight()+exitmargen+entermargen)
        	y=-entermargen;
        spr.setPosition(x, y);
    }*/
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
    	if(activo) spr.draw(batch);
    }
    
    public void checkCollision(Enemigo b2) {
        if(b2.estaActivo() && spr.getBoundingRectangle().overlaps(b2.spr.getBoundingRectangle())){
        	// rebote de asteroides si lo quitas se transpasas
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            //if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
          	setXSpeed(- getXSpeed());
            //b2.setXSpeed(-b2.getXSpeed());
                
            if (getySpeed() ==0) setySpeed(getySpeed() + b2.getySpeed()/2);
            //if (b2.getySpeed() ==0) b2.setySpeed(b2.getySpeed() + getySpeed()/2);
            setySpeed(- getySpeed());
            //b2.setySpeed(- b2.getySpeed()); 
        }
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
    	
    public int getDaño() {
   		return daño;
   	}
    	
   	public boolean esDestructible() {
   		return true;
   	}

	@Override
	public boolean estaActivo() {
		// TODO Auto-generated method stub
		return activo;
	}

}