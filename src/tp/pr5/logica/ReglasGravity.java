package tp.pr5.logica;

public class ReglasGravity implements ReglasJuego {
	private  int numFilas;
	private  int numCols;
	private int maxMov;

	
	public ReglasGravity(int numCols, int numFilas){
		this.numFilas = numFilas;
		this.numCols = numCols;
		this.maxMov = 999;
	}
	
	public ReglasGravity(int numCols, int numFilas,int max){
		this.numFilas = numFilas;
		this.numCols = numCols;
		this.maxMov = max;
	}
	
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador;
		
		ganador = Utilidades.comprobarHorizontal(t);
		if(ganador == null)
			ganador = Utilidades.comprobarVertical(t);
		if(ganador == null)
			ganador = Utilidades.comprobarDiagonalDer(t);
		if(ganador == null)
			ganador = Utilidades.comprobarDiagonalIzq(t);

		
		if(ganador == null)
			ganador = Ficha.VACIA;
		
		return ganador;
	}
	
	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(this.numCols, this.numFilas);
		return tab;
	}
	
	@Override
	public Ficha jugadorInicial(){
		return Ficha.BLANCA;
	}
	
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner,Tablero t) {
		Ficha turnosiguiente = null;
		switch (ultimoEnPoner) {
		case BLANCA:
			turnosiguiente = Ficha.NEGRA;
			break;
		case NEGRA:
			turnosiguiente = Ficha.BLANCA;
			break;
		default:
			break;
		}
		
		return turnosiguiente;
	}
	
	/***************************************************
	 * PARAMETROS: 	Ficha ultimoEnPoner
	 * 				Tablero t
	 * 
	 * RETURN	 : 	boolean
	 * 
	 * SI TODAS LAS COLUMNAS DEL TABLERO ESTAN LLENAS
	 * DEVUELVE TRUE
	 ***************************************************/
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		boolean tablerolleno = true;
		int ncol = 1;
		
		while(tablerolleno && ncol <= t.getAncho()){
			if(Utilidades.posVacia(ncol, t) != -1)
				tablerolleno = false;
			
			ncol++;
		}

		return tablerolleno;
	}
	
	@Override
	public Juegos getJuego() {
		return Juegos.GRAVITY;
	}

	@Override
	public boolean ningunoPuedePoner() {
		boolean ok = false;
		if(Ficha.BLANCA.getNumMovimientos() == this.maxMov && Ficha.NEGRA.getNumMovimientos() == maxMov)
			ok = true;
		
		return ok;
	}
}
