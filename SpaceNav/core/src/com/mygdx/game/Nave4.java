package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;



public class Nave4 {
	
	private boolean destruida = false;
    private int vidas = 50;
    private float xVel = 0;
    private float yVel = 0;
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    
    private float rotacion =0.0f;
    private float direccion = 0.0f;
    private float maxVelocidad=5;
    private float velocidad=0.0f;
    private float aceleracion = 0.3f;
    private float frenoA=0.2f;
    private float frenoP=0.1f;
    private int tiempoAutoDireccion=0;
    private float rotVel=0.0f;
    private float rotVMax=5.0f;
    private float rotAcc=0.2f;
    
    private int cooldownDisparo=0;
    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 60, 92);

    }
    public void draw(SpriteBatch batch, PantallaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();
        if (!herido) {
	        // que se mueva con teclado
        	movimientoJugador();

	        xVel =(float) ( velocidad * -Math.sin(Math.toRadians(rotacion)));
	        yVel =(float) ( velocidad * Math.cos(Math.toRadians(rotacion)));
	        spr.setRotation(rotacion);
	        // que se mantenga dentro de los bordes de la ventana
	        if (x+xVel < 0 || x+xVel+spr.getWidth() > Gdx.graphics.getWidth())
	        	xVel*=-1;
	        if (y+yVel < 0 || y+yVel+spr.getHeight() > Gdx.graphics.getHeight())
	        	yVel*=-1;
	        
	        spr.setPosition(x+xVel, y+yVel);   
         
 		    spr.draw(batch);
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		  spr.setX(x);
 		   tiempoHerido--;
 		   if (tiempoHerido<=0) herido = false;
 		 }
        // disparo
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)&&cooldownDisparo==0) {         
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
              Bullet  balaL = new Bullet(xbullet,ybullet,(int)(-7*Math.cos(Math.toRadians(rotacion))),(int)(-7*Math.sin(Math.toRadians(rotacion))),txBala);
              Bullet  balaR = new Bullet(xbullet,ybullet,(int)(7*Math.cos(Math.toRadians(rotacion))),(int)(7*Math.sin(Math.toRadians(rotacion))),txBala);
          cooldownDisparo=100;
          juego.agregarBala(balaL);
          juego.agregarBala(balaR);
	      soundBala.play();
        }
        
        if (cooldownDisparo>0)cooldownDisparo--;
        if(cooldownDisparo<0)cooldownDisparo=0;
       
    }
    
    private void movimientoJugador() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) direccion = 135.0f;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)) direccion = 45.0f;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.UP)) direccion = 315.0f;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) direccion = 225.0f;
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) direccion = 0.0f;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) direccion = 90.0f;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) direccion = 180.0f;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) direccion = 270.0f;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) 
        		|| Gdx.input.isKeyPressed(Input.Keys.LEFT) 
        		|| Gdx.input.isKeyPressed(Input.Keys.UP) 
        		|| Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        	tiempoAutoDireccion=40;
	        if ((velocidad > 2) &&
	        		((rotacion > direccion+135 && rotacion < direccion +225) ||
	        		 (rotacion > direccion-135 && rotacion < direccion -225))) {
	        	velocidad = velocidad -frenoA;
	        	if (rotVel<rotVMax/2)rotVel-=rotAcc;
	        }
	        else  if( rotVel<rotVMax) rotVel+=rotAcc;
	        if ((rotacion>direccion-1.5f)&&(rotacion<direccion+1.5f)) {
	        	rotacion = direccion;
	        	rotVel=0;
	        	
	        }
	        else if (rotacion < direccion) {
	        	if (rotacion < direccion -180) rotacion=rotacion-rotVel;
	        	else rotacion=rotacion+rotVel;
	        	}
	        else {
	        	if(rotacion < direccion + 180) rotacion=rotacion-rotVel;
	        	else rotacion=rotacion+rotVel;
	        	}
    		
	        if ((direccion==rotacion)||
	        	(rotacion < direccion +45 && rotacion > direccion -45) ||
	        	(rotacion < direccion -315 && rotacion > direccion -405) ||
	        	(rotacion < direccion +405 && rotacion > direccion +315)) {
	        		velocidad = velocidad + aceleracion;
	        		
	        	}
	        

        }
        else {
        	if (velocidad>0)
        	velocidad = velocidad - frenoP;
        	else velocidad = 0.0f;
        	if (velocidad < 0.2f && tiempoAutoDireccion >0) {
        		tiempoAutoDireccion--;
        	}
        	if (tiempoAutoDireccion <=0) direccion = 0.0f;
        }

        if (rotacion>360.0f) rotacion = rotacion -360.0f;
        if (rotacion<0.0f) rotacion = 360.0f - rotacion;
        if (velocidad>maxVelocidad) velocidad = maxVelocidad;
    }
    
    
    public boolean checkCollision(Elemento b) {
    	if (b instanceof Obstaculo) {

	        if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
	        	Obstaculo o = (Obstaculo) b;
	        	// rebote
	        	/*
	            if (xVel ==0) xVel += b.getXSpeed()/2;
	            if (b.getXSpeed() ==0) b.setXSpeed(b.getXSpeed() + (int)xVel/2);
	            xVel = - xVel;
	            b.setXSpeed(-b.getXSpeed());
	            
	            if (yVel ==0) yVel += b.getySpeed()/2;
	            if (b.getySpeed() ==0) b.setySpeed(b.getySpeed() + (int)yVel/2);
	            yVel = - yVel;
	            b.setySpeed(- b.getySpeed());
	            */
	            // despegar sprites
	      /*      int cont = 0;
	            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont<xVel) {
	               spr.setX(spr.getX()+Math.signum(xVel));
	            }   */
	        	//actualizar vidas y herir
	            vidas=vidas- o.getDaño();
	            herido = true;
	  		    tiempoHerido=tiempoHeridoMax;
	  		    sonidoHerido.play();
	            if (vidas<=0) 
	          	    destruida = true; 
	            return true;
        	}

        }
        if (b instanceof Pickup) {
        	if(b.getArea().overlaps(spr.getBoundingRectangle())) return true;
        }
        return false;
    }
    
    public boolean estaDestruido() {
       return !herido && destruida;
    }
    public boolean estaHerido() {
 	   return herido;
    }
    
    public int getVidas() {return vidas;}
    //public boolean isDestruida() {return destruida;}
    public int getX() {return (int) spr.getX();}
    public int getY() {return (int) spr.getY();}
	public void setVidas(int vidas2) {vidas = vidas2;}
}
