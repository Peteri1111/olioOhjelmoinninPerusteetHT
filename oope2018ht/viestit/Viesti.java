package oope2018ht.viestit;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.apulaiset.Komennettava;
import oope2018ht.apulaiset.Setteri;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.Tiedosto;

/*
* Olio-ohjelmoinnin perusteet
* Petteri Sällström
* Viesti.java
* Viesti peruskuvaus
* 9.4.2018
 */
public class Viesti implements Komennettava<Viesti>, Comparable<Viesti> {
    private int id;
    private String text;
    private Viesti aikaisempiViesti;
    private Tiedosto liite;
    private OmaLista viitteet;


    //uusi viestiketju
    public Viesti(int i, String t, Viesti av, Tiedosto l) throws IllegalArgumentException {
        try {
            setId(i);
            setText(t);
            setAikaisempiViesti(av);
            setLiite(l);
            setViitteet(new OmaLista());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Getteri
    public String getText() {
        return text;
    }

    @Getteri
    public int getId() {
        return id;
    }

    @Getteri
    public OmaLista getViitteet() {
        return viitteet;
    }

    @Getteri
    public Tiedosto getLiite() {
        return liite;
    }

    @Getteri
    public Viesti getAikaisempiViesti() {
        return aikaisempiViesti;
    }

    @Setteri
    public void setText(String text) throws IllegalArgumentException {
        if (text.length() > 0) {
            this.text = text;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Setteri
    public void setId(int id) throws IllegalArgumentException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Setteri
    public void setAikaisempiViesti(Viesti aikaisempiViesti) {
        this.aikaisempiViesti = aikaisempiViesti;
    }

    @Setteri
    public void setLiite(Tiedosto liite) {
        this.liite = liite;
    }

    @Setteri
    public void setViitteet(OmaLista viitteet) throws IllegalArgumentException {
        if (viitteet == null) {
            throw new IllegalArgumentException();
        } else {
            this.viitteet = viitteet;
        }
    }

    public int compareTo(Viesti verrattava) {
        if (this.getId() > verrattava.getId()) {
            return 1;
        }
        if (this.getId() < verrattava.getId()) {

            return -1;
        }
        return 0;

    }

    public boolean equals(Object verrattava) {
        if (verrattava instanceof Viesti && this.getId() == ((Viesti)verrattava).getId()) {
            return true;
        }
        return false;
    }

    @Override
    public Viesti hae(Viesti haettava) throws IllegalArgumentException {
        if (haettava == null) {
            throw new IllegalArgumentException();
        }
        Viesti temp = (Viesti) getViitteet().hae(haettava);
        return temp;

    }

    @Override
    public void lisaaVastaus(Viesti lisattava) throws IllegalArgumentException {
        if (lisattava == null || !(getViitteet().hae(lisattava) == null)) {
            throw new IllegalArgumentException();
        } else {
            getViitteet().lisaaLoppuun(lisattava);
        }
    }

    @Override
    public void tyhjenna() {
        setText(POISTETTUTEKSTI);
        setLiite(null);

    }
    @Override
    public String toString() {
        String temp = "";
        temp+="#" + getId() + " " + getText();
        if (!(getLiite() == null)) {
            temp += " (" + getLiite() + ")";
        }
        return temp;
    }
}