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
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

/**
 *
 * @author mattiost
 */
public class Verkkosivu {

    private DrinkkiDao dDao;
    private List<Drinkki> drinkkiListaTest;
    private List<RaakaAine> raakaaineListaTest;

    public Verkkosivu(DrinkkiDao dDao) throws SQLException {
        this.dDao = dDao;
        // tästä...
        this.drinkkiListaTest = new ArrayList<>();
        this.raakaaineListaTest = new ArrayList<>();
        this.raakaaineListaTest.add(new RaakaAine("Maaukko"));
        this.raakaaineListaTest.add(new RaakaAine(("Kakka")));
        // ...tähän on vain testaamisessa tarvittavaa
        //tämä on kommentti
        nakyvaDrinkkimikseri();
        lisaaDrinkki();
    }
    
    // Pitäiskö posteille ja geteille olla eri nimet ihan vaan selkeyden vuoksi?
    // Esim "/lisaadrinkki" tai vastaava?
    
    public void nakyvaDrinkkimikseri() {
        Spark.get("/drinkkimikseri", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("drinkkilistaOikea", this.dDao.findAllDrinkki());
            map.put("raakislistaoikea", this.dDao.findAllRaakaAine());
            map.put("drinkkilista", this.drinkkiListaTest);
            map.put("raakislista", this.raakaaineListaTest);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        // Loput getit tänne
        
        // Helppo redirekti joka ohjaa oikealle verkkosivulle
        // Nyt voi yhdistää suoraan http://localhost:4567 ja päästä oikeaan paikkaan
        Spark.get("*", (req, res) -> {
            res.redirect("/drinkkimikseri");
            
            return "";
        });
        
        // Tänne ei enempää gettejä, viimeisin getti on catch-all
    }

    public void lisaaDrinkki() {
        Spark.post("/drinkkimikseri", (req, res) -> {
            String drinkinNimi = req.queryParams("drinkinNimi");
            Drinkki lisattavaDrinkki = new Drinkki(drinkinNimi);
            // this.dDao.lisaaDrinkki(lisattavaDrinkki);
            this.drinkkiListaTest.add(lisattavaDrinkki);
            System.out.println("Lisättiin drinkki" + lisattavaDrinkki.getNimi());

            res.redirect("/drinkkimikseri");
            return "";
        });
    }

}
