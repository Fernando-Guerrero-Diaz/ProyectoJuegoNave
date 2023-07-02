package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public final class Nave implements Movimiento{
	private static Nave instance;
	public String value;
	private boolean destruida = false;
    private int vidas = 50;
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
    private int maxCooldown=100;
    private int cooldownDano=0;
    private Shoot disparo;
    private Sprite spr;
    private int x=Gdx.graphics.getWidth()/2-50;
    private int y=30;
    private float xVel;
    private float yVel;
    
    private Nave() {
    	reinicio();
    	
    /*private Nave (int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
    	sonidoHerido = soundChoque;
    	this.soundBala = soundBala;
    	this.txBala = txBala;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 60, 92);
    	disparo = new Shoot();
        disparo.setStrategy(new BalasLaterales());*/

    }
    public void nuevaRonda() {
    	maxVelocidad=5;
    	maxCooldown=100;
    	disparo.setStrategy(new BalasLaterales());
    }
    public void reinicio() {
    	sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
    	this.soundBala = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
    	this.txBala = new Texture(Gdx.files.internal("CannonballGrey.png"));
    	spr = new Sprite(new Texture(Gdx.files.internal("North.png")));
    	spr.setPosition(x, y);
    	//spr.setOriginCenter();
    	spr.setBounds(x, y, 60, 92);
    	disparo = new Shoot();
        disparo.setStrategy(new BalasLaterales());
    	setVidas(50);
    	destruida=false;
    	maxVelocidad=5;
    	maxCooldown=100;
    	/*nave = new Nave(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("North.png")),
			Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
			new Texture(Gdx.files.internal("CannonballGrey.png")), 
			Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"))); */
    }
    public static synchronized Nave getNaveInstance() {
    	if(instance==null) {
    		instance = new Nave();
    	}
    	return instance;
    }
    public int getCantDisparos() {
    	return disparo.getCantDisparos();
    }

    
	@Override
	public void moverse() {	
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


	public void draw(SpriteBatch batch, GerenteElementos gerente){
        float x =  spr.getX();
        float y =  spr.getY();
        if (!herido) {
	        // que se mueva con teclado
        	moverse();

	        xVel =(float)( velocidad * -Math.sin(Math.toRadians(rotacion)));
	        yVel =(float)( velocidad * Math.cos(Math.toRadians(rotacion)));
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

           disparo.shoot(gerente,rotacion, spr, txBala);
          cooldownDisparo=maxCooldown;

	      soundBala.play();
        }
        
        if (cooldownDisparo>0)cooldownDisparo--;
        if(cooldownDisparo<0)cooldownDisparo=0;
        if(cooldownDano>0)cooldownDano--;
        if(cooldownDano<0)cooldownDano=0;
       
    }

public void changeStrategy(ShootStrategy shoot) {
	disparo.setStrategy(shoot);
}
public boolean checkCollision(Elemento e) {
	if(cooldownDano<=0) {
        if(!herido && e.estaActivo() && e.getArea().overlaps(spr.getBoundingRectangle())){
        	e.colisionNave();
        	
        	/*
        	Obstaculo o = (Obstaculo) b;
        	if (!o.estaActivo())return false;
        	//actualizar vidas y herir
            vidas=vidas- o.getDaño();
            herido = true;
            cooldownDano=100;
  		    tiempoHerido=tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vidas<=0) 
          	    destruida = true; 
          	    */
            return true;
            
    	}

    }

    return false;
}
public void recibirDaño(int daño) {
    vidas=vidas- daño;
    herido = true;
    cooldownDano=100;
	    tiempoHerido=tiempoHeridoMax;
	    sonidoHerido.play();
    if (vidas<=0) 
  	    destruida = true; 
}

public boolean estaDestruido() {
   return !herido && destruida;
}
public boolean estaHerido() {
	   return herido;
}
public void nuevaVel(float bonus) {maxVelocidad=maxVelocidad+bonus;}
public void nuevoCooldown(int reduccion) {maxCooldown=maxCooldown-reduccion;}
public int getVidas() {return vidas;}
public int getX() {return (int) spr.getX();}
public int getY() {return (int) spr.getY();}
public void setVidas(int vidas2) {vidas = vidas2;}
}

