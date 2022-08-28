package fr.iutfbleau.projetIHM2021FI2.Sujet.View;

import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.*;
import java.time.LocalDate;

import fr.iutfbleau.projetIHM2021FI2.Sujet.Controller.ButtonController;
import fr.iutfbleau.projetIHM2021FI2.Sujet.Controller.RetourAccueil;
import fr.iutfbleau.projetIHM2021FI2.Sujet.Model.*;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;


public class Graph extends JFrame {
	private JButton retour, btnAnneePre, btnAnneeSui, btnPrecedentMois, btnPrecedentSemaine, btnPrecedentJour, btnSuivantJour, btnSuivantMois, btnSuivantSemaine;

	private JLabel annoncePrecedentJour, annonceSuivantJour, annoncePrecedentMois, annoncePrecedentSemaine, annonceSuivantSemaine, annonceSuivantMois;
    private JLabel intro, dateIntro;
	private JLabel tauxGlobal, taux1,taux2,taux3;
    private JLabel typeLit1,typeLit2,typeLit3, moyennePrec1,moyennePrec2,moyennePrec3;

	private int Day, Month, Year, dateModel;

	private String[] mois;
	private String moisNomAffiche;

    private ButtonController btnController;
	private ReservationFactoryModel rfm;

	private GridBagConstraints gbc;

	/**
	 * Constructeur générant le fenêtre d'accueil des statistiques de l'hôtel
	 */
	public Graph(){	
		this.setSize(1000,800);
		this.setTitle("Statistiques");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
 		this.setLayout(new GridBagLayout());


 		this.gbc = new GridBagConstraints();
 		gbc.weightx = 1;
 		gbc.weighty = 1;

		GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateReserv = dateFormat.format(calendar.getTime());



		this.Day = (calendar.get(Calendar.DATE));
		this.Month = (calendar.get(Calendar.MONTH)+1);
		this.Year = (calendar.get(Calendar.YEAR));
		
		GregorianCalendar calendarAPI = (GregorianCalendar) GregorianCalendar.getInstance();

		LocalDate localDate = LocalDate.now(); 
		
		DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		String dateReserv2 = dateFormat2.format(calendarAPI.getTime());

		this.btnController = new ButtonController(this, calendar, calendarAPI, this.Day, this.Month, this.Year);

		this.btnAnneePre = new JButton("Année -1");
		gbc.gridx = 0;
 		gbc.gridy = 0;
 		gbc.gridwidth = 6;
 		gbc.gridheight = 2;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.btnAnneePre,gbc);
		this.btnAnneePre.addActionListener(this.btnController);

		this.intro = new JLabel("Statistiques de l'hôtel : nombre de réservation");
		this.intro.setFont(new Font("Verdana", Font.BOLD, 16));
		this.intro.setHorizontalAlignment(JLabel.CENTER);
		this.intro.setOpaque(true);
 		gbc.gridx = 6;
 		gbc.gridy = 0;
 		gbc.gridwidth = 8;
 		gbc.gridheight = 2;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(this.intro,gbc);

