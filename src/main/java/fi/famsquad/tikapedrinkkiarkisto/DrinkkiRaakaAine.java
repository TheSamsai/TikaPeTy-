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
public class DrinkkiRaakaAine {
    private RaakaAine raakaAine;
    private int maara;
    private int jarjestys;
    private String ohje;

    public DrinkkiRaakaAine(RaakaAine raakaAine, int maara, int jarjestys, String ohje) {
        this.raakaAine = raakaAine;
        this.maara = maara;
        this.jarjestys = jarjestys;
        this.ohje = ohje;
    }

    public RaakaAine getRaakaAine() {
        return raakaAine;
    }

    public int getMaara() {
        return maara;
    }

    public int getJarjestys() {
        return jarjestys;
    }

    public String getOhje() {
        return ohje;
    }
    
    
}
