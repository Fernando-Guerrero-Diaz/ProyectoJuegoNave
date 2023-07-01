package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Elemento {
	private float y;
	private float x;
	private float xVel;
	private float yVel;
	private Sprite spr;
	
	public  Rectangle getArea() {
		return spr.getBoundingRectangle();
	}
	
	//public abstract void doCollision();
	
	public void draw(SpriteBatch batch) {
	   	spr.draw(batch);
	}
	   
	public abstract boolean eliminado();
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getxVel() {
		return xVel;
	}
	public void setxVel(float xVel) {
		this.xVel = xVel;
	}
	public float getyVel() {
		return yVel;
	}
	public void setyVel(float yVel) {
		this.yVel = yVel;
	}
	public Sprite getSpr() {
		return spr;
	}
	public void setSpr(Sprite spr) {
		this.spr = spr;
	}
	public void setSprPosition() {
		spr.setPosition(x, y);
	}
	
	public void addX(float x) {
		this.x+=x;
	}
	public void addY(float y) {
		this.y+=y;
	}
	
}
