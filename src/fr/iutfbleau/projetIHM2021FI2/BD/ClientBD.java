package fr.iutfbleau.projetIHM2021FI2.BD;

import fr.iutfbleau.projetIHM2021FI2.API.Client;
import java.sql.*;

public class ClientBD implements Client {

    private String prenom,nom;
    private int id;
    ConnexionBD connection = new ConnexionBD();
    Connection cnx;

    public ClientBD(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public int getId() {
			cnx = connection.connexion();

            try {
				PreparedStatement rqt = cnx.prepareStatement("SELECT id FROM Client WHERE prenom=? AND nom=?");
                rqt.setString(1,this.prenom);
                rqt.setString(2,this.nom);
				ResultSet rs = rqt.executeQuery();

				while(rs.next()) {
						this.id = rs.getInt(1);
					}

			}catch(SQLException e3) {
					System.err.println("Problème dans la requête SQL pour récupérer l'ID grâce au Prenom et au Nom");
			}

			connection.fermeture(cnx);

            return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

}