package model;

public class Personne {

	private int id;
	private String nom;
	private String prenom;
	private int age;
	
	public Personne(String nom, String prenom, int age) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}
	public Personne(int id, String nom, String prenom, int age) {
		this(nom,prenom,age);
		this.id = id;
	}
	
	public int getId(){
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
	
	public String getPrenom() {
		return this.prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Personne [nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", age=" + this.getAge() + "]";
	}

	
	
	
}
