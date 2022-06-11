import java.util.ArrayList;

public class Kampovanje extends Odmor {
    private static final long serialVersionUID = 7800619579730130854L;

    private String temperatura;

    public Kampovanje(String odmorGUID, int kapacitet, String drzava, String cena, ArrayList<Termin> termini, ArrayList<Rezervacija> rezervacije, String temperatura) {
        super(odmorGUID, kapacitet, drzava, cena, termini, rezervacije);
        this.temperatura = temperatura;
    }

    public Kampovanje() {

    }
}
