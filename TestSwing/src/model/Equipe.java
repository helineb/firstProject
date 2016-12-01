package model;

import java.util.ArrayList;

public class Equipe {
	
	private int id;
    private String nom;
    private Personne leader;
    private ArrayList<Personne> employes;

    public Equipe(String nom, Personne leader) {
        this.nom = nom;
        this.leader = leader;
        this.employes = new ArrayList<Personne>();
    }
    
    public Equipe(int id, String nom, Personne leader) {
    	this(nom, leader);
    	this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    public void setId(int id){
    	this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Personne getLeader(){
        return this.leader;
    }
    
    public void setLeader(Personne p){
        this.leader = p;
    }
    
    public ArrayList<Personne> getEmployes() {
        return this.employes;
    }
    public void setEmployes(ArrayList<Personne> listPersonne) {
        this.employes = listPersonne;
    }

    public void addEmploye(Personne e){
        this.employes.add(e);
    }

    @Override
    public String toString() {
        return "Equipe{" + "id=" + this.getId() + ", nom=" + this.getNom() + ", leader= " + this.leader + ", employes=" + this.getEmployes() + "}";
    }
    
}
