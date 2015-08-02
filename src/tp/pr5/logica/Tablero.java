package tp.pr5.logica;

public class Tablero implements TableroInmutable{
	
	private Ficha [][] tablero;
	private int ancho;
	private int alto;
	
	/**
	 * Constructor del tablero vacío
	 * @param tx Columnas(1....N)
	 * @param ty Filas(1...N)
	 */
	public Tablero(int tx,int ty){
		if(tx <= 0 || ty <= 0){
			tx = 1;
			ty = 1;
		}
		this.ancho = tx;
		this.alto = ty;
		
		this.tablero = new Ficha[this.alto][this.ancho];
		reset();
	}
	
	/**
	 * 
	 * @return altura del tablero(número de filas)
	 */
	public int getAlto(){
		return this.alto;
	}
	
	/**
	 * 
	 * @return anchura del tablero(número de columnas)
	 */
	public int getAncho(){
		return this.ancho;
	}
	
	/**
	 * Si x e y estan en el rango de posiciones del tablero
	 * devuelve el color de la ficha de la posicion (x,y)
	 * del tablero.
	 * 
	 * Si no, devuelve Ficha.VACIA.
	 * 
	 * @param x	Posicion eje x(columna: 1....getAncho())
	 * @param y	Posicion eje y(fila: 1 ....getAlto());
	 * @return Ficha en la posición (y,x) del tablero
	 */
	public Ficha getCasilla(int x, int y){
		Ficha temp;
		if( ((x < 1 || x > getAncho()) || (y < 1 || y > getAlto())))
			temp = Ficha.VACIA;
		else temp = this.tablero[y - 1][x - 1];
		
		return temp;
	}
		
	/**
	 *  
	 * Si x e y estan en el rango de posiciones del tablero
	 * pone en tablero[y-1][x-1] una Ficha color.
	 * Si no, no hace nada.
	 * 
	 * @param x	Posicion eje x(columna: 1....getAncho())
	 * @param y	Posicion eje y(fila: 1 ....getAlto());
	 * @param color Ficha- ficha que se pondra
	 * en la posición(x,y) del tablero.
	 */
	public void setCasilla(int x,int y,Ficha color){
		if( ( !((x < 1 || x > getAncho()) || (y < 1 || y > getAlto()))) )
			this.tablero[y - 1][x - 1] = color;
	}

	
	/**
	 * Devuelve la representación del tablero
	 */
	@Override
	public String toString(){
		String tablero = "";
	
		return parteArriba(tablero) + parteAbajo(tablero) + "\n";
	}
		
	/**
	 * Pone el tablero a Ficha.VACIA
	 */
	private void reset(){
		for(int nfila = 1; nfila <= this.alto;nfila++){
			for(int ncol=1; ncol <= this.ancho;ncol++){
				setCasilla(ncol, nfila, Ficha.VACIA);
			}
		}
		
		
	}
	
	/**
	 * Devuelve la representacion de la parte superior del
	 * tablero(donde se ubican las fichas)
	 * 
	 * @param tablero
	 * @return String
	 */
	private String parteArriba(String tablero){
		
		for(int nfil = 1; nfil <= this.getAlto(); nfil++){
			tablero += "|";
			for(int ncol = 1; ncol <= this.ancho;ncol++){
				tablero += getCasilla(ncol, nfil).getSimbolo();
			}
			tablero += "|";
			tablero += "\n";
		}
		return tablero;
		
	}
	
	/**
	 * Devuelve la representacion de la parte inferior del
	 * tablero
	 * 
	 * @param tablero
	 * @return String
	 */
	private String parteAbajo(String tablero){
		int col = 1;
		tablero += "+";
		
		for(int ncol = 1; ncol <= this.ancho;ncol++){
			tablero += "-";
		}
		tablero += "+";
		tablero += "\n";
		tablero += " ";
		
		for(int ncol = 1; ncol <= this.ancho;ncol++){
			if(col% 10 == 0){
				col = 0;
			}
			tablero += Integer.toString(col); //PASA DE ENTERO A STRING
			col++;
		}
		tablero += "\n";
		
		return tablero;
	}
}
