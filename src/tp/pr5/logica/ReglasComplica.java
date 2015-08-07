package tp.pr5.logica;

public class ReglasComplica implements ReglasJuego {
	//CONSTANTES
	private static final int NUM_FIL = 7;
	private static final int NUM_COL = 4;
	private int maxMov;
	
	public ReglasComplica(){
		this.maxMov = 999;
	};
	
	public ReglasComplica(int max){
		this.maxMov = max;
	}
	
	/**
	 * Devuelve Ficha.VACIA si no hay ganador
	 * o si hay dos ganadores distintos
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador = Ficha.VACIA,ganadorH,ganadorV,ganadorD1,ganadorD2;
		
		ganadorH = Utilidades.comprobarHorizontal(t);
		ganadorV = Utilidades.comprobarVertical(t);
		ganadorD1 = Utilidades.comprobarDiagonalDer(t);
		ganadorD2 = Utilidades.comprobarDiagonalIzq(t);
		
		if(ganadorH == Ficha.VACIA || ganadorV == Ficha.VACIA || ganadorD1 == Ficha.VACIA || ganadorD2 == Ficha.VACIA){
			ganador = Ficha.VACIA;
		}
		else{
			if(ganadorH != null && ganadorV != null && ganadorD1 != null && ganadorD2 != null){
				if(ganadorH == ganadorV && ganadorH == ganadorD1 && ganadorH == ganadorD2){
					ganador = ganadorH;
				}
				else
					ganador = Ficha.VACIA;
			}
			else{
				if(ganadorH == null && ganadorV == null && ganadorD1 == null && ganadorD2 != null){
					ganador = ganadorD2;
				}
				else if(ganadorH == null && ganadorV == null && ganadorD1 != null && ganadorD2 == null){
					ganador = ganadorD1;
				}
				else if(ganadorH == null && ganadorV != null && ganadorD1 == null && ganadorD2 == null){
					ganador = ganadorV;
				}
				if(ganadorH != null && ganadorV == null && ganadorD1 == null && ganadorD2 == null){
					ganador = ganadorH;
				}
				
			}
		}
		
		return ganador;
	}
	
	@Override
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(NUM_COL,NUM_FIL);
		return tab;
	}
	
	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}
	
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		Ficha turnosiguiente = null;

		switch (ultimoEnPoner) {
		case BLANCA:
			turnosiguiente = Ficha.NEGRA;
			if(turnosiguiente.getNumMovimientos() == this.maxMov)
				turnosiguiente = Ficha.BLANCA;
			break;
		case NEGRA:
			turnosiguiente = Ficha.BLANCA;
			if(turnosiguiente.getNumMovimientos() == this.maxMov)
				turnosiguiente = Ficha.NEGRA;
			break;
		default:
			break;
		}
		
		return turnosiguiente;
	}
	
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false;
	}
	
	@Override
	public Juegos getJuego() {
		return Juegos.COMPLICA;
	}

	@Override
	public boolean ningunoPuedePoner() {
		boolean ok = false;
		if(Ficha.BLANCA.getNumMovimientos() == this.maxMov && Ficha.NEGRA.getNumMovimientos() == maxMov)
			ok = true;
		
		return ok;
	}
}
