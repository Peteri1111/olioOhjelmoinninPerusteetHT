package oope2018ht.tiedostot;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.apulaiset.Setteri;

/*
* Olio-ohjelmoinnin perusteet
* Petteri Sällström
* Tiedosto.java
* Tiedosto peruskuvaus
* 9.4.2018
 */
public abstract class Tiedosto {
    private String nimi;    //tiedoston nimi
    private int koko;       //tavuja

    public Tiedosto(String n, int t) {
        setNimi(n);
        setKoko(t);
    }
    @Getteri
    public String getNimi() {
        return nimi;
    }
    @Getteri
    public int getKoko() {
        return koko;
    }
    @Setteri
    public void setKoko(int koko) throws IllegalArgumentException {
        if (koko > 0) {
            this.koko = koko;
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Setteri
    public void setNimi(String nimi) throws IllegalArgumentException {
        if (nimi == null || nimi.length() <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.nimi = nimi;
        }
    }

    @Override
    public String toString() {
        return getNimi() + " " + getKoko() + " B";
    }
}