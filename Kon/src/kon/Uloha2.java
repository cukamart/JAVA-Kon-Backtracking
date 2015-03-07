package kon;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Uloha2 {

    private final Figurka f;
    private final Sachovnica s;
    private boolean mRiesitelne;

    /**
     * Konstruktor -> nastavi Kona na zaciatocnu poziciu, inicializuje sachovnicu do vychodzieho stavu
     */
    public Uloha2() {
        mRiesitelne = false;
        int stlpce, riadky, figurkaX, figurkaY;
        stlpce = riadky = figurkaX = figurkaY = 0;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadajte rozmery hracieho pola (pre 7x7 moze riesenie trvat v niektorych situaciach radovo niekolko minut !)");
        System.out.println("Pocet stlpcov");
        try{
        stlpce = sc.nextInt();
        System.out.println("Pocet riadkov");
        riadky = sc.nextInt();
        System.out.println("Zadajte poziciu figurky (od 1 do rozmeru hracej plochy...)");
        System.out.println("X-ksova suradnica");
        figurkaX = sc.nextInt();
        System.out.println("Y-ksova suradnica");
        figurkaY = sc.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Zle zadany vstup, prosim zadajte hodnotu integer");
            System.exit(0);
        }
        zlyVstup(figurkaX, figurkaY, stlpce, riadky);
        s = new Sachovnica(stlpce, riadky);
        f = new Figurka(--figurkaX, --figurkaY);
        s.setmSachovnica(f.getmPosX(), f.getmPosY(), 0); // prvotna pozicia kona na sachovnici = 0
    }
    
    /**
     * Metoda "osetri" zle vstupy (ukonci program).
     * @param figurkaX - X-ksova suradnica figurky
     * @param figurkaY - Y-nova suradnica figurky
     * @param stlpce - pocet stlpcov hracej plochy
     * @param riadky  - pocet riadkov hracej plochy
     */
    private void zlyVstup(int figurkaX, int figurkaY, int stlpce, int riadky) {
        if (figurkaX < 1 || figurkaY < 1){
            System.out.println("Zle zadany vstup!");
            System.out.println("Pozicia figurky moze byt len v rozmedzi hracej plochy...");
        }
        if (stlpce < 0 || riadky < 0){
            System.out.println("Zle zadany vstup!");
            System.out.println("Hracia plocha nemoze mat nulovy / zaporny rozmer...");
        }
        if (figurkaX > stlpce || figurkaY > riadky){
            System.out.println("Zle zadany vstup!");
            System.out.println("Figurka nemoze byt mimo hraciu plochu");
        }
    }

    /**
     * Figurka poskace sachovnicu (backtracking)
     *
     * @param konX - x suradnica kona
     * @param konY - y suradnica kona
     * @param dalsiKrok - cislo kroku (zaciatocna pozicia kona = 0)
     * @return true -> krok sa podaril / cela hracia plocha je vyskakana false -> krok sa nepodaril....
     */
    public boolean vyries(int konX, int konY, int dalsiKrok) {
        int novaSuradnicaX, novaSuradnicaY;

        if (dalsiKrok == s.getmRozmerX() * s.getmRozmerY()) { // ak dalsi krok == velkosti sachovnice -> figurka presla celu hraciu plochu...
            mRiesitelne = true;
            s.vyriesenaSachovnica();
            return true;
        }

        for (int i = 0; i < Figurka.getPOCETMOZNOSTI(); i++) { // prechadza moznosti figurky
            novaSuradnicaX = konX + Figurka.getXMOVE(i); // postupne skusa vsetky moznosti nastavuje X-sovu suradnicu
            novaSuradnicaY = konY + Figurka.getYMOVE(i); // postupne skusa vsetky moznosti nastavuje Y-novu suradnicu
            if (jePolickoPristupne(novaSuradnicaX, novaSuradnicaY)) { // ak sa da pohnut, pohne
                s.setmSachovnica(novaSuradnicaX, novaSuradnicaY, dalsiKrok); // vyznaci krok na sachovnici
                if (vyries(novaSuradnicaX, novaSuradnicaY, dalsiKrok + 1) == true) { // skusa dalej z novej pozicie...
                    return true;
                } else {
                    s.setmSachovnica(novaSuradnicaX, novaSuradnicaY, -1); // vrati sa krok spat :-)
                }
            }
        }
        return false;
    }

    /**
     * Metoda zistuje ci sa da skocit na dalsie policko sachovnice
     *
     * @param x - xsova suradnica sachovnice
     * @param y - ysova suradnica sachovnice
     * @return vrati true ak sa na dane policko da skocit inak vrati false
     */
    private boolean jePolickoPristupne(int x, int y) {
        // ak x a y je vacsie ako 0 a zaroven mensie ako rozmer sachovnice a este tam kon nebol vrati true inak vrati false
        return x >= 0 && x < s.getmRozmerX() && y >= 0 && y < s.getmRozmerY() && s.getmSachovnica(x, y) == -1;
    }

    public boolean ismRiesitelne() {
        return mRiesitelne;
    }

    public int dajPoziciuKonaX() {
        return f.getmPosX();
    }

    public int dajPoziciuKonaY() {
        return f.getmPosY();
    }
}
