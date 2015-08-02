package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroInmutable;

public class JugadorAleatorioComplica implements Jugador {
	private FactoriaTipoJuego fact;

	/**
	 * Constructor
	 * @param f FactoriaTipoJuego
	 */
	public JugadorAleatorioComplica(FactoriaTipoJuego f){
		this.fact = f;
	}
	
	/**
	 * Devuelve un movimiento generado aleatoriamente
	 * (dentro de los limites del tablero)
	 */
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		int colAleatorio = (int)(Math.random()*tab.getAncho() + 1);
		
		return fact.creaMovimiento(colAleatorio, 0, color);
	}	
}