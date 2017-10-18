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
    /*
    TODO: Korjaa toimivaksi
    
    public Drinkki findDrinkkiById(int id) throws SQLException {
        // Drinkille nimi
        PreparedStatement drinkki = tietokanta.getConnection().prepareStatement("SELECT * FROM Drinkki WHERE id = ?");
        drinkki.setInt(1, id);
        
        ResultSet rs = drinkki.executeQuery();
        
        String nimi = rs.getString("nimi");
        
        // RaakaAineet
        PreparedStatement raakaAineet = tietokanta.getConnection().prepareStatement("SELECT * FROM DrinkkiRaakaAine WHERE drinkki_id = ?");
        raakaAineet.setInt(1, id);
        
        ResultSet raakaAineTulokset = raakaAineet.executeQuery();
        
        ArrayList<RaakaAine> drinkinRaakaAineet = new ArrayList<>();
        
        while (raakaAineTulokset.next()) {
            drinkinRaakaAineet.add(findRaakaAineById(rs.getInt("id")));
        }
        
        Drinkki palautettava = new Drinkki(nimi, drinkinRaakaAineet);
        
        return ra;
    }*/
    
    public List<RaakaAine> findAllRaakaAine() throws SQLException {
        List<RaakaAine> palautettavat = new ArrayList<>();
        
        PreparedStatement statement = tietokanta.getConnection().prepareStatement("SELECT * FROM RaakaAine");
        
        ResultSet rs = statement.executeQuery();
        
        return palautettavat;
    }
    
    public RaakaAine findRaakaAineById(int id) throws SQLException {
        PreparedStatement statement = tietokanta.getConnection().prepareStatement("SELECT * FROM RaakaAine WHERE id = ?");
        statement.setInt(1, id);
        
        ResultSet rs = statement.executeQuery();
        
        String nimi = rs.getString("nimi");
        RaakaAine ra = new RaakaAine(nimi, id);
        
        return ra;
    }
    
    public void addRaakaAine(RaakaAine raakaAine) {
        // Insert statement
    }
    
    public void addDrinkki(Drinkki drinkki) {
        // Insert statement
    }
    
    
    // TODO:    Erilaiset vaadittavat kyselyt
    //          Raaka-aineet yhdessä ja id:n mukaan
    //          Drinkit id:n mukaan
    //          DrinkkiRaakaAine parit?
}
