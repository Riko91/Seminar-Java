import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Administrator extends Korisnik implements AdministracijskaLogika, Serializable

{
	private static final long serialVersionUID = -3908379591905640641L;
	private Date datumPocetkaAdministracije;
	private String email;
	public Administrator(){
		super();
	}

	public Date getDatumPocetkaAdministracije() {
		return datumPocetkaAdministracije;
	}

	public void setDatumPocetkaAdministracije(Date datumPocetkaAdministracije) {
		this.datumPocetkaAdministracije = datumPocetkaAdministracije;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Administrator{" +
				"datumPocetkaAdministracije=" + datumPocetkaAdministracije +
				", email='" + email + '\'' +
				'}';
	}

	public Administrator(Date datumPocetkaAdministracije, String email) {
		this.datumPocetkaAdministracije = datumPocetkaAdministracije;
		this.email = email;
	}

	public Administrator(String ime, String prezime, String korisnickoIme, String sifra, Date datumPocetkaAdministracije, String email) {
		super(ime, prezime, korisnickoIme, sifra);
		this.datumPocetkaAdministracije = datumPocetkaAdministracije;
		this.email = email;
	}

	public Administrator(String korisnickoIme, String sifra, Date datumPocetkaAdministracije, String email) {
		super(korisnickoIme, sifra);
		this.datumPocetkaAdministracije = datumPocetkaAdministracije;
		this.email = email;
	}

	@Override
	public boolean azuriranjeOdmora(Odmor odmor) {
		return false;
	}

	@Override
	public boolean brisanjeOdmora(Odmor odmor) {
		return false;
	}

	@Override
	public <T> boolean generalnoBrisanjeKorisnika(T korisnik) {
		return false;
	}

	@Override
	public boolean kreiranjeOdmora(Odmor odmor) {
		return false;
	}

	@Override
	public boolean kreiranjeRezervacije(Rezervacija rezervacija) {
		return false;
	}


	@Override
	public boolean registracijaAdministratora(Administrator administrator) {
		return false;
	}

	@Override
	public boolean prijava(ArrayList<Object> korisnici) {
		return false;
	}

	@Override
	public Object potraziPoIdentifikacijskomBroju(ArrayList<Object> odmori, String UUID) {
		for(Object odmor: odmori){
			Odmor jedanOdmor = (Odmor) odmor;
			if(jedanOdmor.getOdmorGUID().equals(UUID)){
				return jedanOdmor;
			}
		}
		return  null;
	}

	@Override
	public boolean kreirajAdministratora(ArrayList<Object> korisnici) throws ParseException {
		Scanner ulaz = new Scanner(System.in);
		System.out.println("Molimo vas, unesite vaše ime!\n");
		String ime = ulaz.next();
		System.out.println("Molimo vas, unesite vaše prezime!\n");
		String prezime = ulaz.next();
		System.out.println("Molimo vas, unesite korisničko ime!\n");
		String korisnickoIme = ulaz.next();
		System.out.println("Molimo vas, unesite e-mail!\n");
		String email = ulaz.next();
		SimpleDateFormat formatiranje=new SimpleDateFormat("dd/MM/yyyy");
		String datum = OdgovorAdministratora("Unesite datum kreiranja(dd/MM/yyyy):", "text");

		System.out.println("Molimo vas, unesite šifru!\n");
		String sifra = ulaz.next();

		Administrator admin = new Administrator();
		admin.setIme(ime);
		admin.setPrezime(prezime);
		admin.setKorisnickoIme(korisnickoIme);
		admin.setEmail(email);
		admin.setDatumPocetkaAdministracije(formatiranje.parse(datum));
		admin.setSifra(sifra);
		if(admin.registracijaAdministratora(korisnici, admin)){
			System.out.println("Registrovan korisnik je "+korisnickoIme);
			return true;
		} else {
			System.out.println("Došlo je do greške!");
			return false;
		}
	}

	private static String OdgovorAdministratora(String pitanje, String tip){
		Scanner skener = new Scanner(System.in);
		System.out.println(pitanje);
		String odgovor = skener.next();

		if(tip.equals("number")){
			try {
				int broj = Integer.parseInt(odgovor);
				return odgovor;

			} catch (NumberFormatException greska) {
				return OdgovorAdministratora(pitanje, tip);
			}



		} else if(tip.equals("tip")) {

			try {
				int broj = Integer.parseInt(odgovor);
				if(broj!=1&&broj!=2&&broj!=3&&broj!=4){
					return OdgovorAdministratora(pitanje, tip);
				} else {
					return odgovor;
				}
			} catch (NumberFormatException greska) {
				return OdgovorAdministratora(pitanje, tip);

			}
		} else {
			return odgovor;

		}
	}

	@Override
	public boolean registracijaAdministratora(ArrayList<Object>korisnici, Administrator administrator) {

		boolean registrovanKorisnik = false;
		Object korisnickoImePostoji = Servis.nadjiKorisnika(korisnici, administrator.getKorisnickoIme());
		String test = korisnickoImePostoji.getClass().getSimpleName();
		if(korisnickoImePostoji.getClass().getSimpleName().equals("Boolean")){
			zapisiAdminaUDatoteku(administrator);
			registrovanKorisnik = true;
		} else {
			registrovanKorisnik = false;
		}


		return registrovanKorisnik;
	}

	@Override
	public ArrayList<Object> brisanjeKorisnika(ArrayList<Object> korisnici) {
		int Broj = -1;
		Scanner skener = new Scanner(System.in);
		System.out.println("Upišite broj korisnika za brisanje: ");
		for(int i = 0;i<korisnici.size();i++){
			Korisnik korisnik = (Korisnik) korisnici.get(i);
			System.out.println(i+1+" "+korisnik);
		}
		String odgovor = skener.next();
		try {
			Broj = Integer.parseInt(odgovor);
		} catch(NumberFormatException greska){
			return brisanjeKorisnika(korisnici);
		}
		Object korisnikZaBrisanje = korisnici.get(Broj-1);
		if(korisnikZaBrisanje.getClass().getSimpleName().equals("Administrator")){
			if(dozvoljenoBrisanjeAdministratora(korisnici)){
				korisnici.remove(Broj-1);
			} else {
				System.out.println("Nije moguće izbrisati administratora! Postoji samo 1 administrator.");
			}
		} else {
			korisnici.remove(Broj-1);
		}
		return korisnici;
	}

	private boolean dozvoljenoBrisanjeAdministratora(ArrayList<Object> korisnici){
		int brojAdministratora = 0;
		for(Object korisnik: korisnici){
			if(korisnik.getClass().getSimpleName().equals("Administrator")){
				brojAdministratora += 1;
			}
		}
		if(brojAdministratora>1){
			return true;
		} else {
			return false;
		}
	}

	private void zapisiAdminaUDatoteku(Administrator administrator) {
		try (FileWriter f = new FileWriter("Korisnici.txt", true);
			 BufferedWriter b = new BufferedWriter(f);
			 PrintWriter p = new PrintWriter(b);) {
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String datum = simpleDateFormat.format(administrator.getDatumPocetkaAdministracije());
			String registracija = administrator.getIme() + "," + administrator.getPrezime() + "," + administrator.getKorisnickoIme() + "," + administrator.getSifra() + "," + datum + "," + administrator.getEmail();
			p.println(registracija);

		} catch (IOException i) {
			i.printStackTrace();
		}


	}
}

