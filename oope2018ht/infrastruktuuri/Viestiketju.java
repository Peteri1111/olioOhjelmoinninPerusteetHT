package oope2018ht.infrastruktuuri;

import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.Kuva;
import oope2018ht.tiedostot.Tiedosto;
import oope2018ht.tiedostot.Video;
import oope2018ht.viestit.Viesti;
import java.io.File;
import java.util.Scanner;

/**
 * S.O.B keskustelualue.
 * <p>
 * Viestiketju sisältää viestejä & vastauksia
 * <p>
 * Olio-ohjelmoinnin perusteet 2018.
 * <p>
 * Viimeksi muutettu 8.5.2018 00.51: kommentit lisätty, koodia parsittu, JavaDoc luotu
 * <p>
 * @author Petteri Sällström (Sallstrom.E.Petteri@student.uta.fi),
 * Tietojenkäsittelytieteiden tutkinto-ohjelma, 1. vuosi.
 */

public class Viestiketju {
    private OmaLista viestitKetjussa;

    private String ketjunNimi;
    private int ketjunID;


    public Viestiketju(int indeksi, String syote) {
        this.ketjunID = indeksi;
        this.viestitKetjussa = new OmaLista();
        this.ketjunNimi = syote;


    }


    public OmaLista getViestitKetjussa() {
        return viestitKetjussa;
    }

    public String getKetjunNimi() {
        return ketjunNimi;
    }




    //Tiedoston lukija joka palauttaa haetun tiedoston
    public Tiedosto tiedostonhakija(String tiedostoNimi) throws Exception {
        try {
            //alustetaan tiedosto ohjelmalle ja lukija sille
            File file = new File(tiedostoNimi);
            Scanner fileReader = new Scanner(file);

            //hajautetaan tiedoston data taulukkoon
            String[] data = fileReader.nextLine().split(" ");

            //Palautetaan joko kuva tai video
            if (data[0].equals("Kuva")) {
                int temp1 = Integer.parseInt(data[1]);
                int temp2 = Integer.parseInt(data[2]);
                int temp3 = Integer.parseInt(data[3]);

                return new Kuva(tiedostoNimi, temp1,temp2,temp3);
            } else {
                int temp1 = Integer.parseInt(data[1]);
                double temp2 = Double.parseDouble(data[2]);
                return new Video(tiedostoNimi, temp1, temp2);
            }
            //jotain sattui :(
        } catch (Exception e) {
            throw new Exception();
        }
    }
    public void uusiViesti(String syote, int vastattavanIndeksi, int juoksevaLuku) throws Exception {
        //alustetaan aikaisempi viesti nullina
        Viesti av = null;

        //Jos ollaan luomassa vastausta
        if (vastattavanIndeksi != 0) {
            av = etsiViesti(this.viestitKetjussa, (vastattavanIndeksi));
        }

        //alustetaan uusi viesti
        Viesti uusiViesti = null;

        //tarkistetaan onko viestin lopussa tiedosto
        String temp[] = syote.split(" ");
        if (temp[temp.length - 1].charAt(0) == '&') {

            //parsitaan tekstistä pois tiedosto
            syote = syote.substring(0, (syote.length() - temp[temp.length - 1].length() - 1));

            //luodaan Tiedosto ja tiedostollinen viesti
            Tiedosto tiedosto = tiedostonhakija(temp[temp.length - 1].substring(1));
            uusiViesti = new Viesti(juoksevaLuku, syote, av, tiedosto);

        } else {
            //luodaan tiedostoton viesti
            uusiViesti = new Viesti(juoksevaLuku, syote, av, null);
        }

        //lisätään viesti aikaisemman viestin vastauslistaan
        if (vastattavanIndeksi != 0) {
            av.lisaaVastaus(uusiViesti);
        } else {

            //jos täysin uusi viesti, lisätään se viestiketjun uusiin viesteihin
            this.viestitKetjussa.lisaaLoppuun(uusiViesti);
        }
    }

    public String annaKataloogiMuodossa() {
        // muotoa "#X Ketjun nimi (Y messages)"
        return "#" + this.ketjunID + " " + this.getKetjunNimi() + " (" + (this.getViestienMaara()) + " messages)";

    }
    //getViestienMaara laskee Viestiketjuolion viestien lukumäärän
    public int getViestienMaara() {
        int viestejaYhteensa = 0;

        //käydään jokainen alkuviestin ketju läpi apulaskurilla
        //ja lisätään viestejaYhteensa muuttujaan
        for (int i = 0; i < this.viestitKetjussa.koko(); i++) {
            viestejaYhteensa+= apuLaskuri( (Viesti) this.viestitKetjussa.alkio(i));
        }
        return viestejaYhteensa;
    }

    //palauttaa yhden alkuviestin kaikkien viestejen lukumäärän
    private int apuLaskuri(Viesti viesti) {
       //tämä viesti +1
        int luku = 1;

        //katotaan onko tällä viestillä vastauksia,
        //jos on niin kutakin kutsutaan samalla metodilla (rekursiivisuus)
        for (int i = 0; i < viesti.getViitteet().koko(); i++) {
            luku+= apuLaskuri( (Viesti) viesti.getViitteet().alkio(i));
        }

        return luku;

    }

