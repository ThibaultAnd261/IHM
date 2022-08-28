package fr.iutfbleau.projetIHM2021FI2.Sujet.View;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

import fr.iutfbleau.projetIHM2021FI2.API.Reservation;
import fr.iutfbleau.projetIHM2021FI2.Sujet.Controller.RetourAccueil;
import fr.iutfbleau.projetIHM2021FI2.API.Client;
import fr.iutfbleau.projetIHM2021FI2.API.Chambre;

public class RecapView extends JFrame {

	// Couleur pour l'affichage
	static final Color BACKGROUND = new Color(235,235,235);
	static final Color LIGHT_GRAY = new Color(180, 180, 180);
	static final Color DARK_BLUE = new Color(0, 0, 153);
	static final Color SUNKEN = new Color(210, 214, 212);
	static final Color GRAY = new Color(160, 160, 160);
	static final Color ROSEPUTE = new Color(255, 0, 102);
	static final Color POMME = new Color(154,203,104);

	// JLabels contenants les infos
	private JLabel felicitations, text1, text2, text3, text4, text5, text6, text7, indicationReservation;

	// JLabel contenants les infos qu'on va récupérer
	private JLabel valeur1, valeur2, valeur3, valeur4, valeur5, valeur6, valeur7;

    private Reservation reservation;
    private Client client;
    private Chambre chambre;

	private JButton home;

	public RecapView(Reservation reserv) {
		this.reservation = reserv;
        this.chambre = reserv.getChambre();
        this.client = reserv.getClient();
	}

	public void CreateRecap() {
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

		this.felicitations = new JLabel("Félicitations");
		this.felicitations.setForeground(ROSE);
		this.felicitations.setFont(new Font("Verdana", Font.PLAIN, 40));
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 20, 20, 20);
		gridbag.setConstraints(this.felicitations, c);
		this.add(this.felicitations);

		
		this.indicationReservation = new JLabel("Reservation créée", SwingConstants.CENTER);
		this.indicationReservation.setForeground(POMME);
		this.indicationReservation.setFont(new Font("Verdana", Font.BOLD, 27));
		c.weightx = 0.5;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(40, 40, 40, 40);
		gridbag.setConstraints(this.indicationReservation, c);
		this.add(this.indicationReservation);

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
		c.gridy = 2;
		gridbag.setConstraints(this.text1, c);
		this.add(this.text1);

		this.valeur1 = new JLabel();
		this.valeur1.setText(this.client.getNom());
		this.valeur1.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 2;
		gridbag.setConstraints(this.valeur1, c);
		this.add(this.valeur1);

		/* Prénom */

		this.text2 = new JLabel();
		this.text2.setText("Prénom : ");
		this.text2.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text2.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 2;
		gridbag.setConstraints(this.text2, c);
		this.add(this.text2);

		this.valeur2 = new JLabel();
		this.valeur2.setText(this.client.getPrenom());
		this.valeur2.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		gridbag.setConstraints(this.valeur2, c);
		this.add(this.valeur2);

		/* Référence */

		this.text3 = new JLabel();
		this.text3.setText("Référence : ");
		this.text3.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text3.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 3;
		gridbag.setConstraints(this.text3, c);
		this.add(this.text3);

		this.valeur3 = new JLabel();
		this.valeur3.setText(this.reservation.getReference());
		this.valeur3.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 1;
		c.gridy = 3;
		gridbag.setConstraints(this.valeur3, c);
		this.add(this.valeur3);

		/* Id client */

		this.text4 = new JLabel();
		this.text4.setText("Id : ");
		this.text4.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text4.setForeground(DARK_BLUE);
		c.gridx = 2;
		c.gridy = 3;
		gridbag.setConstraints(this.text4, c);
		this.add(this.text4);

		this.valeur4 = new JLabel();
		this.valeur4.setText(String.valueOf(this.client.getId()));
		this.valeur4.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 3;
		c.gridy = 3;
		gridbag.setConstraints(this.valeur4, c);
		this.add(this.valeur4);

		/* DateDebut */

		this.text5 = new JLabel();
		this.text5.setText("Date de début : ");
		this.text5.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text5.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 4;
		gridbag.setConstraints(this.text5, c);
		this.add(this.text5);

		this.valeur5 = new JLabel();
		this.valeur5.setText(String.valueOf(this.reservation.getDateDebut().format(DateTimeFormatter.ofPattern("dd MMMM yyyy",Locale.FRENCH))));
		this.valeur5.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 1;
		c.gridy = 4;
		gridbag.setConstraints(this.valeur5, c);
		this.add(this.valeur5);

		/* NbJours */

		this.text6 = new JLabel();
		this.text6.setText("Nombre de nuits : ");
		this.text6.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text6.setForeground(DARK_BLUE);
		c.gridx = 2;
		c.gridy = 4;
		gridbag.setConstraints(this.text6, c);
		this.add(this.text6);

		this.valeur6 = new JLabel();
		this.valeur6.setText(String.valueOf(this.reservation.getJours()));
		this.valeur6.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 3;
		c.gridy = 4;
		gridbag.setConstraints(this.valeur6, c);
		this.add(this.valeur6);

		/* TypeChambre */

		this.text7 = new JLabel();
		this.text7.setText("Type chambre : ");
		this.text7.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.text7.setForeground(DARK_BLUE);
		c.gridx = 0;
		c.gridy = 5;
		gridbag.setConstraints(this.text7, c);
		this.add(this.text7);

		this.valeur7 = new JLabel();
		this.valeur7.setText(String.valueOf(this.chambre.getType()));
		this.valeur7.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridx = 1;
		c.gridy = 5;
		gridbag.setConstraints(this.valeur7, c);
		this.add(this.valeur7);

		this.home = new JButton("Retour à l'accueil");
		this.home.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.home.setBackground(SUNKEN);
		c.gridx = 3;
		c.gridy = 6;
		gridbag.setConstraints(this.home, c);
		this.add(this.home);

		this.home.addActionListener(new RetourAccueil(this));
	}
}
