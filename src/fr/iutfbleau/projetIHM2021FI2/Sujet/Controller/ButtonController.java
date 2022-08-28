package fr.iutfbleau.projetIHM2021FI2.Sujet.Controller;

import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;
import java.text.*;
import java.time.format.DateTimeFormatter;

import fr.iutfbleau.projetIHM2021FI2.Sujet.View.Graph;

public class ButtonController implements ActionListener{

    private String cmp, DateEvent, DateEvent2;

	private int DayCtrl, MonthCtrl, YearCtrl;

    private Calendar calendarModif;
    private Calendar calendarModifAPICtrl;

    private Graph GraphView;
    
    public ButtonController(Graph GraphView, GregorianCalendar calendar, GregorianCalendar calendarAPI, int Day, int Month, int Year){
        this.GraphView = GraphView;
        this.calendarModif = calendar;
        this.calendarModifAPICtrl = calendarAPI;
        this.DayCtrl = Day;
        this.MonthCtrl = Month;
        this.YearCtrl = Year;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.cmp = e.getActionCommand();
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");




        if(this.cmp.equals("Année -1")){
            this.calendarModif.add(Calendar.YEAR,-1);
            this.DateEvent = dateFormat.format(this.calendarModif.getTime());

            this.calendarModifAPICtrl.add(Calendar.YEAR, -1);
            this.DateEvent2 = dateFormat.format(this.calendarModifAPICtrl.getTime());


            this.YearCtrl = (calendarModif.get(Calendar.YEAR));
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent, this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }

        if(this.cmp.equals("Année +1")){
            this.calendarModif.add(Calendar.YEAR,1);
            this.DateEvent = dateFormat.format(this.calendarModif.getTime());

            this.calendarModifAPICtrl.add(Calendar.YEAR, 1);
            this.DateEvent2 = dateFormat.format(this.calendarModifAPICtrl.getTime());


            this.YearCtrl = (calendarModif.get(Calendar.YEAR));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent,  this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }

        if(this.cmp.equals("<<<")){
            this.calendarModif.add(Calendar.MONTH,-1);
            this.DateEvent = dateFormat.format(this.calendarModif.getTime());

            this.calendarModifAPICtrl.add(Calendar.MONTH, -1);
            this.DateEvent2 = dateFormat.format(this.calendarModifAPICtrl.getTime());

            this.YearCtrl = (calendarModif.get(Calendar.YEAR));
            this.DayCtrl = (calendarModif.get(Calendar.DATE));
            this.MonthCtrl = (calendarModif.get(Calendar.MONTH)+1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent,  this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }

        if(this.cmp.equals("<<")){
            this.calendarModif.add(Calendar.DATE, -7);
            this.DateEvent = dateFormat.format(this.calendarModif.getTime());

            this.calendarModifAPICtrl.add(Calendar.DATE, -7);
            this.DateEvent2 = dateFormat.format(this.calendarModifAPICtrl.getTime());

            this.DayCtrl = (calendarModif.get(Calendar.DATE));
            this.YearCtrl = (calendarModif.get(Calendar.YEAR));
            this.MonthCtrl = (calendarModif.get(Calendar.MONTH)+1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent,  this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }

        if(this.cmp.equals("<")){
            this.calendarModif.add(Calendar.DATE, -1);
            this.DateEvent = dateFormat.format(this.calendarModif.getTime());

            this.calendarModifAPICtrl.add(Calendar.DATE, -1);
            this.DateEvent2 = dateFormat.format(this.calendarModifAPICtrl.getTime());
            
            this.DayCtrl = (calendarModif.get(Calendar.DATE));
            this.YearCtrl = (calendarModif.get(Calendar.YEAR));
            this.MonthCtrl = (calendarModif.get(Calendar.MONTH)+1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent,  this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }

        if(this.cmp.equals(">")){
            this.calendarModifAPICtrl.add(Calendar.DATE, 1);
            this.DateEvent = dateFormat.format(this.calendarModifAPICtrl.getTime());

            this.calendarModif.add(Calendar.DATE, 1);
            this.DateEvent2 = dateFormat.format(this.calendarModif.getTime());

            this.DayCtrl = (calendarModif.get(Calendar.DATE));
            this.YearCtrl = (calendarModif.get(Calendar.YEAR));
            this.MonthCtrl = (calendarModif.get(Calendar.MONTH)+1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent,  this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }

        if(this.cmp.equals(">>")){
            this.calendarModifAPICtrl.add(Calendar.DATE, 7);
            this.DateEvent = dateFormat.format(this.calendarModifAPICtrl.getTime());

            this.calendarModif.add(Calendar.DATE, 7);
            this.DateEvent2 = dateFormat.format(this.calendarModif.getTime());

            this.DayCtrl = (calendarModif.get(Calendar.DATE));
            this.YearCtrl = (calendarModif.get(Calendar.YEAR));
            this.MonthCtrl = (calendarModif.get(Calendar.MONTH)+1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent,  this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }

        if(this.cmp.equals(">>>")){
            this.calendarModif.add(Calendar.MONTH,1);
            this.DateEvent = dateFormat.format(this.calendarModif.getTime());

            this.calendarModifAPICtrl.add(Calendar.MONTH, 1);
            this.DateEvent2 = dateFormat.format(this.calendarModifAPICtrl.getTime());

            this.YearCtrl = (calendarModif.get(Calendar.YEAR));
            this.DayCtrl = (calendarModif.get(Calendar.DATE));
            this.MonthCtrl = (calendarModif.get(Calendar.MONTH)+1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateModelCtrl = LocalDate.parse(DateEvent2, formatter);

            this.GraphView.majView(DateEvent,  this.DayCtrl, this.MonthCtrl, this.YearCtrl, dateModelCtrl);
        }
    }
}