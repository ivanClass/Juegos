package tp.pr5.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;

@SuppressWarnings("serial")
public class VistaGUI extends JFrame {

	private Container contentPane;
	private ControladorGUI controlador;
	private PanelIzquierdo panelIzquierdo;
	private PanelDerecho panelDerecho;
	
	private JMenuBar mb;
	private JMenu menuArchivo;
	private JMenu menuAyuda;
	private JMenuItem ayuda;
	private JMenuItem reglasc4;
	private JMenuItem reglasco;
	private JMenuItem reglasgr;
	private JMenuItem reglasrv;
	private JMenuItem salir;
	private JMenuItem acercade;
	private JMenuItem reiniciar;
	private JMenu submenureglas;

	public VistaGUI(ControladorGUI control) {
		super("TP.PR4");
		this.controlador = control;
		this.initGUI();
		this.setResizable(false);
		this.controlador.start();
	}
	
	/**
	 * Se encarga de establecer 
	 * la configuración gráfica de la ventana
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		this.contentPane.setLayout(new BorderLayout(30, 0));
		this.setMinimumSize(new Dimension(400,500));
		
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/simbolo.gif"));
	    setIconImage(icon);
	    this.configMenu();
	    this.configEventos();
		
		this.setContentPane(this.contentPane);
		
		this.panelIzquierdo = new PanelIzquierdo(this.controlador,this);
		this.contentPane.add(panelIzquierdo, BorderLayout.WEST);	
		
		this.panelDerecho = new PanelDerecho(this.controlador,this);
		this.contentPane.add(panelDerecho, BorderLayout.EAST);
		

	}
	
	/**
	 * Se encarga de configurar el
	 * menú superior de la ventana
	 */
	private void configMenu(){
		this.mb = new JMenuBar();
		this.setJMenuBar(mb);
		
		this.menuArchivo = new JMenu("Archivo");
		this.mb.add(this.menuArchivo);
		
		this.menuAyuda = new JMenu("Ayuda");
		this.mb.add(this.menuAyuda);
		
	    this.submenureglas = new JMenu("Reglas");
	    this.submenureglas.setMnemonic(KeyEvent.VK_R);
	 
	    this.menuAyuda.add(submenureglas);
	        
	    this.reglasc4 = new JMenuItem("Reglas C4");
	    this.submenureglas.add(this.reglasc4);
			
	    this.reglasco = new JMenuItem("Reglas CO");
		this.submenureglas.add(this.reglasco);
			
		this.reglasgr = new JMenuItem("Reglas GR");
		this.submenureglas.add(this.reglasgr);
		
		this.reglasrv = new JMenuItem("Reglas RV");
		this.submenureglas.add(this.reglasrv);
		
		this.ayuda = new JMenuItem("Ayuda");
		this.menuAyuda.add(this.ayuda);
		
		this.reiniciar = new JMenuItem("Reiniciar");
		this.menuArchivo.add(this.reiniciar);
		
		this.menuArchivo.addSeparator();
		
		this.salir = new JMenuItem("Salir");
		this.menuArchivo.add(this.salir);
		
		this.menuAyuda.addSeparator();
		this.acercade = new JMenuItem("Acerca de la app");
		this.menuAyuda.add(this.acercade);		
	}
	
	/**
	 * Se encarga de establecer los oyentes
	 * para los componentes  gráficos necesarios de
	 * la ventana
	 */
	private void configEventos(){
		this.eventoAyuda();
		this.eventoReiniciar();
		this.eventoSalir();
		this.eventoAcerca();
		this.eventoReglasc4();
		this.eventoReglasco();
		this.eventoReglasgr();
		this.eventoReglasrv();
	}
	

