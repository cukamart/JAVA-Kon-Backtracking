/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grep;

//
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrikazNULL implements IPrikazy {

    @Override
    public void vykonaj(ArrayList<String> vysledok, String[] argumenty, int i) {
        Pattern p = Pattern.compile(argumenty[0]);
        System.out.println("V subore \"" + argumenty[i] + " sa nachadza vyraz " + argumenty[0] + " v nasledujucich riadkoch:");

        for (String string : vysledok) {
            Matcher mat = p.matcher(string);
            if (mat.find()) {
                System.out.println(string);
            }
        }
        System.out.println();
    }

    @Override
    public void dajPopis() {
        System.out.println("V pripade ze uzivatel nezada ziadny prepinac vypisu sa cisto riadky kde sa regularny vyraz nachadza");
    }

}
