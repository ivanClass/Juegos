package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.Utilidades;

public class JugadorAleatorioReversi implements Jugador{
	private FactoriaTipoJuego fact;
	
	/**
	 * Constructor
	 * @param f FactoriaTipoJuego
	 */
	public JugadorAleatorioReversi(FactoriaTipoJuego f){
		this.fact = f;
	}

	/**
	 * Devuelve un movimiento generado aleatoriamente,
	 * movimiento correcto en el reversi
	 */
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		boolean correcto = false;
		int colAleatorio = 1;
		int filAleatorio = 1;
		
		while(!correcto){
			colAleatorio = (int)(Math.random()*tab.getAncho() + 1);
			filAleatorio = (int)(Math.random()*tab.getAlto() + 1);
			
			if(Utilidades.esCorrectoReversi(colAleatorio, filAleatorio, color, tab))
				correcto = true;
		}
		 
		return fact.creaMovimiento(colAleatorio, filAleatorio, color);
	}
}
