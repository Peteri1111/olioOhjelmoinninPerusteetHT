
import oope2018ht.infrastruktuuri.Kayttoliittyma;

/*
 * Olio-ohjelmoinnin perusteet
 * Oope2018HT.java
 * Oope2018HT peruskuvaus
 * Luontipäivämäärä 9.4.2018
 *  @author Petteri Sällström
 *  Viimeksi muokattu 8.5.2018
 */

/**
 * S.O.B main().
 * <p>
 * Olio-ohjelmoinnin perusteet 2018.
 * <p>
 * Luo käyttöliittymän ja ajaa sen
 * <p>
 * @author Petteri Sällström (Sallstrom.E.Petteri@student.uta.fi),
 * Tietojenkäsittelytieteiden tutkinto-ohjelma, 1. vuosi.
 */

public class Oope2018HT {
    public static void main(String[] args) {

        //luodaan käyttöliittymä ja ajetaan se

        /**Ohjelman Käyttöliittymä */
        Kayttoliittyma kayttis = new Kayttoliittyma();
        kayttis.run();

    }

}