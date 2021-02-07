package Controller;

import javax.swing.JOptionPane;

public class ControlDificultad {

	private double nivel;

	public ControlDificultad() {
	}

	public int menuNivel() { // este menu nos permite seleccionar la dificultad
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Seleccion de dificultad",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Facil", "Intermedio", "Dificil", "Imposible" }, "Facil");

		if (seleccion == 0) { // Facil
			nivel = 10;
		}
		if (seleccion == 1) { // Intermedio
			nivel = 5;
		}
		if (seleccion == 2) { // Dificil
			nivel = 2;
		}
		if (seleccion == 3) { // Imposible
			nivel = 0.2;
		}
		if (seleccion < 0) { // obliga al jugador a elegir una dificultad si no elige muestra error
			JOptionPane.showMessageDialog(null, "Tiene que elegir un nivel de dificultad.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			menuNivel();
		}
		return seleccion;
	}

	public double getNivel() {
		return nivel;
	}

}
