/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sami
 */
public class Drinkki {
    private List<DrinkkiRaakaAine> drinkinRaakaAineet;
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
    
    public Drinkki(String drinkinNimi, List<DrinkkiRaakaAine> drinkinRaakaAineet){
        this.nimi = drinkinNimi;
        this.drinkinRaakaAineet = drinkinRaakaAineet;
        this.id = -1;
    }
    
    public Drinkki(String drinkinNimi, int id, List<DrinkkiRaakaAine> drinkinRaakaAineet){
        this.nimi = drinkinNimi;
        this.drinkinRaakaAineet = drinkinRaakaAineet;
        this.id = id;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void vaihdaDrinkinOminaisuudet(List<DrinkkiRaakaAine> drinkinRaakaAineet) {
        this.drinkinRaakaAineet = drinkinRaakaAineet;
    }
    
    public List<DrinkkiRaakaAine> getRaakaAineet() {
        return this.drinkinRaakaAineet;
    }
}
