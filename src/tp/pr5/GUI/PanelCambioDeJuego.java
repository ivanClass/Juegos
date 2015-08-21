package tp.pr5.GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

@SuppressWarnings("serial")
public class PanelCambioDeJuego extends JPanel implements Observer{
	private JTextField textFieldfilas;
	private JTextField textFieldcolum;
	private JComboBox<Juegos> comboBox;
	private JButton btnCambiar;
	private JLabel txtoColumnas;
	private JLabel txtoFilas;
	private JLabel lMov;
	private JTextField tMov;
	
	private JPanel paneldatos;
	
	private ControladorGUI controlador;
	
	private JFrame ventana;
	
	public PanelCambioDeJuego(ControladorGUI control, JFrame mainFrame) {
		this.controlador = control;
		this.ventana = mainFrame;
		this.controlador.addObservador(this);
		
		this.initGUI();
		this.confEventos();
	}
	
	
	/**
	 * Se encarga de establecer 
	 * la configuración gráfica de este panel
	 */
	private void initGUI(){
		this.setBorder(new TitledBorder(null, "Cambio de juego", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setLayout(new GridBagLayout());
		
		this.initComboBox();
		this.initBtonCambiar();
		this.initPanelDatos();
	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * componente comboBox
	 */
	private void initComboBox(){
		this.comboBox = new JComboBox<Juegos>();
		this.comboBox.addItem(Juegos.CONECTA4);
		this.comboBox.addItem(Juegos.COMPLICA);
		this.comboBox.addItem(Juegos.GRAVITY);
		this.comboBox.addItem(Juegos.REVERSI);
		this.comboBox.addItem(Juegos.TRESENRAYA);

		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(this.comboBox, constraints);
	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * panel datos(petición de filas y columnas);
	 */
	private void initPanelDatos(){
		this.paneldatos = new JPanel(new FlowLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(15, 0, 0, 0);
		this.paneldatos.setVisible(false);
		add(this.paneldatos, constraints);


		
		this.txtoFilas = new JLabel("Filas:");
		paneldatos.add(this.txtoFilas);
		
		this.textFieldfilas = new JTextField();
		this.paneldatos.add(textFieldfilas);
		this.textFieldfilas.setColumns(10);
		
		this.txtoColumnas = new JLabel("Columnas:");
		this.paneldatos.add(this.txtoColumnas);
		
		this.textFieldcolum = new JTextField();
		this.paneldatos.add(this.textFieldcolum);
		this.textFieldcolum.setColumns(10);
	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * boton cambiar de este panel
	 */
	private void initBtonCambiar(){
		
		this.lMov = new JLabel("Movimientos:");
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.lMov,constraints);
		
		this.tMov = new JTextField();
		this.tMov.setText("0");
		this.tMov.setHorizontalAlignment(0);
		constraints = new GridBagConstraints();
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.tMov,constraints);
		
		this.btnCambiar = new JButton("Cambiar");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.insets = new Insets(30, 0, 0, 0);
		add(this.btnCambiar,constraints);
		ImageIcon iconoCambiar    = null;
		java.net.URL url_Cambiar = null;
		url_Cambiar = getClass().getResource("imagenes/check.png");
		iconoCambiar = new ImageIcon(url_Cambiar);
		this.btnCambiar.setIcon(iconoCambiar);

	}
	
	/**
	 * Se encarga de establecer los oyentes
	 * para los componentes  gráficos necesarios de
	 * este panel
	 */
	private void confEventos(){
		this.comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	                Object selected = comboBox.getSelectedItem();
	                if(selected == Juegos.GRAVITY){
	                	textFieldfilas.setText("10");
	                	textFieldfilas.setHorizontalAlignment(0);
	                	textFieldcolum.setText("10");
	                	textFieldcolum.setHorizontalAlignment(0);
	                	paneldatos.setVisible(true);
	                }
	                else if(selected == Juegos.TRESENRAYA){
	                	textFieldfilas.setText("6");
	                	textFieldfilas.setHorizontalAlignment(0);
	                	textFieldcolum.setText("7");
	                	textFieldcolum.setHorizontalAlignment(0);
	                	paneldatos.setVisible(true);
	                }
	                else
						paneldatos.setVisible(false);
	                
	                ventana.pack();
                	ventana.setLocationRelativeTo(null);

	                
			}
		});
		
		
		this.btnCambiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int filas = 0, columnas = 0;int max = 0;
				Juegos selected = (Juegos) comboBox.getSelectedItem();
				 try{
	               if(selected == Juegos.GRAVITY || selected == Juegos.TRESENRAYA){
	            	   filas = Integer.parseInt(textFieldfilas.getText());
	            	   columnas= Integer.parseInt(textFieldcolum.getText());
	               }
            	   max = Integer.parseInt(tMov.getText());
	               controlador.cambiarJuego(selected,filas,columnas,max);

	             }catch(NumberFormatException ex){
            		   JOptionPane.showMessageDialog(null, "Datos erróneos...","Error...",JOptionPane.INFORMATION_MESSAGE);
            	 }
			}
		});
	}


	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onReiniciar(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno, final Juegos juego) {
		comboBox.setSelectedItem(juego);
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
