/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sami
 */
public class Tietokanta {
    private String tietokanta;
    
    public Tietokanta(String tietokantaSijainti) throws SQLException {
        this.tietokanta = tietokantaSijainti; 
        // tällä tavalla voidaan syöttää suoraan tiedoston nimi ilman että parametreinä tarvittaisiin ajurilähteitä -mattiost
        // Noice
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + tietokanta);
    }
}
