package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoTresRaya;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.ReglasTresRaya;

public class FactoriaTresRaya implements FactoriaTipoJuego {
	
	private int max;
	
	public FactoriaTresRaya(int maxMov){
		this.max = maxMov;
	}
	
	@Override
	public ReglasJuego creaReglas() {
		ReglasJuego reglas;
		
		if(this.max == 0)
			reglas = new ReglasTresRaya();
		else
			reglas = new ReglasTresRaya(this.max);
		
		return reglas;
	}
	
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {		
		return new MovimientoTresRaya(col, color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoTresRaya(this,in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioTresRaya(this);
	}

}
