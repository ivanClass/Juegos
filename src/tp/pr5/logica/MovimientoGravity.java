package tp.pr5.logica;

public class MovimientoGravity extends Movimiento {
	private int col;
	private int fil;
	
	private int horizontal;
	private int vertical;
	
	/**
	 * Constructor
	 * @param columna	columna del movimiento
	 * @param fila		fila del movimiento
	 * @param color		Ficha
	 */
	public MovimientoGravity(int columna,int fila,Ficha color){
		super(color);
		this.col = columna;
		this.fil = fila;
		this.horizontal = 0;
		this.vertical = 0;
	}
	
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {		
		if(this.col >= 1 && this.col <= tab.getAncho() && this.fil >= 1 && this.fil <= tab.getAlto()){
			if(tab.getCasilla(this.col, this.fil) == Ficha.VACIA){
				posMasCerca(tab);
				tab.setCasilla(this.col, this.fil, getJugador());
			}
			else{
				throw new MovimientoInvalido("Casilla ocupada.");
			}
		}
		else{
			throw new MovimientoInvalido("Posición incorrecta.");
		}
	}
	
	@Override
	public void undo(Tablero tab){
		tab.setCasilla(this.col, this.fil, Ficha.VACIA);		
	}

	/**
	 * Según la fila y columna del movimiento y el cuadrante al que pertenece la casilla,
	 * coloca la ficha en el lugar más cercano según las normas de Gravity
	 * @param tab	Tablero
	 */
	private void posMasCerca(Tablero tab){
		boolean seQueda = false;
		seQueda = analisisDePosicion(tab);
		
		if(!seQueda)
			colocaLaFicha(tab);
	}
	
	/**
	 * PARAMETROS:	LIMITES DE LA COLUMNA Y FILA Y LA POSICION X Y DE LA FICHA: INT  col1, INT col2, INT fil1, INT fil2, INT h, INT v 
	 * (h Y v DIRECCIONES QUE TOMARIA LA FICHA), INT ancho, INT alto
	 * 
	 * 
	 * Calcula la dirección que debe tomar la ficha según las reglas establecidas en el gravity
	 */
	private void cuadrante(int col1, int col2, int fil1, int fil2, int h, int v, int ancho, int alto){
		boolean hor = false, vert = false;
		
		
		//Para saber si una ficha, en vez de ir a una diagonal como en una primera vista parece se va
		//en el caso del 3er cuadrante a la izquierda o en el caso del 4to cuadrante abajo
			hor = (this.fil - 1 == alto - this.fil);
			vert = (this.col - 1 == ancho - this.col);
		
		////////////////////////////////////////////////////
		
		if(col1 - col2 < fil1 - fil2){
			this.horizontal = h;
			this.vertical = 0;
		}
		else if(col1 - col2 > fil1 - fil2){
			this.horizontal = 0;
			this.vertical = v;
		}
		else if((col1 - col2 == fil1 - fil2) && !hor && !vert){
			this.horizontal = h;
			this.vertical = v;
		}
		else if((col1 - col2 == fil1 - fil2) && hor && !vert){
			this.horizontal = h;
			this.vertical = 0;
		}
		else{
			this.horizontal = 0;
			this.vertical = v;
		}
	}
	
	/**
	 *  Analiza si la ficha se queda en el sitio o se
	 *  tiene que desplazar,calculando el cuadrante en el que
	 *  esta y que direcciones tiene que tomar para desplazarse
	 * @param tab Tablero
	 * @return true si la ficha se queda en el centro del tablero
	 */
	private boolean analisisDePosicion(Tablero tab){
		int alto2 = tab.getAlto() / 2;   //a
		int ancho2 = tab.getAncho() / 2; //b
		boolean horizontalPar = true && (tab.getAncho() % 2 == 0);
		boolean verticalPar = true && (tab.getAlto() % 2 == 0);
		boolean seQueda = false;
		
		//CUADRANTES
				/* |1 | 2|       | 1|2 |      | 1| 2| 2|     | 1| 2| 2|      //CUADRANTE 1: SOLO PUEDE IR O ARRIBA O IZQUERDA O AMBAS
				 * |  |  |       +--+--+      |  |  |  |     +--+--+--+      //CUADRANTE 2: SOLO PUEDE IR O ARRIBA O DERECHA O AMBAS
				 * +--+--+       | 3|4 |      +--+--+--+     | 3| 4| 4|      //CUADRANTE 3: SOLO PUEDE IR O ABAJO O IZQUIEDA O AMBAS
				 * |3 | 4|       +--+--+      | 3| 4| 4|     +--+--+--+      //CUADRANTE 4: SOLO PUEDE IR O ABAJO O DERECHAO AMBAS
				 * |  |  |       | 3|4 |      |  |  |  |     | 3| 4| 4|      
				 * +-----+       +-----+      +--------+     +--------+      //ARRIBA : VERTICAL : -1    IZQUIERDA : HORIZONTAL : -1
				 *                                                           //ABAJO  : VERTICAL : +1    DERECHA   : HORIZONTAL : +1
				 * par x par | par x impar  | impar x par | impar x impar    //SI LA FICHA NO SE MUEVE : AMBOS SENTIDOS : 0
				 */
				
				//en tableros impar x impar puede haber una ficha que se queden en el sitio,
		
		if(!horizontalPar && !verticalPar){
			int f = tab.getAlto() / 2;
			int h = tab.getAncho() / 2;
			
			if(this.col == h + 1 && this.fil == f + 1)
				seQueda = true;
		}
		if(!seQueda){
			//dependiendo del cuadrante
			if(this.col <= ancho2 && this.fil <= alto2) //PRIMER CUADRANTE ARRIBA IZQUIERDA
				cuadrante(this.col, 1, this.fil, 1, -1, -1, tab.getAncho(), tab.getAlto());
			else if(this.col > ancho2 && this.fil <= alto2) //SEGUNDO CUADRANTE ARRIBA DERECHA
				cuadrante(tab.getAncho(), this.col, this.fil, 1, 1, -1, tab.getAncho(), tab.getAlto());
			else{
				if(this.col <= ancho2 && this.fil > alto2) //TERCER CUADRANTE ABAJO IZQUIERDA
					cuadrante(this.col, 1, tab.getAlto(), this.fil, -1, +1, tab.getAncho(), tab.getAlto());
				else if(this.col > ancho2 && this.fil > alto2)	//CUARTO CUADRANTE ABAJO DERECHA
					cuadrante(tab.getAncho(), this.col, tab.getAlto(), this.fil, 1, 1, tab.getAncho(), tab.getAlto());
			}
		}
		
		return seQueda;
	}

	/**
	 * Teniendo ya las direcciones que tiene que tomar la ficha
	 * para desplazarse, calcula la primera posición que 
	 * esté libre(Ficha.VACIA) en su trayectoria
	 * @param tab Tablero
	 */
	private void colocaLaFicha(Tablero tab){
		boolean posOcupada = false;
		
		//IR CALCULANDO LA NUEVA POSICION DE LA FICHA HASTA QUE SE TOPE CON UNA O LLEGUE AL L�MITE DEL TABLERO
		while(!posOcupada && this.fil > 1 && this.fil < tab.getAlto() && this.col > 1 && this.col < tab.getAncho()){
			if(tab.getCasilla(this.col + this.horizontal, this.fil + this.vertical) != Ficha.VACIA){
				posOcupada =  true;
			}
			else{
				this.col = this.col + this.horizontal;
				this.fil = this.fil + this.vertical;
			}
		}
	}

	@Override
	public void redo(Tablero tab) {
		tab.setCasilla(this.col, this.fil, getJugador());		
	}
}
