package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoJugar extends Comando{

	private String[] frase;
	
	public ComandoJugar(ControladorConsola c, String[] frase) {
		super(c);
		this.frase = frase;
	}
	

	@Override
	/**
	 * Parsea lo introducido por el usuario y devuelve el comando Jugar 
	 * si es jugar lo que escribió el usuario
	 * 
	 * @param parse Array de string donde solo usa la primera posicion 
	 * porque el comando jugar llama a un método que analiza el resto del array
	 */
	public Comando parseo(String[] parse) {
		if(parse[0].equalsIgnoreCase("jugar")){
			return new ComandoJugar(this.control, parse);
		}
		else
			return null;
	}

	@Override
	public void run() {
		this.control.opcCambiarJuego(this.frase);
	}

}
