package tp.pr5.logica;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

import tp.pr5.GUI.Observer;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.Jugador;

public class Partida{
	private ReglasJuego reglas;
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	
	private Movimiento[] undoStack;
	private Stack<Movimiento> redoStack;
	private int numUndo;
	
	
	private ArrayList<Observer> obs;
	/**
	 * Constructor
	 * @param reglas ReglasJuego
	 */
	
	private void iniciarAtrib(ReglasJuego reglas){
		this.reglas = reglas;
		this.tablero = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.terminada = false;
		this.ganador = null;
		Ficha.BLANCA.setNumMovimientos(0); //Se pone el numMovimientos de cada jugador a 0.
		Ficha.NEGRA.setNumMovimientos(0);
		
		this.numUndo = 0;
	}
	
	public Partida(ReglasJuego reglas){
		iniciarAtrib(reglas);
		this.undoStack = new Movimiento [10];
		this.obs =  new ArrayList<Observer>();
		this.redoStack = new Stack<Movimiento>();
	}
	
	/**
	 * Resetea la partida,con unas determinadas
	 * reglas de juego
	 * @param reglas ReglasJuego
	 */
	public void reset(ReglasJuego reglas){
		iniciarAtrib(reglas);
		for (Observer o: obs){
			o.onReiniciar(this.tablero,this.turno);
		}
	}
	
	/**
	 * Ejecuta un movimiento si la partida no ha terminado,
	 * ,si el turno es correcto o si el movimiento es correcto
	 * 
	 * @param mov Movimiento
	 * @throws MovimientoInvalido
	 */
	public void ejecutaMovimiento(Movimiento mov){	
		try{
			if(!isTerminada()){
				if(this.turno == mov.getJugador()){
					mov.ejecutaMovimiento(this.tablero);
					this.redoStack.clear();
					this.ganador = this.reglas.hayGanador(mov, this.tablero);
					this.turno.incrMov();
					if(this.ganador != Ficha.VACIA || this.reglas.tablas(this.turno, this.tablero) || this.reglas.ningunoPuedePoner())
						this.terminada = true;
						
					if(!isTerminada()){
						guardarMovimiento(mov);
						this.turno = this.reglas.siguienteTurno(this.turno, this.tablero);
						for (Observer o: obs){
							o.onMovimientoEnd(this.tablero, mov.getJugador(), this.turno);
						}
					}
					else{
						for (Observer o: obs){
							o.onPartidaTerminada(this.tablero, this.ganador, this.turno);
						}
					}
					
				}
				else
					throw new MovimientoInvalido("No es tu turno...");
			}
			else
				throw new MovimientoInvalido("La partida ha terminado...");
		}catch(MovimientoInvalido e){
			for (Observer o: obs){
				o.onMovimientoIncorrecto(e);
				o.onMovimientoIncorrecto(this.tablero, this.turno, e);
			}
		}
	}
	
	/**
	 * 
	 * Método auxiliar que es usado en Controlador.
	 * Lo necesitamos porque el metodo getMovimiento de jugador
	 * necesita el Tablero,que no está disponible en Controlador,por lo
	 * que desde controlador llamamos a este método de partida y ya tenemos
	 * el tablero disponible
	 * 
	 * @param jugador Jugador
	 * @throws MovimientoInvalido
	 * @throws InputMismatchException
	 */
	public void poner(Jugador jugador) throws MovimientoInvalido,InputMismatchException {
		ejecutaMovimiento(jugador.getMovimiento(this.tablero, this.turno));
	}
		
	/**
	 * Deshace el último movimiento realizado y cambia el turno
	 * @return boolean,true si se ha podido deshacer,
	 * false si no
	 */
	public boolean undo(){
		boolean ejecutado = true;

		if(numUndo != 0){
			this.undoStack[numUndo - 1].undo(this.tablero);
			this.redoStack.add(this.undoStack[numUndo - 1]);
			this.turno = this.undoStack[numUndo -1].getJugador();

			numUndo--;
			this.turno.decrMov();
			
			for (Observer o: obs){
				o.onUndo(this.tablero, this.turno, this.numUndo != 0);
			}
		}
		else{
			ejecutado = false;
			for (Observer o: obs){
				o.onUndoNotPossible(this.tablero, this.turno);
			}
		}
		
		
		return ejecutado;
	}
	
