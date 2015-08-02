package tp.pr5.logica;

public abstract class Movimiento {
	private Ficha color;
	
	/**
	 * Constructor sin parámetros
	 */
	public Movimiento(){
		
	}
	
	/**
	 * Constructor con parámetros
	 * @param color		Ficha
	 */
	public Movimiento(Ficha color){
		this.color = color;
	}
	
	/**
	 * @return 	Ficha -	color del Movimiento
	 */
	public Ficha getJugador(){
		return this.color;
	};
	
	/**
	 * Ejecuta un movimiento sobre el tablero.
	 * Si no es posible ejecutar este movimiento se lanza
	 * una excepción
	 * 
	 * @param tab	Tablero
	 * @throws MovimientoInvalido
	 */
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	/**
	 * Deshace un movimiento sobre el tablero
	 * @param tab	Tablero
	 */
	public abstract void undo(Tablero tab);

}
