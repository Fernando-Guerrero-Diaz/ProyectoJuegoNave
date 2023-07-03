package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Nivel2 implements FabricaElementos{
	private float xVelEnemigo = 1.3f;
	private float yVelEnemigo = -4.0f;
	private int dañoEnemigo = 8;
	private int vidaEnemigo = 8;
	private int yVelRoca = -3;
	private Random r;
	public Nivel2() {
		r = new Random();
	}
	public Elemento getRandomPower(int x, int y) {
		Random random = new Random();   
		int ra = random.nextInt(100);
		if(ra<33) {
			return getRemo(x,y);
		}
		else if(ra<66) {
			return getBomba(x,y);
		}
		else
			return getPolvora(x,y);
	}
	
	public Elemento getEnemigo(int wait) {
		Enemigo ee = new Enemigo(xVelEnemigo+r.nextInt(2), yVelEnemigo+r.nextInt(2), 
        		new Texture(Gdx.files.internal("enemy3.png")),wait, dañoEnemigo,vidaEnemigo);
		return ee;
	}

	@Override
	public Elemento getRoca(int wait) {
		Roca rr = new Roca(r.nextInt((int)Gdx.graphics.getWidth()),
  	            20+r.nextInt((int)Gdx.graphics.getHeight()-50),
  	            250, 0, yVelRoca, new Texture(Gdx.files.internal("rocaObstaculo.png")),wait);	
		return rr;
	}

	@Override
	public Elemento getTesoro(int x, int y, int puntos) {
		Tesoro tt = new Tesoro(x, 50+y, 20+r.nextInt(10), -2 + r.nextInt(5), -2+r.nextInt(4), 
  	            new Texture(Gdx.files.internal("Tesoro1.png")),puntos);
		return tt;
	}

	public Elemento getPolvora(int x, int y) {
		Tesoro polvora = new Polvora(x, 50+y, 20+r.nextInt(10), -2+r.nextInt(5), -2+r.nextInt(5), 
  	            new Texture(Gdx.files.internal("polvora.png")),0);
		return polvora;
	}


	public Elemento getRemo(int x, int y) {
		Tesoro remo = new Remo(x, 50+y, 20+r.nextInt(10), -2+r.nextInt(5), -2+r.nextInt(5), 
  	            new Texture(Gdx.files.internal("remo.png")),0);
		return remo;
	}


	public Elemento getBomba(int x, int y) {
		Tesoro bomba = new Bomba(x, 50+y, 20+r.nextInt(10), -2+r.nextInt(5), -2+r.nextInt(5), 
  	            new Texture(Gdx.files.internal("bomba.png")),0);
		return bomba;
	}

}
