package Controller;

import javax.swing.JOptionPane;

public class ControlMedida {

	private int alto = 0;
	private int ancho = 0;

	public ControlMedida() {
		super();
	}

	public int menuMedida() { // este menu nos permite seleccionar la medida del tablero

		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Seleccion Medida",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Reducido", "Medio", "Grande" }, "Reducido");
		if (seleccion == 0) { // Reducido
			alto = 600;
			ancho = 400;
		}
		if (seleccion == 1) { // Medio
			alto = 800;
			ancho = 600;
		}
		if (seleccion == 2) { // Grande
			alto = 1000;
			ancho = 800;
		}
		if (seleccion < 0) { // obliga al jugador a elegir una medida de tablero si no elige muestra error
			JOptionPane.showMessageDialog(null, "Tiene que elegir una medida de pantalla de juego.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			menuMedida();
		}
		return seleccion;
	}

	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

}
