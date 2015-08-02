package tp.pr5.logica;

public interface ReglasJuego {
	/**
	 * Comprueba si hay ganador segun las reglas
	 * @param ultimoMovimiento	Movimiento
	 * @param t	Tablero
	 * @return Ficha	Color del ganador,si no hay Ficha.VACIA
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento,Tablero t);
	
	/**
	 * Inicia un tablero vacío
	 * @return Tablero
	 */
	public Tablero iniciaTablero();
	
	/**
	 * Devuelve color del jugador que empieza
	 * la partida según las reglas
	 * @return Ficha
	 */
	public Ficha jugadorInicial();
	
	/**
	 * Devuelve el color del siguiente jugador
	 * al que le toca poner
	 * @param ultimoEnPoner Ficha
	 * @param t	Tablero
	 * @return Ficha
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner,Tablero t);
	
	/**
	 * Comprueba si hay tablas en el tablero segun las reglas
	 * @param ultimoEnPoner	Ficha
	 * @param t	Tablero
	 * @return true si hay tablas según las reglas
	 */
	public boolean tablas(Ficha ultimoEnPoner,Tablero t);
	
	public Juegos getJuego();
	
	public boolean ningunoPuedePoner();
}
