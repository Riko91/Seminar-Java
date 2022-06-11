import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UporabniskiVmesnik {
    private static ArrayList<Object> odmori = new ArrayList<>();
    private static ArrayList<Object> Korisnici = new ArrayList<Object>();
    private static Object prijavljeniKorisnik;
    public static void main(String[] args) throws ParseException, IOException {
        Korisnici = ProcitajListuKorisnika();
        odmori = Serializacija.ProcitajSveOdmoreIzDatoteke();
        GlavnaNit(Korisnici);

    }

    public static void GlavnaNit(ArrayList<Object> korisnici) throws ParseException, IOException {
        System.out.println("\n*** Dobrodošli v turistični agenciji Hawaii ***\n");
        Scanner ulaz = new Scanner(System.in);
        System.out.println("--------------	Za vstop v program se morate prijaviti!	--------------");
        System.out.println("Pritisnite (p) za prijavu");
        System.out.println("Pritisnite (r) za registraciju");
        System.out.println("Pritisnite (e) za izhod");
        System.out.println("------------------------------------------------------------------------------");
        String otkrivenIzbor = ulaz.nextLine();
        if (otkrivenIzbor.equals("p")  || otkrivenIzbor.equals("r") || otkrivenIzbor.equals("e")) {
            switch (otkrivenIzbor){
                case "p":
                    Prijava(Korisnici);
                    break;
                case "r":
                    Registracija();
                    break;
                case "e":
                    System.exit(0);
                    break;
            }

        } else {
            System.out.println("Niste vnesli pravilnega znaka, pritisnite ENTER, da se vrnete na začetek");
            String pocetak = ulaz.nextLine();
            GlavnaNit(Korisnici);
        }

    }
    private static ArrayList<Object> ProcitajListuKorisnika() {
        ArrayList<Object> Korisnici = new ArrayList<Object>();
        try {
            File datoteka = new File("Korisnici.txt");
            Scanner ulaz = new Scanner(datoteka);
            while (ulaz.hasNextLine()) {
                String linija = ulaz.nextLine();
                String[] podaci = linija.split(",");
                if(podaci.length == 6) {
                    Administrator admin = new Administrator();
                    admin.setIme(podaci[0]);
                    admin.setPrezime(podaci[1]);
                    admin.setKorisnickoIme(podaci[2]);
                    admin.setSifra(podaci[3]);
                    Date datum = new SimpleDateFormat("dd/MM/yyyy").parse(podaci[4]);
                    admin.setDatumPocetkaAdministracije(datum);
                    admin.setEmail(podaci[5]);
                    Korisnici.add(admin);
                } else {
                    Korisnik korisnik = new Korisnik();
                    korisnik.setIme(podaci[0]);
                    korisnik.setPrezime(podaci[1]);
                    korisnik.setKorisnickoIme(podaci[2]);
                    korisnik.setSifra(podaci[3]);
                    Korisnici.add(korisnik);
                }
            }
        } catch (FileNotFoundException e) {
            PokaziGreskuIzadji("Prišlo je do napake, datoteka ne obstaja.");
        } catch (ParseException e) {
            PokaziGreskuIzadji("Prišlo je do napake.");
        }

        return Korisnici;
    }



    private static void PokaziGreskuIzadji(String poruka) {
        Scanner ulaz = new Scanner(System.in);
        System.out.println(poruka+" Pritisnite Enter za izhod.");
        String pocetak = ulaz.nextLine();
        System.exit(0);
    }
    private static void Registracija() {
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Prosimo, vnesite svoje ime!\n");
        String ime = ulaz.next();
        System.out.println("Prosimo, vnesite svoj priimek!\n");
        String prezime = ulaz.next();
        System.out.println("Prosimo, vnesite uporabniško ime!\n");
        String korisnickoIme = ulaz.next();
        System.out.println("Prosimo, vnesite geslo!\n");
        String sifra = ulaz.next();
        Korisnik korisnik = new Korisnik(ime, prezime, korisnickoIme, sifra);
        if(korisnik.registracijaKorisnika(Korisnici, korisnik)){
            System.out.println("Registriran uporabnik je "+korisnickoIme);
        } else {
            System.out.println("Prišlo je do napake!");
        }
    }

    private static void Prijava(ArrayList<Object> korisnici) throws ParseException, IOException {
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Prosimo, vnesite uporabniško ime!\n");
        String korisnickoIme = ulaz.next();
        System.out.println("Prosimo, vnesite geslo!\n");
        String sifra = ulaz.next();
        Korisnik korisnik = new Korisnik(korisnickoIme, sifra);
        if(korisnik.prijava(korisnici)){
            System.out.println("Uspešna prijava! Registriran uporabnik je "+korisnickoIme);
            prijavljeniKorisnik = Servis.nadjiKorisnika(korisnici, korisnickoIme);
            if (prijavljeniKorisnik.getClass().getSimpleName().equals("Korisnik")){
                //RegularniMeni();
                UporabnikVmesnik um = new UporabnikVmesnik(prijavljeniKorisnik, odmori);
            } else {
                //AdministratorskiMeni();
                AdminVmesnik av = new AdminVmesnik(prijavljeniKorisnik, odmori, Korisnici);
            }
        } else {
            System.out.println("Vneseni napačni podatki!");
        }


    }
}