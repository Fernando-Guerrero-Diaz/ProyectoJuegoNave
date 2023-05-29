package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gerenteElementos {

	private int velXAsteroides; 
	private int velYAsteroides; 
	private int cantAsteroides;
	private int cantObstaculos;
	private int velYroca;
	private  ArrayList<Ball2> balls1 = new ArrayList<>();
	private  ArrayList<Ball2> balls2 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
	private ArrayList<Roca> roca1 = new ArrayList<>();
	private ArrayList<Roca> roca2 = new ArrayList<>();
	private Tesoro tesoro;
	int score;
	
	public gerenteElementos(int score, int velXAsteroides, int velYAsteroides, int cantAsteroides, int cantObstaculos,int velYroca) {
		this.score=score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		this.cantObstaculos = cantObstaculos;
		this.velYroca = velYroca;
		
        //crear asteroides
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),
	  	            50+r.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            20+r.nextInt(10), velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4), 
	  	            new Texture(Gdx.files.internal("aGreyMedium4.png")));	   
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
	
	public void update(Nave4 nave, SpriteBatch batch) {
	      if (!nave.estaHerido()) {
		      // colisiones entre balas y asteroides y su destruccion  
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bullet b = balas.get(i);
		            b.update();
		            for (int j = 0; j < balls1.size(); j++) {    
		              if (b.checkCollision(balls1.get(j))) {
		            	  //puedes ajustar el sonido colocando entre play un float de entre 0 a 1, serian los %
		            	 //explosionSound.play(0.15f);
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
		      for (Ball2 ball : balls1) {
		          ball.update();
		      }
		      for(Roca roca : roca1) {
		    	  roca.update();
		    	  
		      }
		      if (tesoro.estaActivo()) tesoro.update();
		      
		      //colisiones entre asteroides y sus rebotes  
		      for (int i=0;i<balls1.size();i++) {
		    	Ball2 ball1 = balls1.get(i);   
		        for (int j=0;j<balls2.size();j++) {
		          Ball2 ball2 = balls2.get(j); 
		          if (i<j) {
		        	  ball1.checkCollision(ball2);
		     
		          }
		        }
		      } 
		      
		      /*for (int i=0;i<roca1.size();i++) {
		    	Roca Roca1 = roca1.get(i);   
		        for (int j=0;j<balls2.size();j++) {
		          Ball2 ball2 = balls2.get(j); 
		          if (i<j) {
		        	  balls2.checkCollision(ball2);
		          }
		        }
		      }*/
	      }
	      //dibujar balas
	     for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	      nave.draw(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      for (int i = 0; i < balls1.size(); i++) {
	    	    Ball2 b=balls1.get(i);
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
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
    public boolean nivelCompleto() {
    	return(balls1.size()==0);
    }
}
