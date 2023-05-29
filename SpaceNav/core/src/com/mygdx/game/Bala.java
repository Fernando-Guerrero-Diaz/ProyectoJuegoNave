package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bala extends Movimiento {
	private boolean destroyed = false;
	private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));

    public Bala(float x, float y, float xVel, float yVel, Texture tx) {
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
        this.xVel = xVel;
        this.yVel = yVel;
    	explosionSound.setVolume(1,0.5f);
    }
    
	public void moverse() {
		spr.setPosition(spr.getX()+xVel, spr.getY()+yVel);
        if (spr.getX() < 0 || spr.getX()+spr.getWidth() > Gdx.graphics.getWidth()) {
            destroyed = true;
        }
        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > Gdx.graphics.getHeight()) {
        	destroyed = true;
        }
        
	}
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
    
    public boolean checkCollision(Enemigo b2) {
        if(b2.estaActivo() && spr.getBoundingRectangle().overlaps(b2.getArea())){
        	// Se destruyen ambos
            this.destroyed = true;
            explosionSound.play(0.15f);
            return true;
            
        }
        return false;
    }
    
    public boolean isDestroyed() {return destroyed;}

}