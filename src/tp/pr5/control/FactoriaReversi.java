package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.ReglasReversi;

public class FactoriaReversi implements FactoriaTipoJuego {
	private int max;
	
	public FactoriaReversi (int maxMov){
		this.max = maxMov;
	}
	
	@Override
	public ReglasJuego creaReglas() {
		ReglasJuego reglas;
		
		if(this.max == 0)
			reglas = new ReglasReversi();
		else
			reglas = new ReglasReversi(this.max);
		
		return reglas;
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoReversi(col, fila, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoReversi(this,in);

	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi(this);
	}
}
