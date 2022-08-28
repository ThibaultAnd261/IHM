/** * 
 * Model de connexion aux Base de Données
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Model;

import java.sql.*;

public class ConnectionModel {

	/**
	 * 
	 * @return la connexion à la base de données
	 */
	public Connection connexion() {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm",
						"projetihm", "mhitejorp");
				return cnx;
			} catch (SQLException e1) {
				System.err.println("Problème lors de la connexion");
			}
		} catch (ClassNotFoundException e2) {
			System.err.println("problème avec le Pilote de la BD (indisponible)");
		}
		// changer le return en throw NullPointerException
		throw new NullPointerException("Impossible de se connecter à la BD");
	}

	/**
	 * 
	 * @param cnx la connexion à fermer
	 */
	public void fermeture(Connection cnx) {
		try {
			cnx.close();
		} catch (SQLException e1) {
			System.err.println("Erreur fermeture de la connexion à la base de données");
		}
	}

	/* ------------------- Base de données de Valention pour les chambre et réservations --------------------- */

	public Connection connexionVal() {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				Connection cnx = DriverManager.getConnection(domaine, user, mdp);
				return cnx;
			} catch (SQLException e1) {
				System.err.println("Problème lors de la connexion à la base de données de Valentin");
			}
		} catch (ClassNotFoundException e2) {
			System.err.println("problème avec le Pilote de la BD (indisponible)");
		}
		// changer le return en throw NullPointerException
		return null;
	}

	public void fermetureVal(Connection cnx) {
		try {
			cnx.close();
		} catch (SQLException e1) {
			System.err.println("Erreur fermeture de la connexion à la base de données de Valentin");
		}
	}

}
