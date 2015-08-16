package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.Utilidades;

public class JugadorAleatorioTresRaya implements Jugador {
	private FactoriaTipoJuego fact;
	
	/**
	 * Constructor
	 * @param f FactoriaTipoJuego
	 */
	public JugadorAleatorioTresRaya(FactoriaTipoJuego f){
		this.fact = f;
	}
	
	/**
	 * Devuelve un movimiento generado aleatoriamente,
	 * (no columna llena y dentro de los limites del tablero)
	 */
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		boolean correcto = false;
		int colAleatorio = 1;
		
		while(!correcto){
			colAleatorio = (int)(Math.random()*tab.getAncho() + 1);
			if(Utilidades.posVacia(colAleatorio, tab) != -1)
				correcto = true;
		}
				
		return fact.creaMovimiento(colAleatorio, 0, color);	
	}
}
