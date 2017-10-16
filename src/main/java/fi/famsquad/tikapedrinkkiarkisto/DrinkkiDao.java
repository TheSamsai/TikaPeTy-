/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sami
 */
public class DrinkkiDao {
    private Tietokanta tietokanta;
    
    public DrinkkiDao(Tietokanta tietokanta) {
        this.tietokanta = tietokanta;
    }
    
    public List<Drinkki> findAllDrinkki() throws SQLException {
        List<Drinkki> palautettavat = new ArrayList<>();
        
        PreparedStatement statement = tietokanta.getConnection().prepareStatement("SELECT * FROM Drinkki");
        ResultSet rs = statement.executeQuery();
        
        return palautettavat;
    }
    
    // TODO:    Erilaiset vaadittavat kyselyt
    //          Raaka-aineet yhdessä ja id:n mukaan
    //          Drinkit id:n mukaan
    //          DrinkkiRaakaAine parit?
}
