import java.util.ArrayList;

public class TuristicnaAgencija
{
	private static final long serialVersionUID = 6529685098267757701L;
	private String ime;
	private String adresa;
	private String email;
	private ArrayList<Odmor> ponude;
	public TuristicnaAgencija(){
		super();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Odmor> getPonude() {
		return ponude;
	}

	public void setPonude(ArrayList<Odmor> ponude) {
		this.ponude = ponude;
	}

	@Override
	public String toString() {
		return "TuristicnaAgencija{" +
				"ime='" + ime + '\'' +
				", adresa='" + adresa + '\'' +
				", email='" + email + '\'' +
				", ponude=" + ponude +
				'}';
	}

	public TuristicnaAgencija(String ime, String adresa, String email, ArrayList<Odmor> ponude) {
		this.ime = ime;
		this.adresa = adresa;
		this.email = email;
		this.ponude = ponude;
	}
}

