/** * 
 * Accueil de l'application
 */

package fr.iutfbleau.projetIHM2021FI2.Sujet.View;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.Sujet.Controller.AccueilController;

import java.awt.*;

public class Accueil extends JFrame {

	static final Color BACKGROUND = new Color(235, 235, 235);
	static final Color SUNKEN = new Color(210, 214, 212);

	private JButton btn1, btn2;
	AccueilController controller = new AccueilController(this);

	private JPanel pnl1, pnl2, pnl3, pnl4;

	/**
	 * Constructeur
	 * Créer la fenêtre d'accueil de l'application
	 */
	public Accueil() {

		this.setSize(800, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Accueil");

		this.pnl1 = new JPanel();
		this.pnl2 = new JPanel();
		this.pnl3 = new JPanel();
		this.pnl4 = new JPanel();

		Container contentPane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 1.0;

		contentPane.setBackground(BACKGROUND);

		this.btn1 = new JButton("Gérer les préréservations");
		this.btn1.addActionListener(controller);
		this.btn1.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.btn1.setBackground(SUNKEN);
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		gridbag.setConstraints(this.btn1, c);
		contentPane.add(this.btn1);

		this.btn2 = new JButton("Statistiques de l'hôtel");
		this.btn2.addActionListener(controller);
		this.btn2.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.btn2.setBackground(SUNKEN);
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		gridbag.setConstraints(this.btn2, c);
		contentPane.add(this.btn2);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		gridbag.setConstraints(this.pnl1, c);
		contentPane.add(this.pnl1);

		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		gridbag.setConstraints(this.pnl2, c);
		contentPane.add(this.pnl2);

		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		gridbag.setConstraints(this.pnl3, c);
		contentPane.add(this.pnl3);

		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		gridbag.setConstraints(this.pnl4, c);
		contentPane.add(this.pnl4);

		this.pnl1.setBackground(BACKGROUND);
		this.pnl2.setBackground(BACKGROUND);
		this.pnl3.setBackground(BACKGROUND);
		this.pnl4.setBackground(BACKGROUND);

		this.setVisible(true);
	}

}
