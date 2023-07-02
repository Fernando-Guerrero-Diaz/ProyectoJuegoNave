package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Roca extends Obstaculo implements Movimiento{
	private int margenEntradaSalida = 30;
	private int daño = 10;
	private boolean activo;
	private int spawnWait;
    private int vueltas=0;

		
    public Roca(int x, int y, int size, int xSpeed, int ySpeed, Texture tx,int wait) {
        setSpr(new Sprite(tx));
        setX(x); 
        setY(Gdx.graphics.getHeight()+margenEntradaSalida);
        setSprPosition();
        setxVel(xSpeed);
        setyVel(ySpeed);
        spawnWait=wait;
        activo=false;
    }

    public void moverse() {
        if(activo) {
            addX(getxVel());
            addY(getyVel());
            Random r = new Random();
            if ((getX()+getxVel()< -margenEntradaSalida)) {
                setX(Gdx.graphics.getWidth()+margenEntradaSalida);
                vueltas=vueltas+1;
            }
            if(getX()+getxVel()+getSpr().getWidth() > Gdx.graphics.getWidth()+margenEntradaSalida*2) {
                setX(-margenEntradaSalida) ;
                vueltas=vueltas+1;
                }
            if (getY()+getyVel() < -margenEntradaSalida) {
                setY(Gdx.graphics.getHeight()+margenEntradaSalida);
                setX(50+r.nextInt((int)Gdx.graphics.getHeight()-50));
                vueltas=vueltas+1;
            }
            if(getY()+getyVel()+getSpr().getHeight() > Gdx.graphics.getHeight()+margenEntradaSalida*2)
                setY(Gdx.graphics.getHeight());
                setSprPosition();
                vueltas=vueltas+1;
            }
        else {
        spawnWait--;
        if (spawnWait<=0)activo=true;
        }
    }
 
	
	public int getDaño() {
		return daño;
	}
	
	public boolean esDestructible() {
		return false;
	}
	public boolean estaActivo() {
		return true;
	}

	public boolean eliminado() {
		//Cuando los obstaculos den 3 vueltas a la pantalla son eliminados.
        if(vueltas>=2460)return true;

        return false;
	}


	public void recibeDaño(int daño) {  }

	@Override
	public void colisionBala(int daño) {	}

	@Override
	public void checkCollision(Elemento e2) {
		// TODO Auto-generated method stub
		
	}
}
