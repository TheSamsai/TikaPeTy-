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
    private HashMap<RaakaAine, Integer> raakaAineMaarat;
    private List<String> ohje;
    private String nimi; //lisätty nimi drinkille -mattiost
    private int id;
    
    //luotu konstruktoreita
    public Drinkki(String drinkinNimi){
        this.nimi = drinkinNimi;
        this.id = -1;
    }
    
    public Drinkki(String nimi, int id) {
        this.nimi = nimi;
        this.id = id;
    } 
    
    public Drinkki(String drinkinNimi, HashMap<RaakaAine, Integer> raakaAineet, HashMap<RaakaAine, Integer> raakaAineMaarat, List<String> ohjeet){
        this.nimi = drinkinNimi;
        this.raakaAineet = raakaAineet;
        this.raakaAineMaarat = raakaAineMaarat;
        this.ohje = ohjeet;
        this.id = -1;
    }
    
    public Drinkki(String drinkinNimi, int id, HashMap<RaakaAine, Integer> raakaAineet, HashMap<RaakaAine, Integer> raakaAineMaarat, List<String> ohjeet){
        this.nimi = drinkinNimi;
        this.raakaAineet = raakaAineet;
        this.ohje = ohjeet;
        this.id = id;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void vaihdaDrinkinOminaisuudet(HashMap<RaakaAine, Integer> raakaAineet, List<String> ohjeet) {
        this.raakaAineet = raakaAineet;
        this.ohje = ohjeet;
    }
    
    public HashMap<RaakaAine, Integer> getRaakaAineet() {
        return this.raakaAineet;
    }
    
    public HashMap<RaakaAine, Integer> getRaakaAineMaarat() {
        return this.raakaAineMaarat;
    }
    
    public List<String> getOhjeet() {
        return this.ohje;
    }
}
