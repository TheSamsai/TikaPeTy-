/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

import java.sql.*;

/**
 *
 * @author sami
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Tietokanta tietokanta = new Tietokanta("db/drinkkitietokanta.db");
        DrinkkiDao dDao = new DrinkkiDao(tietokanta);
        Verkkosivu verkkosivu = new Verkkosivu(dDao);
    }
}
