/** * 
 * Controler de l'accueil de l'application
 */

package fr.iutfbleau.projetIHM2021FI2.Sujet.Controller;

import javax.swing.*;
import java.awt.event.*;

import fr.iutfbleau.projetIHM2021FI2.Sujet.View.AccueilView;
import fr.iutfbleau.projetIHM2021FI2.Sujet.View.Graph;

public class AccueilController implements ActionListener{

    private String cmp;
    private JFrame fenetre;

    /**
     * Constructeur
     * @param fenetre la fenêtre d'accueil
     */
    public AccueilController(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.cmp = e.getActionCommand();
        
        // Si clique sur bouton Sujet 1 alors envoie à l'accueil du sujet 1
        if(this.cmp.equals("Gérer les préréservations")){
            AccueilView accueil = new AccueilView();
            accueil.createAccueilView();
            this.fenetre.dispose();
        }
        else {
            // Si clique sur bouton Sujet 2 alors envoie à l'accueil du sujet 2
            if(this.cmp.equals("Statistiques de l'hôtel")) {
                new Graph();
                this.fenetre.dispose();
            }
        }

    }
}