    //rekursiivisesti toimiva metodi, joka toimii kuten apulaskuri,
    //mutta tulostaa aina viestin kun siihen päästään)
    public void tulostaPuuna(Viesti viesti, int leveysKerroin) {

        //sisennys
        for (int k = 0; k < leveysKerroin; k++) {
            System.out.print("   ");

        }
        //tulostetaan nykyinen viesti
        System.out.println(viesti.toString());

        //jos viestillä on vastauksia, siirytään i:ns viestiin
        for (int i = 0; i < viesti.getViitteet().koko(); i++) {
            Viesti temp = (Viesti) viesti.getViitteet().alkio(i);

            //sisennys kasvaa aina yhdellä (3 merkkiä)
            tulostaPuuna(temp, (leveysKerroin + 1));

        }

    }

    //Metodi joka palauttaa sekalaisen listan ketjun viesteistä
    private OmaLista luoLista(int juoksevaLuku) throws Exception {

        //luodaan tyhjä lista
        OmaLista lista = new OmaLista();

        //haetaan indeksi kerrallaan kaikki Viestiketjun viestit ja lisätään ne listaan
        for (int k = 1; k < juoksevaLuku; k++) {
            Viesti viesti = this.etsiViesti(this.viestitKetjussa, k);
            lista.lisaaLoppuun(viesti);
        }

        //palautetaan lista jossa kaikki Viestiketjun viestit
        return lista;
    }

    //Metodi tulostaa listana indeksin mukaan kaikki Viestiketjun viestit
    public void tulostaListana(int juoksevaLuku) throws Exception {
        //haetaan viestit listaan
        OmaLista lista = luoLista(juoksevaLuku);

        //järjestetään viestit (tarpeellinen?)
        sortList(lista, 0);

        //skipataan mahdolliset NULL viestit, tulostetaan muut
        for (int i = 0; i < lista.koko(); i++) {
            if (lista.alkio(i) != null)
            System.out.println(lista.alkio(i));
        }
    }

    //Tulostaa Viestiketjusta haluttu määrä vanhimpia viestejä
    public void tulostaVanhimmat(int alkioita, int juoksevaLuku) throws Exception {

        //haetaan lista ja järjestellään se
        OmaLista lista = luoLista(juoksevaLuku);
        sortList(lista, 0);

        //OmaListan metodi katkaisee listan sopivasta kohdasta
        lista = lista.annaAlku(alkioita);

        //tulostus
        for (int i = 0; i < lista.koko(); i++) {
            System.out.println(lista.alkio(i).toString());
        }
    }
    //kokeile wetossa
    public void tulostaUusimmat(int alkioita, int juoksevaLuku) throws Exception {
        OmaLista lista = luoLista(juoksevaLuku);
        sortList(lista, 0);
        lista = lista.annaLoppu(alkioita);

        for (int i = 0; i < lista.koko(); i++) {
            System.out.println(lista.alkio(i).toString());
        }
    }

    //Collections.sort periaatteessa, en muista miksi parametrinä indeksi
    //itseasiassa, tekeekö tämä mitään :O, tutkitaan myöhemmin
    private OmaLista sortList(OmaLista omalista, int i) {
        for (; i < omalista.koko(); i++) {
            Viesti temp = (Viesti) omalista.alkio(i);
            i++;
            if (temp != null && temp.getId() == (i)) {
                omalista.korvaa((i-1), temp);
            }
        }
        return omalista;

    }

    //Hakee halutun indeksin viestin
    public Viesti etsiViesti(OmaLista lista, int indeksi) throws Exception {

        //käydään läpi haluttu lista
        for (int i = 0; i < lista.koko(); i++) {
            Viesti temp = (Viesti) lista.alkio(i);

            //jos tämä viesti on haluttu viesti
            if (temp.getId() == indeksi) {
                return temp;

                //muulloin tarkistetaan onko viestillä vastauksia,
                //jos on, niin käydään ne läpi
            } else if (temp.getViitteet().koko() > 0) {
                Viesti vastaus = etsiViesti(temp.getViitteet(), indeksi);
                if (vastaus != null) {
                    return vastaus;
                }
            }
        }
        return null;
    }
    //hakee viesti mikä halutaan tyhjentää
    public Viesti haeTyhjennettavaViesti(int indeksi) throws Exception {
        return etsiViesti(this.viestitKetjussa, indeksi);
    }

    //Metodi hakee kaikki viestit jotka sisältään syötteen,
    //tulostaen nämä listana indeksin mukaan
    public void tulostaViestitMerkkijonolla(String syote, int juoksevaLuku) throws Exception {

        //haetaan lista viesteistä
        OmaLista lista = luoLista(juoksevaLuku);

        //käydään lista läpi
        for (int i = 0; i < lista.koko(); i++) {
            Viesti temp = (Viesti) lista.alkio(i);

            //skipataan "eiViestit"
            if (temp == null) {
                continue;
            }
            //jos viesti sisältää syötteen, tulostetaan viestin merkkijonoesitys
            if (temp.toString().contains(syote)) {
                System.out.println(temp);
            }
        }
    }
}
