package estrategiaBalas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Bala;
import com.mygdx.game.GerenteElementos;

public class BalasTotales implements ShootStrategy {
	public BalasTotales(){
		
	}
	public int disparos() {
		return 5;
	}
	//Son la forma de disparo en 8 direcciones diferentes que se disparan por agarrar 1 pick up
	public void shoot(GerenteElementos gerente, float rotacion, Sprite spr, Texture txBala) {
		float xbullet,ybullet;
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
	    Bala  bala1 = new Bala(xbullet,ybullet,-7,7,txBala);
	    Bala  bala2 = new Bala(xbullet,ybullet,7,-7,txBala);
		Bala  bala3 = new Bala(xbullet,ybullet,7,7,txBala);
		Bala  bala4 = new Bala(xbullet,ybullet,-7,-7,txBala);
		Bala  balaN = new Bala(xbullet,ybullet,1/7,9,txBala);
		Bala  balaS = new Bala(xbullet,ybullet,1/7,-9,txBala);
		Bala  balaE = new Bala(xbullet,ybullet,9,1/7,txBala);
		Bala  balaO = new Bala(xbullet,ybullet,-9,1/7,txBala);
		gerente.agregarBala(bala1);
		gerente.agregarBala(bala2);
		gerente.agregarBala(balaS);
	    gerente.agregarBala(balaN);
		gerente.agregarBala(bala3);
		gerente.agregarBala(bala4);
		gerente.agregarBala(balaE);
		gerente.agregarBala(balaO);
		
	}
	
}
