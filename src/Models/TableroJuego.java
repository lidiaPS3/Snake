package Models;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Necesitamos una clase JPanel que represente nuestro tablero y que a la hora
 * de pintarse le diga a la serpiente que tambien se pinte. Para ello necesita
 * saber quien esta controlando el juego (el frame de tipo MySnakeFrame) y hacer
 * un "override" del metodo paint.
 * 
 * 
 * @author andres
 *
 */

@SuppressWarnings("serial")
public class TableroJuego extends JPanel {

	private MySnakeFrame snakeFrame;

	// comportamiento para "enganchar" el tablero con el frame controlador.
	public void setSnakeFrame(MySnakeFrame sf) {
		snakeFrame = sf;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (snakeFrame != null) { // pintamos si tenemos serpiente
			Graphics2D g2d = (Graphics2D) g;
			// configuramos los graficos para que pinte figuras suaves...
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// le decimos al controlador que nos de la serpiente y las manzanas para
			// pintarlas...
			snakeFrame.pintarseManzana(g2d);
			snakeFrame.getSerpiente().pintarse(g2d);
		}
	}
}
