package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;

public class FactoriaConecta4 implements FactoriaTipoJuego {
	
	private int max;
	
	public FactoriaConecta4(int maxMov){
		this.max = maxMov;
	}
	@Override
	public ReglasJuego creaReglas() {
		ReglasJuego reglas;
		
		if(this.max == 0)
			reglas = new ReglasConecta4();
		else
			reglas = new ReglasConecta4(this.max);
		
		return reglas;
	}
	
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {		
		return new MovimientoConecta4(col, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoConecta4(this,in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4(this);
	}
}
