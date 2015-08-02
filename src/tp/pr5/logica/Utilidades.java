package tp.pr5.logica;

public class Utilidades {
	//CONSTANTES
	
	//C4,CO,GR
	private static int NFICHAS_EN_LINEA = 4;
	
	/**
	 * 
	 * @param ncol 	columna
	 * @param tab	Tablero
	 * @return primera posicion en la que hay una Ficha.VACIA en el tablero,
	 * -1(columna completa) si la columna ncol está llena.
	 */
	public static int posVacia(int ncol, TableroInmutable tab){
		boolean huecovacio = false;
		int nfil = tab.getAlto();
		
		while(!huecovacio && nfil > 0){
			if(tab.getCasilla(ncol, nfil) == Ficha.VACIA)
				huecovacio = true;
			else
				nfil--;
		}
		if(!huecovacio)
			nfil = -1;
		
		return nfil;
	}
	
	/**
	 * DEVUELVE, SI ENCUENTRA 4 EN RAYA EN HORIZONTAL,EL COLOR
	 * DE LA LINEA.
	 * 
	 * SI ENCUENTRA DOS,O MAS, 4 EN RAYA EN HORIZONTAL DE COLORES DISTINTOS
	 * DEVUELVE FICHA.VACIA
	 * 
	 * SI NO ENCUENTRA NINGUN GRUPO, DEVUELVE NULL
	 * @param t Tablero
	 * @return ganador Ficha
	 */
	public static Ficha comprobarHorizontal (Tablero t){
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
	public static Ficha comprobarVertical(Tablero t){
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
	public static Ficha comprobarDiagonalDer(Tablero t){
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
	public static Ficha comprobarDiagonalIzq(Tablero t){
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
	
	/**
	 * Nos dice si es correcto poner una ficha
	 * en una posicion (col,fil) del tablero.
	 * Usado para reversi
	 * @param col
	 * @param fil
	 * @param color
	 * @param tab
	 * @return
	 */
	public static boolean esCorrectoReversi(int col, int fil, Ficha color,TableroInmutable tab){
		boolean correcto = false;
		
		for (int i = -1; i < 2 && !correcto; i++) {
			for (int j = -1; j < 2 && !correcto; j++) {
				correcto = Utilidades.esCorrectoReversiAux(col, fil, j, i, color, tab);
			}
		}
		
		return correcto;
	}
	
	/**
	 * Comprueba si alrededor de una posicion (col,fila)
	 * del tablero hay una ficha del color contrario
	 * al pasado por parámetro.Usado para reversi
	 * @param col
	 * @param fil
	 * @param hor
	 * @param vert
	 * @param color
	 * @param tab
	 * @return
	 */
	private static boolean compruebaContiguoAux(int col, int fil,int hor,int vert, Ficha color, TableroInmutable tab){
		boolean correcto =  false;
		if(hor == 0 && vert == 0){
			correcto = false;
		}
		else if(tab.getCasilla(col + hor, fil + vert) != Ficha.VACIA && tab.getCasilla(col + hor, fil + vert) != color){
			correcto = true;
		}
		
		return correcto;
	}
	
	/**
	 * Método auxiliar que nos dice si un movimiento
	 * es correcto en reversi.
	 * @param col
	 * @param fil
	 * @param hor
	 * @param vert
	 * @param color
	 * @param tab
	 * @return
	 */
	public static boolean esCorrectoReversiAux(int col, int fil,int hor,int vert, Ficha color, TableroInmutable tab){
		boolean correcto = false;
		if(tab.getCasilla(col, fil) == Ficha.VACIA){
			//comprobamos si en la direccion que toca hay una ficha del color contrario
			correcto = Utilidades.compruebaContiguoAux(col, fil, hor, vert, color, tab);

			if(correcto){
				correcto = false;
				boolean salir = false;
				int colaux = col;
				int filaux = fil;
				int contFichas = 0;
				boolean hueco = false;
				
				while(!salir && !hueco  && filaux >= 1 && filaux <= tab.getAlto() && colaux >= 1 && colaux <= tab.getAncho()){
						if(tab.getCasilla(colaux + hor, filaux + vert) == color){
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
					if(contFichas > 0)
						correcto = true;
				}
			}
		}
		
		return correcto;
	}

	/**
	 * Este método devuelve un tablero con las fichas del
	 * tablero pasado por parametro mas las pistas de
	 * donde se puede poner en reversi. Se utiliza como
	 * plantilla
	 * @param color
	 * @param tab
	 * @return
	 */
	public static TableroInmutable indicarPosicionesPosiblesRv(Ficha color, TableroInmutable tab){
		Tablero tabret = new Tablero(tab.getAncho(), tab.getAlto());
		
		for (int i = 1; i <= tab.getAlto(); i++) {
			for (int j = 1; j <= tab.getAncho(); j++) {
				if(tab.getCasilla(j, i) != Ficha.VACIA)
					tabret.setCasilla(j, i, tab.getCasilla(j, i));
				if(Utilidades.esCorrectoReversi(j, i, color, tab)){
					tabret.setCasilla(j, i, Ficha.PISTA);
				}
			}
			
		}
		
		return tabret;
	}
	
	public static boolean tableroLLeno(TableroInmutable t){
		boolean tablerolleno = true;
		int ncol = 1;
		
		while(tablerolleno && ncol <= t.getAncho()){
			if(Utilidades.posVacia(ncol, t) != -1)
				tablerolleno = false;
			
			ncol++;
		}
		
		return tablerolleno;
	}
}
