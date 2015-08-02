package tp.pr5.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoTurno;

@SuppressWarnings("serial")
public class PanelIzquierdo extends JPanel implements Observer {
	
	private ControladorGUI controlador;
	private PanelTablero panelTablero;
	private JButton btonAleatorio;
	private JFrame ventana;
	private JTextField infoTurno;
	private PanelMovimientos panelMovimientos;

	public PanelIzquierdo(ControladorGUI control, JFrame mainFrame){
		this.controlador =  control;
		this.ventana = mainFrame;
		this.controlador.addObservador(this);
		
		initGUI();
		confEventos();
		
	}
	
	/**
	 * Se encarga de establecer 
	 * la configuración gráfica de este panel
	 */
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		this.initPanelTablero();
		this.initPanelMovimientos();
		this.initTextField();
		this.initBtonAleatorio();

	}
	
	private void initPanelMovimientos(){
		this.panelMovimientos = new PanelMovimientos(this.controlador);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 5;
		constraints.gridwidth = 5;
		constraints.gridheight = 2;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 0, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(this.panelMovimientos, constraints);
	}
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * panel tablero en este panel
	 */
	private void initPanelTablero(){
		this.panelTablero = new PanelTablero(this.controlador,new Tablero(1, 1)); //INICIALIZACIÓN
		this.panelTablero.setBorder( BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.gridheight = 5;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 0, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(panelTablero, constraints);

	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * componente textfield en el que
	 * se muestra la informacion del turno,ganador...
	 * en este panel
	 */
	private void initTextField(){
		this.infoTurno = new JTextField();
		this.infoTurno.setBorder( BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 7;
		constraints.gridwidth = 5;
		constraints.gridheight = 2;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.insets = new Insets(20, 0, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.infoTurno.setFont(new Font("Comic Sans MS",Font.BOLD,16));
		this.infoTurno.setHorizontalAlignment(JTextField.CENTER);
		this.infoTurno.setEditable(false);
		
		this.add(this.infoTurno, constraints);
	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * componente botón aleatorio
	 */
	private void initBtonAleatorio(){
		this.btonAleatorio = new JButton("Poner aleatorio");
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 9;
		constraints.gridwidth = 5;
		constraints.gridheight = 3;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.CENTER;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(20, 0, 20, 0);

		this.add(btonAleatorio, constraints);
		
		ImageIcon iconoPonerAleatorio  = null;
		java.net.URL url_aleatorio = null;
		url_aleatorio = getClass().getResource("imagenes/random.png");
		iconoPonerAleatorio = new ImageIcon(url_aleatorio);
		this.btonAleatorio.setIcon(iconoPonerAleatorio);

	}
	
	/**
	 * Se encarga de establecer los oyentes
	 * para los componentes  gráficos necesarios de
	 * este panel
	 */
	private void confEventos(){
		this.btonAleatorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.ponerAleatorio();
				
			}
		});
	}

	@Override
	public void onMovimientoEnd(final TableroInmutable tab, Ficha jugador,final Ficha turno) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				panelTablero.paintTablero(tab);
				infoTurno.setText("Juegan " + turno.getNombre());				
			}
		});

	}

	@Override
	public void onReiniciar(final TableroInmutable tablero, final Ficha turno) {
		panelTablero.paintTablero(tablero);
		infoTurno.setText("Juegan " + turno.getNombre());
		btonAleatorio.setEnabled(true);
		ventana.setLocationRelativeTo(null);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador, Ficha turno) {
		String ganaotablas = "";
		if(ganador == Ficha.VACIA){
			ganaotablas = "Tablas." ;
		}
		else{
			ganaotablas = "Ganan las " + ganador.getNombre();
		}
		this.panelTablero.paintTablero(tablero);
		this.panelTablero.desactivarBotones();
		this.infoTurno.setText(ganaotablas);
		this.btonAleatorio.setEnabled(false);		
	}

	@Override
	public void onCambioJuego(final TableroInmutable tablero, final Ficha turno, Juegos juego) {
		remove(panelTablero);
		panelTablero = new PanelTablero(controlador,tablero);
		panelTablero.setBorder( BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));

		add(panelTablero);
		btonAleatorio.setEnabled(true);
		panelTablero.paintTablero(tablero);
		infoTurno.setText("Juegan " + turno.getNombre());

		revalidate();
		ventana.pack();
		ventana.setLocationRelativeTo(null);				
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		JOptionPane.showMessageDialog(null, movimientoException.getMessage(),"Error...",JOptionPane.WARNING_MESSAGE);		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		JOptionPane.showMessageDialog(null, "No se puede deshacer","Error...",JOptionPane.INFORMATION_MESSAGE);				
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		this.panelTablero.paintTablero(tablero);
		this.infoTurno.setText("Juegan " + turno.getNombre());
		
	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable tab, Ficha turno,MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoStart(final Ficha turno,boolean hayMas,final TableroInmutable pistas){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if(turno.getTipoTurno() == TipoTurno.HUMANO){
					if(pistas != null){
						panelTablero.paintTablero(pistas);
					}
					panelTablero.activarBotones();
					btonAleatorio.setEnabled(true);
				}
				else{
					panelTablero.desactivarBotones();
					btonAleatorio.setEnabled(false);
				}				
			}
		});
	
	}

	@Override
	public void onPasaTurno(Ficha turno) {
		infoTurno.setText("Juegan " + turno.getNombre());		
	}
	

}
