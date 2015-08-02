package tp.pr5.logica;

public interface TableroInmutable {
	
	/**
	 * Devuelve el alto del tablero(número de filas)
	 * @return
	 */
	int getAlto();
	
	/**
	 * Devuelve el ancho del tablero(números de columnas)
	 * 
	 * @return
	 */
	int getAncho();
	
	/**
	 * Devuelve la casilla que hay en (col,fila)
	 * @param col
	 * @param fila
	 * @return
	 */
	Ficha getCasilla(int col,int fila);
}
