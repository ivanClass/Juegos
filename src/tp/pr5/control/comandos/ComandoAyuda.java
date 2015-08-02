package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Partida;

public class ComandoAyuda extends Comando{

	private Partida partida;
	
	public ComandoAyuda(ControladorConsola c, Partida p) {
		super(c);
		this.partida = p;
	}

	@Override
	/**
	 * Parsea lo introducido por el usuario y devuelve el comando Ayuda 
	 * si es ayuda lo que escribio el usuario
	 * 
	 * @param parse Array de string donde solo usa la primera 
	 * posicion porque el comando ayuda solo va a tener una palabra
	 */
	public Comando parseo(String[] parse) {
		
		
		if(parse[0].equalsIgnoreCase("ayuda")){
			return new ComandoAyuda(this.getControlador(), this.partida);
		}
		else
			return null;
		
	}

	@Override
	public void run() {
		String s;

		s = "Los comandos disponibles son:\n\n";

		s += "PONER: utilízalo para poner la siguiente ficha.\n";
		s += "DESHACER: deshace el último movimiento hecho en la partida.\n";
		s += "REINICIAR: reinicia la partida.\n";
		s += "JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.\n";
		s += "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n";
		s += "SALIR: termina la aplicación.\n";
		s += "AYUDA: muestra esta ayuda.\n";
		
		
		System.out.println(s);
		this.partida.refrescar();
		
	}

}
