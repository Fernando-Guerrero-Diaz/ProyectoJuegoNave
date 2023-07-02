package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BalasFrontales implements ShootStrategy{
	public BalasFrontales() {
		
	}
	public int disparos() {
		return 20;
	}
	//Dependiendo del angulo del barco es como se dispara las balas frontales.
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
		if(rotacion>90 && rotacion<180) {
    		xbullet=spr.getX() + (float)Math.cos(Math.toRadians(rotacion))*spr.getWidth();
    		ybullet=spr.getY() - (float)Math.sin(Math.toRadians(rotacion))*spr.getWidth();
    		rotacion*=-1;
    	}
		else if (rotacion == 180) {
			xbullet=spr.getX()-spr.getWidth()/2;
    		ybullet=spr.getY()-spr.getHeight();
    		rotacion *= -1; 
		}
    	else if(rotacion>180 && rotacion<270) {
    		xbullet=spr.getX()-(float)Math.cos(Math.toRadians(rotacion))*spr.getWidth();
    		ybullet=spr.getY()+(float)Math.sin(Math.toRadians(rotacion))*spr.getWidth();
    		rotacion *= -1; 
    	}
    	else if(rotacion<90 && rotacion >0) {
    		xbullet=spr.getX()-(float)Math.cos(Math.toRadians(rotacion))*spr.getWidth();
    		ybullet=spr.getY()+(float)Math.sin(Math.toRadians(rotacion))*spr.getWidth();
    		rotacion*=-1;
    	}
    	else if(rotacion==90) {
    		xbullet=spr.getX()-spr.getHeight();
    		ybullet=spr.getY()+spr.getWidth()/2;
    		rotacion*=-1;
    	}
    	else if(rotacion>270 && rotacion<360) {
    		xbullet=spr.getX()+(float)Math.cos(Math.toRadians(rotacion))*spr.getWidth();
    		ybullet=spr.getY()-(float)Math.sin(Math.toRadians(rotacion))*spr.getWidth();
    		rotacion*= -1;
    	}
    	else if(rotacion==270) {
    		xbullet=spr.getX()+spr.getHeight();
    		ybullet=spr.getY()-spr.getWidth()/2;
    		rotacion*= -1;
    	}
    	else {
    		xbullet=spr.getX()+spr.getWidth()/2;
    		ybullet=spr.getY()+ spr.getHeight();

    	}
		Bala  bala = new Bala(xbullet,ybullet,(int)(9*Math.sin(Math.toRadians(rotacion))),(int)(9*Math.cos(Math.toRadians(rotacion))),txBala);
	    gerente.agregarBala(bala);

	}
}
