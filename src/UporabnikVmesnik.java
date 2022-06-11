import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class UporabnikVmesnik {
    public Object prijavljeniKorisnik;
    ArrayList<Object> odmori;
    public UporabnikVmesnik(Object prijavljeniKorisnik, ArrayList<Object> odmori) throws ParseException {
        this.prijavljeniKorisnik = prijavljeniKorisnik;
        this.odmori = odmori;
        RegularniMeni();

    }
    private void RegularniMeni() throws ParseException {
        System.out.println("\n*** Dobrodošli v turistični agenciji Hawaii! ******************* Standardni uporabniški nalog\n");
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Pritisnite (o) za ogled počitnica");
        System.out.println("Pritisnite (r) za rezervaciju");
        System.out.println("Pritisnite (i) za ogled vseh rezervacij");
        System.out.println("Pritisnite (e) za izhod");
        System.out.println("------------------------------------------------------------------------------");
        String otkrivenIzbor = ulaz.nextLine();
        if (otkrivenIzbor.equals("o")  || otkrivenIzbor.equals("r") || otkrivenIzbor.equals("i") || otkrivenIzbor.equals("e")) {
            switch (otkrivenIzbor){
                case "o":
                    PregledOdmora("obicni");
                    RegularniMeni();
                    break;
                case "r":
                    novaRezervacija(prijavljeniKorisnik, odmori);
                    RegularniMeni();
                    break;
                case "i":
                    Korisnik pregledRezervacije = (Korisnik) prijavljeniKorisnik;
                    pregledRezervacije.pregledRezervacija(odmori);
                    RegularniMeni();
                    break;
                case "e":
                    System.exit(0);
                    break;
            }

        } else {
            System.out.println("Niste vnesli pravilnega znaka, pritisnite ENTER, da se vrnete na začetek");
            String pocetak = ulaz.nextLine();
            RegularniMeni();
        }
    }

    private void PregledOdmora(String tipKorisnika) throws ParseException {
        if(tipKorisnika.equals("obicni")) {


            Scanner ulaz = new Scanner(System.in);
            System.out.println("Pritisnite (v) za ogled počiznic po časovnem okviru");
            System.out.println("Pritisnite (d) za ogled počitnic po državah");
            System.out.println("Pritisnite (c) za ogled počitnic po ceni");
            System.out.println("Pritisnite (t) za ogled počitnic po tip");
            System.out.println("Pritisnite (e) za izhod");
            System.out.println("------------------------------------------------------------------------------");
            String otkrivenIzbor = ulaz.nextLine();
            if (otkrivenIzbor.equals("v") || otkrivenIzbor.equals("d") || otkrivenIzbor.equals("c") || otkrivenIzbor.equals("t") || otkrivenIzbor.equals("e")) {
                switch (otkrivenIzbor) {
                    case "v":
                        Korisnik korisnik = (Korisnik) prijavljeniKorisnik;
                        Date datumPolazak = pitajZaDatum();
                        Date datumDolazka = pitajZaDatum();
                        Map<Odmor, Termin> rez = korisnik.PotraziOdmorePoVremenu(odmori, datumPolazak, datumDolazka);
                        Ispis(rez, "plista");
                        break;
                    case "d":
                        Korisnik korisnikDrzava = (Korisnik) prijavljeniKorisnik;
                        String drzava = pitajUnos("Vnesite državo");
                        ArrayList<Object> rezultat = korisnikDrzava.PotraziOdmorePoDrzavi(odmori, drzava);
                        Ispis(rezultat, "drugo");
                        break;
                    case "c":
                        Korisnik korisnikCena = (Korisnik) prijavljeniKorisnik;
                        String cena = pitajUnos("Vnesite ceno OD");
                        String cenaDo = pitajUnos("Vnesite ceno DO");
                        int cenaIntOd = Integer.parseInt(cena);
                        int cenaIntDo = Integer.parseInt(cenaDo);
                        ArrayList<Object> rezultatCena = korisnikCena.PotraziOdmorePoCeni(odmori, cenaIntOd, cenaIntDo);
                        Ispis(rezultatCena, "drugo");
                        break;
                    case "t":
                        Korisnik korisnikTip = (Korisnik) prijavljeniKorisnik;
                        String tip = pitajUnos("Vnesite tip.\nOpcije so:\nKrstarenje (prevod: Križarjenje)\nSkijanje (prevod: Smučanje)\nPutovanje (prevod: Potovanje)\nKampovanje (prevod: Kampiranje)");
                        ArrayList<Object> rezTip = korisnikTip.PotraziOdmorePoTipu(odmori, tip);
                        Ispis(rezTip, "drugo");
                        break;
                    case "e":
                        System.exit(0);
                        break;
                }

            } else {
                System.out.println("Niste vnesli pravilnega znaka, pritisnite ENTER, da se vrnete na začetek");
                String pocetak = ulaz.nextLine();
                PregledOdmora(tipKorisnika);
            }
        } else {
            Scanner ulaz = new Scanner(System.in);
            System.out.println("Pritisnite (v) za ogled počiznicc po časovnem okviru");
            System.out.println("Pritisnite (d) za ogled počitnic po državah");
            System.out.println("Pritisnite (c) za ogled počitnic po ceni");
            System.out.println("Pritisnite (t) za ogled počitnic po tip");
            System.out.println("Pritisnite (i) za iskanje po identifikacijski številki");
            System.out.println("Pritisnite (e) za izhod");
            System.out.println("------------------------------------------------------------------------------");
            String otkrivenIzbor = ulaz.nextLine();
            if (otkrivenIzbor.equals("v") || otkrivenIzbor.equals("d") || otkrivenIzbor.equals("c") || otkrivenIzbor.equals("t") || otkrivenIzbor.equals("i") || otkrivenIzbor.equals("e")) {
                switch (otkrivenIzbor) {
                    case "v":
                        Administrator korisnik = (Administrator) prijavljeniKorisnik;
                        Date datumPolazak = pitajZaDatum();
                        Date datumDolazka = pitajZaDatum();
                        Map<Odmor, Termin> rez = korisnik.PotraziOdmorePoVremenu(odmori, datumPolazak, datumDolazka);
                        Ispis(rez, "plista");
                        break;
                    case "d":
                        Administrator korisnikDrzava = (Administrator) prijavljeniKorisnik;
                        String drzava = pitajUnos("Vnesite državo");
                        ArrayList<Object> rezultat = korisnikDrzava.PotraziOdmorePoDrzavi(odmori, drzava);
                        Ispis(rezultat, "drugo");
                        break;
                    case "c":
                        Administrator korisnikCena = (Administrator) prijavljeniKorisnik;
                        String cena = pitajUnos("Vnesite cenu OD");
                        String cenaDo = pitajUnos("Vnesite cenu DO");
                        int cenaIntOd = Integer.parseInt(cena);
                        int cenaIntDo = Integer.parseInt(cenaDo);
                        ArrayList<Object> rezultatCena = korisnikCena.PotraziOdmorePoCeni(odmori, cenaIntOd, cenaIntDo);
                        Ispis(rezultatCena, "drugo");
                        break;
                    case "t":
                        Administrator korisnikTip = (Administrator) prijavljeniKorisnik;
                        String tip = pitajUnos("Vnesite tip.\\nOpcije so:\\nKrstarenje (prevod: Križarjenje)\\nSkijanje (prevod: Smučanje)\\nPutovanje (prevod: Potovanje)\\nKampovanje (prevod: Kampiranje)\"");
                        ArrayList<Object> rezTip = korisnikTip.PotraziOdmorePoTipu(odmori, tip);
                        Ispis(rezTip, "drugo");
                        break;
                    case "i":
                        Administrator administratorUUID = (Administrator) prijavljeniKorisnik;
                        String UUID = pitajUnos("Vnesite identifikacijsko številko: ");
                        Object odmor = administratorUUID.potraziPoIdentifikacijskomBroju(odmori, UUID);
                        if(odmor!=null){
                            System.out.println("Počitnice so uspešno najdene: "+odmor.toString());
                            PregledOdmora(tipKorisnika);
                        } else {
                            System.out.println("Počitnic ni bilo mogoče najti! Prosim poskusite ponovno.");
                            PregledOdmora(tipKorisnika);
                        }
                        break;
                    case "e":
                        System.exit(0);
                        break;
                }

            } else {
                System.out.println("Niste vnesli pravilnega znaka, pritisnite ENTER, da se vrnete na začetek");
                String pocetak = ulaz.nextLine();
                PregledOdmora(tipKorisnika);
            }
        }
    }

    private void novaRezervacija(Object korisnik, ArrayList<Object> odmori) {
        Korisnik rezervacija = (Korisnik) prijavljeniKorisnik;
        if(rezervacija.kreiranjeRezervacije(korisnik, odmori)){
            return;
        } else {
            novaRezervacija(korisnik, odmori);
        }
    }

    private Date pitajZaDatum() {
        Scanner skener = new Scanner(System.in);
        System.out.println("Vnesite datum (dd/MM/yyyy)");
        String datum = skener.next();
        SimpleDateFormat formatiranje= new SimpleDateFormat("dd/MM/yyyy");

        Termin termin = new Termin();
        try {
            Date datumOBJ = formatiranje.parse(datum);
            return datumOBJ;
        }
        catch (ParseException error){
            return pitajZaDatum();
        }
    }

    private String pitajUnos (String poruka) {
        Scanner skener = new Scanner(System.in);


        System.out.println(poruka);
        String odgovor = skener.next();
        return odgovor;
    }

    private void Ispis(Object objekat, String tip){
        if(tip.equals("plista")) {
            Map<Odmor, Termin> povezanaLista = (Map<Odmor, Termin>) objekat;
            for (Map.Entry<Odmor, Termin> unos : povezanaLista.entrySet()) {
                System.out.println("Počitnica = " + unos.getKey() +
                        ", Termin = " + unos.getValue());
            }
        } else {
            ArrayList<Odmor> odmori = (ArrayList<Odmor>) objekat;
            for(int i = 0;i<odmori.size();i++){
                Odmor odmor = odmori.get(i);
                System.out.println(i+1+". Dežela: "+odmor.getDrzava()+" Cena: "+odmor.getCena()+" Kapaciteta: "+odmor.getKapacitet()+" Termini: "+odmor.getTermini());
            }
        }
    }
}
