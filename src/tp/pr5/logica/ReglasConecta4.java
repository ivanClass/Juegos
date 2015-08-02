package tp.pr5.logica;
import tp.pr5.logica.Utilidades;

public class ReglasConecta4 implements ReglasJuego {
	//CONSTANTES
	private static final int NUM_FIL = 6;
	private static final int NUM_COL = 7;
	private int maxMovimientos;
		
	
	public ReglasConecta4(){
		this.maxMovimientos = 999;
	}
	
	public ReglasConecta4(int maxMov){
		this.maxMovimientos = maxMov;
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
		Tablero tab = new Tablero(NUM_COL,NUM_FIL);
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
			if(turnosiguiente.getNumMovimientos() == this.maxMovimientos)
				turnosiguiente = Ficha.BLANCA;
			break;
		case NEGRA:
			turnosiguiente = Ficha.BLANCA;
			if(turnosiguiente.getNumMovimientos() == this.maxMovimientos)
				turnosiguiente = Ficha.NEGRA;
			break;
		default:
			break;
		}
		
		return turnosiguiente;
	}
	
	/**
	 * Si todas las columnas están llenas
	 * return true
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return Utilidades.tableroLLeno(t);
	}
	
	@Override
	public Juegos getJuego() {
		return Juegos.CONECTA4;
	}

	@Override
	public boolean ningunoPuedePoner() {
		boolean ok = false;
		if(Ficha.BLANCA.getNumMovimientos() == this.maxMovimientos && Ficha.NEGRA.getNumMovimientos() == maxMovimientos)
			ok = true;
		
		return ok;
	}
}
