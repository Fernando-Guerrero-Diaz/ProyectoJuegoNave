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


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int velXAsteroides; 
	private int velYAsteroides; 
	private int cantAsteroides;
	private int cantObstaculos;
	private int velYroca;
	private Texture ocean;
	private Texture ocean2;
	private int yOcean=0;
	private int yOcean2=800;
	private Texture barra;
	private Texture dinabarra;
	
	private GerenteElementos gerente;
	
	private Nave nave;


	public PantallaJuego(SpaceNavigation game, int ronda,int velXAsteroides, 
			int velYAsteroides, int cantAsteroides, int cantObstaculos,int velYroca) {
		this.game = game;
		this.ronda = ronda;
		FabricaElementos fabrica = new Nivel1();
		gerente = new GerenteElementos(cantAsteroides, cantObstaculos,fabrica);
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		this.cantObstaculos = cantObstaculos;
		this.velYroca = velYroca;
		barra= new Texture(Gdx.files.internal("Barra.png"));
		dinabarra= new Texture(Gdx.files.internal("PdeVida4.png"));
		ocean = new Texture(Gdx.files.internal("Ocean.png"));
		ocean2 = new Texture(Gdx.files.internal("Ocean.png"));
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Navegar.mp3")); //
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.20f);
		gameMusic.play();
		
	    nave = Nave.getNaveInstance();
	    //Cuando el juego es reiniciado la nave se resetea sus estadisticas.
        if(ronda == 1) {
			nave.reinicio();
		}
        nave.nuevaRonda();
	}
    
	public void dibujaEncabezado() {
		int x=405,ac=x;
		//Se dibuja la barra de vida, score y alta puntación por la pantalla de juego.
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, "Score:"+nave.getScore(), Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
		if(nave.getCantDisparos()>0) {
			game.getFont().draw(batch, "Balas Restantes:"+nave.getCantDisparos(), (Gdx.graphics.getWidth()/2)+140, 30);
		}
		game.getBatch().draw(barra, 0, 0, 458, 78);
			ac=x*nave.getVidas()/50;
			if(ac<0) ac=0;
			//Se actualiza la barra de vida en el juego
			game.getBatch().draw(dinabarra,18,7,ac,56);
			game.getBatch().draw(barra, 0, 0, 458, 78);
	}
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          batch.begin();
          batch.draw(ocean, 0, yOcean, 1200, 800);
          yOcean--;
          batch.draw(ocean2, 0, yOcean2, 1200, 800);
          yOcean2--;
          if(yOcean==-800) {
        	  yOcean=800;
          }
          if(yOcean2==-800) {
        	  yOcean2=800;
          }
          //updateo de elementos a través de gerente
          gerente.update(nave, batch);
	      
	      
	      if (nave.estaDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
		  dibujaEncabezado();
		  if(ronda==2) {
			  //Diferente fondo de pantalla del nivel 2
	    	  ocean = new Texture(Gdx.files.internal("newOcean.jpg"));
	    	  ocean2 = new Texture(Gdx.files.internal("newOcean.jpg"));
	      }
	      else if(ronda==3) {
	    	  //diferente fondo de pantalla del nivel 3
	    	  ocean = new Texture(Gdx.files.internal("newOcean1.jpg"));
	    	  ocean2 = new Texture(Gdx.files.internal("newOcean1.jpg"));
	      }
	      batch.end();
	      score=nave.getScore();
	      //nivel completado
	      if (gerente.nivelCompleto()) {
			Screen ss = new PantallaJuego(game,ronda+1,	velXAsteroides+1, 
					velYAsteroides+1, cantAsteroides+1,cantObstaculos+1,velYroca);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }
	     
	}
    
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}
   
}
