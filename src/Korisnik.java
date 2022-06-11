import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Korisnik implements KorisnickaLogika, Serializable
{
	private static final long serialVersionUID = -7932850642304791999L;
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String sifra;
	public Korisnik(){
		super();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	@Override
	public String toString() {
		return "Korisnik{" +
				"ime='" + ime + '\'' +
				", prezime='" + prezime + '\'' +
				", korisnickoIme='" + korisnickoIme + '\'' +
				", sifra='" + sifra + '\'' +
				'}';
	}

	public Korisnik(String ime, String prezime, String korisnickoIme, String sifra) {
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
	}

	public Korisnik(String korisnickoIme, String sifra) {
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
	}

	@Override
	public String ispisRezervacija() {
		return null;
	}


	@Override
	public boolean prijava(ArrayList<Object> korisnici) {

		if(ProveriPodatke(korisnici,this.getKorisnickoIme(),this.getSifra())) {
			return true;
		} else {

			return false;
		}

	}

	@Override
	public boolean registracijaKorisnika(ArrayList<Object>korisnici, Korisnik korisnik) {

		boolean registrovanKorisnik = false;
		Object korisnickoImePostoji = Servis.nadjiKorisnika(korisnici, korisnik.getKorisnickoIme());
		String test = korisnickoImePostoji.getClass().getSimpleName();
		if(korisnickoImePostoji.getClass().getSimpleName().equals("Boolean")){
			zapisiKorisnikaUDatoteku(korisnik);
			registrovanKorisnik = true;
		} else {
			registrovanKorisnik = false;
		}


		return registrovanKorisnik;
	}

	private void zapisiKorisnikaUDatoteku(Korisnik korisnik) {
		try (FileWriter f = new FileWriter("Korisnici.txt", true);
			 BufferedWriter b = new BufferedWriter(f);
			 PrintWriter p = new PrintWriter(b);) {

			String registracija = korisnik.getIme() + "," + korisnik.getPrezime() + "," + korisnik.getKorisnickoIme() + "," + korisnik.getSifra();
			p.println(registracija);

		} catch (IOException i) {
			i.printStackTrace();
		}


	}

	@Override
	public <T> String trazi(T tip, String kriterijum) {
		return null;
	}

	@Override
	public ArrayList<Object> obrisiOdmor(ArrayList<Object> odmori) {
		int Broj = -1;
		Scanner skener = new Scanner(System.in);
		System.out.println("Vnesite število počitnice ki jih želite izbrisati: ");
		for(int i = 0;i<odmori.size();i++){
			Odmor odmor = (Odmor) odmori.get(i);
			System.out.println(i+1+". Dežela: "+odmor.getDrzava()+" Cena: "+odmor.getCena()+" Kapaciteta: "+odmor.getKapacitet()+" Termini: "+odmor.getTermini());
		}
		String odgovor = skener.next();
		try {
			Broj = Integer.parseInt(odgovor);
		} catch(NumberFormatException greska){
			return obrisiOdmor(odmori);
		}
		ArrayList<Object> odmoriLista = odmori;
		if(Broj!=-1) {
			odmori.remove(Broj -1);
			return odmori;
		} else {
			return obrisiOdmor(odmori);
		}
	}

	@Override
	public ArrayList<Object> izmeniOdmor(ArrayList<Object> odmori) {
		int Broj = -1;
		Scanner skener = new Scanner(System.in);
		System.out.println("Vnesite število počitnice ki jih želite spremeniti: ");
		for(int i = 0;i<odmori.size();i++){
			Odmor odmor = (Odmor) odmori.get(i);
			System.out.println(i+1+". Dežela: "+odmor.getDrzava()+" Cena: "+odmor.getCena()+" Kapaciteta: "+odmor.getKapacitet()+" Termini: "+odmor.getTermini());
		}
		String odgovor = skener.next();
		try {
			Broj = Integer.parseInt(odgovor);
		} catch(NumberFormatException greska){

			return obrisiOdmor(odmori);
		}
		String drzava = pitajKorisnika("Vnesite državo: ","text");
		String cena = pitajKorisnika("Vnesite ceno: ","number");
		String kapacitet = pitajKorisnika("Vnesite kapaciteto ljudi: ","number");
		Odmor odmor = (Odmor) odmori.get(Broj -1);
		odmor.setDrzava(drzava);
		odmor.setCena(cena);
		odmor.setKapacitet(Integer.parseInt(kapacitet));
		odmori.set(Broj -1, odmor);

		return odmori;
	}

	@Override
	public boolean kreiranjeRezervacije(Object korisnik, ArrayList<Object> odmori) {
		int Broj = -1;
		Scanner skener = new Scanner(System.in);
		System.out.println("Vnesite število počitnic, da ustvarite rezervacijo: ");
		for(int i = 0;i<odmori.size();i++){
			Odmor odmor = (Odmor) odmori.get(i);
			System.out.println(i+1+". Dežela: "+odmor.getDrzava()+" Cena: "+odmor.getCena()+" Kapaciteta: "+odmor.getKapacitet()+" Rezervacija: "+odmor.getRezervacije()+" Termini: "+odmor.getTermini());
		}
		String odgovor = skener.next();
		try {
			Broj = Integer.parseInt(odgovor);
		} catch(NumberFormatException greska){
			return kreiranjeRezervacije(korisnik, odmori);
		}
		int brojOdraslih = Integer.parseInt(pitajKorisnika("Vnesite število odraslih: ", "number"));
		int brojDece = Integer.parseInt(pitajKorisnika("Vnesite število otrok: ", "number"));
		Rezervacija novaRezervacija = new Rezervacija();
		novaRezervacija.setBrojOdraslihOsoba(brojOdraslih);
		novaRezervacija.setBrojDece(brojDece);
		novaRezervacija.setKreirao((Korisnik) korisnik);
		Odmor odmor = (Odmor) odmori.get(Broj -1);
		int maxBrojOsoba = odmor.getKapacitet();
		int vecRezervisano = 0;
		for(Rezervacija trenutnaRezervacija: odmor.getRezervacije()) {
			vecRezervisano += trenutnaRezervacija.getBrojOdraslihOsoba()+ trenutnaRezervacija.getBrojDece();
		}
		vecRezervisano += novaRezervacija.getBrojOdraslihOsoba()+novaRezervacija.getBrojDece();
		if (vecRezervisano>=maxBrojOsoba){
			return false;
		} else {
			ArrayList<Rezervacija> rNova = odmor.getRezervacije();
			rNova.add(novaRezervacija);
			odmor.setRezervacije(rNova);
			odmori.set(Broj -1, odmor);
			Serializacija.ZapisiUDatoteku(odmori);
			return true;
		}

	}

	@Override
	public void pregledRezervacija(ArrayList<Object> odmori) {
		int Broj = -1;
		Scanner skener = new Scanner(System.in);
		System.out.println("Vnesite število počitnice za katero želite pregledati rezervacijo: ");
		for(int i = 0;i<odmori.size();i++){
			Odmor odmor = (Odmor) odmori.get(i);
			System.out.println(i+1+". Dežela: "+odmor.getDrzava()+" Cena: "+odmor.getCena()+" Kapaciteta: "+odmor.getKapacitet()+" Termini: "+odmor.getTermini());
		}
		String odgovor = skener.next();
		try {
			Broj = Integer.parseInt(odgovor);
		} catch(NumberFormatException greska){

			pregledRezervacija(odmori);
		}
		Odmor odmor = (Odmor) odmori.get(Broj -1);
		ArrayList<Rezervacija> rezervacije = odmor.getRezervacije();
		for(Rezervacija rezervacija: rezervacije){
			System.out.println(rezervacija);
		}
	}

	public String pitajKorisnika(String pitanje, String tip){
		Scanner skener = new Scanner(System.in);
		System.out.println(pitanje);
		String odgovor = skener.next();
		if(tip.equals("text")){
			return odgovor;
		} else {
			try {
				int Broj = Integer.parseInt(odgovor);
				return odgovor;
			} catch (NumberFormatException greska) {
				return pitajKorisnika(pitanje, tip);
			}
		}
	}

	@Override
	public boolean unosOdmora(Object korisnik) throws ParseException {
		UnosOdmor(false, korisnik);
		return true;
	}

	@Override
	public Map<Odmor, Termin> PotraziOdmorePoVremenu(ArrayList<Object> odmori, Date odDatum, Date doDatum) {
		Map<Odmor, Termin> odmoriSaTerminima = new HashMap<>();

		ArrayList<Odmor> sortiraniOdmori = new ArrayList<>();
			for(Object odmor: odmori) {
				Odmor cOdmor = (Odmor) odmor;
				for (Termin termin: cOdmor.getTermini()) {
					//var termini = cOdmor.getTermini();
					if(odDatum.before(termin.getDatumOdlaska()) && doDatum.after(termin.getDatumDolaska())) {
						odmoriSaTerminima.put(cOdmor, termin);
					}
				}

			}
		return odmoriSaTerminima;
	}

	@Override
	public ArrayList<Object> PotraziOdmorePoDrzavi(ArrayList<Object> odmori, String drzava) {
		ArrayList<Object> sortiraniOdmori = new ArrayList<>();

		for(Object odmor: odmori){
			Odmor cOdmor = (Odmor) odmor;
			if(cOdmor.getDrzava().equals(drzava)){
				sortiraniOdmori.add(odmor);
			}
		}
			return sortiraniOdmori;

	}

	@Override
	public ArrayList<Object> PotraziOdmorePoCeni(ArrayList<Object> odmori, int cenaOD, int cenaDO) {
		ArrayList<Object> sortiraniOdmori = new ArrayList<>();
		int pravaCena;
		for(Object odmor: odmori) {
			Odmor cOdmor = (Odmor) odmor;
			try{
				pravaCena = Integer.parseInt(cOdmor.getCena());
			} catch(NumberFormatException greska)  {
				continue;
			}
			if(pravaCena > cenaOD && pravaCena < cenaDO) {
				sortiraniOdmori.add(odmor);
			}
		}

		return sortiraniOdmori;
	}

	@Override
	public ArrayList<Object> PotraziOdmorePoTipu(ArrayList<Object> odmori, String tip) {
		ArrayList<Object> sortiraniOdmori = new ArrayList<>();
		for(Object odmor: odmori) {
			Odmor cOdmor = (Odmor) odmor;
			if(odmor.getClass().getSimpleName().equals(tip)) {
				sortiraniOdmori.add(odmor);
			}
		}
		return sortiraniOdmori;
	}

	private static void UnosOdmor(Boolean greška, Object korisnik) throws ParseException {
		if(!greška) {
			ArrayList<Object> odmori = Serializacija.ProcitajSveOdmoreIzDatoteke();
			String id = UUID.randomUUID().toString();
			String tipOdmora = OdgovorKorisnika("Zdravo.\n1. Putovanje.\n" +
					"2. Skijanje\n" +
					"3. Krstarenje\n" +
					"4. Kampovanje", "tip");

			String kapacitet = OdgovorKorisnika("Vnesite kapaciteto objekta: ", "number");
			String drzava = OdgovorKorisnika("Vnesite državo: ", "text");
			String unesiteCenu = OdgovorKorisnika("Vnesite cenu: ", "number");
			ArrayList<Rezervacija> rezervacije = RekurzivniUnosRezervacija(korisnik);
			ArrayList<Termin> termini = RekurzivniUnosTermina(korisnik);
			switch (tipOdmora) {
				case "1":
					Putovanje putovanje = new Putovanje();

					putovanje.setOdmorGUID(id);
					putovanje.setKapacitet(Integer.parseInt(kapacitet));
					putovanje.setDrzava(drzava);
					putovanje.setCena(unesiteCenu);
					putovanje.setRezervacije(rezervacije);
					putovanje.setTermini(termini);
					odmori.add(putovanje);
					Serializacija.ZapisiUDatoteku(odmori);
					for (Object odmorOBJ : odmori) {
						System.out.println(odmorOBJ.toString() + "Ispisan objekat!");
					}
					break;
				case "2":
					Skijanje skijanje = new Skijanje();

					skijanje.setOdmorGUID(id);
					skijanje.setKapacitet(Integer.parseInt(kapacitet));
					skijanje.setDrzava(drzava);
					skijanje.setCena(unesiteCenu);
					skijanje.setRezervacije(rezervacije);
					skijanje.setTermini(termini);
					odmori.add(skijanje);
					Serializacija.ZapisiUDatoteku(odmori);
					for (Object odmorOBJ : odmori) {
						System.out.println(odmorOBJ.toString() + "Ispisan objekat!");
					}
					break;
				case "3":
					Krstarenje krstarenje = new Krstarenje();


					krstarenje.setOdmorGUID(id);
					krstarenje.setKapacitet(Integer.parseInt(kapacitet));
					krstarenje.setDrzava(drzava);
					krstarenje.setCena(unesiteCenu);
					krstarenje.setRezervacije(rezervacije);
					krstarenje.setTermini(termini);
					odmori.add(krstarenje);
					Serializacija.ZapisiUDatoteku(odmori);
					for (Object odmorOBJ : odmori) {
						System.out.println(odmorOBJ.toString() + "Ispisan objekat!");
					}
					break;
				case "4":
					Kampovanje kampovanje = new Kampovanje();


					kampovanje.setOdmorGUID(id);
					kampovanje.setKapacitet(Integer.parseInt(kapacitet));
					kampovanje.setDrzava(drzava);
					kampovanje.setCena(unesiteCenu);
					kampovanje.setRezervacije(rezervacije);
					kampovanje.setTermini(termini);
					odmori.add(kampovanje);
					Serializacija.ZapisiUDatoteku(odmori);
					for (Object odmorOBJ : odmori) {
						System.out.println(odmorOBJ.toString() + "Ispisan objekat!");
					}
					break;
		  	}
			}

	}

	private static String OdgovorKorisnika(String pitanje, String tip){
		Scanner skener = new Scanner(System.in);
		System.out.println(pitanje);
		String odgovor = skener.next();

		if(tip.equals("number")){
				try {
					int broj = Integer.parseInt(odgovor);
					return odgovor;

				} catch (NumberFormatException greska) {
					return OdgovorKorisnika(pitanje, tip);
				}



				} else if(tip.equals("tip")) {

			try {
				int broj = Integer.parseInt(odgovor);
				if(broj!=1&&broj!=2&&broj!=3&&broj!=4){
					return OdgovorKorisnika(pitanje, tip);
				} else {
					return odgovor;
				}
			} catch (NumberFormatException greska) {
				return OdgovorKorisnika(pitanje, tip);

			}
		} else {
				return odgovor;

		}
	}

	private static ArrayList<Termin>RekurzivniUnosTermina(Object korisnik) throws ParseException {
		Scanner skener = new Scanner(System.in);
		ArrayList<Termin> termini = new ArrayList<>();
		while (true){
			SimpleDateFormat formatiranje=new SimpleDateFormat("dd/MM/yyyy");
			String odlazak = OdgovorKorisnika("Vnesite datum odhoda(dd/MM/yyyy):", "text");
			String dolazak = OdgovorKorisnika("Vnesite datum prihoda(dd/MM/yyyy):", "text");
			Termin termin = new Termin();
			try {
				termin.setDatumOdlaska(formatiranje.parse(odlazak));
				termin.setDatumDolaska(formatiranje.parse(dolazak));
			}
			catch (ParseException error){
				continue;
			}
			termini.add(termin);
			System.out.println("Predmet je bil uspešno vnešen, ali želite nadaljevati (Da / Ne)?");
			String odgovor = skener.next();
			if(odgovor.equals("Ne")) {
				break;
			}
		}
		return termini;
	}
	private static ArrayList<Rezervacija>RekurzivniUnosRezervacija(Object korisnik) throws ParseException {
		Scanner skener = new Scanner(System.in);
		ArrayList<Rezervacija> rezervacije = new ArrayList<>();
		while (true){
			String odrasli = OdgovorKorisnika("Vnesite število odraslih:", "number");
			String dece = OdgovorKorisnika("Vnesite število otrok:", "number");
			Rezervacija rezervacija = new Rezervacija();
			rezervacija.setBrojOdraslihOsoba(Integer.parseInt(odrasli.toString()));
			rezervacija.setBrojDece(Integer.parseInt(dece.toString()));
			rezervacije.add(rezervacija);
			if (korisnik.getClass().getSimpleName().equals("Korisnik")){
				rezervacija.setKreirao((Korisnik) korisnik);
			} else {
				rezervacija.setKreirao((Korisnik) korisnik);
			}
			System.out.println("Predmet je bil uspešno vnešen, ali želite nadaljevati (Da / Ne)?");
			String odgovor = skener.next();
			if(odgovor.equals("Ne")) {
				break;
			}
		}
		return rezervacije;
	}

	private boolean ProveriPodatke(ArrayList<Object> korisnici, String korisnickoIme, String sifra) {
		boolean nadjenKorisnik = false;
		for(Object korisnik:korisnici) {
			String ime = korisnik.getClass().getSimpleName();
			if(korisnik.getClass().getSimpleName().equals("Korisnik")){
				Korisnik obicniKorisnik = (Korisnik) korisnik;
				if(obicniKorisnik.getKorisnickoIme().equals(korisnickoIme)&&obicniKorisnik.getSifra().equals(sifra)){
					nadjenKorisnik = true;
					break;
				}
			} else {
				Administrator administrator = (Administrator) korisnik;
				if(administrator.getKorisnickoIme().equals(korisnickoIme)&&administrator.getSifra().equals(sifra)){
					nadjenKorisnik = true;
					break;
				}
			}
		}
		return nadjenKorisnik;
	}
}

