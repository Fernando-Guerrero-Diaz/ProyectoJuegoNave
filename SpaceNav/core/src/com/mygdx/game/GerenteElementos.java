package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GerenteElementos {

	private ArrayList<Elemento> elems1 = new ArrayList<>();
	private ArrayList<Elemento> elems2 = new ArrayList<>();
	private  ArrayList<Bala> balas = new ArrayList<>();
	private Tesoro tesoro;
	private Tesoro remo;
	private Tesoro polvora;
	private Tesoro canon;
	private Tesoro bomba;
	private int cantEnemigos;
	
	public GerenteElementos(int velXAsteroides, int velYAsteroides, int cantAsteroides, int cantObstaculos,int velYroca) {
		cantEnemigos = cantAsteroides;
		int wait =200;
        //crear asteroides
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Enemigo bb = new Enemigo(velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	        		new Texture(Gdx.files.internal("enemy.png")),300 + i*wait, 5,5);	   
	  	    elems1.add(bb);
	  	    elems2.add(bb);
	  	}
	    //crear rocas 
	    for (int i = 0; i < cantObstaculos; i++) {
	        Roca b2 = new Roca(r.nextInt((int)Gdx.graphics.getWidth()),
	  	            20+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            250, 0, -velYAsteroides+r.nextInt(1), 
	  	            new Texture(Gdx.files.internal("rocaObstaculo.png")),300 + i*wait);	   
	  	    elems1.add(b2);
	  	    elems2.add(b2);
	    }
	    
	    //crear tesoro
	    tesoro = new Tesoro(r.nextInt((int)Gdx.graphics.getWidth()),
  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("Tesoro.png")),100);
	    elems1.add(tesoro);
	    elems2.add(tesoro);
	    // crear remo
	    remo = new Tesoro(r.nextInt((int)Gdx.graphics.getWidth()),
  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("remo.png")),0);
	    elems1.add(remo); 
	    elems2.add(remo);
	    // crear polvora
	    polvora = new Tesoro(r.nextInt((int)Gdx.graphics.getWidth()),
  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("polvora.png")),0);
	    elems1.add(polvora);
	    elems2.add(polvora);
	 // crear canon
	    canon = new Tesoro(r.nextInt((int)Gdx.graphics.getWidth()),
  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("canon.png")),0);
	    elems1.add(canon);
	    elems2.add(canon);
	 // crear bomba
	    bomba = new Tesoro(r.nextInt((int)Gdx.graphics.getWidth()),
  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("bomba.png")),0);
	    elems1.add(bomba);
	    elems2.add(bomba);
	}
	
	public void update(Nave nave, SpriteBatch batch) {
	      if (!nave.estaHerido()) {
		      // colisiones entre balas y enemigos y la destrucción de balas  
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bala b = balas.get(i);
		            b.moverse();
		            for (int j = 0; j < elems1.size(); j++) {    
		              b.checkCollision(elems1.get(j)); 
		  	        }
		                
		         //   b.draw(batch);
		            if (b.isDestroyed()) {
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }
		      //actualizar movimiento de asteroides dentro del area
	    	  
	    	  for (Elemento elem :elems1) {
	    		  elem.moverse();
	    	  }
		      
		      //colisiones entre asteroides y sus rebotes  
		      for (int i=0;i<elems1.size();i++) {
		    	Elemento e1 = elems1.get(i);   
		        for (int j=0;j<elems2.size();j++) {
		          Elemento e2 = elems2.get(j); 
		          if (i!=j) {
		        	  e1.checkCollision(e2);
		     
		          }
		        }
		      } 	      
	      }
	      //dibujar balas
	     for (Bala b : balas) {       
	          b.draw(batch);
	      }
	      nave.draw(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      for (int i = 0; i < elems1.size(); i++) {
	    	    Elemento  e=elems1.get(i);
	    	    e.draw(batch);
	            nave.checkCollision(e); 
  	        }

	      if (tesoro.estaActivo()) {
	    	  tesoro.draw(batch);
	    	  if(nave.checkCollision(tesoro)) nave.agregarPuntos(tesoro.getPuntos());
	      }
	      if (remo.estaActivo()) {
	    	  remo.draw(batch);
	    	  if(nave.checkCollision(remo)) {nave.agregarPuntos(remo.getPuntos());nave.nuevaVel(3.0f);}
	      }
	      if (polvora.estaActivo()) {
	    	  polvora.draw(batch);
	    	  if(nave.checkCollision(polvora)) {nave.agregarPuntos(polvora.getPuntos());nave.nuevoCooldown(90);}
	      }
	      if (canon.estaActivo()) {
	    	  canon.draw(batch);
	    	  if(nave.checkCollision(canon)) {nave.agregarPuntos(canon.getPuntos());nave.changeStrategy(new BalasFrontales());}
	      }
	      if (bomba.estaActivo()) {
	    	  bomba.draw(batch);
	    	  if(nave.checkCollision(bomba)) {nave.agregarPuntos(bomba.getPuntos());nave.changeStrategy(new BalasTotales());}
	      }
	      
	      
	      cleanupEliminados();
	}
    public boolean agregarBala(Bala bb) {
    	return balas.add(bb);
    }
    public boolean nivelCompleto() {
    	return(cantEnemigos<=0);
    }


    //Elimina los elementos del array cuando son eliminados de la pantalla
    public void cleanupEliminados() {
    	for (int i = 0; i < elems1.size(); i++) {
    		
    		Elemento e = elems1.get(i); 
            if (e.eliminado()) {
            	if(e instanceof Enemigo) cantEnemigos--;
           
          	 elems1.remove(i);
          	 elems2.remove(i);
          	 i--;
          	 
            }
    	}
    }

}
