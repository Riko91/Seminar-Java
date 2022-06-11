import java.io.Serializable;

public class Rezervacija implements Serializable

{
	private static final long serialVersionUID = -6292702247502040343L;
	private Korisnik kreirao;
	private int brojOdraslihOsoba;
	private int brojDece;
	public Rezervacija(){
		super();
	}

	public Korisnik getKreirao() {
		return kreirao;
	}

	public void setKreirao(Korisnik kreirao) {
		this.kreirao = kreirao;
	}

	public int getBrojOdraslihOsoba() {
		return brojOdraslihOsoba;
	}

	public void setBrojOdraslihOsoba(int brojOdraslihOsoba) {
		this.brojOdraslihOsoba = brojOdraslihOsoba;
	}

	public int getBrojDece() {
		return brojDece;
	}

	public void setBrojDece(int brojDece) {
		this.brojDece = brojDece;
	}

	@Override
	public String toString() {
		return "Rezervacija{" +
				"kreirao=" + kreirao +
				", brojOdraslihOsoba=" + brojOdraslihOsoba +
				", brojDece=" + brojDece +
				'}';
	}

	public Rezervacija(Korisnik kreirao, int brojOdraslihOsoba, int brojDece) {
		this.kreirao = kreirao;
		this.brojOdraslihOsoba = brojOdraslihOsoba;
		this.brojDece = brojDece;
	}
}

