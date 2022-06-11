import java.util.ArrayList;

public class Skijanje extends Odmor {
    private static final long serialVersionUID = -6447520075884062782L;
    private String nivoSnijega;

    public Skijanje(String odmorGUID, int kapacitet, String drzava, String cena, ArrayList<Termin> termini, ArrayList<Rezervacija> rezervacije, String nivoSnijega) {
        super(odmorGUID, kapacitet, drzava, cena, termini, rezervacije);
        this.nivoSnijega = nivoSnijega;
    }

    public Skijanje() {

    }
}
