/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Martin
 */
public class Grep {

    /**
     * Vnorena Trieda Obsahuje vsetky mozne prikazy s ktorymi vieme operovat
     */
    private class ZoznamPrikazov {

        private final HashMap<String, IPrikazy> zoznamPrikazov;

        public ZoznamPrikazov() {
            zoznamPrikazov = new HashMap<>();

            zoznamPrikazov.put("c", new PrikazC());
            zoznamPrikazov.put("v", new PrikazV());
        }
    }

    private final ZoznamPrikazov zp;
    private final MyParser parser;
    private final ArrayList<String> prepinace;
    private String[] argumenty;
    private final ArrayList<String> vysledok;

    public Grep(String[] args) {
        parser = new MyParser(args);
        vysledok = new ArrayList<>();
        prepinace = parser.getPrepinace();
        argumenty = new String[parser.getOstatneArgumenty().size()];
        argumenty = parser.getOstatneArgumenty().toArray(argumenty);
        zp = new ZoznamPrikazov();
    }

    /**
     * Nacita postupne vsetky subory a postupne nad nimi robi dane operacie.
     *
     * @throws IOException ak sa nepodarilo nacitat subor
     */
    private void LoadFiles() throws IOException {
        for (int i = 1; i < argumenty.length; i++) {
            if (ReadFile(i)) {
                LoadIPrikaz(i);
            }
            vysledok.clear();
        }
    }

    /**
     * Precita postupne vsetky subory....
     *
     * @param i poradove cislo suboru
     * @throws IOException ak sa nepodarilo nacitat subor
     * @return - true ak sa podari nacitat false ak sa nepodari
     */
    public boolean ReadFile(int i) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(argumenty[i]))) {
            String line;
            while ((line = br.readLine()) != null) {
                vysledok.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Zadali ste neplatnu cestu k suboru: " + argumenty[i]);
            return false;
        }
        return true;
    }

    /**
     * Vykona prikazy
     *
     * @param i poradove cislo suboru
     */
    private void LoadIPrikaz(int i) {
        for (String string : prepinace) {
            if (zp.zoznamPrikazov.containsKey(string)) {
                zp.zoznamPrikazov.get(string).vykonaj(vysledok, argumenty, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Grep g = new Grep(args);
        g.LoadFiles();
    }
}
