package kysymyspankki;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iltal_000
 */
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import dao.KysymysDao;
import dao.VastausDao;
import domain.Kysymys;
import domain.Vastaus;
import database.Database;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        
        Database db = new Database("jdbc:sqlite:kysymyspankki.db");
        
        KysymysDao kysymykset = new KysymysDao(db);
        VastausDao vastaukset = new VastausDao(db);
        
        Spark.get("*", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("kysymykset", kysymykset.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/lisaa", (req, res) -> {
            
            Kysymys k = new Kysymys(-1, req.queryParams("kurssi"), req.queryParams("aihe"), req.queryParams("teksti"));
            kysymykset.save(k);
            
            res.redirect("*");
            
            return "";
            
        });
        
        Spark.post("/poista", (req, res) -> {
            
            kysymykset.delete(Integer.parseInt(req.queryParams("tunnus")));
            
            res.redirect("*");
            
            return "";
        });
    }
    
}
