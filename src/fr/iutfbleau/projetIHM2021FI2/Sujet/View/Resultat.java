package fr.iutfbleau.projetIHM2021FI2.Sujet.View;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

import fr.iutfbleau.projetIHM2021FI2.API.Prereservation;
import fr.iutfbleau.projetIHM2021FI2.API.Client;
import fr.iutfbleau.projetIHM2021FI2.API.Chambre;
import fr.iutfbleau.projetIHM2021FI2.Sujet.Controller.ValidationReservation;

public class Resultat extends JFrame {

	// Couleur pour l'affichage
	static final Color BACKGROUND = new Color(225, 225, 225);
	static final Color LIGHT_GRAY = new Color(180, 180, 180);
	static final Color DARK_BLUE = new Color(0, 0, 153);
	static final Color SUNKEN = new Color(210, 214, 212);

	private Set<Chambre> ensembleCh;
	private Chambre chambre[];
	private String ensembleNum[];
	private int nbChambre;

	Prereservation prereservation;
	Client client;

	JButton confirmer;

	// Liste déroulante
	private JComboBox liste;

	// JLabels contenants les infos
	private JLabel text1, text2, text3, text4, text5, text6, text7, text8;

	// JLabel contenants les infos qu'on va récupérer
	private JLabel valeur1, valeur2, valeur3, valeur4, valeur5, valeur6, valeur7, indicationPrereservation;

	public Resultat(Prereservation prereservation, Set<Chambre> ensembleCh) {
		this.prereservation = prereservation;
		client = prereservation.getClient();

		this.ensembleCh = ensembleCh;
		this.nbChambre = this.ensembleCh.size();
		this.chambre = new Chambre[this.nbChambre];
		this.chambre = this.ensembleCh.toArray(this.chambre);

		this.ensembleNum = new String[this.nbChambre];

		for (int i = 0; i < nbChambre; i++) {
			this.ensembleNum[i] = String.valueOf(this.chambre[i].getNumero());
		}
	}

	public void windowCreate() {
		this.setSize(1000, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setBackground(BACKGROUND);

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(40, 40, 40, 40);

		this.indicationPrereservation = new JLabel("Préréservation", SwingConstants.CENTER);
		this.indicationPrereservation.setForeground(DARK_BLUE);
		this.indicationPrereservation.setFont(new Font("Verdana", Font.BOLD, 27));
		c.weightx = 0.5;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		gridbag.setConstraints(this.indicationPrereservation, c);
		this.add(this.indicationPrereservation);

		// Reset à 1 la place que prenne les éléments en terme de colonnes et reset de
		// la marge
		c.gridwidth = 1;
		c.insets = new Insets(10, 10, 10, 10);

		/* Nom */

		this.text1 = new JLabel();
		this.text1.setText("Nom : ");
		this.text1.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text1.setForeground(DARK_BLUE);
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		gridbag.setConstraints(this.text1, c);
		this.add(this.text1);

		this.valeur1 = new JLabel();
		this.valeur1.setText(this.client.getNom());
		this.valeur1.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 1;
		gridbag.setConstraints(this.valeur1, c);
		this.add(this.valeur1);

		/* Prénom */

		this.text2 = new JLabel();
		this.text2.setText("Prénom : ");
		this.text2.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text2.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 1;
		gridbag.setConstraints(this.text2, c);
		this.add(this.text2);

		this.valeur2 = new JLabel();
		this.valeur2.setText(this.client.getPrenom());
		this.valeur2.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		gridbag.setConstraints(this.valeur2, c);
		this.add(this.valeur2);

		/* Référence */

		this.text3 = new JLabel();
		this.text3.setText("Référence : ");
		this.text3.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text3.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 2;
		gridbag.setConstraints(this.text3, c);
		this.add(this.text3);

		this.valeur3 = new JLabel();
		this.valeur3.setText(this.prereservation.getReference());
		this.valeur3.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 1;
		c.gridy = 2;
		gridbag.setConstraints(this.valeur3, c);
		this.add(this.valeur3);

		/* Id client */

		this.text4 = new JLabel();
		this.text4.setText("Id : ");
		this.text4.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text4.setForeground(DARK_BLUE);
		c.gridx = 2;
		c.gridy = 2;
		gridbag.setConstraints(this.text4, c);
		this.add(this.text4);

		this.valeur4 = new JLabel();
		this.valeur4.setText(String.valueOf(this.client.getId()));
		this.valeur4.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 3;
		c.gridy = 2;
		gridbag.setConstraints(this.valeur4, c);
		this.add(this.valeur4);

		/* DateDebut */

		this.text5 = new JLabel();
		this.text5.setText("Date de début : ");
		this.text5.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text5.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 3;
		gridbag.setConstraints(this.text5, c);
		this.add(this.text5);

		this.valeur5 = new JLabel();
		this.valeur5.setText(String.valueOf(this.prereservation.getDateDebut().format(DateTimeFormatter.ofPattern("dd MMMM yyyy",Locale.FRENCH))));
		this.valeur5.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 1;
		c.gridy = 3;
		gridbag.setConstraints(this.valeur5, c);
		this.add(this.valeur5);

		/* NbJours */

		this.text6 = new JLabel();
		this.text6.setText("Nombre de nuits : ");
		this.text6.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text6.setForeground(DARK_BLUE);
		c.gridx = 2;
		c.gridy = 3;
		gridbag.setConstraints(this.text6, c);
		this.add(this.text6);

		this.valeur6 = new JLabel();
		this.valeur6.setText(String.valueOf(this.prereservation.getJours()));
		this.valeur6.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 3;
		c.gridy = 3;
		gridbag.setConstraints(this.valeur6, c);
		this.add(this.valeur6);

		/* TypeChambre */

		this.text7 = new JLabel();
		this.text7.setText("Type chambre : ");
		this.text7.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text7.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 4;
		gridbag.setConstraints(this.text7, c);
		this.add(this.text7);

		this.valeur7 = new JLabel();
		this.valeur7.setText(String.valueOf(this.prereservation.getTypeChambre()));
		this.valeur7.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 1;
		c.gridy = 4;
		gridbag.setConstraints(this.valeur7, c);
		this.add(this.valeur7);

		/* Chambre dispo */

		c.insets = new Insets(10, 10, 20, 10);

		this.text8 = new JLabel();
		this.text8.setText("Chambres disponibles : ");
		this.text8.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text8.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 5;
		gridbag.setConstraints(this.text8, c);
		this.add(this.text8);

		liste = new JComboBox(ensembleNum);
		c.gridx = 1;
		c.gridy = 5;
		gridbag.setConstraints(this.liste, c);
		this.add(this.liste);

		this.confirmer = new JButton("Réserver la chambre");
		this.confirmer.setBackground(SUNKEN);
		this.confirmer.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 1;
		c.gridy = 7;
		gridbag.setConstraints(this.confirmer, c);
		contentPane.add(this.confirmer);
		this.confirmer.addActionListener(new ValidationReservation(this));

		this.setVisible(true);
	}

	/**
	 * 
	 * @return le numéro de la chambre sélectionnée
	 */
	public String getTheItemSelected() {
		return String.valueOf(this.liste.getSelectedItem());
	}

	/**
	 * 
	 * @return la préréservation actuel
	 */
	public Prereservation getCurrentPrereservation() {
		return this.prereservation;
	}
	
	/**
	 * Ferme la fenêtre
	 */
	public void closeWindow(){
		this.dispose();
	}
}
