package tp.pr5.control.comandos;

import tp.pr5.control.ControladorConsola;

public class ComandoRehacer extends Comando{
	
	public ComandoRehacer(ControladorConsola c){
		super(c);
	}
	public Comando parseo(String[] parse) {
		if(parse[0].equalsIgnoreCase("rehacer"))
			return new ComandoRehacer(this.control);
		else
			return null;
	}

	@Override
	public void run() {
		this.control.opcRehacer();	
	}
}
