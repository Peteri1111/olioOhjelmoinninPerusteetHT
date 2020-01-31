package oope2018ht.omalista;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2018ht.apulaiset.Ooperoiva;

/*
* Olio-ohjelmoinnin perusteet
* Petteri Sällström
* OmaLista.java
* OmaLista peruskuvaus
* 9.4.2018
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva {

    @Override
    public Object hae(Object haettava) {
        for (int i = 0; i < super.koko(); i++) {
            if (super.alkio(i).equals(haettava)) {
                return super.alkio(i);
            }
        }
        return null;
    }

    @SuppressWarnings({"unchecked"})
    public boolean lisaa(Object uusi) {
        if (uusi == null || !(uusi instanceof Comparable)) {
            return false;
        }
        for (int i = 0; i < super.koko(); i++) {
            if (((Comparable) uusi).compareTo(super.alkio(i)) < 0) {
                super.lisaa(i,uusi);
                return true;
            }
        }
        super.lisaaLoppuun(uusi);
        return true;

    }

    @Override
    public OmaLista annaAlku(int n) {
        if (n == 0 || super.koko() == 0 || n > super.koko()) {
            return null;
        }
        OmaLista temp = new OmaLista();
        for (int i = 0; i < n; i++) {
            temp.lisaaLoppuun(super.alkio(i));
        }

        return temp;

    }

    @Override
    public OmaLista annaLoppu(int n) {
        if (n == 0 || super.koko() == 0 || n > super.koko()) {
            return null;
        }
        OmaLista temp = new OmaLista();
        for (int i = super.koko()-n; i < super.koko(); i++) {
            temp.lisaaLoppuun(super.alkio(i));
        }
        return temp;

    }
}