package tp.pr5.GUI;

import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class PanelTablero extends JPanel {
	private CasillaGUI [][] casillas;
	private ControladorGUI control;
	private TableroInmutable tableromodelo;
	
	public PanelTablero(ControladorGUI controlador,TableroInmutable tab){
		this.casillas = new CasillaGUI[tab.getAlto()][tab.getAncho()];
		this.tableromodelo = tab;
		this.control = controlador;
		
		initGui();
		confEventos();
	}
	
	/**
	 * Se encarga de establecer los oyentes
	 * para los componentes  gráficos necesarios de
	 * este panel
	 */
	private void confEventos() {
		for (int i = 0; i < this.tableromodelo.getAlto(); i++) {
			for (int j = 0; j < this.tableromodelo.getAncho(); j++) {
				this.casillas[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						CasillaGUI boton = (CasillaGUI)evt.getSource();
						int fila = boton.getFila() ;
						int col = boton.getCol();
						
						control.poner(fila,col);
					}
				});
			}
		}
	}
	
	/**
	 * Se encarga de establecer 
	 * la configuración gráfica de este panel
	 */
	private void initGui() {
		setLayout(new GridBagLayout());
		this.casillas =  new CasillaGUI[this.tableromodelo.getAlto()][this.tableromodelo.getAncho()];
		GridBagConstraints constraints = new GridBagConstraints();

		for (int i = 0; i < this.tableromodelo.getAlto(); i++) {
			for (int j = 0; j < this.tableromodelo.getAncho(); j++) {
				casillas[i][j] = new CasillaGUI(i+1,j+1,Ficha.VACIA);
				
				constraints.gridx = j; 
				constraints.gridy = i;
				constraints.gridwidth = 1;
				constraints.gridheight = 1;
				constraints.weightx = 0.1;
				constraints.weighty = 0.1;

				casillas[i][j].setBackground(Ficha.VACIA.getColor());
				add(casillas[i][j],constraints);
			}
		}	
	}
	
	/**
	 * Método que visualiza el tablero
	 * @param tablero
	 */
	public void paintTablero(TableroInmutable tablero){
		for (int i = 0; i < tablero.getAlto(); i++) {
			for (int j = 0; j < tablero.getAncho(); j++) {
				if(tablero.getCasilla(j+1, i+1) != casillas[i][j].getColor()){
					Ficha color = tablero.getCasilla(j+1, i+1);
					casillas[i][j].setBackground(color.getColor());
					casillas[i][j].setColor(color);
				}
			}
			
		}
	}
	
	/**
	 * Método que desactiva los botones del tablero
	 */
	public void desactivarBotones(){
		for (int i = 0; i < tableromodelo.getAlto(); i++) {
			for (int j = 0; j < tableromodelo.getAncho(); j++) {
				casillas[i][j].setEnabled(false);
			}
			
		}
	}
	
	/**
	 * Método que activa los botones
	 */
	public void activarBotones(){
		for (int i = 0; i < tableromodelo.getAlto(); i++) {
			for (int j = 0; j < tableromodelo.getAncho(); j++) {
				casillas[i][j].setEnabled(true);
			}
			
		}
	}

}