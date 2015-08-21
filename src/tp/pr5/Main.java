package tp.pr5;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.GUI.VistaConsola;
import tp.pr5.GUI.VistaGUI;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.ControladorGUI;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.FactoriaTresRaya;
import tp.pr5.logica.Partida;

/*************************************************************
 * 					PR_5.GUI					2014/2015
 * FDI-UCM
 * LAB 5
 * GRUPO 18
 * 
 * IVAN AGUILERA CALLE
 * DANIEL GARCIA MORENO
 *************************************************************/

/**
 * CAMBIOS SEGUNDA ENTREGA TP.PR5.
 * ->Arreglado el cambio de turno en deshacer de reversi => negras +negras y deshacer => negras, no blancas.
 * 		En el undo de partida :
 * 			Antes : this.turno = this.reglas.siguienteTurno(Ficha ultimoEnPoner, Tablero t);
 *
 * 			Después: this.turno = this.undoStack[numUndo -1].getJugador();
 * 
 * ->Hemos quitado los invokeLater de onReiniciar y onCambioJuego ya que no hacían falta
 * 	 de panelIzquierdo, panelGestJugadores
 * 
 * ->En controladorGUI en reiniciar y cambiarJuego reiniciamos los jugadores a humanos antes de hacer reset/cambio
 * 	 juego.
 */
public class Main {
	private static final int FILAS_DEFECTO = 10;
	private static final int COLS_DEFECTO = 10;
	private static final FactoriaTipoJuego FACTORIA_DEFECTO = new FactoriaConecta4(0);
	private static final String MODO_GRAFICO_DEF = "console";
	
	private static String modoElegido;
	private static FactoriaTipoJuego factoriaElegida;
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Options options = crearOpciones();
		inicioDeAplicacionArgs(options, args);

