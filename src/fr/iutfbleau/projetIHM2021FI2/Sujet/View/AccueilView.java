/** * 
 * Accueil du sujet 1
 */

package fr.iutfbleau.projetIHM2021FI2.Sujet.View;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.Sujet.Controller.*;

import java.awt.*;

public class AccueilView extends JFrame {
	
	private JLabel other, prenom, nom, title;
	private JButton button1;
	private JTextField text1, text2, text3;

	private JPanel pnl1, pnl2, pnl3, pnl4;

	static final Color BACKGROUND = new Color(235,235,235);
	static final Color LIGHT_GRAY = new Color(180,180,180);
	static final Color DARK_BLUE = new Color(0,0,153);
	static final Color SUNKEN = new Color(210,214,212);

	/**
	 * Méthode qui créer la fenêtre d'accueil pour chercher la préréservation d'un client
	 */
	public void createAccueilView() {

		this.setSize(800,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Recherche de la préréservation d'un client");

		this.pnl1 = new JPanel();
		this.pnl2 = new JPanel();
		this.pnl3 = new JPanel();
		this.pnl4 = new JPanel();
		
		Container contentPane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);

		contentPane.setBackground(BACKGROUND);

		this.title = new JLabel("Préréservation d'un client", SwingConstants.CENTER);
		this.title.setFont(new Font("Verdana", Font.BOLD, 20));
		this.title.setForeground(DARK_BLUE);
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15,20,20,15);
		gridbag.setConstraints(this.title, c);
		contentPane.add(this.title);


		this.prenom = new JLabel("Prénom", SwingConstants.CENTER);
		this.prenom.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		gridbag.setConstraints(this.prenom, c);
		contentPane.add(this.prenom);

		this.nom = new JLabel("Nom", SwingConstants.CENTER);
		this.nom.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 2;
		c.weightx = 2;
		gridbag.setConstraints(this.nom, c);
		contentPane.add(this.nom);

		this.text1 = new JTextField();
		this.text1.setFont(new Font("Verdana", Font.PLAIN, 17));
		this.text1.setPreferredSize(new Dimension(150, 30));
		this.text1.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY , 2));
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5,30,5,30);
		gridbag.setConstraints(this.
		text1,c);
		contentPane.add(this.text1);

		this.text2 = new JTextField();
		this.text2.setFont(new Font("Verdana", Font.PLAIN, 17));
		this.text2.setPreferredSize(new Dimension(150, 30));
		this.text2.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY , 2));
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 3;
		gridbag.setConstraints(this.text2, c);
		contentPane.add(this.text2);

		this.other = new JLabel("OU", SwingConstants.CENTER);
		this.other.setFont(new Font("Verdana", Font.PLAIN, 20));
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(20,20,20,20);
		gridbag.setConstraints(this.other, c);
		contentPane.add(this.other);

		this.text3 = new JTextField("Référence");
		this.text3.setFont(new Font("Verdana", Font.PLAIN, 17));
		this.text3.setPreferredSize(new Dimension(150, 30));
		this.text3.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY , 2));
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5,30,5,30);
		gridbag.setConstraints(this.text3, c);
		contentPane.add(this.text3);


		c.gridwidth = 1;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 6;
		gridbag.setConstraints(this.pnl1, c);
		contentPane.add(this.pnl1);

		c.gridwidth = 1;
		c.weightx = 1.0;
		c.gridx = 1;
		c.gridy = 6;
		gridbag.setConstraints(this.pnl2, c);
		contentPane.add(this.pnl2);

		c.gridwidth = 1;
		c.weightx = 1.0;
		c.gridx = 2;
		c.gridy = 6;
		gridbag.setConstraints(this.pnl3, c);
		contentPane.add(this.pnl3);
		
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.gridx = 3;
		c.gridy = 6;
		gridbag.setConstraints(this.pnl4, c);
		contentPane.add(this.pnl4);

		this.pnl1.setForeground(BACKGROUND);
		this.pnl2.setForeground(BACKGROUND);
		this.pnl3.setForeground(BACKGROUND);
		this.pnl4.setForeground(BACKGROUND);

		this.button1 = new JButton("Chercher");

		this.button1.addActionListener(new PrereservationController(this.text1, this.text2, this.text3,this));

		this.button1.setFont(new Font("Verdana", Font.PLAIN, 17));
		this.button1.setBackground(SUNKEN);
		c.insets = new Insets(10,10,10,10);
		c.gridx = 2;
		c.gridwidth = 2;
		c.gridy = 7;
		gridbag.setConstraints(this.button1, c);
		contentPane.add(this.button1);

		this.setVisible(true);

	}

	/**
	 * Méthode qui ferme la fenêtre d'accueil pour chercher la préréservation d'un client
	 */
	public void closeAccueilWindow() {
		this.dispose();
	}
	
}