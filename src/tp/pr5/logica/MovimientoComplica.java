package tp.pr5.logica;

public class MovimientoComplica extends Movimiento {
	private int col;
	private int fil;
	private Ficha fichaCae;
	
	/**
	 * Constructor
	 * @param donde columna del movimiento
	 * @param color	Ficha
	 */
	public MovimientoComplica(int donde,Ficha color){
		super(color);
		this.col = donde;
	}
	
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if(this.col >= 1 && this.col <= tab.getAncho()){
			this.fil = Utilidades.posVacia(this.col,tab);
			
			if(this.fil != -1){
				tab.setCasilla(this.col, this.fil, getJugador());	
			}
			else{
				this.fichaCae = tab.getCasilla(this.col, tab.getAlto());
				desplazarFichasAbajo(tab);
				tab.setCasilla(this.col, 1, getJugador());
			}	
		}
		else{
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + Integer.toString(tab.getAncho())+ ".");
		}
	}
	
	@Override
	public void undo(Tablero tab) {
		
		if(this.fil == -1){
			desplazarFichasArriba(tab);
			tab.setCasilla(this.col, tab.getAlto(), this.fichaCae);
		}
		else
			tab.setCasilla(this.col, this.fil, Ficha.VACIA);
	}
	
	/**
	 * Desplaza las fichas de una columna(indicada por el movimiento)
	 * del tablero hacia abajo
	 * @param tab Tablero
	 */
	private void desplazarFichasAbajo(Tablero tab){
		for (int i = tab.getAlto(); i > 1; i--) {
			tab.setCasilla(this.col, i, tab.getCasilla(this.col, i-1));
		}
	}
	
	/**
	 * Desplaza las fichas de una columna(indicada por el movimiento)
	 * del tablero hacia arriba
	 * @param tablero Tablero
	 */
	private void desplazarFichasArriba(Tablero tablero){
		for(int i =  1; i < tablero.getAlto();i++){
			tablero.setCasilla(this.col, i,tablero.getCasilla(this.col, i+1));
		}
	}

	@Override
	public void redo(Tablero tab) {
		if(this.fil != -1){
			tab.setCasilla(this.col, this.fil, getJugador());
		}
		else{
			this.fichaCae = tab.getCasilla(this.col, tab.getAlto());
			desplazarFichasAbajo(tab);
			tab.setCasilla(this.col, 1, getJugador());
		}	
		
	}
}
