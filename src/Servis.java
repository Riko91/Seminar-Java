import java.util.ArrayList;

public class Servis {

    public static Object nadjiKorisnika(ArrayList<Object>korisnici, String korisnickoIme){
        for(Object korisnik:korisnici) {
            String ime = korisnik.getClass().getSimpleName();
            if(korisnik.getClass().getSimpleName().equals("Korisnik")){
                Korisnik obicniKorisnik = (Korisnik) korisnik;
                if(obicniKorisnik.getKorisnickoIme().equals(korisnickoIme)){
                    return obicniKorisnik;
                }
            } else {
                Administrator administrator = (Administrator) korisnik;
                if(administrator.getKorisnickoIme().equals(korisnickoIme)){
                    return administrator;
                }
            }
        }
        return false;
    }

}
