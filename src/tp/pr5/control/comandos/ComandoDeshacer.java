package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoDeshacer extends Comando{

	public ComandoDeshacer(ControladorConsola c) {
		super(c);
	}

	@Override
	/**
	 * Parsea lo introducido por el usuario y devuelve el comando Deshacer
	 * si es deshacer lo que escribió el usuario
	 * 
	 * @param parse Array de string donde solo usa la primera posicion 
	 * porque el comando deshacer solo va a tener una palabra
	 */
	public Comando parseo(String[] parse) {
		if(parse[0].equalsIgnoreCase("deshacer"))
			return new ComandoDeshacer(this.control);
		else
			return null;
	}

	@Override
	public void run() {
		this.control.opcDeshacer();	
	}

}
