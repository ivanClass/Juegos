package tp.pr5.logica;

import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.FactoriaTresRaya;

public enum Juegos {
	CONECTA4("Conecta 4"){
		public FactoriaTipoJuego crearFactoria(int filas, int columnas, int max){
			return new FactoriaConecta4(max);
		}
	},
	COMPLICA("Complica"){
		public FactoriaTipoJuego crearFactoria(int filas, int columnas,int max){
			return new FactoriaComplica(max);
		}
	},
	GRAVITY("Gravity"){
		public FactoriaTipoJuego crearFactoria(int filas, int columnas,int max){
			return new FactoriaGravity(columnas, filas,max);
		}
	},
	REVERSI("Reversi"){
		public FactoriaTipoJuego crearFactoria(int filas, int columnas,int max){
			return new FactoriaReversi(max);
		}
	},
	TRESENRAYA("Tres en raya"){

		@Override
		public FactoriaTipoJuego crearFactoria(int filas, int columnas, int max) {
			return new FactoriaTresRaya(max);
		}
		
	};
		
	private String nombre;
	
	private Juegos(String nomb){
		this.nombre = nomb;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public abstract FactoriaTipoJuego crearFactoria(int filas, int columnas, int max);
	
}
