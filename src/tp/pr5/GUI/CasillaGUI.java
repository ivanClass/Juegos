package tp.pr5.GUI;

import java.awt.Dimension;

import javax.swing.*;

import tp.pr5.logica.Ficha;

@SuppressWarnings("serial")
public class CasillaGUI extends JButton{
	private int nfila;
	private int ncol;
	private Ficha color;
	
	public CasillaGUI(int nfila,int ncol,Ficha color){
		this.nfila = nfila;
		this.ncol = ncol;
		this.color = color;
		this.setPreferredSize(new Dimension(40,40));
	}
	
	public int getFila() {
		return nfila;
	}

	public int getCol() {
		return ncol;
	}



	public Ficha getColor() {
		return color;
	}

	public void setColor(Ficha color) {
		this.color = color;
	}
	

}
