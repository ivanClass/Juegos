package tp.pr5.control;

import java.util.InputMismatchException;
import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.TableroInmutable;

public class JugadorHumanoTresRaya implements Jugador {
	private Scanner in;
	private FactoriaTipoJuego fact;
	
	/**
	 * Constructor
	 * @param f 	FactoriaTipoJuego
	 * @param in	Scanner
	 */
	public JugadorHumanoTresRaya(FactoriaTipoJuego f,Scanner in){
		this.in = in;
		this.fact = f;
	}

	/**
	 * Solicita la columna al jugador y crea un movimiento
	 */
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) throws InputMismatchException {
		System.out.print("Introduce la columna: ");
		int col = this.in.nextInt();
		this.in.nextLine();
		return fact.creaMovimiento(col, 0, color);
	}
}
