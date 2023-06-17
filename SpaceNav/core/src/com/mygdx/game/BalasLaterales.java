package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public class BalasLaterales implements ShootStrategy{
	public BalasLaterales() {
		
	}
	public void shoot(GerenteElementos gerente, float rotacion, Sprite spr, Texture txBala) {
		float xbullet,ybullet;
    	if(rotacion>90 && rotacion<180) {
    		xbullet=spr.getX()-spr.getWidth()*8/9;
    		ybullet=spr.getY()-spr.getHeight()*1/9;
    	}
    	else if(rotacion>=180 && rotacion<270) {
    		xbullet=spr.getX()-spr.getWidth()/3;
    		ybullet=spr.getY()-spr.getHeight()/2;
    	}
    	else if(rotacion<=90 && rotacion >0) {
    		xbullet=spr.getX()-spr.getHeight()/2;
    		ybullet=spr.getY()+spr.getWidth();
    	}
    	else if(rotacion>=270 && rotacion<360) {
    		xbullet=spr.getX()+spr.getHeight()*3/5;
    		ybullet=spr.getY();
    	}
    	else {
    		xbullet=spr.getX()+spr.getWidth()/2;
    		ybullet=spr.getY()+ spr.getHeight()/2;
    	}
      Bala  balaL = new Bala(xbullet,ybullet,(int)(-7*Math.cos(Math.toRadians(rotacion))),(int)(-7*Math.sin(Math.toRadians(rotacion))),txBala);
      Bala  balaR = new Bala(xbullet,ybullet,(int)(7*Math.cos(Math.toRadians(rotacion))),(int)(7*Math.sin(Math.toRadians(rotacion))),txBala);
      gerente.agregarBala(balaL);
      gerente.agregarBala(balaR);
	}
}
