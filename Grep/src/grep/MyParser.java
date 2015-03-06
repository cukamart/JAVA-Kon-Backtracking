package grep;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyParser {

    private final String[] zadanyVyraz;
    private final ArrayList<String> ostatneArgumenty;
    private final ArrayList<String> prepinace;

    public MyParser(String[] args) {

        this.zadanyVyraz = args;

        this.ostatneArgumenty = new ArrayList<>();
        this.prepinace = new ArrayList<>();

        parse();

    }

    private void parse() {

        if (zadanyVyraz.length < 2) {
            System.err.println("Neboli zadane dostatocne parametre.");
            System.err.println("Treba zadat minimalne regularny vyraz a subor v ktorom hladat.");
            System.exit(0);
        }

        Pattern p = Pattern.compile("^\\-[a-zA-Z]"); // prvy znak je pomlcka dalsi pismeno a potom lubovolne znaky
        for (String arg : this.zadanyVyraz) {
            Matcher mat = p.matcher(arg);
            if (mat.matches()) {
                String tmp = mat.group(); // vrati do Stringu prepinac co nasiel
                String s = tmp.substring(1); // vymaze pomlcku
                prepinace.add(s); // ulozi do arrayListu
            } else {
                this.ostatneArgumenty.add(arg);
            }
        }
        
        if (prepinace.isEmpty())
            prepinace.add("null");
    }

    public ArrayList<String> getOstatneArgumenty() {
        return ostatneArgumenty;
    }

    public ArrayList<String> getPrepinace() {
        return prepinace;
    }
}
