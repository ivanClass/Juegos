package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoPasaTurno extends Comando {

	public ComandoPasaTurno(ControladorConsola c) {
		super(c);
	}

	@Override
	public Comando parseo(String[] parse) {
		if(parse[0].equalsIgnoreCase("PASA") && parse.length == 1){
			return new ComandoPasaTurno(this.control);
		}
		else
			return null;
	}

	@Override
	public void run() {
		this.control.opcPasarTurno();
	}

}
