package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.Personne;
import model.PersonneDAO;
import view.FormPersonne;
import view.MenuPrincipal;

public class ActionListenerManageEmploye implements ActionListener {
	
	private FormPersonne formPersonne;
	private String action;
	
	public ActionListenerManageEmploye(FormPersonne formPers, String action){
		this.formPersonne = formPers;
		this.action = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Personne p = new Personne(this.formPersonne.getTfNom().getText(), this.formPersonne.getTfPrenom().getText(),Integer.parseInt(this.formPersonne.getTfAge().getText()));
		PersonneDAO dao = new PersonneDAO();
		try {
			switch(this.action){
				case "add":
					int id = dao.insert(p);
					p.setId(id);
					this.formPersonne.getEntreprise().addEmploye(p);
					JOptionPane.showMessageDialog(this.formPersonne,"Personne ajoutée");
				break;
				case "update":
					p.setId(this.formPersonne.getEmp().getId());
					dao.update(p);
					JOptionPane.showMessageDialog(this.formPersonne,"Personne modifiée");
					break;
				case "delete":
					dao.delete(this.formPersonne.getEmp());
					JOptionPane.showMessageDialog(this.formPersonne,"Personne supprimée");
					break;
				default:
					JOptionPane.showMessageDialog(this.formPersonne,"Qu'est-ce que vous voulez faire?!");
					break;
			}
		}
		catch(Exception e1){
			Test.logger.log(Level.SEVERE,"erreur " , e1);
		}
		
		try {
			PersonneDAO persDAO = new PersonneDAO();
			ArrayList<Personne> listP = new ArrayList<Personne>();
			listP = persDAO.selectAll();
			this.formPersonne.getEntreprise().setEmployes(listP);
		}
		catch(Exception e2){
			Test.logger.log(Level.SEVERE,"erreur " , e2);
		}
		
		this.formPersonne.dispose();
		MenuPrincipal menu = new MenuPrincipal(this.formPersonne.getEntreprise());
		
	}

}
