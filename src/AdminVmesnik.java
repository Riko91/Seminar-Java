import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class AdminVmesnik {
    public Object prijavljeniKorisnik;
    public ArrayList<Object> odmori;
    public ArrayList<Object> Korisnici;

    public AdminVmesnik(Object prijavljeniKorisnik, ArrayList<Object> odmori, ArrayList<Object> Korisnici) throws ParseException, IOException {
        this.prijavljeniKorisnik = prijavljeniKorisnik;
        this.odmori = odmori;
        this.Korisnici = Korisnici;
        AdministratorskiMeni();
    }
    private void AdministratorskiMeni() throws ParseException, IOException {
        System.out.println("\n*** Dobrodošli v turistični agenciji Hawaii! ********************************* Administrator\n");
        Scanner ulaz = new Scanner(System.in);
        System.out.println("----------------------------------	Odmor	----------------------------------");
        System.out.println("Pritisnite (o) za ogled počitnica");
        System.out.println("Pritisnite (u) za vnos počitnic");
        System.out.println("Pritisnite (z) za brisanje počitnic");
        System.out.println("Pritisnite (i) za spreminanje");
        System.out.println("Pritisnite (r) za rezervaciju");
        System.out.println("Pritisnite (p) za ogled vseh rezervacij");
        System.out.println("----------------------------------	Admin	----------------------------------");
        System.out.println("Pritisnite (a) za dodat novega administratorja");
        System.out.println("Pritisnite (b) za brisanje uporabnika ali administratora");
        System.out.println("Pritisnite (e) za izhod");
        System.out.println("------------------------------------------------------------------------------");
        String otkrivenIzbor = ulaz.nextLine();
        if (otkrivenIzbor.equals("o")  || otkrivenIzbor.equals("u") || otkrivenIzbor.equals("z") || otkrivenIzbor.equals("i") || otkrivenIzbor.equals("r") || otkrivenIzbor.equals("p") || otkrivenIzbor.equals("a") || otkrivenIzbor.equals("b") || otkrivenIzbor.equals("e")) {
            switch (otkrivenIzbor){
                case "o":
                    PregledOdmora("admin");
                    AdministratorskiMeni();
                    break;
                case "u":
                    Administrator korisnik = (Administrator) prijavljeniKorisnik;
                    korisnik.unosOdmora(prijavljeniKorisnik);
                    AdministratorskiMeni();
                    break;
                case "z":
                    ArrayList<Object> listaBrisanje = Serializacija.ProcitajSveOdmoreIzDatoteke();
                    Administrator brisanje = (Administrator) prijavljeniKorisnik;
                    ArrayList<Object> lista = brisanje.obrisiOdmor(listaBrisanje);
                    Serializacija.ZapisiUDatoteku(lista);
                    AdministratorskiMeni();
                    break;
                case "i":
                    ArrayList<Object> listaAzuriranje = Serializacija.ProcitajSveOdmoreIzDatoteke();
                    Administrator azuriranje = (Administrator) prijavljeniKorisnik;
                    ArrayList<Object> listaA = azuriranje.izmeniOdmor(listaAzuriranje);
                    Serializacija.ZapisiUDatoteku(listaA);
                    AdministratorskiMeni();
                    break;
                case "r":
                    novaRezervacija(prijavljeniKorisnik, odmori);
                    AdministratorskiMeni();
                    break;
                case "p":
                    Administrator pregledRezervacije = (Administrator) prijavljeniKorisnik;
                    pregledRezervacije.pregledRezervacija(odmori);
                    AdministratorskiMeni();
                    break;
                case "a":
                    Administrator registracija = (Administrator) prijavljeniKorisnik;
                    registracija.kreirajAdministratora(Korisnici);
                    AdministratorskiMeni();
                    break;
                case "b":
                    Administrator administrator = (Administrator) prijavljeniKorisnik;
                    ArrayList<Object> korisnici = administrator.brisanjeKorisnika(Korisnici);
                    zapisiPonovoKorisnika(korisnici);
                    AdministratorskiMeni();
                    break;
                case "e":
                    System.exit(0);
                    break;
            }

        } else {
            System.out.println("Niste vnesli pravilnega znaka, pritisnite ENTER, da se vrnete na začetek");
            String pocetak = ulaz.nextLine();
            AdministratorskiMeni();
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
                        String cena = pitajUnos("Vnesite cenu OD");
                        String cenaDo = pitajUnos("Vnesite cenu DO");
                        int cenaIntOd = Integer.parseInt(cena);
                        int cenaIntDo = Integer.parseInt(cenaDo);
                        ArrayList<Object> rezultatCena = korisnikCena.PotraziOdmorePoCeni(odmori, cenaIntOd, cenaIntDo);
                        Ispis(rezultatCena, "drugo");
                        break;
                    case "t":
                        Korisnik korisnikTip = (Korisnik) prijavljeniKorisnik;
                        String tip = pitajUnos("Vnesite tip.\\nOpcije so:\\nKrstarenje (prevod: Križarjenje)\\nSkijanje (prevod: Smučanje)\\nPutovanje (prevod: Potovanje)\\nKampovanje (prevod: Kampiranje)\"");
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

    private void zapisiPonovoKorisnika(ArrayList<Object> korisnici) throws IOException {
        String linijaZaUpis;
        PrintWriter writer = new PrintWriter("Korisnici.txt");
        writer.print("");
        writer.close();
        for(Object jedanKorisnik: korisnici){
            if(jedanKorisnik.getClass().getSimpleName().equals("Administrator")){
                Administrator administrator = (Administrator) jedanKorisnik;
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String datum = simpleDateFormat.format(administrator.getDatumPocetkaAdministracije());
                linijaZaUpis = administrator.getIme() + "," + administrator.getPrezime() + "," + administrator.getKorisnickoIme() + "," + administrator.getSifra() + "," + datum + "," + administrator.getEmail();
            } else {
                Korisnik korisnik = (Korisnik)  jedanKorisnik;
                linijaZaUpis = korisnik.getIme() + "," + korisnik.getPrezime() + "," + korisnik.getKorisnickoIme() + "," + korisnik.getSifra();
            }
            FileWriter fr = new FileWriter("Korisnici.txt", true);
            fr.write(linijaZaUpis + "\n");
            fr.close();
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

    private String pitajUnos (String poruka) {
        Scanner skener = new Scanner(System.in);


        System.out.println(poruka);
        String odgovor = skener.next();
        return odgovor;
    }
}
