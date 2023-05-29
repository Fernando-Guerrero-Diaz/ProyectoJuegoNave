package com.mygdx.game.Movimiento;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Ball2;

public class Bala extends Movimiento {
	private boolean destroyed = false;
	
    public Bala(float x, float y, float xVel, float yVel, Texture tx) {
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
        this.xVel = xVel;
        this.yVel = yVel;
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
    
    public boolean checkCollision(Ball2 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.getArea())){
        	// Se destruyen ambos
            this.destroyed = true;
            return true;

        }
        return false;
    }
    
    public boolean isDestroyed() {return destroyed;}

}