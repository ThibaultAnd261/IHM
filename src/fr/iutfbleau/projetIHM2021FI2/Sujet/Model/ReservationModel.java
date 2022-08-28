/** * 
 * Model de reservation qui reprend l'API reservation
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Model;

import fr.iutfbleau.projetIHM2021FI2.API.Reservation;
import fr.iutfbleau.projetIHM2021FI2.API.Client;
import fr.iutfbleau.projetIHM2021FI2.API.Chambre;

import java.sql.*;
import java.time.LocalDate;

public class ReservationModel implements Reservation {

    Connection cnx;
    ConnectionModel connection = new ConnectionModel();

    private int idReservation, clientNumber, nbNuits, idChambre;
    private String reference;

    private Date date;

    /**
     * Constructeur
     * 
     * @param idReservation l'identifiant de la réservation
     */
    public ReservationModel(String reference) {
        this.reference = reference;
    }

    /**
     * @return la référence de la réservation
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * @return la date de début de la réservation
     */
    public LocalDate getDateDebut() {

        cnx = connection.connexion();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT debut FROM Reservation WHERE reference=?");
            rqt.setString(1, this.reference);
            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.date = rs.getDate(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer la date DANS ReservationModel");
        }

        connection.fermeture(cnx);

        return this.date.toLocalDate();
    }

    /**
     * @return le nombre de nuits réservées
     */
    public int getJours() {

        cnx = connection.connexion();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT nuits FROM Reservation WHERE reference=?");
            rqt.setString(1, this.reference);
            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.nbNuits = rs.getInt(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer le nombre de nuits DANS RéservationModel");
        }

        connection.fermeture(cnx);

        return this.nbNuits;
    }

    /**
     * @return la chambre de la réservation
     */
    public Chambre getChambre() {

        cnx = connection.connexionVal();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT idChambre FROM reservationIHM WHERE reference=?");
            rqt.setString(1, this.reference);
            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.idChambre = rs.getInt(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer l'ID grâce au Prenom et au Nom");
        }

        connection.fermetureVal(cnx);

        return new ChambreModel(this.idChambre);
    }

    /**
     * @return le client qui a réservé cette chambre
     */
    public Client getClient() {

        cnx = connection.connexion();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT client FROM Reservation WHERE reference=?");
            rqt.setString(1, this.reference);
            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.clientNumber = rs.getInt(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL pour récupérer le numéro du client de cette réservation");
        }

        connection.fermeture(cnx);

        return new ClientModel(this.clientNumber);
    }

}