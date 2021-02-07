package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.ControlDificultad;
import Controller.ControlMedida;
import Controller.ControlTeclado;
import Models.MySnakeFrame;
import Models.TableroJuego;

public class GenerateView {

	private int contador;
	private MySnakeFrame frame;
	private TableroJuego tablero;
	private ControlTeclado miControlador;
	private JPanel mainPanel;
	private JPanel botonera;
	private JLabel puntos;
	private JLabel puntosNum;
	private JButton start;
	private JButton pause;
	private ControlMedida medida;
	private ControlDificultad nivel;
	private Toolkit miPantalla;
	private Dimension sizePantalla;

	public GenerateView() {
		this.medida = new ControlMedida();
		this.nivel = new ControlDificultad();
		this.miControlador = new ControlTeclado();
		this.tablero = new TableroJuego();
		this.frame = new MySnakeFrame();

	}

	public void initialize() throws InterruptedException {

		medida.menuMedida();
		nivel.menuNivel();
		frame.setView(medida);
		setComponent();
		setMenu();
		setContador();

	}

	public void setComponent() {
		miPantalla = Toolkit.getDefaultToolkit();
		sizePantalla = miPantalla.getScreenSize();
		frame.setBounds(sizePantalla.width / 3, sizePantalla.height / 4, medida.getAlto(), medida.getAncho());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		frame.add(mainPanel);
		tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		tablero.setBackground(new java.awt.Color(16, 25, 96));
		mainPanel.add(tablero, BorderLayout.CENTER);
		tablero.setSnakeFrame(frame);
		frame.setVisible(true); // activamos la ventana principal para que sea "pintable"
		frame.setResizable(false);
	}

	public void setMenu() {

		botonera = new JPanel();
		botonera.setBorder(BorderFactory.createLineBorder(Color.black));
		botonera.setBackground(new java.awt.Color(150, 150, 150));
		mainPanel.add(botonera, BorderLayout.PAGE_END);

		// Ahora definimos las dos etiquetas para los puntos.
		puntos = new JLabel();
		puntos.setText("Puntos: ");
		puntos.setBackground(new java.awt.Color(190, 190, 190));

		puntosNum = new JLabel();
		puntosNum.setText("0");
		puntosNum.setBackground(new java.awt.Color(190, 190, 190));

		// turno de los botones de empezar y pausar/continuar
		start = new JButton();
		start.setSize(50, 20);
		start.setText("Start");
		start.addActionListener(new Controller.MyButtonListener(frame, tablero));

		pause = new JButton();
		pause.setSize(50, 20);
		pause.setText("Pause");
		pause.addActionListener(new Controller.MyButtonListener(frame, tablero));

		// Preparamos el control del teclado
		miControlador = new ControlTeclado();
		miControlador.setSnakeFrame(frame); // le damos al controlador de teclado un enlace el frame principal
		tablero.addKeyListener(miControlador); // le decimos al tablero que el teclado es cosa de nuestro controlador
		tablero.setFocusable(true); // permitimos que el tablero pueda coger el foco.

		// Añadimos los componentes uno a uno, cada uno en su contenedor, y al final el
		// panel principal
		// se agrega al frame principal.
		botonera.add(start);
		botonera.add(pause);
		botonera.add(puntos);
		botonera.add(puntosNum);
	}

	public void setContador() throws InterruptedException {

		contador = 0; // nuestro control de los pasos del tiempo. Cada vez que contador cuenta un
						// paso, pasan 10ms

		while (true) { // por siempre jamas (hasta que nos cierren la ventana) estamos controlando el
			// juego.

			// actualizamos el estado del juego
			if (contador % 20 == 0) { // cada 200ms nos movemos o crecemos...

				if (frame.come() == true) { // si se encuentra encima de la manzana comer es true y la serpiente crece y
											// gana puntos
					frame.tocaCrecer();
					puntosNum.setText(Integer.toString(frame.getSerpiente().getPuntos()));
				}
				if (contador == 60) { // Cada 600ms crecemos y reseteamos el contador
					contador = 0;
				} else { // a los 200 y 400 ms nos movemos...
					contador++;
					frame.tocaMoverse();

				}
				frame.comprobarEstado(tablero.getHeight(), tablero.getWidth()); // comprobamos si hemos muerto o no.

			} else { // Cada vez que no hay que moverse o crecer, simplemente contamos...
				contador++;
			}

			// hemos terminado?? mostramos msg
			if (frame.mostrarFin()) {
				JOptionPane.showMessageDialog(frame,
						"Se acabo vaquero, has conseguido " + puntosNum.getText() + " puntos");
			}

			// Repintamos
			tablero.repaint();

			// Esperamos para dar tiempo al thread de repintado a pintar.
			Thread.sleep((long) nivel.getNivel());

		}
	}
}
