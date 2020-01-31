package oope2018ht.tiedostot;

/*
* Olio-ohjelmoinnin perusteet
* Petteri Sällström
* Kuva.java
* Kuva peruskuvaus
* 9.4.2018
 */


import oope2018ht.apulaiset.Getteri;
import oope2018ht.apulaiset.Setteri;

public class Kuva extends Tiedosto {
    private int korkeus;
    private int leveys;

    public Kuva(String n, int t, int k, int l) {
        super(n, t);
        setKorkeus(k);
        setLeveys(l);
    }
    @Getteri
    public int getKorkeus() {
        return korkeus;
    }
    @Getteri
    public int getLeveys() {
        return leveys;
    }
    @Setteri
    public void setKorkeus(int korkeus) throws IllegalArgumentException {
        if (korkeus > 0) {
            this.korkeus = korkeus;
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Setteri
    public void setLeveys(int leveys) throws IllegalArgumentException {
        if (leveys > 0) {
            this.leveys = leveys;
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String toString() {
        return super.toString() + " " + getKorkeus() + "x" + getLeveys();
    }
}