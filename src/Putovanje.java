import java.util.ArrayList;

public class Putovanje extends Odmor {
    private static final long serialVersionUID = 3189383850717631887L;
    private ArrayList<String> vaznaMesta;

    public Putovanje(String odmorGUID, int kapacitet, String drzava, String cena, ArrayList<Termin> termini, ArrayList<Rezervacija> rezervacije) {
        super(odmorGUID, kapacitet, drzava, cena, termini, rezervacije);
    }

    public Putovanje(String odmorGUID, int kapacitet, String drzava, String cena, ArrayList<Termin> termini, ArrayList<Rezervacija> rezervacije, ArrayList<String> vaznaMesta) {
        super(odmorGUID, kapacitet, drzava, cena, termini, rezervacije);
        this.vaznaMesta = vaznaMesta;
    }

    public Putovanje() {

    }
}
