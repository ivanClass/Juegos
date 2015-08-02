package tp.pr5.control;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import tp.pr5.GUI.Observer;
import tp.pr5.control.comandos.Comando;
import tp.pr5.control.comandos.ComandoAyuda;
import tp.pr5.control.comandos.ComandoCambioJugador;
import tp.pr5.control.comandos.ComandoDeshacer;
import tp.pr5.control.comandos.ComandoJugar;
import tp.pr5.control.comandos.ComandoPoner;
import tp.pr5.control.comandos.ComandoReiniciar;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;

public class ControladorConsola{
	private  Jugador jugblancas;
	private  Jugador jugnegras;
	private  Jugador jugturno;
	private FactoriaTipoJuego factoria;
	private Partida partida;
	private Scanner in;
	private ArrayList<Comando> misComandos;
	
	public ControladorConsola(FactoriaTipoJuego f, Partida p, Scanner in){
		this.factoria = f;
		this.partida = p;
		this.in = in;
		this.jugblancas = factoria.creaJugadorHumanoConsola(in);
		this.jugnegras = factoria.creaJugadorHumanoConsola(in);
		this.jugturno = null;
		this.misComandos = new ArrayList<Comando>();
		this.inicializarComandos();
	}
	
	public void addObservador(Observer v) {
		partida.addObserver(v);
		
	}
		
	public void run(){
		String [] frase;
		
		this.partida.refrescar();
		
		frase = pedirDatos();
        
		while(!frase[0].equalsIgnoreCase("salir") && !this.partida.isTerminada()){
		
			Comando i = null;
			boolean comandoUsado = false;
			
			for(Comando c: this.misComandos){
				i = c.parseo(frase);
				
				if(i != null){
					i.run();
					comandoUsado = true;
				}
			}
			
			if(comandoUsado == false){
				System.err.println("No te entiendo.");
				this.partida.refrescar();
			}
			else
				i = null;
			
			if(!this.partida.isTerminada()){
				frase = pedirDatos();
			}
		}
		
	}
	
	public void opcReiniciar(){
		this.partida.reset(this.factoria.creaReglas());
	}
	
	public void opcDeshacer(){
		this.partida.undo();
	}
	
	
	/**
	 * Dependiendo del array introducido creara un tipo de juego u otro
	 * @param frase array de string con el posible juego a cambiar (y dimensiones si procede)
	 */
	public void opcCambiarJuego(String[] frase){
		boolean error = true;
		if(frase.length == 3 || frase.length == 5){
			if(frase[1].equalsIgnoreCase("c4")){
				jugarC4(Integer.parseInt(frase[2]));
				error = false;
			}
			else if (frase[1].equalsIgnoreCase("co")){
				jugarCo(Integer.parseInt(frase[2]));
				error = false;
			}
			else if (frase[1].equalsIgnoreCase("rv")){
				jugarRv(Integer.parseInt(frase[2]));
				error = false;
			}
			else if (frase.length == 5 && frase[1].equalsIgnoreCase("gr")){ 
				jugarGravity(frase);
				error = false;
			}
		}
		
		if(error){
			System.err.println("No te entiendo.");
			partida.refrescar();
		}
		else{
			reiniciarPartida();
		}
	}

	/**
	 * Dependiendo del string introducido crea un jugador de tipo humano o aleatorio
	 * @param frase string al que le puede llegar los dos posible tipos de judador
	 * @return jugador jugador resultante del cambio
	 */
	private Jugador cambioJugador(String frase){
		Jugador jugador = null;
		
		if(frase.equalsIgnoreCase("humano"))
			jugador = factoria.creaJugadorHumanoConsola(this.in);
		else if(frase.equalsIgnoreCase("aleatorio"))
			jugador = factoria.creaJugadorAleatorio();
		
		return jugador;
	}
	
