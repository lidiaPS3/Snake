package Models;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controller.ControlMedida;

/**
 * Clase MySnakeFrame
 * 
 * Este es el primer ejemplo de comentarios de documentacion que vemos en JAVA
 * Como se puede apreciar son un bloque de comentarios, no solo una linea
 * 
 * Aqui­ explicamos que sentido tiene la clase
 * 
 * Esta clase hereda de JFrame. JFrame es una ventana equivalente a un Form en
 * Gambas3 Nuestra ventana principal ademas del comportamiento y estado natural
 * a una ventana grafica controlara todo el estado del juego. Como nuestro "game
 * loop" esta codificado como "run forever" tenemos que tener algun objeto que
 * controle si el juego este pausado, empezado, terminado en todo momento, y que
 * a su vez le diga a la serpiente que se resetee, que se mueva, crezca, etc.
 * Para controlar el estado se usan "semeforos". Estos no son nada mes que
 * booleanos. Cuando estan a false seria el equivalente a "semaforo rojo" y
 * cuando esten a true seria el equivalente a "semaforo verde" Estos semaforos
 * se ponen en una serie de "if" que permiten controlar al objeto MySnakeFrame
 * si se ejecuta alguna accion o no en la serpiente.
 * 
 * @author andres
 *
 */

public class MySnakeFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// Nuestra serpiente
	private Serpiente snake;
	private Manzana manzana;
	// semaforos para indicar que estamos jugando o no
	private boolean jugando;
	private boolean pausado;

	// semaforos para mostrar mensaje al final, solo una vez.
	private boolean mostrarFinal;
	private boolean mostrado;

	private ArrayList<Manzana> listaManzanas;
	private ControlMedida medida;

	// Constructor
	public MySnakeFrame() {

		snake = new Serpiente();
		jugando = false;
		mostrarFinal = false;
		mostrado = false;
		pausado = false;
		listaManzanas = new ArrayList<Manzana>();

	}

	// **** Comportamientos

	public void generaManzana() { // se generan manzanas en el tablero con una posicion aleatoria
		int x = medida.getAlto() - 100;
		int y = medida.getAncho() - 100;
		int xRandom, yRandom;

		do {
			yRandom = (int) (Math.random() * y);
			for (Manzana manzana : listaManzanas) { // comprobamos que ninguna manzana este en esta posicion
				if (manzana.getY() == yRandom) {
					yRandom = (int) (Math.random() * y);
				}
			}
		} while (yRandom % 20 != 0); // comprobamos que este en el eje de la serpiente
		do {
			xRandom = (int) (Math.random() * x);
			for (Manzana manzana : listaManzanas) {
				if (manzana.getX() == xRandom) { // comprobamos que ninguna manzana este en esta posicion
					xRandom = (int) (Math.random() * x);
				}
			}
		} while (xRandom % 20 != 0); // comprobamos que este en el eje de la serpiente
		listaManzanas.add(new Manzana(xRandom, yRandom, 20, 234009036));
	}

	// si alguien necesita nuestra serpiente, se la proporcionamos.
	public Serpiente getSerpiente() {
		return snake;
	}

	public Manzana getManzana() {
		return manzana;
	}

	public void setView(ControlMedida medida) {
		this.medida = medida;
	}

	public boolean come() {
		if (listaManzanas.size() < 3) { // metodo que comprueba que siempre sea tres el numero de manzanas en el tablero
			for (int i = listaManzanas.size(); i < 3; i++) {
				generaManzana();
			}
		}
		Cuadrado cabeza;
		manzana = listaManzanas.get(0);
		cabeza = snake.getListaCuadrados().get(0);
		for (Manzana manzana : listaManzanas) {
			if (cabeza.estaEncimaDe(manzana)) { // serpiente come...
				generaManzana();
				listaManzanas.remove(manzana);
				return true;
			}
		}

		return false;

	}

	public void pintarseManzana(Graphics2D g) {
		int iCont;

		// pintamos desde el cuadrado 0 hasta el ultimo. Cuidado, aqui con el "<"
		// evitamos
		// tener que poner el "-1" que poniamos en el for del BASIC
		for (iCont = 0; iCont < listaManzanas.size(); iCont++) {
			listaManzanas.get(iCont).pintarse(g);
		}
	}

	// tenemos que mostrar la ventanita de final de partida??? Solo una vez...
	public boolean mostrarFin() {
		boolean resultado;

		resultado = false;
		if (mostrarFinal && !mostrado) { // estamos al final de una partida y no hemos mostrado nada
			resultado = true; // activamos el resultado para que se muestre la ventana
			mostrado = true; // ya no dejamos que se muestre mas la proxima vez...
		}

		return resultado;
	}

	// toca crecer solo si estamos en una partida activa y no estamos pausados...
	public void tocaCrecer() {
		if (jugando && !pausado)
			snake.crecer(medida.getAncho(), medida.getAlto());
	}

	// toca moverse solo si estamos en una partida activa y no estamos pausados...
	public void tocaMoverse() {
		if (jugando && !pausado)
			snake.moverse(medida.getAncho(), medida.getAlto());

	}

	// han pulsado el boton de start, hay que ponerlo todo en orden.
	public void empezarDeNuevo() {
		snake = new Serpiente(); // nueva y flamante serpiente
		jugando = true; // estamos jugando a partir de ¡YA!
		mostrarFinal = false; // ni estamos al final ni mucho menos
		mostrado = false; // hemos mostrado el msg de final
		pausado = false; // Y todavia nadie ha pulsado el pause, todavia...
	}

	// Hay que ver si la serpiente sigue viva, pero solo si estamos jugando y no en
	// modo pausa...
	public void comprobarEstado(int iAlto, int iAncho) {

		if (jugando && !pausado) {

			if (snake.estaMuerta(iAlto, iAncho)) {
				// acabamos de matarnos. Hay que mostrar msg al final y ya no jugamos...
				jugando = false;
				mostrarFinal = true;
				mostrado = false;
			}
		}
	}

	// solo pausamos / continuamos si estamos jugando.
	public void pausaContinuaJuego() {
		if (jugando) {
			pausado = !pausado;
		}
	}

	// nos han pulsado tecla, cambiamos de direccion.
	public void cambiaDireccion(int key) {
		snake.cambiaDireccion(key);
	}
}
