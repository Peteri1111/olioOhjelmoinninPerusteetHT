package oope2018ht.infrastruktuuri;

import oope2018ht.omalista.OmaLista;
import oope2018ht.viestit.Viesti;

/**
 * S.O.B keskustelualue.
 * <p>
 * Sisältää useita viestiketjuja (topicceja) ja käsittelee niitä
 * <p>
 * Olio-ohjelmoinnin perusteet 2018.
 * <p>
 * Viimeksi muutettu 8.5.2018 00.51: kommentit lisätty, JavaDoc luotu
 * <p>
 * @author Petteri Sällström (Sallstrom.E.Petteri@student.uta.fi),
 * Tietojenkäsittelytieteiden tutkinto-ohjelma, 1. vuosi.
 */

public class Alue {
    //Viestintäalueen attribuutit

    /** Lista alueen viestiketjuista */
    private OmaLista viestiketjut;

    /** Uusien viestiketjujen juokseva luku*/
    private int juoksevaLuku;

    /** Uusien viestien sekä vastausten juokseva luku*/
    private int viestienJuoksevaLuku;

    /** Alueen aktiivinen viestiketju johon toiminnot kohistuvat*/
    private Viestiketju aktiivinenKetju;

    //:D
    /** Viestialueen nimi */
    private String nimi;


    /** Alustetaan Viestiketjujen ja viestien juoksevat luvut,
     * Lista viestiketjuille, sekä viestittelyalueen nimi
     * */
    public Alue() {
        //alustetaan juoksevat luvut indekseillä 1
        this.juoksevaLuku = 1;
        this.viestienJuoksevaLuku = 1;

        //alueen lista ketjuista
        this.viestiketjut = new OmaLista();

        //keskustelualueella on yleensä nimi, mutta tämä nyt ei vaan tee mitään
        this.nimi = "kissafoorumi";

    }

    public OmaLista getViestiketjut() {
        return viestiketjut;
    }

    public void lisaaViestiKetju(String syote) throws Exception {
        if (!(syote.length() < 1 || syote.equals(""))) {
            this.viestiketjut.lisaaLoppuun(new Viestiketju(this.juoksevaLuku, syote));
            if (this.juoksevaLuku == 1) {
                this.aktiivinenKetju = (Viestiketju) this.viestiketjut.alkio(0);
            }
            this.juoksevaLuku++;

        } else {
            throw new Exception();
        }
    }

    public void kataLoogi() {
        //Käydään kukin viestiketju erikseen läpi
        for (int i = 0; i < this.viestiketjut.koko(); i++) {

            //haetaan i:ns viestiketju, sekä tulostetaan tämän katalogiviesti
            Viestiketju temp =(Viestiketju) this.viestiketjut.alkio(i);
            System.out.println(temp.annaKataloogiMuodossa());

        }


    }

    public void valitseKetju(int indeksi) throws Exception {
        //jos haluttua ketjua ei ole olemassa, heitetään virheilmoitus
        if (this.getViestiketjut().koko() < indeksi || indeksi == 0) {
            throw new Exception();
        }
        //alueen aktiiviseksi ketjuksi asetetaan indeksin mukainen ketju
        this.aktiivinenKetju = (Viestiketju) this.viestiketjut.alkio((indeksi - 1));
    }

    private boolean viestiOK(String syote) {
        //viesti ei saa olla pelkkä ' ' tai ilman tekstiä
        if (syote.equals(" ") || syote.length() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void luoUusiViesti(String syote) throws Exception {
        //tarkistetaan viestin oikeellisuus
        if (viestiOK(syote)) {

            //aktiiviseen ketjuun lisätään uusi ensimmäinen viesti indeksoiden juoksevalla luvulla
            this.aktiivinenKetju.uusiViesti(syote, 0, this.viestienJuoksevaLuku);
            this.viestienJuoksevaLuku++;

        } else {
            throw new Exception();
        }
    }

    public void vastaaViestiin(String syote) throws Exception {
        if (!viestiOK(syote)) {
            throw new Exception();
        }

        //hajautetaan taulukkoon helpompaa käsittelyä varten
        String temp[] = syote.split(" ");

        //otetaan vastattavan kohteen indeksi muistiin
        int var = Integer.parseInt(temp[0]);

        //tarkistetaan että ei vastata olemattomaan viestiin
        if (var >= this.viestienJuoksevaLuku || var == 0) {
            throw new Exception();
        }

        //lisätään halutulle viestille uusi vastaus
        this.aktiivinenKetju.uusiViesti(syote.substring((temp[0].length()+1)),var, this.viestienJuoksevaLuku);
        this.viestienJuoksevaLuku++;



    }

    public void tulostaKetju(boolean tulostaPuuna) throws Exception {
        //alkuosa tulostusta
        System.out.println("=\n" +
                "== " + this.aktiivinenKetju.annaKataloogiMuodossa() + "\n" +
                "===");

        //tulostetaan puuna tai listana
        if (tulostaPuuna) {

            //tulostetaan järjestyksessä uusien viestien koko oksisto
            for (int i = 0; i < this.aktiivinenKetju.getViestitKetjussa().koko(); i++) {
                Viesti viesti = (Viesti) this.aktiivinenKetju.getViestitKetjussa().alkio(i);
                this.aktiivinenKetju.tulostaPuuna(viesti, 0);
            }
        } else {
            //tulostetaan järjestelty lista viestiketjun viesteistä
            this.aktiivinenKetju.tulostaListana(this.viestienJuoksevaLuku);
        }
    }


    public void tulostaVanhimmat(int alkioita) throws Exception {

        //heitetään virhettä jos halutaan tulostaa liikaa, tai liian vähän viestejä
        if (alkioita < 1 || alkioita > this.aktiivinenKetju.getViestienMaara()) {
            throw new Exception();
        } else {
            this.aktiivinenKetju.tulostaVanhimmat(alkioita, this.viestienJuoksevaLuku);
        }
    }
    public void tulostaUusimmat(int alkioita) throws Exception {
        //heitetään virhettä jos halutaan tulostaa liikaa, tai liian vähän viestejä
        if (alkioita < 1 || alkioita > this.aktiivinenKetju.getViestienMaara()) {
            throw new Exception();
        } else {
            this.aktiivinenKetju.tulostaUusimmat(alkioita, this.viestienJuoksevaLuku);
        }
    }

    public void tyhjennaViesti(int viestinIndeksi) throws Exception {
        //yritetään hakea indeksin viestiä, jos löytyy niin tyhjennetään
        Viesti viesti = this.aktiivinenKetju.haeTyhjennettavaViesti(viestinIndeksi);
        if (viesti == null) {
            throw new Exception();
        } else {
            viesti.tyhjenna();
        }
    }
    //etsitään kaikki vestit jotka sisältävät halutun viestin.
    //Tulostetaan nämä viestit järjestyksessä
    public void etsiViestit(String viesti) throws Exception {
        this.aktiivinenKetju.tulostaViestitMerkkijonolla(viesti, this.viestienJuoksevaLuku);

    }


}
