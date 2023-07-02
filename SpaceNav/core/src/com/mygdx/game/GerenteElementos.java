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
	private Elemento tesoro;
	private Elemento remo;
	private Elemento polvora;
	private Elemento canon;
	private Elemento bomba;
	private int cantEnemigos;
	private FabricaElementos fabrica;
	public GerenteElementos(int cantEnemigos, int cantObstaculos, FabricaElementos fabrica) {
		this.cantEnemigos = cantEnemigos;
		this.fabrica = fabrica;
		int wait =200;
        //crear asteroides
        Random r = new Random();
	    for (int i = 0; i < cantEnemigos; i++) {
	        Elemento bb = fabrica.getEnemigo(300 + i*wait);
	  	    elems1.add(bb);
	  	    elems2.add(bb);
	  	}
	    //crear rocas 
	    for (int i = 0; i < cantObstaculos; i++) {
	        Elemento b2 = fabrica.getRoca(300 + i*wait);
	  	    elems1.add(b2);
	  	    elems2.add(b2);
	    }
	    
	    //crear tesoro
	    tesoro = fabrica.getTesoro(cantEnemigos, cantObstaculos, 100);
	    elems1.add(tesoro);
	    elems2.add(tesoro);
	    // crear remo
	    remo = fabrica.getRemo(cantObstaculos, 0);
	    elems1.add(remo); 
	    elems2.add(remo);
	    // crear polvora
	    polvora = fabrica.getPolvora(cantObstaculos, 0);
	    elems1.add(polvora);
	    elems2.add(polvora);
	 // crear canon
	    canon = fabrica.getCañon(cantObstaculos, 0);
	    elems1.add(canon);
	    elems2.add(canon);
	 // crear bomba
	    bomba = fabrica.getBomba(cantObstaculos, 0);
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
