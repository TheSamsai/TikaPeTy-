/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author sami
 */

public class DebugKayttoliittyma {
    private DrinkkiDao dao;
    
    public DebugKayttoliittyma(DrinkkiDao dao) throws SQLException {
        this.dao = dao;
        
        Scanner lukija = new Scanner(System.in);
        
        System.out.println("DrinkkiArkiston debug-käyttöliittymä:");
        System.out.println("1. lisää raaka-aine");
        System.out.println("2. lisää drinkki");
        System.out.println("3. näytä raaka-aineet");
        System.out.println("4. näytä drinkit");
        
        while (true) {
            System.out.print("> ");
            String komento = lukija.nextLine();
            
            if (komento.equals("1")) {
                System.out.print("Raaka-aineen nimi: ");
                String nimi = lukija.nextLine();
                
                dao.addRaakaAine(new RaakaAine(nimi));
            } else if (komento.equals("2")) {
                System.out.print("Drinkin nimi: ");
                String nimi = lukija.nextLine();
                Drinkki uusiDrinkki = dao.addDrinkki(new Drinkki(nimi));
                
                System.out.println("Kuvaile resepti:");
                HashMap<RaakaAine, Integer> raakaAineet = new HashMap<>();
                HashMap<RaakaAine, Integer> raakaAineidenMaarat = new HashMap<>();
                ArrayList<String> ohje = new ArrayList<>();
                
                while (true) {
                    System.out.print("Lisättävä raaka-aine: ");
                    int id = lukija.nextInt();
                    
                    if (id < 0) {
                        break;
                    }
                    
                    System.out.print("Määrä: ");
                    int maara = lukija.nextInt();
                    
                    System.out.print("Järjestys: ");
                    int jarj = lukija.nextInt();
                    
                    System.out.print("Ohje: ");
                    String ohje = lukija.nextLine();
                }
            }
        }
    }
}