	/**
	 * Se encarga de controlar la parte del cambio de
	 * jugador.
	 * 
	 * Si el usuario introduce jugador blancas aleatorio
	 * el jugador blancas pasa a ser aleatorio, igual con negras.
	 * 
	 * Si el usuario introduce jugador blancas humano
	 * el jugador blancas pasa a ser humano, igual con negras.
	 * 
	 * Si no introduce los datos exactos se muestra un mensaje de
	 * error("No te entiendo")
	 * @param frase
	 */
	public void opcCambiarJugador(String[] frase){
		Jugador jugaux;
		boolean error = true;
		if(frase.length == 3){
			if(frase[1].equalsIgnoreCase("blancas")){
				jugaux = cambioJugador(frase[2]);
				if(jugaux != null){
					this.jugblancas = cambioJugador(frase[2]);
					error = false;
				}
			}
			else if(frase[1].equalsIgnoreCase("negras")){
				jugaux = cambioJugador(frase[2]);
				if(jugaux != null){
					this.jugnegras = cambioJugador(frase[2]);
					error = false;
				}
			}
		}
		
		if(error){
			System.err.println("No te entiendo.");
		}

	}
	
	private void reiniciarPartida(){
		this.partida.reset(factoria.creaReglas());
		iniciarJugadores();
	}
	/**
	 * Realiza las operaciones necesarias para empezar
	 * una partida del Conecta4
	 */
	private void jugarC4(int max){
		factoria = new FactoriaConecta4(max);
	}
	
	/**
	 * Realiza las operaciones necesarias para empezar
	 * una partida del Complica
	 */
	private void jugarCo(int max){
		factoria = new FactoriaComplica(max);
	}
	
	
	private void jugarRv(int max) {
		factoria = new FactoriaReversi(max);
	}
	
	/**
	 * Realiza las operaciones necesarias para empezar
	 * una partida del Gravity
	 * 
	 * @param frase String[]
	 */
	private void jugarGravity(String[] frase){
		try{
			Integer num1 = Integer.parseInt(frase[2]); // pasa de string a entero
			Integer num2 = Integer.parseInt(frase[3]);
			factoria = new FactoriaGravity(num1,num2,Integer.parseInt(frase[4]));
		}
		catch(Exception e){
			System.err.println("No te entiendo.");
		}
			
	}
	
	
	/**
	 * Inicia los dos jugadores,del tipo del juego
	 * que estemos usando, como humanos y pone
	 * como primer jugador de la partida a jugblancas
	 */
	private void iniciarJugadores(){
		jugblancas = factoria.creaJugadorHumanoConsola(in);
		jugnegras = factoria.creaJugadorHumanoConsola(in);
		this.jugturno = jugblancas;
	}
	
	/**
	 * Se encarga de controlar la parte de ejecución
	 * de un movimiento(cuando el usuario introduce poner)
	 */
	public void opcPoner(){
		try{
			if(this.partida.getTurno() == Ficha.NEGRA){
				this.jugturno = jugnegras;
			}
			else{
				this.jugturno = jugblancas;
			}
			this.partida.poner(this.jugturno);
		} catch (MovimientoInvalido e){
			System.err.println(e.getMessage());
		}
		catch(InputMismatchException e){
			System.err.println("Columna incorrecta.");
			this.in.nextLine();
			partida.refrescar();
		};
	}
	
	/**
	 * Se encarga de solicitar los datos al usuario
	 * 
	 * @return String[] - array de string que contiene
	 * lo que el usuario haya introducido por consola
	 */
	private String[] pedirDatos(){
		
		String frase = this.in.nextLine();
		frase = frase.toLowerCase();
		
        return frase.split(" ");
	}
	
	/**
	 * Añade al array de comandos todos los comando disponibles
	 */
	private void inicializarComandos(){
		this.misComandos.add(new ComandoAyuda(this, this.partida));
		this.misComandos.add(new ComandoCambioJugador(this, null, this.partida));
		this.misComandos.add(new ComandoDeshacer(this));
		this.misComandos.add(new ComandoJugar(this, null));
		this.misComandos.add(new ComandoPoner(this));
		this.misComandos.add(new ComandoReiniciar(this));
	}
}
