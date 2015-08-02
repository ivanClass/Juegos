package tp.pr5.logica;

public class MovimientoConecta4 extends Movimiento {
	private int col;
	private int fil;
	
	/**
	 * Constructor
	 * @param donde columna del movimiento
	 * @param color	Ficha
	 */	
	public MovimientoConecta4(int donde,Ficha color){
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
				throw new MovimientoInvalido("Columna llena.");
			}
		}
		else{
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + Integer.toString(tab.getAncho())+ ".");
		}
	}
	
	@Override
	public void undo(Tablero tab){
		tab.setCasilla(this.col, this.fil, Ficha.VACIA);		
	}
}
