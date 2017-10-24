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

    public Verkkosivu(DrinkkiDao dDao) throws SQLException {
        this.dDao = dDao;
//        // tästä...
//        this.drinkkilista = this.dDao.findAllDrinkki();
//        this.raakaaineListaTest = this.dDao.findAllRaakaAine();
        // ...tähän on vain testaamisessa tarvittavaa
        //tämä on kommentti
        nakyvaDrinkkimikseriSpark();
        lisaaDrinkkiSpark();
        lisaaDrinkkiinRaakaAineSpark();
    }

    // Pitäiskö posteille ja geteille olla eri nimet ihan vaan selkeyden vuoksi?
    // Esim "/lisaadrinkki" tai vastaava?
    public void nakyvaDrinkkimikseriSpark() {
        Spark.get("/drinkkimikseri", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("drinkkilista", this.dDao.findAllDrinkki());
            map.put("raakislista", this.dDao.findAllRaakaAine());
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

    public void lisaaDrinkkiSpark() {
        Spark.post("/drinkkimikseri", (req, res) -> {
            String drinkinNimi = req.queryParams("drinkinNimi");
            Drinkki lisattavaDrinkki = new Drinkki(drinkinNimi);
            this.dDao.addDrinkki(lisattavaDrinkki);
            System.out.println("Lisättiin drinkki" + lisattavaDrinkki.getNimi());

            res.redirect("/drinkkimikseri");
            return "";
        });
    }

    public void lisaaDrinkkiinRaakaAineSpark() {
        Spark.post("/drinkkiraakis", (req, res) -> {
            int drinkinId = Integer.parseInt(req.queryParams("drinkkiSelect"));
            int raakiksenId = Integer.parseInt(req.queryParams("raakisSelect"));
            int jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
            int maara = Integer.parseInt(req.queryParams("maara"));
            String ohje = req.queryParams("ohje");

            DrinkkiRaakaAine dra = new DrinkkiRaakaAine(this.dDao.findRaakaAineById(raakiksenId), maara, jarjestys, ohje);
            this.dDao.addDrinkkiinRaakaAine(this.dDao.findDrinkkiById(drinkinId), dra);
            res.redirect("/drinkkimikseri");
            return "";
        });
    }
}
