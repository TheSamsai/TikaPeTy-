/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

/**
 *
 * @author sami
 */
public class RaakaAine {
    private String nimi;
    private int id;
    
    public RaakaAine(String nimi) {
        this.nimi = nimi;
        this.id = -1;
    }
    
    public RaakaAine(String nimi, int id) {
        this.nimi = nimi;
        this.id = id;
    }
    
    public String getNimi() { // automaattisin nimin varustettuja gettejä tarvitaan webbisovelluksen toimintaan -mattiost
        return nimi;
    }
    
}
