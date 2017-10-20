/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

import java.sql.Connection;
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
        try (Connection conn = tietokanta.getConnection()) {
            List<Drinkki> palautettavat = new ArrayList<>();

            PreparedStatement drinkki = conn.prepareStatement("SELECT * FROM Drinkki");

            ResultSet rs = drinkki.executeQuery();

            while (rs.next()) {
                palautettavat.add(findDrinkkiById(rs.getInt("id")));
            }

            return palautettavat;
        }
    }

    public Drinkki findDrinkkiByNimi(String nimi) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement drinkki = conn.prepareStatement("SELECT * FROM Drinkki WHERE nimi = ?");
            drinkki.setString(1, nimi);

            ResultSet rs = drinkki.executeQuery();
            
            int id = rs.getInt("id");

            return findDrinkkiById(id);
        }
    }

    public Drinkki findDrinkkiById(int id) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            // Drinkille nimi
            PreparedStatement drinkki = conn.prepareStatement("SELECT * FROM Drinkki WHERE id = ?");
            drinkki.setInt(1, id);

            ResultSet rs = drinkki.executeQuery();

            String nimi = rs.getString("nimi");

            // RaakaAineet
            PreparedStatement raakaAineet = conn.prepareStatement("SELECT * FROM DrinkkiRaakaAine WHERE drinkki_id = ?");
            raakaAineet.setInt(1, id);

            ResultSet raakaAineTulokset = raakaAineet.executeQuery();

            ArrayList<DrinkkiRaakaAine> drinkinRaakaAineet = new ArrayList<>();

            while (raakaAineTulokset.next()) {
                drinkinRaakaAineet.add(new DrinkkiRaakaAine(findRaakaAineById(raakaAineTulokset.getInt("raakaAine_id")), raakaAineTulokset.getInt("maara"),
                        raakaAineTulokset.getInt("jarjestys"), raakaAineTulokset.getString("ohje")));
            }

            Drinkki palautettava = new Drinkki(nimi, id, drinkinRaakaAineet);

            return palautettava;
        }
    }

    public List<RaakaAine> findAllRaakaAine() throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            List<RaakaAine> palautettavat = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM RaakaAine");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String nimi = rs.getString("nimi");
                palautettavat.add(new RaakaAine(nimi));
            }

            return palautettavat;
        }
    }

    public RaakaAine findRaakaAineByNimi(String nimi) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM RaakaAine WHERE nimi = ?");
            statement.setString(1, nimi);

            ResultSet rs = statement.executeQuery();

            int id = rs.getInt("id");

            return findRaakaAineById(id);
        }
    }

    public RaakaAine findRaakaAineById(int id) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM RaakaAine WHERE id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            String nimi = rs.getString("nimi");
            RaakaAine ra = new RaakaAine(nimi, id);

            return ra;
        }
    }

    public RaakaAine addRaakaAine(RaakaAine raakaAine) throws SQLException {
        // Insert statement
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement insertti = conn.prepareStatement("INSERT INTO RaakaAine (nimi) VALUES (?)");
            insertti.setString(1, raakaAine.getNimi());

            insertti.executeUpdate();

            return findRaakaAineByNimi(raakaAine.getNimi());
        }
    }

    public Drinkki addDrinkki(Drinkki drinkki) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement insertti = conn.prepareStatement("Insert INTO Drinkki (nimi) VALUES (?)");
            insertti.setString(1, drinkki.getNimi());

            insertti.executeUpdate();

            return findDrinkkiByNimi(drinkki.getNimi());
        }
    }

    /*
    public void addRaakaAineDrinkkiin(Drinkki )
     */
    public void addDrinkkiinRaakaAine(Drinkki drinkki, DrinkkiRaakaAine dra) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
//            Drinkki uusidrinkki = findDrinkkiById(drinkki.getId());

            PreparedStatement lisays = conn.prepareStatement("INSERT INTO DrinkkiRaakaAine (drinkki_id, raakaAine_id, jarjestys, maara, ohje) VALUES (?, ?, ?, ?, ?)");
            lisays.setInt(1, drinkki.getId());
            lisays.setInt(2, dra.getRaakaAine().getId());
            lisays.setInt(3, dra.getJarjestys());
            lisays.setInt(4, dra.getMaara());
            lisays.setString(5, dra.getOhje());

            lisays.executeUpdate();

//            return uusidrinkki;
        }
    }

    public List<DrinkkiRaakaAine> findAllDrinkinRaakaAineet(int drinkinId) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement haku = conn.prepareStatement("SELECT * FROM DrinkkiRaakaAine WHERE drinkki_id = ?");
            haku.setInt(1, drinkinId);

            ResultSet rSet = haku.executeQuery();
            List<DrinkkiRaakaAine> draLista = new ArrayList<>();

            while (rSet.next()) {
                DrinkkiRaakaAine dra = new DrinkkiRaakaAine(findRaakaAineById(rSet.getInt("drinkki_id")), rSet.getInt("maara"), rSet.getInt("jarjestys"), rSet.getString("ohje"));
                draLista.add(dra);
            }

            return draLista;
        }
    }
    
    public void removeDrinkkiById(int id) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement tyhjennaDrinkkiRaakaAine = conn.prepareStatement("DELETE FROM DrinkkiRaakaAine WHERE drinkki_id = ?");
            tyhjennaDrinkkiRaakaAine.setInt(1, id);
            tyhjennaDrinkkiRaakaAine.executeUpdate();
            
            PreparedStatement tyhjennaDrinkki = conn.prepareStatement("DELETE FROM Drinkki WHERE id = ?");
            tyhjennaDrinkki.setInt(1, id);
            tyhjennaDrinkki.executeUpdate();
        }
    }
    
    public void removeRaakaAineById(int id) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement tyhjennaDrinkkiRaakaAine = conn.prepareStatement("DELETE FROM DrinkkiRaakaAine WHERE raakaAine_id = ?");
            tyhjennaDrinkkiRaakaAine.setInt(1, id);
            tyhjennaDrinkkiRaakaAine.executeUpdate();
            
            PreparedStatement tyhjennaRaakaAine = conn.prepareStatement("DELETE FROM RaakaAine WHERE id = ?");
            tyhjennaRaakaAine.setInt(1, id);
            tyhjennaRaakaAine.executeUpdate();
        }
    }
    
    public void removeRaakaAineFromDrinkkiById(int raakaAineId, int drinkkiId) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement tyhjennaDrinkkiRaakaAine = conn.prepareStatement("DELETE FROM DrinkkiRaakaAine WHERE drinkki_id = ? AND raakaAine_id = ?");
            tyhjennaDrinkkiRaakaAine.setInt(1, drinkkiId);
            tyhjennaDrinkkiRaakaAine.setInt(2, raakaAineId);
            tyhjennaDrinkkiRaakaAine.executeUpdate();
        }
    }
    
    // TODO:    Erilaiset vaadittavat kyselyt
    //          Raaka-aineet yhdessä ja id:n mukaan
    //          Drinkit id:n mukaan
    //          DrinkkiRaakaAine parit?
}