		this.btnAnneeSui = new JButton("Année +1");
		gbc.gridx = 14;
 		gbc.gridy = 0;
 		gbc.gridwidth = 6;
 		gbc.gridheight = 2;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.btnAnneeSui,gbc);
		this.btnAnneeSui.addActionListener(this.btnController);

		this.annoncePrecedentMois = new JLabel("Mois -1");
		this.annoncePrecedentMois.setHorizontalAlignment(JLabel.CENTER);
		this.annoncePrecedentMois.setOpaque(true);
		gbc.gridx = 0;
 		gbc.gridy = 2;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.annoncePrecedentMois,gbc);

		this.annoncePrecedentSemaine = new JLabel("Semaine -1");
		this.annoncePrecedentSemaine.setHorizontalAlignment(JLabel.CENTER);
		this.annoncePrecedentSemaine.setOpaque(true);
		gbc.gridx = 3;
 		gbc.gridy = 2;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.annoncePrecedentSemaine,gbc);

		this.annoncePrecedentJour = new JLabel("Jour -1");
		this.annoncePrecedentJour.setHorizontalAlignment(JLabel.CENTER);
		this.annoncePrecedentJour.setOpaque(true);
		gbc.gridx = 6;
 		gbc.gridy = 2;
 		gbc.gridwidth = 4;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.annoncePrecedentJour,gbc);

		this.annonceSuivantJour = new JLabel("Jour +1");
		this.annonceSuivantJour.setHorizontalAlignment(JLabel.CENTER);
		this.annonceSuivantJour.setOpaque(true);
		gbc.gridx = 10;
 		gbc.gridy = 2;
 		gbc.gridwidth = 4;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.annonceSuivantJour,gbc);		

		this.annonceSuivantSemaine = new JLabel("Semaine +1");
		this.annonceSuivantSemaine.setHorizontalAlignment(JLabel.CENTER);
		this.annonceSuivantSemaine.setOpaque(true);
		gbc.gridx = 14;
 		gbc.gridy = 2;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.annonceSuivantSemaine,gbc);

		this.annonceSuivantMois = new JLabel("Mois +1");
		this.annonceSuivantMois.setHorizontalAlignment(JLabel.CENTER);
		this.annonceSuivantMois.setOpaque(true);
		gbc.gridx = 17;
 		gbc.gridy = 2;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.annonceSuivantMois,gbc);

		this.btnPrecedentMois = new JButton("<<<");
		gbc.gridx = 0;
 		gbc.gridy = 3;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.btnPrecedentMois,gbc);
		this.btnPrecedentMois.addActionListener(this.btnController);

		this.btnPrecedentSemaine = new JButton("<<");
		gbc.gridx = 3;
 		gbc.gridy = 3;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.btnPrecedentSemaine,gbc);
		this.btnPrecedentSemaine.addActionListener(this.btnController);

		this.btnPrecedentJour = new JButton("<");
		gbc.gridx = 6;
 		gbc.gridy = 3;
 		gbc.gridwidth = 4;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.btnPrecedentJour,gbc);
		this.btnPrecedentJour.addActionListener(this.btnController);

		this.btnSuivantJour = new JButton(">");
		gbc.gridx = 10;
 		gbc.gridy = 3;
 		gbc.gridwidth = 4;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.btnSuivantJour,gbc);
		this.btnSuivantJour.addActionListener(this.btnController);

		this.btnSuivantSemaine = new JButton(">>");
		gbc.gridx = 14;
 		gbc.gridy = 3;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.btnSuivantSemaine,gbc);
		this.btnSuivantSemaine.addActionListener(this.btnController);

		this.btnSuivantMois = new JButton(">>>");
		gbc.gridx = 17;
 		gbc.gridy = 3;
 		gbc.gridwidth = 3;
 		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 40;
		this.add(this.btnSuivantMois,gbc);
		this.btnSuivantMois.addActionListener(this.btnController);

		this.dateIntro = new JLabel();
  		this.dateIntro.setHorizontalAlignment(JLabel.CENTER);
 		gbc.gridx = 0;
 		gbc.gridy = 4;
 		gbc.gridwidth = 20;
 		gbc.gridheight = 3;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(this.dateIntro,gbc);

		this.typeLit1 = new JLabel("Taux d'occupation des chambres avec un lit simple :");
		this.typeLit1.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
 		gbc.gridwidth = 10;
 		gbc.gridheight = 1;
 		gbc.gridy = 7;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.typeLit1,gbc);

		this.taux1 = new JLabel("Ta1");
		this.taux1.setHorizontalAlignment(JLabel.CENTER);
		this.taux1.setBackground(Color.orange);
		this.taux1.setOpaque(true);
		gbc.gridx = 10;
 		gbc.gridwidth = 10;
 		gbc.gridheight = 1;
 		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.ipadx = 200;
		this.add(this.taux1,gbc);

        this.moyennePrec1 = new JLabel("Moyenne des 3 dernières années : ");
  		this.moyennePrec1.setHorizontalAlignment(JLabel.CENTER);
 		gbc.gridx = 0;
 		gbc.gridy = 8;
 		gbc.gridwidth = 20;
 		gbc.gridheight = 1;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(this.moyennePrec1,gbc);

		this.typeLit2 = new JLabel("Taux d'occupation des chambres avec un lit double :");
		this.typeLit2.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
 		gbc.gridwidth = 10;
 		gbc.gridheight = 1;
 		gbc.gridy = 9;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.typeLit2,gbc);

		this.taux2 = new JLabel("Taux n°2 : " );
		this.taux2.setHorizontalAlignment(JLabel.CENTER);
		this.taux2.setBackground(Color.orange);
		this.taux2.setOpaque(true);
		gbc.gridx = 10;
 		gbc.gridwidth = 10;
 		gbc.gridheight = 1;
 		gbc.gridy = 9;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.ipadx = 200;
		this.add(this.taux2,gbc);

        this.moyennePrec2 = new JLabel("Moyenne des 3 dernières années : ");
  		this.moyennePrec2.setHorizontalAlignment(JLabel.CENTER);
 		gbc.gridx = 0;
 		gbc.gridwidth = 20;
 		gbc.gridheight = 1;
 		gbc.gridy = 10;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(this.moyennePrec2,gbc);

        this.typeLit3 = new JLabel("Taux d'occupation des chambres avec deux lits simples :");
		this.typeLit3.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
 		gbc.gridwidth = 10;
 		gbc.gridheight = 1;
 		gbc.gridy = 11;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.typeLit3,gbc);

		this.taux3 = new JLabel("Taux n°3 : " );
		this.taux3.setHorizontalAlignment(JLabel.CENTER);
		this.taux3.setBackground(Color.orange);
		this.taux3.setOpaque(true);
		gbc.gridx = 10;
 		gbc.gridwidth = 10;
 		gbc.gridheight = 1;
 		gbc.gridy = 11;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.ipadx = 15*10;
 		this.add(this.taux3,gbc);

        this.moyennePrec3 = new JLabel("Moyenne des 3 dernières années : ");
  		this.moyennePrec3.setHorizontalAlignment(JLabel.CENTER);
 		gbc.gridx = 0;
 		gbc.gridwidth = 20;
 		gbc.gridheight = 1;
 		gbc.gridy = 12;
 		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(this.moyennePrec3,gbc);

		this.tauxGlobal = new JLabel();
   		this.tauxGlobal.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
 		gbc.gridwidth = 20;
 		gbc.gridheight = 2;
 		gbc.gridy = 13;
		gbc.ipadx = 1;
 		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.add(this.tauxGlobal,gbc);		

        this.retour = new JButton("Retour à l'accueil");
        gbc.gridx = 14;
 		gbc.gridwidth = 6;
 		gbc.gridheight = 2;
 		gbc.gridy = 15;
		gbc.ipady = 20;
		gbc.ipadx = 1;
 		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.retour.addActionListener(new RetourAccueil(this));
		this.add(this.retour,gbc);

		this.majView(dateReserv, this.Day, this.Month, this.Year, localDate);

		this.setVisible(true);
	}

	public void majView(String DateEventJSM, int Day, int Month, int Year, LocalDate dateLocalDate){
		this.mois = new String[] {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
		this.moisNomAffiche = this.mois[Month-1];
		this.dateIntro.setText("Jour sélectionné : "  +Day + " " +this.moisNomAffiche + " " +Year +" (" +DateEventJSM +")");
		
		this.rfm = new ReservationFactoryModel();


		if(Year < 2018 || (Year == 2018 && Day == 1)){
			this.tauxGlobal.setForeground(Color.RED);
			this.tauxGlobal.setText("TAUX D'OCCUPATION GLOBAL : DONNEES INDISPONIBLES AVANT LE 1ER JANVIER 2018");
			this.tauxGlobal.setFont(new Font("Verdana", Font.BOLD, 17));
			this.taux1.setText("ERREUR");
			this.taux2.setText("ERREUR");
			this.taux3.setText("ERREUR");
		}else{
			this.tauxGlobal.setText("TAUX D'OCCUPATION GLOBAL : " +rfm.getRatio(dateLocalDate) + " %");
			this.tauxGlobal.setFont(new Font("Verdana", Font.BOLD, 17));
			this.tauxGlobal.setForeground(new Color(108,198,185));
			this.taux1.setText(rfm.getRatio(dateLocalDate,TypeChambre.UNLS) + "%");
			this.taux2.setText(rfm.getRatio(dateLocalDate,TypeChambre.UNLD) + "%");
			this.taux3.setText(rfm.getRatio(dateLocalDate,TypeChambre.DEUXLS) + "%");
			this.remove(this.taux1);
			this.remove(this.taux2);
			this.remove(this.taux3);

			gbc.gridx = 10;
 			gbc.gridwidth = 10;
 			gbc.gridheight = 1;
 			gbc.gridy = 7;
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx = (rfm.getRatio(dateLocalDate,TypeChambre.UNLS))*5;
			this.add(this.taux1,gbc); 

			gbc.gridx = 10;
 			gbc.gridwidth = 10;
 			gbc.gridheight = 1;
 			gbc.gridy = 9;
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx = (rfm.getRatio(dateLocalDate,TypeChambre.UNLD))*5;
			this.add(this.taux2,gbc);

			gbc.gridx = 10;
 			gbc.gridwidth = 10;
 			gbc.gridheight = 1;
 			gbc.gridy = 11;
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.ipadx = (rfm.getRatio(dateLocalDate,TypeChambre.DEUXLS))*5;
 			this.add(this.taux3,gbc);
		}

				
		this.revalidate();
	}
}