/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.famsquad.tikapedrinkkiarkisto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        System.out.println("5. lisää drinkkiin raaka-aine");
        System.out.println("6. Näytä drinkin raaka-aineet");
        System.out.println("7. Poista Drinkistä raaka-aine"); // Vain DrinkkiRaakaAine taulu kyseiselle drinkille ja raaka-aineelle
        System.out.println("8. Poista Drinkki"); // <-- // Poistetaan drinkki, poistetaan DrinkkiRaakaAine taulut drinkille
        System.out.println("9. Poista Raaka-aine"); // Tyhjennä myös DrinkkiRaakaAine kyseiselle RaakaAineelle

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

//                System.out.println("Kuvaile resepti:");
//                HashMap<RaakaAine, Integer> raakaAineet = new HashMap<>();
//                HashMap<RaakaAine, Integer> raakaAineidenMaarat = new HashMap<>();
//                ArrayList<String> ohje = new ArrayList<>();
//                
//                while (true) {
//                    System.out.print("Lisättävä raaka-aine: ");
//                    int id = lukija.nextInt();
//                    
//                    if (id < 0) {
//                        break;
//                    }
//                    
//                    System.out.print("Määrä: ");
//                    int maara = lukija.nextInt();
//                    
//                    System.out.print("Järjestys: ");
//                    int jarj = lukija.nextInt();
//                    
//                    System.out.print("Ohje: ");
//                    String ohje = lukija.nextLine();
//                }
            } else if (komento.equals("3")) {
                List<RaakaAine> raakaAineLista = dao.findAllRaakaAine();
                raakaAineLista.forEach(raakaAine -> System.out.println(raakaAine.getNimi()));
    
            } else if (komento.equals("4")) {
                for (int i = 0; i < dao.findAllDrinkki().size(); i++) {
                    System.out.println(dao.findAllDrinkki().get(i).getNimi());
                }
            }  else if (komento.equals("5")) {
                System.out.print("Syötä drinkin id: ");
                int drinkinId = Integer.parseInt(lukija.nextLine());
                Drinkki drinkkiJohonLisataan = dao.findDrinkkiById(drinkinId);
                System.out.print("Syötä lisättävän raaka-aineen id: ");
                int raakaAineId = Integer.parseInt(lukija.nextLine());
                RaakaAine lisattavaRaakaAine = dao.findRaakaAineById(raakaAineId);
                System.out.println("Syötä määrä: ");
                int maara = Integer.parseInt(lukija.nextLine());
                System.out.println("Syötä järjestysnumero: ");
                int jarjestys = Integer.parseInt(lukija.nextLine());
                System.out.println("Syötä ohje: ");
                String ohje = lukija.nextLine();
                DrinkkiRaakaAine dra = new DrinkkiRaakaAine(lisattavaRaakaAine, maara, jarjestys, ohje);
                dao.addDrinkkiinRaakaAine(drinkkiJohonLisataan, dra);
            } else if (komento.equals("6")) {
                System.out.println("Syötä drinkin id");
                List<DrinkkiRaakaAine> draLista = dao.findAllDrinkinRaakaAineet(Integer.parseInt(lukija.nextLine()));
                draLista.forEach(dra -> System.out.println(dra.getRaakaAine().getNimi()));
            } else if (komento.equals("7")){
                System.out.println("Syötä drinkin id");
                int drinkkiId = Integer.parseInt(lukija.nextLine());
                System.out.println("Syötä raaka-aineen id");
                int raakaAineId = Integer.parseInt(lukija.nextLine());
                dao.removeRaakaAineFromDrinkkiById(raakaAineId, drinkkiId);
            } else if (komento.equals("8")){
                System.out.println("Syötä drinkin id");
                int drinkkiId = Integer.parseInt(lukija.nextLine());
                dao.removeDrinkkiById(drinkkiId);
            } else if (komento.equals("9")){
                System.out.println("Syötä raaka-aineen id");
                int raakaAineId = Integer.parseInt(lukija.nextLine());
                dao.removeRaakaAineById(raakaAineId);
            }
        }
    }
}
