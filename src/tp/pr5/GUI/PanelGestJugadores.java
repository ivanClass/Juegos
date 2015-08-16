package tp.pr5.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoTurno;

@SuppressWarnings("serial")
public class PanelGestJugadores extends JPanel implements Observer {
	private JLabel jugBlancas;
	private JLabel jugNegras;
	private JComboBox<TipoTurno> comboBlancas;
	private JComboBox<TipoTurno> comboNegras;
	
	private ControladorGUI control;
	@SuppressWarnings("unused")
	private JFrame ventana;
	
	public PanelGestJugadores(ControladorGUI control,JFrame ventPrinc){
		this.control = control;
		this.ventana = ventPrinc;
		this.control.addObservador(this);
		
		this.initGUI();
		this.confEventos();
	}
	
	/**
	 * Se encarga de establecer la configuración
	 * gráfica de este panel
	 */
	private void initGUI() {
		this.setBorder(new TitledBorder(null, "Gestión de jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setLayout(new GridBagLayout());
		this.initLabelBlancas();
		this.initComboBlancas();
		this.initLabelNegras();
		this.initComboNegras();
	}
	
	private void initLabelBlancas(){
		
		this.jugBlancas = new JLabel("Jugador de blancas");
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.jugBlancas, constraints);
	}
	
	private void initComboBlancas(){
		this.comboBlancas = new JComboBox<TipoTurno>();
		this.comboBlancas.addItem(TipoTurno.HUMANO);
		this.comboBlancas.addItem(TipoTurno.AUTOMATICO);
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 3; 
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.comboBlancas,constraints);
		
	}
	
	private void initLabelNegras(){
		
		this.jugNegras = new JLabel("Jugador de negras");
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.jugNegras, constraints);
	}
	
	private void initComboNegras(){
		this.comboNegras = new JComboBox<TipoTurno>();
		this.comboNegras.addItem(TipoTurno.HUMANO);
		this.comboNegras.addItem(TipoTurno.AUTOMATICO);
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 3; 
		constraints.gridy = 1;
		constraints.gridwidth = 5;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.comboNegras,constraints);
	}
	
	/**
	 * Se encarga de establecer los oyentes
	 * para los componentes  gráficos necesarios de
	 * este panel
	 */
	private void confEventos() {
		
		this.comboBlancas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoTurno tTurno = (TipoTurno) comboBlancas.getSelectedItem();
				control.cambiarJugador(Ficha.BLANCA, tTurno);				
			}
		});

		
		this.comboNegras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoTurno tTurno = (TipoTurno) comboNegras.getSelectedItem();
				control.cambiarJugador(Ficha.NEGRA, tTurno);				
			}
		});
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReiniciar(TableroInmutable tablero, final Ficha turno) {
		comboBlancas.setSelectedItem(turno.getTipoTurno());
		comboNegras.setSelectedItem(turno.getTipoTurno());				
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, final Ficha turno, Juegos juego) {
		comboBlancas.setSelectedItem(turno.getTipoTurno());
		comboNegras.setSelectedItem(turno.getTipoTurno());				
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable tab, Ficha turno,
			MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoStart(Ficha turno,boolean hayMasDeshacer,boolean hayMasRehacer, TableroInmutable pistas){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPasaTurno(Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReDo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReDoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}
	
}
