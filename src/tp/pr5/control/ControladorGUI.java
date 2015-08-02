package tp.pr5.control;

import tp.pr5.GUI.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.ModoAutomatico;
import tp.pr5.logica.ModoHumano;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TipoTurno;

public class ControladorGUI {
	private Partida modelo;
	private FactoriaTipoJuego factoria;
	
	public ControladorGUI(FactoriaTipoJuego f, Partida modelo){
		this.factoria = f;
		this.modelo = modelo;
	}
	
	/**
	 * Añade observadores de la vista al modelo
	 * @param v
	 */
	public void addObservador(Observer v){
		this.modelo.addObserver(v);
	}

	/**
	 * Deshace el último movimiento realizado
	 */
	public void deshacer() {
		this.modelo.undo();
		this.modelo.continuarPartida();
	}

	/**
	 * Reinicia la partida (sin quitar el tablero y 
	 * añadir un tablero nuevo a la vista)
	 */
	public void reiniciar() {
		this.modelo.detenerPartida();
		Ficha.reiniciarFichas();
		this.modelo.reset(this.factoria.creaReglas());
		this.modelo.continuarPartida();
	}
	
	/**
	 * Ponee una ficha en la fila y columnas
	 * indicadas
	 * @param fila
	 * @param col
	 */
	public void poner(int fila, int col) {
		this.modelo.ejecutaMovimiento(this.factoria.creaMovimiento(col, fila, this.modelo.getTurno()));
		this.modelo.continuarPartida();
	}
	
	/**
	 * Sirve para iniciar las vistas
	 */
	public void start(){
		this.modelo.cambiarJuego(factoria.creaReglas());
	}
	
	/**
	 * Pone aleatoriamente una ficha en el tablero
	 */
	public void ponerAleatorio() {
		this.modelo.ponerAleatorio(this.factoria);
		this.modelo.continuarPartida();
	}

	/**
	 * Cambia el juego al juego selected
	 * @param selected
	 * @param nfilas
	 * @param ncols
	 */
	public void cambiarJuego(Juegos selected, int nfilas, int ncols, int max) {
		this.modelo.detenerPartida();
		this.factoria = selected.crearFactoria(nfilas, ncols,max);
		Ficha.reiniciarFichas();
		this.modelo.cambiarJuego(this.factoria.creaReglas());
		this.modelo.continuarPartida();
	}
	
	/**
	 * cambia el tipo de jugador f al tipo tTurno
	 * @param f color del jugador
	 * @param tTurno Humano o Automatico
	 */
	public void cambiarJugador(Ficha f, TipoTurno tTurno){
		this.modelo.detenerPartida();
		f.setTipoTurno(tTurno);
		
		if(f.getTipoTurno() == TipoTurno.HUMANO){
			f.setModo(new ModoHumano());
		}
		else{
			f.setModo(new ModoAutomatico(this));
		}
		this.modelo.continuarPartida();
	}
	
	public void pasaTurno(){
		this.modelo.pasaTurno();
		this.modelo.continuarPartida();
	}
	
}
