/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sami
 */
public class Drinkki {
    private HashMap<RaakaAine, Integer> raakaAineet;
    private List<String> ohje;
    
    public Drinkki(HashMap<RaakaAine, Integer> raakaAineet, List<String> ohjeet) {
        this.raakaAineet = raakaAineet;
        this.ohje = ohjeet;
    }
    
    public HashMap<RaakaAine, Integer> getRaakaAineet() {
        return this.raakaAineet;
    }
    
    public List<String> getOhjeet() {
        return this.ohje;
    }
}
