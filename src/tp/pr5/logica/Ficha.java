package tp.pr5.logica;

import java.awt.Color;

public enum Ficha{
	VACIA("vacias", " ", new Color(135,206,250), new ModoHumano(), null,0),
	BLANCA("blancas", "O", Color.WHITE, new ModoHumano(), TipoTurno.HUMANO,0),
	NEGRA("negras", "X", Color.BLACK, new ModoHumano(), TipoTurno.HUMANO,0),
	PISTA(null, null,new Color(204,229,255),null,null,0);
	
	private String nombre;
	private String simbolo;
	private Color color;
	private Modo modoJuego;
	private TipoTurno tTurno;
	private int nMovimientos;
	private Ficha(String nomb,String simb, Color col, Modo modo, TipoTurno tipoTurno, int numMovimientos){
		this.nombre = nomb;
		this.simbolo = simb;
		this.color = col;
		this.modoJuego = modo;
		this.tTurno = tipoTurno;
		this.nMovimientos = numMovimientos;
	}
	
	/**
	 * Reinicia el modo de juego de
	 * los jugadores a humanos.
	 */
	public static void reiniciarFichas(){
		Ficha.BLANCA.setModo(new ModoHumano());
		Ficha.NEGRA.setModo(new ModoHumano());
		Ficha.BLANCA.setTipoTurno(TipoTurno.HUMANO);
		Ficha.NEGRA.setTipoTurno(TipoTurno.HUMANO);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getSimbolo(){
		return this.simbolo;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public Modo getModo(){
		return this.modoJuego;
	}	
	
	public void setModo(Modo m){
		this.modoJuego = m;
	}
	
	public TipoTurno getTipoTurno(){
		return this.tTurno;
	}
	
	public void setTipoTurno(TipoTurno tTurno){
		this.tTurno = tTurno;
	}
	
	public int getNumMovimientos(){
		return nMovimientos;
	}
	
	public void setNumMovimientos(int nMovimientos){
		this.nMovimientos = nMovimientos;
	}
	
	public void incrMov(){
		this.nMovimientos++;
	}
	
	public void decrMov(){
		this.nMovimientos--;
	}
}

