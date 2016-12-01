package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import model.Equipe;
import model.EquipeDAO;
import model.Personne;
import view.FormEquipe;
import view.MenuPrincipal;


public class ActionListenerManageEquipe implements ActionListener {

	private FormEquipe formEquipe;
	private String action;
	
	public ActionListenerManageEquipe(FormEquipe formEq, String action){
		this.formEquipe = formEq;
		this.action = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Personne leader = (Personne) this.formEquipe.getCbLeader().getSelectedItem();
		Equipe equipeInsert = new Equipe(this.formEquipe.getTfNom().getText(),leader);
		TableModel modelePers = this.formEquipe.getTablePersonne().getModel();
		for (int i=0;i<modelePers.getRowCount();i++){
			Personne emp = new Personne(Integer.parseInt((String)modelePers.getValueAt(i, 0)), (String)modelePers.getValueAt(i, 1), (String)modelePers.getValueAt(i, 2),0);
			equipeInsert.addEmploye(emp);
		}
		try {
			EquipeDAO equDAO = new EquipeDAO();
			switch(this.action){
				case "add":
					equDAO.insert(equipeInsert);
					JOptionPane.showMessageDialog(this.formEquipe,"Equipe ajoutée");
					break;
				case "update":
					equipeInsert.setId(this.formEquipe.getEquipe().getId());
					equDAO.update(equipeInsert);
					JOptionPane.showMessageDialog(this.formEquipe,"Equipe modifiée");
					break;
				case "delete":
					equDAO.delete(this.formEquipe.getEquipe());
					JOptionPane.showMessageDialog(this.formEquipe,"Equipe supprmée");
					break;
			}
			
		}
		catch(Exception e1){
			Test.logger.log(Level.SEVERE,"erreur " , e1);
		}
		
		//Récupération de la liste des Equipes dans la bdd après traitement
		try {
			EquipeDAO eqDAO = new EquipeDAO();
			ArrayList<Equipe> listEq = eqDAO.selectAll();
			this.formEquipe.getEntreprise().setEquipes(listEq);
		}
		catch(Exception e2){
			Test.logger.log(Level.SEVERE,"erreur " , e2);
		}
		
		
		this.formEquipe.dispose();
		MenuPrincipal menu = new MenuPrincipal(this.formEquipe.getEntreprise());
		
	}

}
