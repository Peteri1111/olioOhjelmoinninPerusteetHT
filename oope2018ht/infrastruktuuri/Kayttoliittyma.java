package oope2018ht.infrastruktuuri;

import oope2018ht.apulaiset.In;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.Kuva;
import oope2018ht.tiedostot.Tiedosto;
import oope2018ht.tiedostot.Video;
import oope2018ht.viestit.*;

import java.io.File;
import java.util.Scanner;

/**
 * S.O.B Käyttöliittymä.
 * <p>
 * Käyttöliittymä, jonka kanssa käyttäjä kommunikoi käyttäessään S.O.B:tä
 * <p>
 * Olio-ohjelmoinnin perusteet 2018.
 * <p>
 * Viimeksi muutettu 8.5.2018 00.35: kommentit lisätty, koodia siistitty, JavaDoc luotu
 * <p>
 * @author Petteri Sällström (Sallstrom.E.Petteri@student.uta.fi),
 * Tietojenkäsittelytieteiden tutkinto-ohjelma, 1. vuosi.
 */

public class Kayttoliittyma {
    //Attribuutit

    /** Käyttöliittymän viestialue.*/
    private Alue alue;
    /** Virheilmoituksen viesti. */
    final String ERROR = "Error!";


    /** Käyttöliittymän ajokomento, joka luo uuden keskustelualueen, sekä
     * aloittaa ohjelman ja käyttäjän dialogin.
      */

    public void run() {
        this.alue = new Alue();
        System.out.println("Welcome to S.O.B.");
        boolean programEnd = false;


        while (!programEnd) {
            System.out.print(">");
            String syote = In.readString();

            //Puretaan käyttäjän antama komento palasiksi käsittelyä varten
            String[] syotePalat = syote.split(" ");

            //Tarkistetaan onko tyhjä viesti
            if (syotePalat.length < 1) {
                System.out.println(ERROR);
            }

            /*switchillä tarkistetaan ensimmäinen sana syötteestä ja
            toteutaan sanaa vastaava komento, jos se on olemassa.
            Muuten heitetään erroria ja pyydetään uusi komento.
            Try/catch vastaanottaa mahdollisen virheilmoituksen
             ja tulostaa virheilmoituksen
            */
            try {
                switch (syotePalat[0]) {
                    case "add":
                        this.alue.lisaaViestiKetju(syote.substring(4));
                        break;
                    case "catalog":
                        this.alue.kataLoogi();
                        break;
                    case "select":
                        this.alue.valitseKetju(Integer.parseInt(syote.substring(7)));
                        break;
                    case "new":
                        this.alue.luoUusiViesti(syote.substring(4));
                        break;
                    case "reply":
                        this.alue.vastaaViestiin(syote.substring(6));
                        break;
                    case "list":
                        this.alue.tulostaKetju(false);
                        break;
                    case "tree":
                        this.alue.tulostaKetju(true);
                        break;
                    case "head":
                        this.alue.tulostaVanhimmat(Integer.parseInt(syote.substring(5)));
                        break;
                    case "tail":
                        this.alue.tulostaUusimmat(Integer.parseInt(syote.substring(5)));
                        break;
                    case "empty":
                        this.alue.tyhjennaViesti(Integer.parseInt(syote.substring(6)));
                        break;
                    case "find":
                        this.alue.etsiViestit(syote.substring(5));
                        break;
                    case "exit":
                        System.out.println("Bye! See you soon.");
                        programEnd = true;
                        break;
                    //jos jokin muu "komento"
                    default:
                        System.out.println(ERROR);
                }
            } catch (Exception e) {
                System.out.println(ERROR);
            }
        }
    }
}