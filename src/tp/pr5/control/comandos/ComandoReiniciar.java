package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoReiniciar extends Comando{

	public ComandoReiniciar(ControladorConsola c) {
		super(c);
	}

	@Override
	/**
	 * Parsea lo introducido por el usuario y devuelve el comando Reiniciar 
	 * si es reiniciar lo que escribió el usuario
	 * @param parse Array de string donde solo usa 
	 * la primera posicion porque el comando reiniciar solo va a tener una palabra
	 */
	public Comando parseo(String[] parse) {
		if(parse[0].equalsIgnoreCase("reiniciar") && parse.length == 1)
			return new ComandoReiniciar(this.control);
		return null;
	}

	@Override
	public void run() {
		this.control.opcReiniciar();
	}

}
