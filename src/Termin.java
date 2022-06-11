import java.io.Serializable;
import java.util.Date;

public class Termin implements Serializable
{
	private static final long serialVersionUID = 5996261714547773470L;
	private Date datumDolaska;
	private Date datumOdlaska;
	public Termin(){
		super();
	}

	public Date getDatumDolaska() {
		return datumDolaska;
	}

	public void setDatumDolaska(Date datumDolaska) {
		this.datumDolaska = datumDolaska;
	}

	public Date getDatumOdlaska() {
		return datumOdlaska;
	}

	public void setDatumOdlaska(Date datumOdlaska) {
		this.datumOdlaska = datumOdlaska;
	}

	@Override
	public String toString() {
		return "Termin{" +
				"datumDolaska=" + datumDolaska +
				", datumOdlaska=" + datumOdlaska +
				'}';
	}

	public Termin(Date datumDolaska, Date datumOdlaska) {
		this.datumDolaska = datumDolaska;
		this.datumOdlaska = datumOdlaska;
	}
}

