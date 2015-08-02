package tp.pr5.logica;

import tp.pr5.control.ControladorGUI;

public class ModoAutomatico implements Modo{
	private ControladorGUI contr;
	private Thread hebra;
	
	public ModoAutomatico(ControladorGUI controlador){
		this.contr = controlador;
	}
	
	@Override
	public void comenzar() {
		this.hebra = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					contr.ponerAleatorio();
				} catch (InterruptedException e) {
				}
			};
		};

		this.hebra.start();
	}

	@Override
	public void terminar() {
		if(this.hebra != null)
			this.hebra.interrupt();	
	}

}
