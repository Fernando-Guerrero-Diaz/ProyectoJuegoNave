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
    
    public boolean checkCollision(Enemigo b2) {
        if(b2.estaActivo() && getArea().overlaps(b2.getArea())){
        	// Se destruyen ambos
            this.destroyed = true;
            explosionSound.play(0.15f);
            b2.recibeDaño(daño);
            return true;
            
        }
        return false;
    }
    
    public boolean isDestroyed() {return destroyed;}
    
    public boolean eliminado() {return destroyed;}
    

}