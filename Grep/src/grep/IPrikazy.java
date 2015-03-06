/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grep;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public abstract interface IPrikazy {
    
    public void vykonaj(ArrayList<String> vysledok, String[] argumenty, int i);

    public void dajPopis();
}
