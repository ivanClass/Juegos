package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;;

public abstract class Comando {
	
	protected ControladorConsola control;
	
	public Comando(ControladorConsola c) {
		this.control = c;
	}
	
	
	public ControladorConsola getControlador(){
		return this.control;
	}
	
	/**
	 * Parsea el comando y comprueba si el comando escrito es el comando en cuestión
	 */
	public abstract Comando parseo (String[] parse);
	
	/**
	 * Ejecuta la operación correspondiente al comando solicitado
	 */
	public abstract void run();
	
}
