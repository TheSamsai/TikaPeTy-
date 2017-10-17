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
    private String nimi; //lisätty nimi drinkille -mattiost

    
    //luotu konstruktoreita
    public Drinkki(String drinkinNimi){
        this.nimi = drinkinNimi;
    }
    
    public Drinkki(String drinkinNimi, HashMap<RaakaAine, Integer> raakaAineet, List<String> ohjeet){
        this.nimi = drinkinNimi;
        this.raakaAineet = raakaAineet;
        this.ohje = ohjeet;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public void vaihdaDrinkinOminaisuudet(HashMap<RaakaAine, Integer> raakaAineet, List<String> ohjeet) {
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