	private void eventoReglasc4(){
		this.reglasc4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s;
				s = "Conecta 4 (Tambien conocido como '4 en raya') es un juego en el que el objetivo \n";
				s += "es ser el primero en hacer una linea de cuatro fichas consecutivas. \n \n";
				s += "El objetivo del juego es colocar cuatro fichas consecutivas en una linea \n"; 
				s += "Son validas las lineas horizontales, verticales y diagonales. \n\n";
				
				s += "El juego se desarrolla en un tablero de 6 filas y 7 columnas en posición vertical.\n";
				s += "Los jugadores se turnan para echar sus fichas en las columnas que no esten completas.\n";
				s += "Las fichas ocuparán la posición mas baja de la columna cada vez.\n";
				s += "El jugador gana cuando consigue colocar 4 de sus fichas en linea (Horizontal, vertical o diagonal),\n";
				s += "con lo que acaba el juego. Hay empate si las columnas se llenan de fichas, pero ninguno ha conseguido ganar.";
				JOptionPane.showMessageDialog(null, s, "ReglasC4", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	private void eventoReglasco(){
		this.reglasco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s;
				s = "Complica es un juego en el que el objetivo es ser el primero \n";
				s += "en hacer una linea de cuatro fichas consecutivas. \n \n";
				s += "El objetivo del juego es colocar cuatro fichas consecutivas en una linea \n"; 
				s += "Son validas las lineas horizontales, verticales y diagonales. \n\n";
				
				s += "El juego se desarrolla en un tablero de 7 filas y 4 columnas en posición vertical.\n";
				s += "Los jugadores se turnan para echar sus fichas en las columnas.\n";
				s += "Las fichas ocuparán la posición mas baja de la columna cada vez.\n";
				s += "Si un jugador pone ficha en una columna llena,la columna de fichas caerá una posición\n";
				s += "El jugador gana cuando consigue colocar 4 de sus fichas en linea (Horizontal, vertical o diagonal),\n";
				s += "con lo que acaba el juego. Hay empate si los dos jugadores consiguen 4 en raya al mismo tiempo,por lo que la partida continua.";
				JOptionPane.showMessageDialog(null, s, "ReglasCO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	public void eventoReglasgr(){
		this.reglasgr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s;
				s = "Gravity es un juego en el que el objetivo es ser el primero \n";
				s += "en hacer una linea de cuatro fichas consecutivas. \n \n";
				s += "El objetivo del juego es colocar cuatro fichas consecutivas en una linea \n"; 
				s += "Son validas las lineas horizontales, verticales y diagonales. \n\n";
				
				s += "El juego se desarrolla en un tablero de n filas y m columnas en posición vertical.\n";
				s += "Los jugadores se turnan para colocar sus fichas en el tablero.\n";
				s += "Las fichas se verán atraídas por los bordes del tablero.\n";
				s += "El jugador no puede poner ficha encima de otra que ya haya sido colocada\n";
				s += "El jugador gana cuando consigue colocar 4 de sus fichas en linea (Horizontal, vertical o diagonal),\n";
				s += "con lo que acaba el juego. Hay empate si el tablero se llena de fichas, pero ninguno ha conseguido ganar.";
				JOptionPane.showMessageDialog(null, s, "ReglasGR", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
	}
	
	public void eventoReglasrv(){
		this.reglasrv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s;
				s = "Reversi es un juego que consiste en tener el mayor número " + 
					"de fichas en el tablero \n \n";
				s += "El juego se desarrolla en una tablero de 8 filas y 8 columnas\n\n";
				s += "Los jugadores se turnan para colocar sus fichas en el tablero, comenzando el jugador de negras.\n";
				s += "El movimiento es correcto solamente si flanquea a una o a varias fichas del color opuesto.\n";
				s += "Por flanquear se entiende por el hecho de que al colocar una ficha en un extremo de una hilera de fichas\n";
				s += "en cuyo extremo opuesto hay una ficha del color de la ficha que se coloca no hayan huecos libres. \n";
				s += "Esta hilera puede ser indistintamente vertical, horizontal o diagonal.\n";
				s += "De este modo, las fichas del contrincante quedan encerradas entre una que ya estaba en el tablero y la nueva ficha\n";
				s += "Cada vez que un jugador incorpore una ficha, encerrando a otras del color contrario, se voltearán a todas las fichas\n";
				s += "encerradas, convirtiéndolas en propias \n\n";
				
				s += "Si no fuera posible para un jugador encerrar a ninguna ficha, se pasará el turno automaticamente al contrincante.\n";
				s += "Si ninguno de los dos puede poner, la partida finaliza y gana el que tenga mayor nºfichas en el tablero \n";
				s += "La partida finaliza cuando el tablero está lleno o bien cuando ningun jugador puede poner.\n";
				s += "Tablas si tienen el mismo nº de fichas";
				
				JOptionPane.showMessageDialog(null, s, "ReglasRV", JOptionPane.INFORMATION_MESSAGE);

			}
		});
	}

	
	private void eventoAyuda(){
		this.ayuda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s;

				s = "Los comandos disponibles son:\n\n";

				s += "PONER: pulsar una casilla del tablero para poner\n";
				s += "DESHACER: deshace el último movimiento(10 últimos)  hechos en la partida.\n";
				s += "REINICIAR: reinicia la partida.\n";
				s += "CAMBIAR: cambia el tipo de juego.\n";
				s += "PONER ALEATORIO: coloca una ficha de manera aleatoria. \n";
				s += "SALIR: termina la aplicación.\n";
				s += "AYUDA: muestra esta ayuda.\n";
				
				JOptionPane.showMessageDialog(null, s, "Ayuda", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
	}
	
	private void eventoSalir(){
		this.salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int i = JOptionPane.showConfirmDialog(null, "¿Realmente quieres salir de la aplicación?", "Salir", JOptionPane.YES_NO_OPTION);
				
				if(i == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}
			
		});
	}
	
	private void eventoReiniciar(){
		this.reiniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.reiniciar();		
			}
		});
	}

	
	private void eventoAcerca() {
		this.acercade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String a = "";
				
				a  = "Práctica 5 - Tecnología de la programación - 2ºB" + "\n";
				a += "Universidad Complutense de Madrid" + "\n";
				a += "Iván Aguilera Calle - Daniel García Moreno" + "\n";
				a += "Grado en Ingeniería Informática";
			
				
				JOptionPane.showMessageDialog(null, a, "Acerca de la aplicación", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
	}
	
	

}
