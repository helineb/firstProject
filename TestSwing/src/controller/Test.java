package controller;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.*;
import view.*;

public class Test {

	public static Logger logger = Logger.getLogger(Test.class.getName());
	
	//main CTRL + ESPACE
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Création fichier log
		try {
			FileHandler fh = new FileHandler("./log.out", false); // true pour append,
			 // fh1.setFormatter(new XMLFormatter()) par défaut;
			fh.setLevel(Level.ALL);
			//associer ce Handler au Logger
			logger.addHandler(fh);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//Récupération des personnes et des équipes de l'entreprise
		PersonneDAO persDAO = new PersonneDAO();
		ArrayList<Personne> listP = new ArrayList<Personne>();
		EquipeDAO eqDAO = new EquipeDAO();
		ArrayList<Equipe> listEq = new ArrayList<Equipe>();
		
		try {
			listP = persDAO.selectAll();
			listEq = eqDAO.selectAll();
			Entreprise ent = new Entreprise("Entreprise", 152456, listP, listEq);
			//sortie des messages de niveau severe dans le fichier log.out au format XML
			
			logger.log(Level.INFO, "Entreprise créée");
			MenuPrincipal menu= new MenuPrincipal(ent);
			
		}
		catch(Exception e){
			logger.log(Level.SEVERE,"erreur " , e);
		}
		
		
		
		
	}

}
