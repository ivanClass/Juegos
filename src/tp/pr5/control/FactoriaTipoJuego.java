package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.ReglasJuego;

public interface FactoriaTipoJuego {

	/**
	 * Crea las reglas necesarias segun al tipo
	 * de juego que estemos usando
	 * @return ReglasJuego
	 */
	public ReglasJuego creaReglas();
		
	/**
	 * Crea un movimiento del tipo del juego
	 * que estemos usando
	 * @param col		columna
	 * @param fila		fila
	 * @param color		color
	 * @return Movimiento
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	/**
	 * Crea un jugador humano del tipo del
	 * juego que estemos usando
	 * @param in	Scanner in
	 * @return Jugador
	 */
	public Jugador creaJugadorHumanoConsola(Scanner in);
	
	/**
	 * Crea un jugador aleatorio del tipo del
	 * juego que estemos usando
	 * @return Jugador
	 */
	public Jugador creaJugadorAleatorio();
}
