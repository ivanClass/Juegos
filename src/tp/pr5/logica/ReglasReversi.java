package tp.pr5.logica;

public class ReglasReversi implements ReglasJuego {
	//CONSTANTES
	private static final int NUM_FIL = 8;
	private static final int NUM_COL = 8;
	private int maxMovimientos;

	public ReglasReversi() {
		this.maxMovimientos = 999;
	}
	
	public ReglasReversi(int max){
		this.maxMovimientos = max;
	}
	
	
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador = Ficha.VACIA;

		if(Utilidades.tableroLLeno(t) || (!puedePoner(Ficha.BLANCA, t) && !puedePoner(Ficha.NEGRA, t))){
			ganador = cuentaFichas(t);
		}
		
		
		return ganador;
	}

	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(NUM_COL,NUM_FIL);
		
		tab.setCasilla(4, 4, Ficha.BLANCA);
		tab.setCasilla(5, 4, Ficha.NEGRA);
		
		tab.setCasilla(4, 5, Ficha.NEGRA);
		tab.setCasilla(5, 5, Ficha.BLANCA);
		
		return tab;
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha turnosiguiente = null;
		
		switch (ultimoEnPoner) {
		case BLANCA:
			turnosiguiente = Ficha.NEGRA;
			if(!puedePoner(turnosiguiente, t))
				turnosiguiente = Ficha.BLANCA;
			break;
		case NEGRA:
			turnosiguiente = Ficha.BLANCA;
			if(!puedePoner(turnosiguiente, t))
				turnosiguiente = Ficha.NEGRA;
			break;
		default:
			break;
		}
		
		return turnosiguiente;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t){
		boolean hayTablas = false;
		if(Utilidades.tableroLLeno(t) && cuentaFichas(t) == Ficha.VACIA){
			hayTablas = true;
		}
		
		return hayTablas;
	}

	@Override
	public Juegos getJuego() {
		return Juegos.REVERSI;
	}
	
	/**
	 * Cuenta las fichas del tablero y devuelve el color
	 * del ganador, VACIA si hay empate
	 * @param tab
	 * @return
	 */
	private Ficha cuentaFichas(TableroInmutable tab){
		int contblanco = 0;
		int contnegro = 0;
		Ficha ganador = null;
		
		for (int i = 1; i <= tab.getAlto(); i++) {
			for (int j = 1; j <= tab.getAncho(); j++) {
				if(tab.getCasilla(j, i) == Ficha.BLANCA)
					contblanco++;
				else if(tab.getCasilla(j, i) == Ficha.NEGRA)
					contnegro++;
			}
		}
		
		if(contblanco > contnegro)
			ganador = Ficha.BLANCA;
		else if(contblanco < contnegro)
			ganador = Ficha.NEGRA;
		else{
			ganador = Ficha.VACIA;
		}

		
		return ganador;
	}
	
	
	/**
	 * Comprueba si el jugador(color) puede pooner
	 * @param color
	 * @param t
	 * @return
	 */
	private boolean puedePoner(Ficha color, Tablero t) {
		boolean sePuede = false;
		
		for (int i = 1; i <= t.getAlto() && !sePuede; i++) {
			for (int j = 1; j <= t.getAncho() && !sePuede; j++) {
				if(t.getCasilla(j, i) == Ficha.VACIA){
					if(Utilidades.esCorrectoReversi(j, i, color, t)){
						sePuede = true;
					}	
				}
			}
		}
		
		return sePuede;
	}

	@Override
	public boolean ningunoPuedePoner() {
		boolean ok = false;
		if(Ficha.BLANCA.getNumMovimientos() == this.maxMovimientos && Ficha.NEGRA.getNumMovimientos() == maxMovimientos)
			ok = true;
		
		return ok;
	}
	

}
