package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
public class Enemigo extends Obstaculo implements Movimiento{
	private int exitmargen=100;
	private int entermargen=10;

	private int spawnWait;
    private boolean activo;
    private int vida;
	
    public Enemigo(float xVel, float yVel, Texture tx, int wait, int daño, int vida) {
    	setSpr(new Sprite(tx));
    	Random r = new Random();
    	setX(r.nextInt((int)Gdx.graphics.getWidth()));
    	setY(Gdx.graphics.getHeight()+entermargen);
        getSpr().setPosition(getX(), getY());
        setxVel(xVel);
        setyVel(yVel);
        spawnWait = wait;
        activo=false;
        setDaño(daño);
        this.vida=vida;
    }		

	public void moverse() {
		if (activo) {
        addX(getxVel());
        addY(getyVel());
        
        if ((getX()+getxVel()< -exitmargen))
        	setX(Gdx.graphics.getWidth()+entermargen);        	
        else if(getX()+getxVel()+getSpr().getWidth() > Gdx.graphics.getWidth()+exitmargen+entermargen)
        	setX(-entermargen) ;        
        if (getY()+getyVel() < -exitmargen +entermargen)
        	setY(Gdx.graphics.getHeight()+entermargen);
        if(getY()+getyVel()+getSpr().getHeight() > Gdx.graphics.getHeight()+exitmargen+entermargen)
        	setY(-entermargen);
        getSpr().setPosition(getX(), getY());
		}
        else {
    		spawnWait--;
    		if (spawnWait<=0)activo=true;
    	}
    }
		

    public Rectangle getArea() {
    	return getSpr().getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
    	if(activo) getSpr().draw(batch);
    }
    
    public void checkCollision(Elemento e2) {
        if(e2.estaActivo() && getArea().overlaps(e2.getArea()) && (e2 instanceof Obstaculo)){
        	// rebote de enemigos entre si
            if (getxVel() ==0) setxVel(getxVel() + e2.getxVel()/2);
          	setxVel(- getxVel());
            if (getyVel() ==0) setyVel(getyVel() + e2.getyVel()/2);
            setyVel(- getyVel());
        }
    }
    	
    	

    	
   	public boolean esDestructible() {
   		return true;
   	}

	public boolean estaActivo() {
		// TODO Auto-generated method stub
		return activo;
	}

	@Override
	public boolean eliminado() {
		
		if(vida<=0) {
		ganarPuntos(10);	
		return true;
		}
		
		return false;
	}

	@Override
	public void recibeDaño(int daño) {
		vida = vida - daño;
		
	}

	@Override
	public void colisionBala(int daño) {
		// TODO Auto-generated method stub
		recibeDaño(daño);
	}


}
