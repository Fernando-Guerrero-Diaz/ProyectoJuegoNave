package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gerenteElementos {


	private  ArrayList<Enemigo> balls1 = new ArrayList<>();
	private  ArrayList<Enemigo> balls2 = new ArrayList<>();
	private  ArrayList<Bala> balas = new ArrayList<>();
	private ArrayList<Roca> roca1 = new ArrayList<>();
	private ArrayList<Roca> roca2 = new ArrayList<>();
	private Tesoro tesoro;
	int score;
	
	public gerenteElementos(int score, int velXAsteroides, int velYAsteroides, int cantAsteroides, int cantObstaculos,int velYroca) {
		this.score=score;

		int wait =200;
        //crear asteroides
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Enemigo bb = new Enemigo(velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	        		new Texture(Gdx.files.internal("enemy.png")),300 + i*wait);	   
	  	    balls1.add(bb);
	  	    balls2.add(bb);
	  	}
	    //crear rocas 
	    for (int i = 0; i < cantObstaculos; i++) {
	        Roca b2 = new Roca(r.nextInt((int)Gdx.graphics.getWidth()),
	  	            20+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            250, 0, -velYAsteroides+r.nextInt(1), 
	  	            new Texture(Gdx.files.internal("rocaObstaculo.png")));	   
	  	    roca1.add(b2);
	  	    roca2.add(b2);
	    }
	    
	    //crear tesoro
	    tesoro = new Tesoro(r.nextInt((int)Gdx.graphics.getWidth()),
  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("Tesoro.png")));	
	
	}
	
	public void update(Nave nave, SpriteBatch batch) {
	      if (!nave.estaHerido()) {
		      // colisiones entre balas y asteroides y su destruccion  
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bala b = balas.get(i);
		            b.moverse();
		            for (int j = 0; j < balls1.size(); j++) {    
		              if (b.checkCollision(balls1.get(j))) {
		            	 balls1.remove(j);
		            	 balls2.remove(j);
		            	 j--;
		            	 score +=10;
		              }   	  
		  	        }
		                
		         //   b.draw(batch);
		            if (b.isDestroyed()) {
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }
		      //actualizar movimiento de asteroides dentro del area
		      for (Enemigo ball : balls1) {
		          ball.moverse();
		      }
		      for(Roca roca : roca1) {
		    	  roca.moverse();
		    	  
		      }
		      if (tesoro.estaActivo()) tesoro.moverse();
		      
		      //colisiones entre asteroides y sus rebotes  
		      for (int i=0;i<balls1.size();i++) {
		    	Enemigo ball1 = balls1.get(i);   
		        for (int j=0;j<balls2.size();j++) {
		          Enemigo ball2 = balls2.get(j); 
		          if (i!=j) {
		        	  ball1.checkCollision(ball2);
		     
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
	      for (int i = 0; i < balls1.size(); i++) {
	    	    Enemigo b=balls1.get(i);
	    	    b.draw(batch);
		          //perdió vida o game over
	              if (nave.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 balls1.remove(i);
	            	 balls2.remove(i);
	            	 i--;
	              }   	  
  	        }
	      for (int i = 0; i < roca1.size(); i++) {
	    	    Roca b=roca1.get(i);
	    	    b.draw(batch);
		          //perdió vida o game over
	              nave.checkCollision(b);
		            //asteroide se destruye con el choque             
	            	 //roca1.remove(i);
	            	 //roca2.remove(i);
	            	 //i--;
	              //}   	  
	        }
	      
	      for (int i = 0; i < roca1.size(); i++) {
	    	    Roca b=roca1.get(i);
	    	    b.draw(batch);
	      }
	      if (tesoro.estaActivo()) {
	    	  tesoro.draw(batch);
	    	  if(nave.checkCollision(tesoro)) score+=tesoro.getPuntos();
	      }
	}
    public boolean agregarBala(Bala bb) {
    	return balas.add(bb);
    }
    public boolean nivelCompleto() {
    	return(balls1.size()==0);
    }
}
