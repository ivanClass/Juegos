package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego {
	private int nfilas;
	private int ncols;
	private int max;

	/**
	 * Constructor de FactoriaGravity,
	 * asigna el numero de filas y columnas
	 * que tendrá gravity cuando se llame a
	 * creaReglas()
	 * @param ncols		Número de columnas
	 * @param nfilas	Número de filas
	 */
	public FactoriaGravity(int ncols,int nfilas, int maxMov) {
		this.ncols = ncols;
		this.nfilas = nfilas;
		this.max = maxMov;
	}
	
	
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(this.ncols, this.nfilas);
	}
	
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}
	
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(this,in);
	}
	
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity(this);
	}

}
