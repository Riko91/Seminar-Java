import java.io.*;
import java.util.ArrayList;

public class Serializacija implements Serializable {
    private static final long serialVersionUID = 6529685098267757699L;
    public static ArrayList<Object> ProcitajSveOdmoreIzDatoteke(){
        ArrayList<Object> procitaniOdmori = new ArrayList<>();

        try
        {
            FileInputStream file = new FileInputStream("Podaci.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            procitaniOdmori = (ArrayList<Object>) in.readObject();

            in.close();
            file.close();

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }


        return procitaniOdmori;
    }

    public static boolean ZapisiUDatoteku(ArrayList<Object> odmori){
        try {

            FileOutputStream fileOut = new FileOutputStream("Podaci.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(odmori);
            objectOut.close();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean ZapisiUDatotekuKorisnika(ArrayList<Object> korisnici){
        try {

            FileOutputStream fileOut = new FileOutputStream("Korisnici.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(korisnici);
            objectOut.close();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
