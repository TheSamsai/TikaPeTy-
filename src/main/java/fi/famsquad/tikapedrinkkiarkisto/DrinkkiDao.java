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
            
            while(rs.next()){
                String nimi = rs.getString("nimi");
                palautettavat.add(new RaakaAine(nimi));
            }

            return palautettavat;
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

            PreparedStatement haku = conn.prepareStatement("SELECT * FROM RaakaAine WHERE nimi = ?");
            haku.setString(1, raakaAine.getNimi());

            ResultSet raakaAineTulos = haku.executeQuery();

            String nimi = raakaAineTulos.getString("nimi");
            int id = raakaAineTulos.getInt("id");

            return new RaakaAine(nimi, id);
        }
    }

    public Drinkki addDrinkki(Drinkki drinkki) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement insertti = conn.prepareStatement("Insert INTO Drinkki (nimi) VALUES (?)");
            insertti.setString(1, drinkki.getNimi());

            insertti.executeUpdate();

            PreparedStatement haku = conn.prepareStatement("SELECT id FROM Drinkki WHERE nimi = ?");
            haku.setString(1, drinkki.getNimi());

            ResultSet drinkkiHaku = haku.executeQuery();

            int id = drinkkiHaku.getInt("id");
            
            return new Drinkki(drinkki.getNimi(), id);
        }
    }
    
    /*
    public Drinkki addDrinkkiRaakaAineilla(Drinkki drinkki) throws SQLException {
        try (Connection conn = tietokanta.getConnection()) {
            PreparedStatement haku = conn.prepareStatement("SELECT id FROM Drinkki WHERE nimi = ?");
            haku.setString(1, drinkki.getNimi());

            ResultSet drinkkiHaku = haku.executeQuery();

            int id = drinkkiHaku.getInt("id");

            for (RaakaAine r : drinkki.getRaakaAineet().keySet()) {
                PreparedStatement drinkkiRaakaAine = conn.prepareStatement("INSERT INTO DrinkkiRaakaAine (drinkki_id, raakaAine_id, ohje, jarjestys, maara) VALUES (?, ?. ?, ?, ?)");
                drinkkiRaakaAine.setInt(1, id);

                if (r.getId() == -1) {
                    return null;
                } else {
                    drinkkiRaakaAine.setInt(2, r.getId());

                }

                int jarj = drinkki.getRaakaAineet().get(r);
                int maara = drinkki.getRaakaAineMaarat().get(r);

                drinkkiRaakaAine.setString(3, drinkki.getOhjeet().get(jarj));
                drinkkiRaakaAine.setInt(4, jarj);
                drinkkiRaakaAine.setInt(5, maara);

                drinkkiRaakaAine.executeUpdate();
            }

            return new Drinkki(drinkki.getNimi(), id, drinkki.getRaakaAineet(), drinkki.getRaakaAineMaarat(), drinkki.getOhjeet());
        }
    }*/

    // TODO:    Erilaiset vaadittavat kyselyt
    //          Raaka-aineet yhdessä ja id:n mukaan
    //          Drinkit id:n mukaan
    //          DrinkkiRaakaAine parit?
}
