package tp.pr5.logica;

public class ReglasTresRaya implements ReglasJuego{
	private static final int NFICHAS_EN_LINEA = 3;
	
	private  int numFilas;
	private  int numCols;
	
	private int maxMovimientos;
		
	
	public ReglasTresRaya(int numCols, int numFilas){
		this.numFilas = numFilas;
		this.numCols = numCols;
		this.maxMovimientos = 999;
	}
	
	public ReglasTresRaya(int numCols, int numFilas,int maxMov){
		this.numFilas = numFilas;
		this.numCols = numCols;
		this.maxMovimientos = maxMov;
	}
	
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) { //CAMBIAR LOS DE UTILIDADES -> NFICHAS_EN_LINEA = 3
		Ficha ganador;
		
		ganador = comprobarHorizontal(t);
		if(ganador == null)
			ganador = comprobarVertical(t);
		if(ganador == null)
			ganador = comprobarDiagonalDer(t);
		if(ganador == null)
			ganador = comprobarDiagonalIzq(t);

		
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
		return Juegos.TRESENRAYA;
	}

	@Override
	public boolean ningunoPuedePoner() {
		boolean ok = false;
		if(Ficha.BLANCA.getNumMovimientos() == this.maxMovimientos && Ficha.NEGRA.getNumMovimientos() == maxMovimientos)
			ok = true;
		
		return ok;
	}
	
	private Ficha comprobarHorizontal (Tablero t){
		Ficha ganador = null;
		boolean lineaBlanca = false;
		boolean lineaNegra = false;
		int nfila = 1;
		int ncol = 1;
		int contNegras = 1;
		int contBlancas = 1;

		while(nfila <= t.getAlto()){
			while(ncol < t.getAncho()){
				if(t.getCasilla(ncol, nfila) == t.getCasilla(ncol + 1, nfila)){
					if(t.getCasilla(ncol, nfila) == Ficha.BLANCA)
						contBlancas++;
					if(t.getCasilla(ncol, nfila) == Ficha.NEGRA)
						contNegras++;
				}
				else{
					contBlancas = 1;
					contNegras = 1;
				}
				if(contBlancas >= NFICHAS_EN_LINEA){
					lineaBlanca = true;
				}
				if (contNegras >= NFICHAS_EN_LINEA){
					lineaNegra = true;
				}
				ncol++;
			}
			ncol = 1;
			contBlancas = 1;
			contNegras = 1;
			nfila++;
		}
		
		if(lineaBlanca && !lineaNegra)
			ganador = Ficha.BLANCA;
		else if(!lineaBlanca && lineaNegra)
			ganador = Ficha.NEGRA;
		else if(lineaBlanca && lineaNegra)
			ganador = Ficha.VACIA;

		return ganador;

	}
	
	/**
	 * DEVUELVE, SI ENCUENTRA 4 EN RAYA EN VERTICAL,EL COLOR
	 * DE LA LINEA.
	 * 
	 * SI ENCUENTRA DOS,O MAS, 4 EN RAYA EN VERTICAL DE COLORES DISTINTOS
	 * DEVUELVE FICHA.VACIA
	 * 
	 * SI NO ENCUENTRA NINGUN GRUPO, DEVUELVE NULL
	 * @param t Tablero
	 * @return ganador Ficha
	 */
	private Ficha comprobarVertical(Tablero t){
		Ficha ganador = null;
		boolean lineaBlanca = false;
		boolean lineaNegra = false;
		int nfila = t.getAlto();
		int ncol = 1;
		int contNegras = 1;
		int contBlancas = 1;

		while(ncol <= t.getAncho()){
			while(nfila > 1){
				if(t.getCasilla(ncol, nfila) == t.getCasilla(ncol, nfila - 1)){
					if(t.getCasilla(ncol, nfila) == Ficha.BLANCA)
						contBlancas++;
					if(t.getCasilla(ncol,nfila) == Ficha.NEGRA)
						contNegras++;
				}
				else{
					contBlancas = 1;
					contNegras = 1;
				}
				if(contBlancas >= NFICHAS_EN_LINEA){
					lineaBlanca = true;
				}
				if (contNegras >= NFICHAS_EN_LINEA){
					lineaNegra = true;
				}

				nfila--;
			}
			ncol++;
			nfila = t.getAlto();
			contBlancas = 1;
			contNegras = 1;
		}

		if(lineaBlanca && !lineaNegra)
			ganador = Ficha.BLANCA;
		else if(!lineaBlanca && lineaNegra)
			ganador = Ficha.NEGRA;
		else if(lineaBlanca && lineaNegra)
			ganador = Ficha.VACIA;

		return ganador;
	}

	/**
	 * DEVUELVE, SI ENCUENTRA 4 EN RAYA EN DIAGONAL '\',EL COLOR
	 * DE LA LINEA.
	 * 
	 * SI ENCUENTRA DOS,O MAS, 4 EN RAYA DIAGONAL '\' DE COLORES DISTINTOS
	 * DEVUELVE FICHA.VACIA
	 * 
	 * SI NO ENCUENTRA NINGUN GRUPO, DEVUELVE NULL
	 * @param t Tablero
	 * @return ganador Ficha
	 */
	private Ficha comprobarDiagonalDer(Tablero t){
		Ficha ganador = null;
		boolean lineaBlanca = false;
		boolean lineaNegra = false;
		int ncol = 1;
		int nfila = 1;
		int nfilatmp;
		int ncoltmp;
		int contNegras = 1;
		int contBlancas = 1;


		while(nfila <= t.getAlto()){
			nfilatmp = nfila;
			ncoltmp = ncol;
			//si el numero de filas entre la ultima y la fila a empezar es menor a NFICHAS_EN_LINEA...
			//...es imposible que haya una diagonal. Idem con columnas
			while((t.getAlto() - nfila + 1>= NFICHAS_EN_LINEA) && (t.getAncho() - ncol + 1 >= NFICHAS_EN_LINEA) && ncoltmp < t.getAncho()){
				if(t.getCasilla(ncoltmp, nfilatmp) == t.getCasilla(ncoltmp + 1, nfilatmp + 1)){
					if(t.getCasilla(ncoltmp, nfilatmp) == Ficha.BLANCA){
						contBlancas++;
						nfilatmp++;
					}
					else if(t.getCasilla(ncoltmp, nfilatmp) == Ficha.NEGRA){
						contNegras++;
						nfilatmp++;
					}
					else
						nfilatmp = nfila;
				}
				else{
					contBlancas = 1;
					contNegras = 1;
				}
				if(contBlancas >= NFICHAS_EN_LINEA){
					lineaBlanca = true;
				}
				if (contNegras >= NFICHAS_EN_LINEA){
					lineaNegra = true;
				}
				ncoltmp++;
			}
			contNegras = 1;
			contBlancas = 1;
			nfila++;
		}
		
		if(lineaBlanca && !lineaNegra)
			ganador = Ficha.BLANCA;
		else if(!lineaBlanca && lineaNegra)
			ganador = Ficha.NEGRA;
		else if(lineaBlanca && lineaNegra)
			ganador = Ficha.VACIA;
		return ganador;
	}
	
	/**
	 * DEVUELVE, SI ENCUENTRA 4 EN RAYA EN DIAGONAL '/',EL COLOR
	 * DE LA LINEA.
	 * 
	 * SI ENCUENTRA DOS,O MAS, 4 EN RAYA DIAGONAL '/' DE COLORES DISTINTOS
	 * DEVUELVE FICHA.VACIA
	 * 
	 * SI NO ENCUENTRA NINGUN GRUPO, DEVUELVE NULL
	 * @param t Tablero
	 * @return ganador Ficha
	 */
	private Ficha comprobarDiagonalIzq(Tablero t){
		Ficha ganador = null;
		boolean lineaBlanca = false;
		boolean lineaNegra = false;
		int ncol = t.getAncho();
		int nfila = 1;
		int contNegras = 1;
		int contBlancas = 1;
		int nfilatmp;
		int ncoltmp;



		while(nfila <= t.getAlto()){
			nfilatmp = nfila;
			ncoltmp = ncol;
			//si el numero de filas entre la ultima y la fila a empezar es menor a NFICHAS_EN_LINEA...
			//...es imposible que haya una diagonal. Idem con columnas
			while((t.getAlto() - nfila + 1>= NFICHAS_EN_LINEA) && (ncol >= NFICHAS_EN_LINEA) && ncoltmp > 1){
				if(t.getCasilla(ncoltmp, nfilatmp) == t.getCasilla(ncoltmp - 1, nfilatmp + 1)){
					if(t.getCasilla(ncoltmp, nfilatmp) == Ficha.BLANCA){
						contBlancas++;
						nfilatmp++;
					}
					else if(t.getCasilla(ncoltmp, nfilatmp) == Ficha.NEGRA){
						contNegras++;
						nfilatmp++;
					}
					else
						nfilatmp = nfila;
				}
				else{
					contBlancas = 1;
					contNegras = 1;
				}
				if(contBlancas >= NFICHAS_EN_LINEA){
					lineaBlanca = true;
				}
				if (contNegras >= NFICHAS_EN_LINEA){
					lineaNegra = true;
				}
				ncoltmp--;
			}
			contNegras = 1;
			contBlancas = 1;
			nfila++;
		}

		if(lineaBlanca && !lineaNegra)
			ganador = Ficha.BLANCA;
		else if(!lineaBlanca && lineaNegra)
			ganador = Ficha.NEGRA;
		else if(lineaBlanca && lineaNegra)
			ganador = Ficha.VACIA;

		return ganador;
	}
}
