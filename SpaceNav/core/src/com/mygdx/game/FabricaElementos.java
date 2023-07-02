package com.mygdx.game;

public interface FabricaElementos {

	
	public Elemento getEnemigo(int wait);
	public Elemento getRoca(int wait);
	public Elemento getTesoro(int x, int y, int puntos);
	public Elemento getCañon(int x, int y);
	public Elemento getPolvora(int x, int y);
	public Elemento getRemo(int x, int y);
	public Elemento getBomba(int x, int y);
}
