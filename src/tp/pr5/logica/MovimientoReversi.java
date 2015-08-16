package tp.pr5.logica;

import java.util.HashMap;
import java.util.Iterator;



public class MovimientoReversi extends Movimiento {
	private int col;
	private int fil;
	
	private HashMap<String, Integer> map;
	
	/**
	 * Constructor
	 * @param columna	columna del movimiento
	 * @param fila		fila del movimiento
	 * @param color		Ficha
	 */
	public MovimientoReversi(int columna, int fila, Ficha color){
		super(color);
		this.col = columna;
		this.fil = fila;
		this.map = new HashMap<String, Integer>(); //APLICANDO CONCEPTOS DE EDA...
	}
	
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if(this.col >= 1 && this.col <= tab.getAncho() && this.fil >= 1 && this.fil <= tab.getAlto()){
			if(Utilidades.esCorrectoReversi(this.col, this.fil, this.getJugador(), tab)){
				tab.setCasilla(this.col, this.fil, getJugador());
				this.revertirFichas(tab);
			}
			else{
				throw new MovimientoInvalido("Aqui no puedes poner.");
			}
		}
		else{
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		
	}


	@Override
	public void undo(Tablero tab) {
		
		for( Iterator<String> it = this.map.keySet().iterator(); it.hasNext();) {
			String s = it.next(); //COL Y FIL DESTINO
	        int nFichas = this.map.get(s); //DIRECCION
	        
	        String dir[] =  s.split(" ");
	        int hor = Integer.parseInt(dir[0]);
	        int vert = Integer.parseInt(dir[1]);
	        
	        int colaux =  this.col;
	        int filaux = this.fil;
	        Ficha color;
	        
	        if(this.getJugador() == Ficha.BLANCA)
	        	color = Ficha.NEGRA;
	        else
	        	color = Ficha.BLANCA;
	        
			while(nFichas != 0){
				colaux = colaux + hor;
				filaux = filaux + vert;
				tab.setCasilla(colaux, filaux, color);
				nFichas--;
			}


	    }
		
		
		tab.setCasilla(this.col, this.fil, Ficha.VACIA);

	}
	
	/**
	 * Da la vuelta a las fichas apropiadas
	 * de todos los sentidos
	 * @param tab
	 */
	private void revertirFichas(Tablero tab){
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				this.auxRevertirFichas(tab, j, i);
			}
		}
	}
	
	/**
	 * Da la vuelta a las fichas dependiendo de la direccion
	 * @param tab
	 * @param hor
	 * @param vert
	 */
	private void auxRevertirFichas(Tablero tab, int hor, int vert){
		boolean salir = false;
		int colaux = this.col;
		int filaux = this.fil;
		int contFichas = 0;
		boolean hueco = false;
		
		while(!salir && !hueco  && filaux >= 1 && filaux <= tab.getAlto() && colaux >= 1 && colaux <= tab.getAncho()){
				if(tab.getCasilla(colaux + hor, filaux + vert) == this.getJugador()){
					salir = true;
					if(hor == 0 && vert == 0) //caso k sea el mismo
						hueco = true;
				}
				else if( hor == 0 && vert == 0){
					hueco = true;
				}
				else if(tab.getCasilla(colaux + hor, filaux + vert) == Ficha.VACIA){
					hueco = true;
				}
				else{
					colaux = colaux + hor;
					filaux = filaux + vert;
					contFichas++;
				}
		}
		
		if(salir && !hueco){
			this.map.put(hor + " " + vert, contFichas);
			colaux = this.col;
			filaux = this.fil;
			while(contFichas != 0){
				colaux = colaux + hor;
				filaux = filaux + vert;
				tab.setCasilla(colaux, filaux, this.getJugador());
				contFichas--;
			}
		}	
	}

	@Override
	public void redo(Tablero tab) {
		tab.setCasilla(this.col, this.fil, getJugador());
		this.revertirFichas(tab);		
	}
}
