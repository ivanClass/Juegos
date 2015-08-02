package tp.pr5.GUI;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

public interface Observer {
	/**
	 * Notifica a las vistas desde la partida que se ha puesto una
	 * ficha en el tablero y actualiza las vistas convenientemente
	 * 
	 * @param tab
	 * @param jugador
	 * @param turno
	 */
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno);
	
	/**
	 * Notifica a las vistas desde la partida que esta ha sido reiniciada
	 * y actualiza las vistas convenientemente
	 * @param tablero
	 * @param turno
	 */
	public void onReiniciar(TableroInmutable tablero, Ficha turno);
	
	/**
	 * Notifica a las vistas desde la partida que esta ha terminado
	 * y actualiza las vistas convenientemente
	 * 
	 * @param tablero
	 * @param ganador
	 * @param turno TODO
	 */
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador, Ficha turno);
	
	/**
	 * Notifica a las vistas desde la partida que se ha cambiado el juego
	 * de la partida y actualiza las vistas convenientemente
	 * @param tablero
	 * @param turno
	 * @param juego
	 */
	public void onCambioJuego(TableroInmutable tablero, Ficha turno,Juegos juego);
	
	/**
	 * Notifica a las vistas desde la partida que se ha realizado
	 * un movimiento incorrecto y actualiza las vistas convenientemente
	 * Utilizado para VistaGUI
	 * @param movimientoException
	 */
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
	
	/**
	 * Notifica a las vistas desde la partida que no se puede deshacer
	 * el último movimiento realizado de la partida y actualiza las vistas 
	 * convenientemente
	 * @param tablero
	 * @param turno
	 */
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno);
	
	/**
	 * Notifica a las vistas desde la partida que se ha deshecho
	 * el último movimiento realizado de la partida y actualiza las vistas 
	 * convenientemente
	 * @param tablero
	 * @param turno
	 * @param hayMas
	 */
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas);
	
	/**
	 * Notifica a las vistas desde la partida que se ha realizado
	 * un movimiento incorrecto y actualiza las vistas convenientemente
	 * Utilizado para vistaConsola
	 * @param tab
	 * @param turno
	 * @param movimientoException
	 */
	public void onMovimientoIncorrecto(TableroInmutable tab, Ficha turno, MovimientoInvalido movimientoException);

	
	/**
	 * Notifica a las vistas desde la partida que va a comenzar el turno 
	 * de un jugador
	 * @param turno
	 * @param hayMas
	 * @param pistas
	 */
	public void onMovimientoStart(Ficha turno,boolean hayMas,TableroInmutable pistas);
	
	public void onPasaTurno(Ficha turno);
}
