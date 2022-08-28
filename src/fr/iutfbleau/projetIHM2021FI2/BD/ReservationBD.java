package fr.iutfbleau.projetIHM2021FI2.BD;

import fr.iutfbleau.projetIHM2021FI2.API.Reservation;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;
import fr.iutfbleau.projetIHM2021FI2.API.Client;
import fr.iutfbleau.projetIHM2021FI2.API.Chambre;

import java.sql.*;
import java.time.LocalDate;

public class ReservationBD implements Reservation {

    Connection cnx;
    ConnexionBD connection = new ConnexionBD();
    private int idReservation,categorie, clientNumber, nbNuits;
    private String reference;
    private Client client;
    private Date date;
    
    public ReservationBD(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getReference() {

        cnx = connection.connexion();

        try {
				PreparedStatement rqt = cnx.prepareStatement("SELECT reference FROM Reservation WHERE id=?");
                rqt.setInt(1,this.idReservation);
				ResultSet rs = rqt.executeQuery();

				while(rs.next()) {
						this.reference = rs.getString(1);
					}

			}catch(SQLException e3) {
					System.err.println("Problème dans la requête SQL pour récupérer l'ID grâce au Prenom et au Nom");
			}

        connection.fermeture(cnx);

        return this.reference;
    }


    public LocalDate getDateDebut() {
        cnx = connection.connexion();

        try {
				PreparedStatement rqt = cnx.prepareStatement("SELECT debut FROM Reservation WHERE id=?");
                rqt.setInt(1,this.idReservation);
				ResultSet rs = rqt.executeQuery();

				while(rs.next()) {
						this.date = rs.getDate(1);
				}

			}catch(SQLException e3) {
					System.err.println("Problème dans la requête SQL pour récupérer la date");
			}

        connection.fermeture(cnx);

        return this.date.toLocalDate();
    }

    public int getJours() {
        cnx = connection.connexion();

        try {
				PreparedStatement rqt = cnx.prepareStatement("SELECT nuits FROM Reservation WHERE id=?");
                rqt.setInt(1,this.idReservation);
				ResultSet rs = rqt.executeQuery();

				while(rs.next()) {
						this.nbNuits = rs.getInt(1);
				}

			}catch(SQLException e3) {
					System.err.println("Problème dans la requête SQL pour récupérer le nombre de nuits grâce au Prenom et au Nom");
			}

        connection.fermeture(cnx);
        
        return this.nbNuits;
    }
    
    public TypeChambre getTypeChambre() {
        cnx = connection.connexion();

        try {
				PreparedStatement rqt = cnx.prepareStatement("SELECT categorie FROM Reservation WHERE id=?");
                rqt.setInt(1,this.idReservation);
				ResultSet rs = rqt.executeQuery();

				while(rs.next()) {
						this.categorie = rs.getInt(1);
				}

			}catch(SQLException e3) {
					System.err.println("Problème dans la requête SQL pour récupérer l'ID grâce au Prenom et au Nom");
			}

        connection.fermeture(cnx);

        /* Demander si c'est possible ou si passer par la table Categorie */
        if(this.categorie == 1) {
            return TypeChambre.UNLS;
        }
        else {
            if(this.categorie == 2) {
                return TypeChambre.UNLD;
            }
            else {
                if(this.categorie == 3) {
                    return TypeChambre.DEUXLS;
                }
            }
        }
        return null;
    }

    public Client getClient() {

        cnx = connection.connexion();

        try {
				PreparedStatement rqt = cnx.prepareStatement("SELECT client FROM Reservation WHERE id=?");
                rqt.setInt(1,this.idReservation);
				ResultSet rs = rqt.executeQuery();

				while(rs.next()) {
						this.clientNumber = rs.getInt(1);
				}

			}catch(SQLException e3) {
					System.err.println("Problème dans la requête SQL pour récupérer le numéro du client de cette réservation");
			}

        connection.fermeture(cnx);

        System.out.println("Client numéro : " + this.clientNumber);

        return null;
    }

    public Chambre getChambre() {
        return null;
    }
}