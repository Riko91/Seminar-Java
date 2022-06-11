import java.io.Serializable;
import java.util.ArrayList;

public class Odmor implements Serializable
{
	private static final long serialVersionUID = 2870292495352665366L;
	private String odmorGUID;
	private int kapacitet;
	private String drzava;
	private String cena;
	private ArrayList<Termin> termini;
	private ArrayList<Rezervacija> rezervacije;

	public Odmor() {

	}

	public String getOdmorGUID() {
		return odmorGUID;
	}

	public void setOdmorGUID(String odmorGUID) {
		this.odmorGUID = odmorGUID;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public ArrayList<Termin> getTermini() {
		return termini;
	}

	public void setTermini(ArrayList<Termin> termini) {
		this.termini = termini;
	}

	public ArrayList<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(ArrayList<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	@Override
	public String toString() {
		return "Odmor{" +
				"odmorGUID='" + odmorGUID + '\'' +
				", kapacitet=" + kapacitet +
				", drzava='" + drzava + '\'' +
				", cena='" + cena + '\'' +
				", termini=" + termini +
				", rezervacije=" + rezervacije +
				'}';
	}

	public Odmor(String odmorGUID, int kapacitet, String drzava, String cena, ArrayList<Termin> termini, ArrayList<Rezervacija> rezervacije) {
		this.odmorGUID = odmorGUID;
		this.kapacitet = kapacitet;
		this.drzava = drzava;
		this.cena = cena;
		this.termini = termini;
		this.rezervacije = rezervacije;
	}
}

