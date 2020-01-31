package oope2018ht.tiedostot;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.apulaiset.Setteri;

/*
* Olio-ohjelmoinnin perusteet
* Petteri Sällström
* Video.java
* Video peruskuvaus
* 9.4.2018
 */
public class Video extends Tiedosto {
    private double pituus;

    public Video(String n, int t, double p) {
        super(n, t);
        setPituus(p);
    }
    @Getteri
    public double getPituus() {
        return pituus;
    }
    @Setteri
    public void setPituus(double pituus) throws IllegalArgumentException {
        if (pituus > 0) {
            this.pituus = pituus;
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String toString() {
        return super.toString() + " " + getPituus() + " s";
    }
}