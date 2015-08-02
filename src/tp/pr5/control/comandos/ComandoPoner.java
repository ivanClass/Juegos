package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoPoner extends Comando{

	public ComandoPoner(ControladorConsola c) {
		super(c);
	}

	@Override
	/**
	 * Parsea lo introducido por el usuario y devuelve el comando Poner si es 
	 * poner lo que escribió el usuario
	 * 
	 * @param parse Array de string donde solo usa 
	 * la primera posicion porque el comando poner llama a un método que analiza el resto
	 * del array
	 */
	public Comando parseo(String[] parse) {
		if(parse[0].equalsIgnoreCase("poner") && parse.length == 1){
			return new ComandoPoner(this.control);
		}
		else
			return null;
	}

	@Override
	public void run() {
		this.control.opcPoner();
	}

}
