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
import java.util.HashMap;
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
        
        PreparedStatement drinkki = tietokanta.getConnection().prepareStatement("SELECT * FROM Drinkki");
        
        ResultSet rs = drinkki.executeQuery();
        
        while (rs.next()) {
            palautettavat.add(findDrinkkiById(rs.getInt("id")));
        }
        
        return palautettavat;
    }
    
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
        
        HashMap<RaakaAine, Integer> drinkinRaakaAineet = new HashMap<>();
        ArrayList<String> ohje = new ArrayList<>();
        
        while (raakaAineTulokset.next()) {
            drinkinRaakaAineet.put(findRaakaAineById(rs.getInt("id")), rs.getInt("jarjestys"));
            ohje.add(rs.getString("ohje"));
        }
        
        Drinkki palautettava = new Drinkki(nimi, drinkinRaakaAineet, ohje);
        
        return palautettava;
    }
    
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
    
    public void addRaakaAine(RaakaAine raakaAine) throws SQLException {
        // Insert statement
        
        PreparedStatement insertti = tietokanta.getConnection().prepareStatement("INSERT INTO RaakaAine (nimi) VALUES (?)");
        insertti.setString(1, raakaAine.getNimi());
        
        insertti.executeUpdate();
    }
    
    /*
    public void addDrinkki(Drinkki drinkki) throws SQLException {
        // Insert statement
        
        PreparedStatement insertti = tietokanta.getConnection().prepareStatement("Insert INTO Drinkki (nimi) VALUES (?)");
        insertti.setString(1, drinkki.getNimi());
        
        insertti.executeUpdate();
        
        for (RaakaAine r : drinkki.getRaakaAineet().keySet()) {
            PreparedStatement drinkkiRaakaAine = tietokanta.getConnection().prepareStatement("INSERT INTO DrinkkiRaakaAine (drinkki_id, raakaAine_id)");
        }
    }*/
    
    
    // TODO:    Erilaiset vaadittavat kyselyt
    //          Raaka-aineet yhdessä ja id:n mukaan
    //          Drinkit id:n mukaan
    //          DrinkkiRaakaAine parit?
}
