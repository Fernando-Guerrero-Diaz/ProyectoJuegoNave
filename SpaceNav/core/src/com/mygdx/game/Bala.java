package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bala extends Elemento {
	private int daño = 5;
	private boolean destroyed = false;
	private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));

    public Bala(float x, float y, float xVel, float yVel, Texture tx) {
    	setSpr(new Sprite(tx));
    	setX(x);
    	setY(y);
    	setSprPosition();
        setxVel(xVel);
        setyVel(yVel);
    	explosionSound.setVolume(1,0.5f);
    }
    
	public void moverse() {
		addX(getxVel());
		addY(getyVel());
		setSprPosition();
        if (getX() < 0 || getX()+getSpr().getWidth() > Gdx.graphics.getWidth()) {
            destroyed = true;
        }
        if (getY() < 0 || getY()+getSpr().getHeight() > Gdx.graphics.getHeight()) {
        	destroyed = true;
        }
        
	}
    
    public void checkCollision(Elemento e) {
    	
        if(e.estaActivo() && getArea().overlaps(e.getArea()) && e.esDestructible()){
        	//se destruye bala
            this.destroyed = true;
            explosionSound.play(0.15f);
            e.colisionBala(daño);
            
            
        }
 
    }
    
    public void colisionBala(int daño) {};
    public boolean esDestructible() {return true;}
    public boolean isDestroyed() {return destroyed;}
    
    public boolean eliminado() {return destroyed;}

	@Override
	public boolean estaActivo() {
		// TODO Auto-generated method stub
		return true;
	}
    

}