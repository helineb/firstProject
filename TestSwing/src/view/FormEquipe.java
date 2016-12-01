package view;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.ActionListenerExportXML;
import controller.ActionListenerManageEquipe;
import model.*;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FormEquipe extends JFrame{

	private JTable tablePersonne;
	private JTextField tfNom;
	private JComboBox<Personne> cbLeader;
	private JComboBox<Personne> cbPersonnes;
	private Equipe equipe;
	private Entreprise ent;
	/**
	 * Create the application.
	 * @wbp.parser.constructor
	 */
	public FormEquipe(Entreprise ent) {
		this.equipe = null;
		this.ent = ent;
		initialize();
		setVisible(true);
	}
	public FormEquipe(Entreprise ent, Equipe equipe) {
		this.ent = ent;
		this.equipe = equipe;
		initialize();
		remplirFenetre();
		setVisible(true);
	}

	public JTable getTablePersonne(){
		return this.tablePersonne;
	}
	public JTextField getTfNom(){
		return this.tfNom;
	}
	public JComboBox<Personne> getCbLeader(){
		return this.cbLeader;
	}
	public JComboBox<Personne> getCbPersonnes(){
		return this.cbPersonnes;
	}
	public Equipe getEquipe(){
		return this.equipe;
	}
	public Entreprise getEntreprise(){
		return this.ent;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Equipes");
		setBounds(100, 100, 700, 550);
		getContentPane().setLayout(new GridLayout());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{570, 0};
		gbl_panel.rowHeights = new int[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNom = new JLabel("Nom :");
		panel_1.add(lblNom);
		
		tfNom = new JTextField();
		panel_1.add(tfNom);
		tfNom.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblLeader = new JLabel("Leader :");
		panel_3.add(lblLeader);
		
		cbLeader = new JComboBox<Personne>();
		panel_3.add(cbLeader);
		
		JLabel lblListePersonnes = new JLabel("Liste des personnes :");
		GridBagConstraints gbc_lblListePersonnes = new GridBagConstraints();
		gbc_lblListePersonnes.fill = GridBagConstraints.BOTH;
		gbc_lblListePersonnes.insets = new Insets(0, 0, 5, 0);
		gbc_lblListePersonnes.gridx = 0;
		gbc_lblListePersonnes.gridy = 2;
		panel.add(lblListePersonnes, gbc_lblListePersonnes);
		
		String[] entete = {"id","nom","prenom"};
		DefaultTableModel model = new DefaultTableModel(entete, 0);
		tablePersonne = new JTable(model);
		tablePersonne.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(tablePersonne);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 6;
		panel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		cbPersonnes = new JComboBox<Personne>();
		panel_2.add(cbPersonnes);
		
		for(Personne p : this.ent.getEmployes()){
			cbLeader.addItem(p);
			cbPersonnes.addItem(p);
		}
		
		JButton btnAddPersonne = new JButton("Ajouter \u00E0 la liste");
		btnAddPersonne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel tm = (DefaultTableModel)tablePersonne.getModel();
				
				Personne p = (Personne)cbPersonnes.getSelectedItem();
				String [] data = {p.getId()+"",p.getNom(),p.getPrenom()};
				tm.addRow(data);
				tablePersonne.setModel(tm);
				tablePersonne.repaint();
			}
		});
		panel_2.add(btnAddPersonne);
		
		
		
		GridBagConstraints gbc_btnAnnuler = new GridBagConstraints();
		gbc_btnAnnuler.fill = GridBagConstraints.BOTH;
		gbc_btnAnnuler.gridx = 0;
		
		if(this.equipe == null){
			JButton btnAddEquipe = new JButton("Cr\u00E9er l'\u00E9quipe");
			btnAddEquipe.addActionListener(new ActionListenerManageEquipe(this,"add"));
			
			GridBagConstraints gbc_btnAddEquipe = new GridBagConstraints();
			gbc_btnAddEquipe.fill = GridBagConstraints.BOTH;
			gbc_btnAddEquipe.gridx = 0;
			gbc_btnAddEquipe.gridy = 7;
			panel.add(btnAddEquipe, gbc_btnAddEquipe);
			
			
			//placement du bouton annuler
			gbc_btnAnnuler.gridy = 8;
		}
		else {
			JButton btnUpdEquipe = new JButton("Modifier");
			btnUpdEquipe.addActionListener(new ActionListenerManageEquipe(this,"update"));
			
			GridBagConstraints gbc_btnUpdEquipe = new GridBagConstraints();
			gbc_btnUpdEquipe.fill = GridBagConstraints.BOTH;
			gbc_btnUpdEquipe.gridx = 0;
			gbc_btnUpdEquipe.gridy = 7;
			panel.add(btnUpdEquipe, gbc_btnUpdEquipe);
			
			JButton btnDelEquipe = new JButton("Supprimer l'équipe");
			btnDelEquipe.addActionListener(new ActionListenerManageEquipe(this,"delete"));
			GridBagConstraints gbc_btnDelEquipe = new GridBagConstraints();
			gbc_btnDelEquipe.fill = GridBagConstraints.BOTH;
			gbc_btnDelEquipe.gridx = 0;
			gbc_btnDelEquipe.gridy = 8;
			panel.add(btnDelEquipe, gbc_btnDelEquipe);
			
			
			
			//placement du bouton annuler
			gbc_btnAnnuler.gridy = 9;
		}
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormEquipe.this.dispose();
				MenuPrincipal menu = new MenuPrincipal(FormEquipe.this.ent);
			}
		});

		panel.add(btnAnnuler, gbc_btnAnnuler);
		
		JButton btnExportXML = new JButton("Exporter en XML l'équipe");
		btnExportXML.addActionListener(new ActionListenerExportXML(this));
		
		GridBagConstraints gbc_btnExportEquipe = new GridBagConstraints();
		gbc_btnExportEquipe.fill = GridBagConstraints.BOTH;
		gbc_btnExportEquipe.gridx = 0;
		gbc_btnExportEquipe.gridy = 10;
		panel.add(btnExportXML, gbc_btnExportEquipe);
		
	}
	
	private void remplirFenetre() {
		this.tfNom.setText(this.equipe.getNom());
		for (Personne p : this.ent.getEmployes()){
			if(p.getId()==this.equipe.getLeader().getId())
				this.cbLeader.setSelectedItem(p);
		}
		if(this.equipe.getEmployes() != null && this.equipe.getEmployes().size() > 0){
			DefaultTableModel tm = (DefaultTableModel)tablePersonne.getModel();
			for(Personne p : this.equipe.getEmployes()) {
				String [] data = {p.getId()+"",p.getNom(),p.getPrenom()};
				tm.addRow(data);
			}
			tablePersonne.setModel(tm);
			tablePersonne.repaint();
		}
	}

}
