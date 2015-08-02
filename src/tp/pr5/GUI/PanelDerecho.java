package tp.pr5.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;


@SuppressWarnings("serial")
public class PanelDerecho extends JPanel {
	private ControladorGUI controlador;
	private PanelPartida panelPartida;
	private PanelGestJugadores panelGest;
	private PanelCambioDeJuego panelCambioDeJuego;
	private JButton btnSalir;
	
	private JFrame ventana;
	
	public PanelDerecho(ControladorGUI control,JFrame mainFrame){
		this.controlador = control;
		this.ventana = mainFrame;
		this.initGUI();
		this.confEventos();
	}
	
	/**
	 * Se encarga de establecer 
	 * la configuración gráfica de este panel
	 */
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		this.initPanelPartida();
		this.initPanelGest();
		this.initPanelCambioJuego();
		this.initBtnSalir();
	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * panel partida en este panel
	 */
	private void initPanelPartida(){
		GridBagConstraints constraints = new GridBagConstraints();
		this.panelPartida = new PanelPartida(this.controlador);
		constraints.insets = new Insets(10,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(panelPartida, constraints);
	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * panel cambioJuego en este panel
	 */
	private void initPanelCambioJuego(){ //ESTO VA A SER PANEL GESTION
		GridBagConstraints constraints = new GridBagConstraints();
		this.panelCambioDeJuego = new PanelCambioDeJuego(this.controlador,this.ventana);
		constraints.gridx = 0; 
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(panelCambioDeJuego, constraints);

	}
	
	/**
	 * Se encarga de establecer la configuración
	 * gráfica del panel gestión de jugadores en
	 * este panel
	 */
	private void initPanelGest(){
		GridBagConstraints constraints = new GridBagConstraints();
		this.panelGest = new PanelGestJugadores(this.controlador,this.ventana);
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(this.panelGest, constraints);
	}
	
	/**
	 * Se encarga de establecer
	 * la configuración gráfica del
	 * boton salir de este panel
	 */
	private void initBtnSalir(){
		GridBagConstraints constraints = new GridBagConstraints();
		this.btnSalir = new JButton("Salir");
		constraints.gridx = 0; 
		constraints.gridy = 8;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		constraints.weightx = 0.1;
		constraints.weighty	= 0.1;
		constraints.insets = new Insets(20, 0, 20, 0);
		constraints.fill = GridBagConstraints.CENTER;
		constraints.anchor = GridBagConstraints.SOUTH;

		this.add(btnSalir, constraints);
		
		ImageIcon iconoSalir = null;
		java.net.URL url_salir = null;
		url_salir = getClass().getResource("imagenes/exit.png");
		iconoSalir = new ImageIcon(url_salir);
		
		this.btnSalir.setIcon(iconoSalir);
	}
	
	/**
	 * Se encarga de establecer los oyentes
	 * para los componentes  gráficos necesarios de
	 * este panel
	 */
	private void confEventos() {
		this.btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "¿Realmente quieres salir de la aplicación?",
						"Salir", JOptionPane.YES_NO_OPTION);
				
				if(i == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}
		});
		
	}
}
