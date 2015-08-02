package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego{
	
	private int max;
	
	public FactoriaComplica(int maxMov){
		this.max = maxMov;
	}
	@Override
	public ReglasJuego creaReglas() {
		ReglasJuego reglas;
		
		if(this.max == 0)
			reglas = new ReglasComplica();
		else
			reglas = new ReglasComplica(this.max);
		
		return reglas;
	}
	
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}
	
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoComplica(this,in);
	}
	
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica(this);
	}

}