		Partida partida = new Partida(factoriaElegida.creaReglas());
		if(modoElegido.equalsIgnoreCase("window")){	
			
	        ControladorGUI contr = new ControladorGUI(factoriaElegida,partida);
	        VistaGUI f = new VistaGUI(contr);
	        f.setVisible(true);
		}
		else{
			Scanner in = new Scanner(System.in);
			ControladorConsola controlador = new ControladorConsola(factoriaElegida,partida,in);
			VistaConsola vista = new VistaConsola(controlador);
			controlador.run();
		}
	}
	
	/**
	 * Se construyen las opciones disponibles para los comandos
	 * @return Options
	 */
	@SuppressWarnings("static-access")
	private static Options crearOpciones(){
		Options options = new Options();
		 
		options.addOption("h","help",false,"Muestra esta ayuda.");
			
		options.addOption(OptionBuilder .withLongOpt("game")
										.withDescription("Tipo de juego (c4, co, gr, rv, tr). Por defecto, c4.")
										.withArgName("game")
										.hasArg()
										.create("g"));
		
		options.addOption(OptionBuilder .withLongOpt("ui")
										.withDescription("Tipo de interfaz (console, window). Por defecto, console.")
										.withArgName("tipo")
										.hasArg()
										.create("u"));
			
		options.addOption(OptionBuilder .withLongOpt("tamX")
										.withDescription("Número de columnas del tablero (sólo para Gravity). Por defecto, 10.")
										.withArgName("columnNumber")
										.hasArg()
										.create("x"));
		options.addOption(OptionBuilder .withLongOpt("tamY")
										.withDescription("Número de filas del tablero (sólo para Gravity). Por defecto, 10.")
										.withArgName("rowNumber")
										.hasArg()
										.create("y"));
		
		options.addOption(OptionBuilder .withLongOpt("numMovs")
				.withDescription("Número máximo de movimientos por jugador,0 si no hay límite.")
				.withArgName("num-movs")
				.hasArg()
				.create("n"));
		
		return options;
	}
	
	/**
	 * Inicia la aplicacion dependiendo de los argumentos
	 * @param options
	 * @param args
	 */
	private static void inicioDeAplicacionArgs(Options options,String[] args){
		CommandLineParser parser = new BasicParser();
		
		String nfilas;
		String ncols;
		String juego;
		factoriaElegida = FACTORIA_DEFECTO;
		modoElegido = MODO_GRAFICO_DEF;
		 try{
			 	CommandLine cmdLine = parser.parse(options, args);
				if(cmdLine.hasOption("g")){
					
					juego = cmdLine.getOptionValue("g");
					if(juego.equalsIgnoreCase("gr")){
						ncols = cmdLine.getOptionValue("x");
						nfilas = cmdLine.getOptionValue("y");
						if(nfilas == null && ncols == null){ //si no hay ni columnas ni filas en los argumentos
							if(!cmdLine.hasOption("n"))
								factoriaElegida = new FactoriaGravity(COLS_DEFECTO, FILAS_DEFECTO,0);
							else{
								String s = cmdLine.getOptionValue("n");
								factoriaElegida = new FactoriaGravity(COLS_DEFECTO, FILAS_DEFECTO,Integer.parseInt(s));
							}
						}
						else if(nfilas != null && ncols != null){
							if(!cmdLine.hasOption("n"))
								factoriaElegida = new FactoriaGravity(Integer.parseInt(ncols),Integer.parseInt(nfilas),0);
							else{
								String s = cmdLine.getOptionValue("n");
								factoriaElegida = new FactoriaGravity(Integer.parseInt(ncols),Integer.parseInt(nfilas),Integer.parseInt(s));
							}
						}
						else{
							throw new ParseException("Faltan argumentos.");
						}
					}
					else if(juego.equalsIgnoreCase("tr")){ //CAMBIAR ESTO DE FIL_dEFECTO,COL_DEFECTO -> NO ME GUSTA ASI
						ncols = cmdLine.getOptionValue("x");
						nfilas = cmdLine.getOptionValue("y");
						if(nfilas == null && ncols == null){ //si no hay ni columnas ni filas en los argumentos
							if(!cmdLine.hasOption("n"))
								factoriaElegida = new FactoriaTresRaya(0,6,7);
							else{
								String s = cmdLine.getOptionValue("n");
								factoriaElegida = new FactoriaTresRaya(Integer.parseInt(s),6,7);
							}
						}
						else if(nfilas != null && ncols != null){
							if(!cmdLine.hasOption("n"))
								factoriaElegida = new FactoriaTresRaya(0,Integer.parseInt(nfilas),Integer.parseInt(ncols));
							else{
								String s = cmdLine.getOptionValue("n");
								factoriaElegida = new FactoriaTresRaya(Integer.parseInt(s), Integer.parseInt(nfilas), Integer.parseInt(ncols));
							}
						}
						else{
							throw new ParseException("Faltan argumentos.");
						}
					}
					else if(juego.equalsIgnoreCase("co")){
						if(cmdLine.getArgs().length == 0){
							if(!cmdLine.hasOption("n"))
								factoriaElegida = new FactoriaComplica(0);
							else{
								String s = cmdLine.getOptionValue("n");
								factoriaElegida = new FactoriaComplica(Integer.parseInt(s));
							}
						}
						else{
							String message = "Argumentos no entendidos:";
							String [] a = cmdLine.getArgs();
							for(String s : a){
								message += " " + s;
							}
							throw new ParseException(message);
						}
					}
					else if(juego.equalsIgnoreCase("c4")){
							if(cmdLine.getArgs().length == 0){
								if(!cmdLine.hasOption("n"))
									factoriaElegida = new FactoriaConecta4(0);
								else{
									String s = cmdLine.getOptionValue("n");
									factoriaElegida = new FactoriaConecta4(Integer.parseInt(s));
								}
							}
							else{
								String message = "Argumentos no entendidos:";
								String [] a = cmdLine.getArgs();
								for(String s : a){
									message += " " + s;
								}
								throw new ParseException(message);
							}
					}
					else if(juego.equalsIgnoreCase("rv")){
						if(cmdLine.getArgs().length == 0){
							if(!cmdLine.hasOption("n"))
								factoriaElegida = new FactoriaReversi(0);
							else{
								String s = cmdLine.getOptionValue("n");
								factoriaElegida = new FactoriaReversi(Integer.parseInt(s));
							}
						}
						else{
							String message = "Argumentos no entendidos:";
							String [] a = cmdLine.getArgs();
							for(String s : a){
								message += " " + s;
							}
							throw new ParseException(message);
						}
					}
					else{
						String message = "Juego '" + juego +"' incorrecto.";
						throw new ParseException(message);
					}
				}
				if(cmdLine.hasOption("u")){
					modoElegido = cmdLine.getOptionValue("u");
						if(modoElegido.equalsIgnoreCase("window")){
							if(cmdLine.getArgs().length == 0)
								modoElegido = "window";
							else{
								String message = "Argumentos no entendidos:";
								String [] a = cmdLine.getArgs();
								for(String s : a){
									message += " " + s;
								}
								throw new ParseException(message);
							}
						}
						else if(modoElegido.equalsIgnoreCase("console")){
							if(cmdLine.getArgs().length == 0)
								modoElegido = "console";
							else{
								String message = "Argumentos no entendidos:";
								String [] a = cmdLine.getArgs();
								for(String s : a){
									message += " " + s;
								}
								throw new ParseException(message);
							}
						}
						else{
							String message = "Modo '" + modoElegido +"' incorrecto.";
							throw new ParseException(message);
						}
				
				}
				if(cmdLine.hasOption("h")){
					 new HelpFormatter().printHelp(Main.class.getCanonicalName(), options ,true);  
		             System.exit(0);
				}
			}catch(ParseException ex){
				System.err.print("Uso incorrecto: ");
				System.err.println(ex.getMessage());
				System.err.println("Use -h|--help para más detalles.");
				System.exit(1);
			}catch(NumberFormatException ex){ //Por si se introducen letras en vez de números
				System.err.println("Error: datos mal introducidos");
				System.err.println("Use -h|--help para más detalles.");
	            System.exit(1);
			}	
	}
	
	
	

}
