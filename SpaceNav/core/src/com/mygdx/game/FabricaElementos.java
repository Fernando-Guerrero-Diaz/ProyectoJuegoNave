package com.mygdx.game;

public interface FabricaElementos {

	
	public Elemento getEnemigo(int wait);
	public Elemento getRoca(int wait);
	public Elemento getTesoro(int x, int y, int puntos);
	public Elemento getRandomPower(int x,int y);
}
