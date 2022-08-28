/** * 
 * Model du client qui reprend l'API client
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Model;

import fr.iutfbleau.projetIHM2021FI2.API.Client;
import java.sql.*;

public class ClientModel implements Client {

    private String prenom,nom;
    private int id;
    ConnectionModel connection = new ConnectionModel();
    Connection cnx;

    /**
    * 
    * @param id l'id du client
    */
    public ClientModel(int id) {
        this.id = id;
    }

    /**
     * @return l'id du client
     */
    public int getId() {
		return this.id;
    }

    /**
     * @return le nom du client
     */
    public String getNom() {

        this.cnx = connection.connexion();
                
        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT nom FROM Client WHERE id=?");
            rqt.setInt(1, this.id);

            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.nom = rs.getString(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer le nom du client DANS ClientModel");
        }
        
        connection.fermeture(this.cnx);

        return this.nom;
    }

    /**
     * @return le prénom du client
     */
    public String getPrenom() {

        this.cnx = connection.connexion();
                        
        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT prenom FROM Client WHERE id=?");
            rqt.setInt(1, this.id);

            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.prenom = rs.getString(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer le prenom du client DANS ClientModel");
        }
        
        connection.fermeture(this.cnx);
        
        return this.prenom;
    }

}
