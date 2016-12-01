package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableRow;

import controller.MouseListenerDisplayFormEquipe;
import controller.MouseListenerDisplayFormPersonne;

import java.awt.Color;
import model.Personne;
import model.PersonneDAO;
import model.Entreprise;
import model.Equipe;
import model.EquipeDAO;

import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JScrollPane;
import java.awt.Scrollbar;

public class MenuPrincipal extends JFrame{
	
	private JTable tablePersonne;
	private JTable tableEquipe;
	
	private Entreprise ent;
	
	public MenuPrincipal(Entreprise ent) {
		this.ent = ent;
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(150,150,500,502);
		setTitle("Entreprise : " + ent.getNom() + " (siret: " + ent.getSiret() + ")");
		
		JPanel panelPrincipal = new JPanel();
		setContentPane(panelPrincipal);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 470, 399);
		panelPrincipal.add(panel);
		
		Object[][] pers = null;
		Object[] etpers = {"ID","Nom","Prénom"};
		tablePersonne = new JTable();
		if(ent.getEmployes() != null && ent.getEmployes().size() > 0){
			int compPers = 0;
			pers = new Object[ent.getEmployes().size()][3];
			for(Personne p: ent.getEmployes()){
				pers[compPers][0] = p.getId();
				pers[compPers][1] = p.getNom();
				pers[compPers][2] = p.getPrenom();
				compPers++;
			}
			tablePersonne = new JTable(pers,etpers);
		}
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{470, 0};
		gbl_panel.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblListePersonnes = new JLabel("Liste des personnes");
		GridBagConstraints gbc_lblListePersonnes = new GridBagConstraints();
		gbc_lblListePersonnes.fill = GridBagConstraints.BOTH;
		gbc_lblListePersonnes.insets = new Insets(0, 0, 5, 0);
		gbc_lblListePersonnes.gridx = 0;
		gbc_lblListePersonnes.gridy = 0;
		panel.add(lblListePersonnes, gbc_lblListePersonnes);

		tablePersonne.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(tablePersonne);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		
		tablePersonne.addMouseListener(new MouseListenerDisplayFormPersonne(this));
		
		JButton btnAddPersonne = new JButton("Ajouter une personne");
		btnAddPersonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormPersonne formPers = new FormPersonne(ent);
				MenuPrincipal.this.dispose();
			}
		});
		
		GridBagConstraints gbc_btnAddPersonne = new GridBagConstraints();
		gbc_btnAddPersonne.fill = GridBagConstraints.BOTH;
		gbc_btnAddPersonne.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddPersonne.gridx = 0;
		gbc_btnAddPersonne.gridy = 4;
		panel.add(btnAddPersonne, gbc_btnAddPersonne);
		
		JLabel lblListEquipes = new JLabel("Liste des \u00E9quipes");
		GridBagConstraints gbc_lblListEquipes = new GridBagConstraints();
		gbc_lblListEquipes.fill = GridBagConstraints.BOTH;
		gbc_lblListEquipes.insets = new Insets(0, 0, 5, 0);
		gbc_lblListEquipes.gridx = 0;
		gbc_lblListEquipes.gridy = 5;
		panel.add(lblListEquipes, gbc_lblListEquipes);
		
		tableEquipe = new JTable();
		Object[][] eqs = null;
		Object[] eteqs = {"ID","Nom"};
		if(ent.getEquipes() != null && ent.getEquipes().size() > 0){
			int comptEq = 0;
			eqs = new Object[ent.getEquipes().size()][2];
			for(Equipe eq: ent.getEquipes()){
				eqs[comptEq][0] = eq.getId();
				eqs[comptEq][1] = eq.getNom();
				comptEq++;
			}
			tableEquipe = new JTable(eqs,eteqs);
		}
		tableEquipe.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane2 = new JScrollPane(tableEquipe);
		tableEquipe.addMouseListener(new MouseListenerDisplayFormEquipe(this));
		
		GridBagConstraints gbc_scrollPane2 = new GridBagConstraints();
		gbc_scrollPane2.gridheight = 3;
		gbc_scrollPane2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane2.gridx = 0;
		gbc_scrollPane2.gridy = 6;
		panel.add(scrollPane2, gbc_scrollPane2);
		
		JButton btnAddEquipe = new JButton("Ajouter une \u00E9quipe");
		btnAddEquipe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormEquipe formEquipe = new FormEquipe(ent);
				MenuPrincipal.this.dispose();
			}
			
		});
		GridBagConstraints gbc_btnAddEquipe = new GridBagConstraints();
		gbc_btnAddEquipe.fill = GridBagConstraints.BOTH;
		gbc_btnAddEquipe.gridx = 0;
		gbc_btnAddEquipe.gridy = 9;
		panel.add(btnAddEquipe, gbc_btnAddEquipe);

		setVisible(true);
	}
	
	public Entreprise getEntreprise(){
		return this.ent;
	}
	
}
