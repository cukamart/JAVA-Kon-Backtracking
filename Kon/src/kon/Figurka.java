package kon;

/**
 * Trieda predstavuje lubovolnu figurku. Staci nastavit moznosti pohybu a zaciatocnu poziciu figurky....
 *
 * @author Martin Čuka 5ZI021
 */
public class Figurka {

    // na kona
    private static final int[] XMOVE = {2, 1, -1, -2, -2, -1, 1, 2}; // mozne pohyby kona....
    private static final int[] YMOVE = {1, 2, 2, 1, -1, -2, -2, -1}; // staci zmenit na ine kombinacie a funguje pre ine figurky
    private static final int POCETMOZNOSTI = 8;

   // toto by fungovalo na krala (resp stroskotanca) :-)
    // private static final int[] XMOVE = {1, 0, -1, 0};
    // private static final int[] YMOVE = {0, 1, 0, -1}; 
    // private static final int POCETMOZNOSTI = 4;
    private final int mPosX;
    private final int mPosY;

    public Figurka(int posX, int posY) {
        mPosX = posX;
        mPosY = posY;
    }

    public int getmPosX() {
        return mPosX;
    }

    public int getmPosY() {
        return mPosY;
    }

    public static int getXMOVE(int i) {
        return XMOVE[i];
    }

    public static int getYMOVE(int i) {
        return YMOVE[i];
    }

    public static int getPOCETMOZNOSTI() {
        return POCETMOZNOSTI;
    }
}
