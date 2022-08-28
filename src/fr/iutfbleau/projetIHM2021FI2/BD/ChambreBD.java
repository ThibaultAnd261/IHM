package fr.iutfbleau.projetIHM2021FI2.BD;

import fr.iutfbleau.projetIHM2021FI2.API.Chambre;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;

public class ChambreBD implements Chambre {

    private int numero;
    private TypeChambre type;

    public ChambreBD(int numero, TypeChambre t) {
        this.numero = numero;
        this.type = t;
    }

    public int getNumero() {
        return this.numero;
    }

    public TypeChambre getType() {
        return this.type;
    }

    public boolean unLitDouble() {
        return (this.type == TypeChambre.UNLD);
    }

    public boolean deuxLitsSimples() {
        return (this.type == TypeChambre.DEUXLS);
    }

    public boolean unLitSimple() {
        return (this.type == TypeChambre.UNLS);
    }
}