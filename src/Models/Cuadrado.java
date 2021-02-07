package Models;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cuadrado {
	// Constantes. Son las direcciones en las que nuestro cuadrado puede moverse
	public static final int ARRIBA = 1;
	public static final int ABAJO = 2;
	public static final int IZQ = 3;
	public static final int DER = 4;

	// Atributos -- Estado

	// Necesitamos la posicion del cuadrado marcado por su esquina superior izq.
	private int posX;
	private int posY;

	// Tambien necesitamos el lado del cuadrado
	private int lado;

	// Ahora el color
	private int colorCuadrado;

	// comportamiento

	// creacion
	public Cuadrado(int pX, int pY, int l, int cc) {

		posX = pX;
		posY = pY;
		lado = l;
		colorCuadrado = cc;

	}

	// un cuadrado se mueve arriba, abajo, derecha e izquierda
	public void moverse(int iDireccion, int heightTablero, int widthTablero) {

		switch (iDireccion) {
		case Cuadrado.ARRIBA:
			if (posY > 0) // mientras la posicion y sea mayor que cero se mueve normal
				posY -= lado; // 1 es arriba
			else // cuando es menor que cero pasa al otro lado del tablero para teletransportarse
				posY = heightTablero - 20;
			break;
		case Cuadrado.ABAJO:
			if (posY < heightTablero - 20) // cuando es menor que el alto del tablero se mueve normal
				posY += lado; // 2 es abajo
			else // cuando es mayor que el alto del tablero, pasa al otro lado del tablero para
					// teletransportarse
				posY = 0;
			break;
		case Cuadrado.IZQ:
			if (posX > 0) // mientras la posicion y sea mayor que cero se mueve normal
				posX -= lado; // 3 es izquierda
			else // cuando es menor que cero pasa pasa al otro lado del tablero para
					// teletransportarse
				posX = widthTablero - 20;
			break;
		case Cuadrado.DER:
			if (posX < widthTablero - 20)// cuando es menor que el ancho del tablero se mueve normal
				posX += lado; // 4 es derecha
			else // cuando es mayor que el ancho del tablero, pasa al otro lado del tablero para
					// teletransportarse
				posX = 0;
		}
	}

	// un cuadrado puede estar colisionando con otro
	public boolean estaEncimaDe(Cuadrado cabeza) {
		// en nuestro caso, solo comprobamos la esquina superior izq
		// almacenada en las posiciones X e Y. No hay otra posibilidad.
		return (cabeza.getX() == posX && cabeza.getY() == posY);
	}

	// Metodos de obtencion de datos
	public int getX() {
		return posX;
	}

	public void setX(int x) {
		posX = x;
	}

	public int getY() {
		return posY;
	}

	public void setY(int y) {
		posY = y;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int l) {
		lado = l;
	}

	public int getColor() {
		return colorCuadrado;
	}

	// Un cuadrado tiene que saber pintarse
	public void pintarse(Graphics2D g) {

		g.setColor(new Color(colorCuadrado));
		// g.drawRect(posX, posY, lado, lado);
		g.fillRect(posX, posY, lado, lado);
		// g.fillOval(posX, posY, lado, lado);

	}
}
