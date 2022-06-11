import java.util.ArrayList;

public class Krstarenje extends Odmor {
    private static final long serialVersionUID = 2103246411281200010L;
    private String ocjenaBroda;

    public Krstarenje(String odmorGUID, int kapacitet, String drzava, String cena, ArrayList<Termin> termini, ArrayList<Rezervacija> rezervacije, String ocjenaBroda) {
        super(odmorGUID, kapacitet, drzava, cena, termini, rezervacije);
        this.ocjenaBroda = ocjenaBroda;
    }

    public Krstarenje() {

    }
}
