package tp.pr5.GUI;

import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juegos;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

public class VistaConsola implements Observer{
	
	private ControladorConsola control;
	
	public VistaConsola(ControladorConsola c){
		this.control = c;
		this.control.addObservador(this);
	}
	
	private String mostrarTextoInf(TableroInmutable tab, Ficha turno, boolean reinicio){
		String ret = "";
		
		if(reinicio)
			ret = "Partida reiniciada" + "\n" + tab.toString() + "Juegan " + turno.getNombre() + "\n" +
					"Mov blancas: " + Ficha.BLANCA.getNumMovimientos() + "\n" +
					"Mov negras: " + Ficha.NEGRA.getNumMovimientos() + "\n" + "Qué quieres hacer? ";
		else
			ret = tab.toString() + "Juegan " + turno.getNombre() + "\n"  + "Mov blancas: " + Ficha.BLANCA.getNumMovimientos() + "\n" +
					"Mov negras: " + Ficha.NEGRA.getNumMovimientos() + "\n" + "Qué quieres hacer? ";
		
		return ret;
	}	
	
	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		
		System.out.print(mostrarTextoInf(tab, turno, false));
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		//System.err.println(movimientoException.getMessage());	
	}
	
	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador, Ficha turno) {
		System.out.print(tablero.toString());
		System.out.println("Mov blancas: " + Ficha.BLANCA.getNumMovimientos());
		System.out.println("Mov negras: " + Ficha.NEGRA.getNumMovimientos());
		if(ganador != Ficha.VACIA)
			System.out.println("Ganan las " + ganador.getNombre());
		else
			System.out.println("Partida terminada en tablas.");
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		System.err.println("Imposible deshacer.");
		System.out.print(mostrarTextoInf(tablero, turno, false));
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		System.out.print(mostrarTextoInf(tablero, turno, false));
	}

	@Override
	public void onReiniciar(TableroInmutable tablero, Ficha turno) {
		System.out.print(mostrarTextoInf(tablero, turno, true));
	}

	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno, Juegos juego) {
		System.out.print(mostrarTextoInf(tablero, turno, true));
	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable tab, Ficha turno, MovimientoInvalido movimientoException) {
		System.err.println(movimientoException.getMessage());
		System.out.print(mostrarTextoInf(tab, turno, false));
		
	}

	@Override
	public void onMovimientoStart(Ficha turno,boolean hayMasDeshacer,boolean hayMasRehacer, TableroInmutable pistas){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPasaTurno(Ficha turno) {
		// TODO Auto-generated method stub
		
	}
}
