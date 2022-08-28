/** * 
 * Model de ReservationFactory qui reprend l'API ReservationFactory
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Model;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.API.Prereservation;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;
import fr.iutfbleau.projetIHM2021FI2.API.Chambre;
import fr.iutfbleau.projetIHM2021FI2.API.Reservation;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.time.LocalDate;

public class ReservationFactoryModel implements ReservationFactory {

    private Connection cnx;

    private Set<Chambre> ensembleChambre;
    private List<Integer> ensembleNum;
    private TypeChambre typeChambreP;

    private int ratio, nbNuits, date, currentDay, totalCh, calculFinal, categorie, cpt = 0;
    private String cateType;
    private double conversion;

    private Reservation reserv;
    private int idReserv;

    public ReservationFactoryModel(){
    }

    public ReservationFactoryModel(int currentDay){
        this.currentDay = currentDay;
    }
    /**
     * @param p la préréservation
     * @return les chambres réservables correspondants aux critères de
     *         préréservation
     */
    public Set<Chambre> getChambres(Prereservation p) {

        this.typeChambreP = p.getTypeChambre();

        if (p.equals(null)) {
            throw new NullPointerException("La préréservation est null");
        }

        try {

            ConnectionModel connection = new ConnectionModel();
            this.cnx = connection.connexionVal();
            /*
             * On regarde s'il y a au moins une chambre disponible, si oui on return un
             * ensemble de chambre sinon on return null qui lèvera une exception
             */
            PreparedStatement rqt = cnx.prepareStatement(
                    "SELECT id FROM chambreIHM WHERE type=? AND id NOT IN (SELECT idChambre from reservationIHM)");
            rqt.setString(1, String.valueOf(this.typeChambreP));
            ResultSet rs = rqt.executeQuery();

            // Verifie s'il y a au moins une chambre correspondante
            if (!(rs.next())) {
                throw new IllegalStateException("Aucune chambre disponible");
            } else {

                ensembleChambre = new HashSet<Chambre>();
                ensembleNum = new ArrayList<>();

                rs.beforeFirst();

                while ((rs.next())) {
                    ensembleNum.add(rs.getInt(1));
                    int numC = rs.getInt(1);
                    Chambre cha = new ChambreModel(numC);
                    ensembleChambre.add(cha);
                }
            }
            connection.fermetureVal(this.cnx);

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL getChambres");
        }
        return ensembleChambre;
    }

    /**
     * @param p la préréservation
     * @return une chambre réservable correspondant aux critères de préréservation
     */
    public Chambre getChambre(Prereservation p) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    /**
     * @param p la préréservation
     * @param c la chambre à réserver
     * @return la réservation créée
     */
    public Reservation createReservation(Prereservation p, Chambre c) {

        if (p == null || c == null) {
            throw new NullPointerException("Prereservation (null) || Chambre (null)");
        }

        try {

            ConnectionModel connection = new ConnectionModel();
            this.cnx = connection.connexionVal();

            /*
             * On verifie qu'il n'y a pas de reservation deja crée avec cette prereservation
             * grace a la reference
             */
            PreparedStatement rqt = cnx.prepareStatement("SELECT idReservation FROM reservationIHM WHERE reference=?");
            rqt.setString(1, String.valueOf(p.getReference()));
            ResultSet rs = rqt.executeQuery();

            // Verifie s'il y a au moins un resultat
            if ((rs.next())) {
                JOptionPane.showMessageDialog(new JFrame(), "Une réservation existe déjà pour cette préréservation",
                        "Erreur réservation", JOptionPane.ERROR_MESSAGE);
                throw new IllegalStateException("Une réservation existe déjà pour cette préréservation");
            } else {

                try {
                    // si aucune reservation déja créer
                    rqt = cnx.prepareStatement("INSERT INTO reservationIHM(idCHambre,reference) VALUES (?,?)");
                    rqt.setInt(1, c.getNumero());
                    rqt.setString(2, p.getReference());

                    rs = rqt.executeQuery();

                    System.out.println("reservation crée");

                    // recupère idReservation
                    try {
                        rqt = cnx.prepareStatement("SELECT idReservation FROM reservationIHM WHERE reference=?");
                        rqt.setString(1, p.getReference());

                        rs = rqt.executeQuery();

                        rs.beforeFirst();

                        while ((rs.next())) {
                            idReserv = rs.getInt(1);
                        }
                        // creation de l'objet Reservation
                        reserv = new ReservationModel(p.getReference());

                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Impossible de recuperer l'idReserv",
                                "Erreur idReservation", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException e2) {
                    JOptionPane.showMessageDialog(new JFrame(), "Impossible de cree la reservation",
                            "Erreur reservation", JOptionPane.ERROR_MESSAGE);
                }
            }

            connection.fermetureVal(this.cnx);

        } catch (SQLException e3) {
            System.err.println(
                    "Problème dans la requête SQL creationReservation avec prereservation et chambre DANS ReservationFactoryModel");
        }

        return reserv;
    }

    public int getRatio(LocalDate d) {

        if(d.equals(null)){
            throw new NullPointerException("Date invalide");
        }

        try {

            ConnectionModel connection = new ConnectionModel();
            this.cnx = connection.connexion();
            
            PreparedStatement rqt = cnx.prepareStatement("SELECT debut, nuits FROM Reservation WHERE debut<?");
            rqt.setDate(1, java.sql.Date.valueOf(d));
            ResultSet rs = rqt.executeQuery();

            if (!(rs.next())) {
                throw new IllegalStateException("Aucune date correspondant à la requête");
            } else {
                rs.beforeFirst();
                while ((rs.next())) {
                    this.date += rs.getDate(1).toLocalDate().getDayOfMonth();
                    if((this.date + rs.getInt(2))>=this.currentDay){
                        this.cpt++;
                    }
                }
            }
            connection.fermeture(this.cnx);

            this.cnx = connection.connexionVal();


            PreparedStatement rqt2 = cnx.prepareStatement("SELECT COUNT (*) AS total FROM chambreIHM");
            ResultSet rs2 = rqt2.executeQuery();

            if(!(rs2.next())){
                throw new IllegalStateException("Aucune date correspondant à la requête");
            } else {
                rs2.beforeFirst();
                while ((rs2.next())){
                    this.totalCh += rs2.getInt("total");
                }
            }
            connection.fermetureVal(this.cnx);

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL getRatio");
        }
        this.conversion = (Double.valueOf(this.cpt)/Double.valueOf(this.totalCh))*100;
        this.calculFinal = (int) Math.round(this.conversion);
        this.cpt = 0;
        this.totalCh = 0;
        return this.calculFinal;
    }

    public int getRatio(LocalDate d, TypeChambre t) {
        if (t == TypeChambre.UNLS) {
            this.categorie = 1;
            this.cateType = "UNLS";
        } else {
            if (t == TypeChambre.UNLD) {
                this.categorie = 2;
                this.cateType = "UNLD";
            } else {
                if (t == TypeChambre.DEUXLS) {
                    this.categorie = 3;
                    this.cateType = "DEUXLS";
                }
                else {
                    throw new NullPointerException("Pas de type de chambre");
                }
            }
        }
         try {

            ConnectionModel connection = new ConnectionModel();
            this.cnx = connection.connexion();
            
            PreparedStatement rqt = cnx.prepareStatement("SELECT debut, nuits FROM Reservation WHERE debut<? AND categorie=?");
            rqt.setDate(1, java.sql.Date.valueOf(d));
            rqt.setInt(2, this.categorie);
            ResultSet rs = rqt.executeQuery();

            if (!(rs.next())) {
                throw new IllegalStateException("Aucune date correspondant à la requête");
            } else {
                rs.beforeFirst();
                while ((rs.next())) {
                    this.date += rs.getDate(1).toLocalDate().getDayOfMonth();
                    if((this.date + rs.getInt(2))>=this.currentDay){
                        this.cpt++;
                    }
                }
            }
            connection.fermeture(this.cnx);

            this.cnx = connection.connexionVal();


            PreparedStatement rqt2 = cnx.prepareStatement("SELECT COUNT (*) AS total FROM chambreIHM WHERE type=?");
            rqt2.setString(1, this.cateType);
            ResultSet rs2 = rqt2.executeQuery();

            if(!(rs2.next())){
                throw new IllegalStateException("Aucune date correspondant à la requête");
            } else {
                rs2.beforeFirst();
                while ((rs2.next())){
                    this.totalCh += rs2.getInt("total");
                }
            }
            connection.fermetureVal(this.cnx);

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL getRatio");
        }
        this.conversion = (Double.valueOf(this.cpt)/Double.valueOf(this.totalCh))*100;
        this.calculFinal = (int) Math.round(this.conversion);
        this.cpt = 0;
        this.totalCh = 0;
        return this.calculFinal;
    }

    public Set<Reservation> getReservation(LocalDate d) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public int getDisponibles(LocalDate d) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public Set<Reservation> getReservation(LocalDate d, TypeChambre t) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public int getDisponibles(LocalDate d, TypeChambre t) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public int getDisponibles(LocalDate d1, LocalDate d2) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public Set<Reservation> getReservation(LocalDate d1, LocalDate d2, TypeChambre t) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public int getDisponibles(LocalDate d1, LocalDate d2, TypeChambre t) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public int getRatio(LocalDate d1, LocalDate d2) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

    public int getRatio(LocalDate d1, LocalDate d2, TypeChambre t) {
        throw new java.lang.UnsupportedOperationException("Pas d'implémentation prévue");
    }

}