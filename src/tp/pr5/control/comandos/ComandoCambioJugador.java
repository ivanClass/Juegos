package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Partida;

public class ComandoCambioJugador extends Comando {

	private String[] frase;
	private Partida partida;
	
	public ComandoCambioJugador(ControladorConsola c, String[] frase, Partida p) {
		super(c);
		this.frase = frase;
		this.partida = p;
	}
	

	@Override
	/**
	 * Parsea lo introducido por el usuario y devuelve el comando CambioJugador 
	 * si es ayuda lo que escribio el usuario
	 * 
	 * @param frase Array de string donde solo usa 
	 * la primera posicion porque el comando Jugador llama a un metodo que 
	 * analiza el resto del array
	 */
	public Comando parseo(String[] frase) {
		if(frase[0].equalsIgnoreCase("jugador")){
			return new ComandoCambioJugador(this.control, frase, this.partida);
		}
		else
			return null;
	}
	
	@Override
	public void run() {
		this.control.opcCambiarJugador(this.frase);
		this.partida.refrescar();
	}

}
