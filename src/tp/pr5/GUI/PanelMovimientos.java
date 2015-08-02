package tp.pr5.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

public class PanelMovimientos extends JPanel implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControladorGUI controlador;
	
	private JLabel lBlancas;
	private JLabel lNegras;
	
	private JTextField tBlancas;
	private JTextField tNegras;
	
	public PanelMovimientos(ControladorGUI control){
		this.controlador = control;
		this.controlador.addObservador(this);
		initGUI();	
	}
	
	private void initGUI(){
		this.setBorder(new TitledBorder(null, "Movimientos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setLayout(new GridBagLayout());
		this.initlBlancas();
		this.initlNegras();
		this.inittBlancas();
		this.inittNegras();
	}
	
	private void initlBlancas(){
		this.lBlancas = new JLabel("Blancas");
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(this.lBlancas, constraints);
	}
	
	private void initlNegras(){
		this.lNegras = new JLabel("Negras");
	
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 4; 
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(this.lNegras, constraints);
	}
	
	private void inittBlancas(){
		this.tBlancas = new JTextField();
		this.tBlancas.setEditable(false);
		this.tBlancas.setText("0");
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.tBlancas, constraints);
	}
	
	private void inittNegras(){
		this.tNegras = new JTextField();
		this.tNegras.setText("0");
		this.tNegras.setEditable(false);
		GridBagConstraints constraints =  new GridBagConstraints();
		constraints.gridx = 4; 
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.tNegras, constraints);
	}
	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		Integer k = jugador.getNumMovimientos();
		if(jugador == Ficha.BLANCA){
			this.tBlancas.setText(k.toString());
		}
		else{
			this.tNegras.setText(k.toString());
		}
		
	}

	@Override
	public void onReiniciar(TableroInmutable tablero, Ficha turno) {
		Integer k = turno.getNumMovimientos();
		this.tBlancas.setText(k.toString());
		this.tNegras.setText(k.toString());		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador, Ficha turno) {
		Integer k = turno.getNumMovimientos();
		if(turno == Ficha.BLANCA){
			this.tBlancas.setText(k.toString());
		}
		else{
			this.tNegras.setText(k.toString());
		}
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno,
			Juegos juego) {
		Integer k = turno.getNumMovimientos();
		this.tBlancas.setText(k.toString());
		this.tNegras.setText(k.toString());

		
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
		Integer k = turno.getNumMovimientos();
		if(turno == Ficha.BLANCA){
			this.tBlancas.setText(k.toString());
		}
		else{
			this.tNegras.setText(k.toString());
		}
		
	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable tab, Ficha turno,
			MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoStart(Ficha turno, boolean hayMas,
			TableroInmutable pistas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPasaTurno(Ficha turno) {
		// TODO Auto-generated method stub
		
	}

}
