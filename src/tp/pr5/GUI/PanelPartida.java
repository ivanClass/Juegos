package tp.pr5.GUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoTurno;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelPartida extends JPanel implements Observer {
	
	private JButton btnDeshacer;
	private JButton btnReiniciar;
	private JButton btnPasa;
	
	private ControladorGUI controlador;
	private JButton btnRehacer;

	public PanelPartida(ControladorGUI control) {
		super();
		this.controlador = control;
		this.controlador.addObservador(this);
		initGUI();
		confEventos();
		
	}
		
	/**
	 * Se encarga de establecer los oyentes
	 * para los componentes  gráficos necesarios de
	 * este panel
	 */
	private void confEventos() {
		this.btnDeshacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.deshacer();
			}
		});
			
		this.btnReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.reiniciar();
			}
		});
		
		this.btnPasa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.pasaTurno();
			}
		});
		
		this.btnRehacer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.rehacer();	
			}
		});
	}

	/**
	 * Se encarga de establecer 
	 * la configuración gráfica de este panel
	 */
	private void initGUI() {
		setBorder(new TitledBorder(null, "Partida", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setLayout(new GridBagLayout());
		
		this.initBtnDeshacer();
		this.initBtnReiniciar();
		this.initBtnPasa();
		this.initBtnRehacer();

	}
	
	private void initBtnRehacer(){
		GridBagConstraints constraints =  new GridBagConstraints();
		this.btnRehacer =  new JButton("Rehacer");
		constraints.gridx = 0; 
		constraints.gridy = 4;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(btnRehacer,constraints);
		
		ImageIcon iconoRehacer  = null;
		java.net.URL url_Rehacer = null;
		url_Rehacer = getClass().getResource("imagenes/redo13.png");
		iconoRehacer = new ImageIcon(url_Rehacer);
			
		this.btnRehacer.setIcon(iconoRehacer);
	}
	private void initBtnPasa(){
		GridBagConstraints constraints = new GridBagConstraints();
		this.btnPasa = new JButton("Pasar Turno");
		constraints.gridx = 4; 
		constraints.gridy = 4;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(btnPasa,constraints);
		
		ImageIcon iconoPasa    = null;
		java.net.URL url_Pasar = null;
		url_Pasar = getClass().getResource("imagenes/swing1.png");
		iconoPasa = new ImageIcon(url_Pasar);
		this.btnPasa.setIcon(iconoPasa);
	}
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * componente botón deshacer
	 */
	private void initBtnDeshacer(){
		GridBagConstraints constraints = new GridBagConstraints();
		this.btnDeshacer = new JButton("Deshacer");
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(btnDeshacer,constraints);
		
		ImageIcon iconoDeshacer   = null;
		java.net.URL url_Deshacer = null;
		url_Deshacer = getClass().getResource("imagenes/undo.png");
		iconoDeshacer = new ImageIcon(url_Deshacer);
		this.btnDeshacer.setIcon(iconoDeshacer);

	}
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * componente botón reiniciar
	 */
	private void initBtnReiniciar(){
		GridBagConstraints constraints = new GridBagConstraints();
		btnReiniciar = new JButton("Reiniciar");
		
		constraints.gridx = 4; 
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(btnReiniciar,constraints);
			
		ImageIcon iconoReiniciar  = null;
		java.net.URL url_Reinciar = null;
		url_Reinciar = getClass().getResource("imagenes/reset.png");
		iconoReiniciar = new ImageIcon(url_Reinciar);
			
		this.btnReiniciar.setIcon(iconoReiniciar);
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador,Ficha turno) {
			btnDeshacer.setEnabled(true);
	}

	@Override
	public void onReiniciar(TableroInmutable tablero, Ficha turno) {
		btnDeshacer.setEnabled(false);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero,Ficha ganador, Ficha turno) {
		this.btnDeshacer.setEnabled(false);
		this.btnPasa.setEnabled(false);
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno, Juegos juego) {
		btnDeshacer.setEnabled(false);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
			// TODO Auto-generated method stub		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		if(!hayMas){
			this.btnDeshacer.setEnabled(false);
		}
		else
			this.btnDeshacer.setEnabled(true);

	}
	
	@Override
	public void onMovimientoIncorrecto(TableroInmutable tab,Ficha turno, MovimientoInvalido movimientoException) {
			// TODO Auto-generated method stub
			
	}

	@Override
	public void onMovimientoStart(Ficha turno,boolean hayMasDeshacer,boolean hayMasRehacer, TableroInmutable pistas){

		if(turno.getTipoTurno() == TipoTurno.AUTOMATICO){
			btnDeshacer.setEnabled(false);
			btnRehacer.setEnabled(false);
			btnPasa.setEnabled(false);
		}
		else{
			if(hayMasDeshacer){
				btnDeshacer.setEnabled(true);
			}
			else{
				btnDeshacer.setEnabled(false);
			}
			
			if(hayMasRehacer){
				btnRehacer.setEnabled(true);
			}
			else{
				btnRehacer.setEnabled(false);
			}
			
			btnPasa.setEnabled(true);

		}
	}

	@Override
	public void onPasaTurno(Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReDo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		if(!hayMas){
			this.btnRehacer.setEnabled(false);
		}
		else
			this.btnRehacer.setEnabled(true);		
	}

	@Override
	public void onReDoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

}
