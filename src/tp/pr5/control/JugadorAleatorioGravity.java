package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroInmutable;

public class JugadorAleatorioGravity implements Jugador {
	private FactoriaTipoJuego fact;
	
	/**
	 * Constructor
	 * @param f FactoriaTipoJuego
	 */
	public JugadorAleatorioGravity(FactoriaTipoJuego f){
		this.fact = f;
	}
	
	/**
	 * Devuelve un movimiento generado aleatoriamente,
	 * (no encima de casilla ocupada, y dentro de los límites
	 * del tablero)
	 */
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		boolean correcto = false;
		int colAleatorio = 1;
		int filAleatorio = 1;
		
		while(!correcto){
			colAleatorio = (int)(Math.random()*tab.getAncho() + 1);
			filAleatorio = (int)(Math.random()*tab.getAlto() + 1);
			
			if(tab.getCasilla(colAleatorio, filAleatorio) == Ficha.VACIA)
				correcto = true;
		}
		 
		return fact.creaMovimiento(colAleatorio, filAleatorio, color);
	}
}
