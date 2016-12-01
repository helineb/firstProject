package model;

import java.util.ArrayList;

public class Entreprise {
	  	private String nom;
	    private int siret;
	    private ArrayList<Personne> employes;
	    private ArrayList<Equipe> equipes;
	 
	    public Entreprise(String nom, int siret) {
	        this.nom = nom;
	        this.siret = siret;
	        this.employes = new ArrayList<Personne>();
	        this.equipes = new ArrayList<Equipe>();
	    }
	    
	    public Entreprise(String nom, int siret, ArrayList<Personne> employes) {
	        this.nom = nom;
	        this.siret = siret;
	        this.employes = employes;
	        this.equipes = new ArrayList<Equipe>();
	    }
	    
	    public Entreprise(String nom, int siret, ArrayList<Personne> employes, ArrayList<Equipe> equipes) {
	        this(nom,siret,employes);
	        this.equipes = equipes;
	    }

	    public String getNom() {
	        return this.nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public int getSiret() {
	        return this.siret;
	    }

	    public void setSiret(int siret) {
	        this.siret = siret;
	    }

	    public ArrayList<Personne> getEmployes() {
	        return this.employes;
	    }
	    
	    public void setEmployes(ArrayList<Personne> emps) {
	        this.employes = emps;
	    }
	    
	    public void addEmploye(Personne e){
	        this.employes.add(e);
	    }
	    
	    public ArrayList<Equipe> getEquipes() {
	        return this.equipes;
	    }
	    
	    public void setEquipes(ArrayList<Equipe> equs) {
	        this.equipes = equs;
	    }
	    
	    public void addEquipe(Equipe eq){
	        this.equipes.add(eq);
	    }

	    @Override
	    public String toString() {
	        return "Entreprise{" + "nom=" + this.getNom() + ", siret=" + this.getSiret() + ", employes=" + this.getEmployes() + ", equipes=" + this.getEquipes() + "}";
	    }
}
