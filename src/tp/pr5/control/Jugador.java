package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroInmutable;

public interface Jugador {
	/**
	 * Devuelve el Movimiento de un jugador
	 * del tipo de juego que estemos usando
	 * 
	 * @param tab	Tablero
	 * @param color	color del jugador
	 * @return	Movimiento
	 */
	public Movimiento getMovimiento(TableroInmutable tab,Ficha color);
}
