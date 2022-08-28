package fr.iutfbleau.projetIHM2021FI2.BD;

import java.sql.*;

public class ConnexionBD {

    public Connection connexion() {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm","projetihm","mhitejorp");
				System.out.println("\n\t*************************");
				System.out.println("\t*   Connexion établie   *");
				System.out.println("\t*************************");
                return cnx;
			}catch(SQLException e1) {
					System.err.println("Problème lors de la connexion");
			}
		}catch(ClassNotFoundException e2) {
					System.err.println("problème avec le Pilote de la BD (indisponible)");
		}
        return null;
    }

	public void fermeture(Connection cnx) {
			try {
				cnx.close();
				System.out.println("\n\t*****************************");
				System.out.println("\t* Fermeture de la connexion *");
				System.out.println("\t*****************************\n");
			} catch(SQLException e1) {
				System.err.println("Erreur fermeture de la connexion à la base de données");
			}
	}

}