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
    
    public RaakaAine(String nimi) {
        this.nimi = nimi;
    }

    public String getNimi() { // automaattisin nimin varustettuja gettejä tarvitaan webbisovelluksen toimintaan -mattiost
        return nimi;
    }
    
}