	public boolean redo(){
		boolean ejecutado = true;
		Ficha turnoAux;
		
		if(!this.redoStack.isEmpty()){
			this.redoStack.peek().getJugador().incrMov();
			this.undoStack[this.numUndo] = this.redoStack.peek();
			this.numUndo++;
			
			this.turno = this.reglas.siguienteTurno(this.redoStack.peek().getJugador(), this.tablero);
			turnoAux = this.redoStack.peek().getJugador();
			this.redoStack.pop().redo(this.tablero);
			
			for (Observer o: obs){
				o.onMovimientoEnd(this.tablero, turnoAux, this.turno);
			}
		}
		else{
			ejecutado = false;
			for(Observer o: obs){
				o.onReDoNotPossible(this.tablero,this.turno);
			}
			
		}
		
		return ejecutado;
	}
	/**
	 * Devuelve el turno actual,
	 * Ficha.VACIA si la partida ha terminado
	 * @return Ficha
	 */
	public Ficha getTurno(){
		if(isTerminada())
			this.turno = Ficha.VACIA;
		
		return this.turno;
	}
	
	/**
	 * Devuelve el ganador de la partida
	 * Ficha.VACIA si la partida ha terminado en tablas
	 * @return Ficha
	 */
	public Ficha getGanador(){
		if(isTerminada() && this.reglas.tablas(this.turno, this.tablero))
			this.ganador = Ficha.VACIA;
		
		return this.ganador;
	}
	
	/**
	 * Devuelve si la partida a terminado o no
	 * @return bool
	 */
	public boolean isTerminada(){
		return this.terminada;
	}
	
	/****************
	 * USAR SOLO PARA LOS TESTS
	 ****************/
	public Tablero getTablero(){
		return this.tablero;
	}
		
	/**
	 * Devuelve la representacion de la partida(tablero y
	 * de quien es el turno,quien ha ganado la partida o
	 * si ha terminado en tablas)
	 * 
	 * @return String
	 */
	@Override
	public String toString(){
		String partida;
		
		partida = this.tablero.toString();
		if(!isTerminada())
			partida += "Juegan " + this.turno.getNombre();
		else{
			if(getGanador() == Ficha.VACIA)
				partida += "Partida terminada en tablas.";
			else{
				partida += "Ganan las " + getGanador().getNombre();
			}
		}
		
		return partida;
	}
	
	/**
	 * Guarda un Movimiento en el array undoStack.
	 * Guarda los 10 últimos movimientos
	 * @param mov Movimiento
	 */
	private void guardarMovimiento(Movimiento mov){
		if(this.numUndo < 10){
			this.undoStack[this.numUndo] = mov;
			this.numUndo++;
		}
		else{
			for (int i = 0; i < this.numUndo - 1; i++) {
				this.undoStack[i] = this.undoStack[i+1];
			}
			this.undoStack[this.numUndo - 1] = mov;
		}
	}
	
	
	//MÉTODOS AÑADIDOS PRÁCTICA 4
	
	/**
	 * Añade un observador a la partida
	 * @param v
	 */
	public void addObserver(Observer v) {
		this.obs.add(v);
	}
	
	/**
	 * Pone aleatoriamente una ficha en el tablero
	 * de la partida dada la factoria.
	 * @param fact
	 */
	public void ponerAleatorio(FactoriaTipoJuego fact) {
		Movimiento mov = fact.creaJugadorAleatorio().getMovimiento(this.tablero,this.turno);
		this.ejecutaMovimiento(mov);
	}
	
	/**
	 * Cambiar el juego dependiendo de las
	 * reglas indicadas
	 * @param reglas
	 */
	public void cambiarJuego(ReglasJuego reglas){
		iniciarAtrib(reglas);
		for (Observer o: obs){
			o.onCambioJuego(this.tablero,this.turno,reglas.getJuego());
		}
	}
	
	
	//MÉTODOS AÑADIDOS A LA PRÁCTICA 5
	/**
	 * Sirve para avisar a vista consola
	 */
	public void refrescar() {
		for(Observer o: obs){
			o.onMovimientoEnd(this.tablero, this.turno, this.turno);
		}
	}
	
	public void detenerPartida(){
		this.turno.getModo().terminar();
	}

	public void continuarPartida(){
		if(!this.isTerminada()){
			TableroInmutable pistas = null;
			if(this.turno.getTipoTurno() == TipoTurno.HUMANO && this.reglas.getJuego() == Juegos.REVERSI){
				pistas = Utilidades.indicarPosicionesPosiblesRv(this.turno, this.tablero);
			}
			for(Observer o: obs){
				o.onMovimientoStart(this.turno, this.numUndo != 0, !this.redoStack.isEmpty(), pistas);
			}
			this.turno.getModo().comenzar();			
		}
	}
	
	public void pasaTurno(){
		this.turno = this.reglas.siguienteTurno(this.turno, this.tablero);
		
		for(Observer o: obs){
			o.onPasaTurno(this.turno);
		}
	}
}
