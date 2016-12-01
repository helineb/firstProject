package view;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;

import controller.ActionListenerManageEmploye;
import controller.Test;
import model.Entreprise;
import model.Equipe;
import model.EquipeDAO;
import model.Personne;
import model.PersonneDAO;

public class FormPersonne extends JFrame{

	private JLabel lblNom;
	private JTextField tfNom;
	private JTextField tfPrenom;
	private JTextField tfAge;
	private Personne emp;
	private Entreprise ent;
	
	public FormPersonne(Entreprise ent) {
		this.ent= ent;
		this.emp = null;
		initFenetre();
		setVisible(true);
	}
	
	public FormPersonne(Entreprise ent,Personne emp) {
		this.ent = ent;
		this.emp = emp;
		initFenetre();
		remplirFenetre();
		setVisible(true);
	}
	
	//Getters
	public JTextField getTfNom(){
		return this.tfNom;
	}
	public JTextField getTfPrenom(){
		return this.tfPrenom;
	}
	public JTextField getTfAge(){
		return this.tfAge;
	}
	public Personne getEmp(){
		return this.emp;
	}
	public Entreprise getEntreprise(){
		return this.ent;
	}
	
	private void initFenetre(){
		
		//positionnement + taille
		setBounds(50,50,300,300);
		setTitle("Ajout d'une personne");
		
		JPanel monPanel = new JPanel();
		setContentPane(monPanel);
		setLayout(null);
		
		lblNom = new JLabel();
		lblNom.setText("Nom :");
		lblNom.setBounds(20, 20, 100, 20);
		monPanel.add(lblNom);
		
		tfNom = new JTextField();
		tfNom.setBounds(20, 40, 100, 20);
		monPanel.add(tfNom);
		
		JLabel monLabel2 = new JLabel();
		monLabel2.setText("Prénom :");
		monLabel2.setBounds(20, 60, 100, 20);
		monPanel.add(monLabel2);
		
		tfPrenom = new JTextField();
		tfPrenom.setBounds(20, 80, 100, 20);
		monPanel.add(tfPrenom);
		
		JLabel monLabel3 = new JLabel();
		monLabel3.setText("Age :");
		monLabel3.setBounds(20, 100, 100, 20);
		monPanel.add(monLabel3);
		
		tfAge = new JTextField();
		tfAge.setBounds(20, 120, 100, 20);
		monPanel.add(tfAge);
		
		if(this.emp == null){
			JButton monBtn1 = new JButton("Ajouter");
			monBtn1.setBounds(20, 160, 100, 20);
			monPanel.add(monBtn1);
			monBtn1.addActionListener(new ActionListenerManageEmploye(this, "add"));
		}
		else {
			JButton monBtn1 = new JButton("Modifier");
			monBtn1.setBounds(20, 160, 100, 20);
			monPanel.add(monBtn1);
			monBtn1.addActionListener(new ActionListenerManageEmploye(this, "update"));
			
			JButton monBtn2 =  new JButton("Supprimer");
			monBtn2.setBounds(20,180,100,20);
			monPanel.add(monBtn2);
			monBtn2.addActionListener(new ActionListenerManageEmploye(this, "delete"));
		}
		
		JButton monBtn3 =  new JButton("Annuler");
		monBtn3.setBounds(20,200,100,20);
		monPanel.add(monBtn3);
		monBtn3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormPersonne.this.dispose();
				MenuPrincipal menu = new MenuPrincipal(FormPersonne.this.ent);
			}
			
		});
	}
	
	private void remplirFenetre(){
		this.tfNom.setText(this.emp.getNom());
		this.tfPrenom.setText(this.emp.getPrenom());
		this.tfAge.setText(String.valueOf(this.emp.getAge()));
	}
	
}
