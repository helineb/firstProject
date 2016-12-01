package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Equipe;
import view.FormEquipe;

public class ActionListenerExportXML implements ActionListener{

	private JFrame frame;
	
	public ActionListenerExportXML(JFrame frame){
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(this.frame instanceof FormEquipe){
			Equipe equipe = ((FormEquipe)this.frame).getEquipe();
			if(equipe != null){
				try {
					ExportXMLEquipe export = new ExportXMLEquipe(equipe);
				}
				catch(IOException e){
					JOptionPane.showMessageDialog(this.frame, "Erreur lors de l'export : "+e.getMessage());
				}
			}
		}
	}

}
