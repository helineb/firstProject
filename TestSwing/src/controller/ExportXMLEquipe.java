package controller;

import java.io.*;

import model.Equipe;

public class ExportXMLEquipe {

	private Equipe equipe;
	
	public ExportXMLEquipe(Equipe equipe) throws IOException {
		this.equipe = equipe;
		export();
	}
	
	private void export() throws IOException {
		try {
			FileInputStream fis = new FileInputStream(new File("equipe.xml"));
			
		}
		catch(IOException e){
			throw e;
		}
	}
	
}
