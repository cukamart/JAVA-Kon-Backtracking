package kon;

/**
 * Trieda predstavuje hraciu plochu. Treba nastavit rozmery plochy.
 *
 * @author Martin Čuka 5ZI021
 */
public class Sachovnica {

    private final int NENAVSTIVENE = -1;
    private final int mRozmerX;
    private final int mRozmerY;
    private final int[][] mSachovnica;

    /**
     * Inicializuje Hraciu Plochu do zakladneho stavu
     *
     * @param rozmerX - pocet stlpcov
     * @param rozmerY - pocet riadkov
     */
    public Sachovnica(int rozmerX, int rozmerY) {

        mRozmerX = rozmerX;
        mRozmerY = rozmerY;
        mSachovnica = new int[rozmerX][rozmerY];

        for (int x = 0; x < mRozmerX; x++) {
            for (int y = 0; y < mRozmerY; y++) {
                mSachovnica[x][y] = NENAVSTIVENE;
            }
        }
    }

    /**
     * Vypise riesenie...
     */
    public void vyriesenaSachovnica() {
        System.out.println("Riesenie:\n");
        for (int x = 0; x < mRozmerX; x++) {
            for (int y = 0; y < mRozmerY; y++) {
                System.out.format("%4d", mSachovnica[x][y]);
            }
            System.out.println("");
        }
    }

    public int getmSachovnica(int x, int y) {
        return mSachovnica[x][y];
    }

    public void setmSachovnica(int x, int y, int hodnota) {
        mSachovnica[x][y] = hodnota;
    }

    public int getmRozmerX() {
        return mRozmerX;
    }

    public int getmRozmerY() {
        return mRozmerY;
    }

}
