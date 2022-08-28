package fr.iutfbleau.projetIHM2021FI2.Sujet.Controller;

import java.awt.event.*;
import javax.swing.JFrame;

import fr.iutfbleau.projetIHM2021FI2.Sujet.View.Accueil;

public class RetourAccueil implements ActionListener {
    
    private String cmp;
    private JFrame fenetre;
    
    public RetourAccueil(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.cmp = e.getActionCommand();

        if(this.cmp.equals("Retour à l'accueil")) {
            this.fenetre.dispose();
            new Accueil();
        }
    }
}
