/** * 
 * Model de Prereservation qui reprend l'API Prereservation
 */

package fr.iutfbleau.projetIHM2021FI2.Sujet.Model;

import fr.iutfbleau.projetIHM2021FI2.API.*;

import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;

public class PrereservationModel implements Prereservation {

    private String ref;
    ConnectionModel connection = new ConnectionModel();
    private Connection cnx;
    private Date date;
    private int categorie, nbNuits, clientId;

    /**
     * Constructeur pour la recherche par référence sans indiquer la BD
     * @param ref la référence
     */
    public PrereservationModel(String ref) {
        this.ref = ref;
    }

    /**
     * @return la référence
     */
    public String getReference() {
        return this.ref;
    }

    /**
     * @return la date de préréservation
     */
    public LocalDate getDateDebut() {

        this.cnx = connection.connexion();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT debut FROM Reservation WHERE reference=?");
            rqt.setString(1, this.ref);

            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.date = rs.getDate(1);
            }

        } catch (SQLException e3) {
            System.err.println(
                    "Problème dans la requête SQL pour récupérer la date de prereservation DANS PrereservationModel");
        }

        connection.fermeture(this.cnx);

        return this.date.toLocalDate();
    }

    /**
     * @return le nombre de nuits préréservées
     */
    public int getJours() {

        this.cnx = connection.connexion();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT nuits FROM Reservation WHERE reference=?");
            rqt.setString(1, this.ref);

            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.nbNuits = rs.getInt(1);
            }

        } catch (SQLException e3) {
            System.err
                    .println("Problème dans la requête SQL pour récupérer le nombre de nuits DANS PrereservationModel");
        }
        
        connection.fermeture(this.cnx);

        return this.nbNuits;
    }

    /**
     * @return le type de la chambre préréservée
     */
    public TypeChambre getTypeChambre() {

        this.cnx = connection.connexion();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT categorie FROM Reservation WHERE reference=?");
            rqt.setString(1, this.ref);

            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.categorie = rs.getInt(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer le type de la chambre DANS PrereservationModel");
        }
        
        connection.fermeture(this.cnx);

        if (this.categorie == 1) {
            return TypeChambre.UNLS;
        } else {
            if (this.categorie == 2) {
                return TypeChambre.UNLD;
            } else {
                if (this.categorie == 3) {
                    return TypeChambre.DEUXLS;
                }
                else {
                    throw new NullPointerException("Pas de type de chambre");
                }
            }
        }
    }

    /**
     * @return le client de la préréservation
     */
    public Client getClient() {

        this.cnx = connection.connexion();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT client FROM Reservation WHERE reference=?");
            rqt.setString(1, this.ref);

            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.clientId = rs.getInt(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer le client DANS PrereservationModel");
        }

        connection.fermeture(this.cnx);

        ClientModel clm = new ClientModel(this.clientId);

        return clm;
    }

}
