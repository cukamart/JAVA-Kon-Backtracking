package kon;

/**
 *
 * @author Martin Čuka 5ZI021
 */
public class cv2uloha2kon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Uloha2 u = new Uloha2();
        u.vyries(u.dajPoziciuKonaX(), u.dajPoziciuKonaY(), 1);

        if (!u.ismRiesitelne()) {
            System.out.println("Uloha pri zadanych rozmeroch nema riesenie !");
        }
    }
}
