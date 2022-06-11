import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public  interface AdministracijskaLogika
{

	public boolean azuriranjeOdmora(Odmor odmor);
	public boolean brisanjeOdmora(Odmor odmor);
	public <T> boolean generalnoBrisanjeKorisnika(T korisnik);
	public String ispisRezervacija();
	public boolean kreiranjeOdmora(Odmor odmor);
	public boolean kreiranjeRezervacije(Rezervacija rezervacija);
	public boolean registracijaAdministratora(Administrator administrator);
	public <T> String trazi(T tip, String kriterijum);
	public boolean prijava(ArrayList<Object> korisnici);
	public boolean registracijaKorisnika(ArrayList<Object>korisnici, Korisnik korisnik);
	public ArrayList<Object> obrisiOdmor(ArrayList<Object> odmori);
	public ArrayList<Object> izmeniOdmor(ArrayList<Object> odmori);
	public boolean kreiranjeRezervacije(Object korisnik, ArrayList<Object> odmori);
	public void pregledRezervacija(ArrayList<Object> odmori);
	public boolean unosOdmora(Object korisnik) throws ParseException;
	public Map<Odmor, Termin> PotraziOdmorePoVremenu(ArrayList<Object> odmori, Date odDatum, Date doDatum);
	public ArrayList<Object> PotraziOdmorePoDrzavi(ArrayList<Object> odmori, String drzava);
	public ArrayList<Object> PotraziOdmorePoCeni(ArrayList<Object> odmori, int cenaOD, int cenaDO);
	public ArrayList<Object> PotraziOdmorePoTipu(ArrayList<Object> odmori, String tip);
	public Object potraziPoIdentifikacijskomBroju(ArrayList<Object> odmori, String UUID);
	public boolean kreirajAdministratora(ArrayList<Object> korisnici) throws ParseException;
	public boolean registracijaAdministratora(ArrayList<Object>korisnici, Administrator administrator);
	public ArrayList<Object> brisanjeKorisnika(ArrayList<Object> korisnici);
	
	
}

