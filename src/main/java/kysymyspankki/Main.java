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
        
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("kysymykset", kysymykset.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/lisaa", (req, res) -> {
            
            Kysymys k = new Kysymys(-1, req.queryParams("kurssi"), req.queryParams("aihe"), req.queryParams("teksti"));
            kysymykset.save(k);
            
            res.redirect("/");
            
            return "";
            
        });
        
        Spark.post("/poista", (req, res) -> {
            
            kysymykset.delete(Integer.parseInt(req.queryParams("tunnus")));
            
            res.redirect("/");
            
            return "";
        });
        
        Spark.get("/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("vastaukset", vastaukset.findWithKysymys(Integer.parseInt(req.params(":id"))));
            map.put("kysymys", "/lisaavastaus/" + Integer.parseInt(req.params(":id")));
            
            //System.out.println(req.queryParams(":id"));

            return new ModelAndView(map, "vastaus");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/lisaavastaus/:id", (req, res) -> {
            
            Boolean x = true;
            
            if(req.queryParams("oikein") == null) {
                x = false;
            }
            
            Vastaus v = new Vastaus(-1, Integer.parseInt(req.params(":id")), 
                    req.queryParams("teksti"), x);
            
            vastaukset.save(v);
            
            res.redirect("/" + Integer.parseInt(req.params(":id")));            
            
            //System.out.println(req.queryParams("oikein"));
            
            return "";
            
        });
        
        Spark.post("/poistavastaus/:id", (req, res) -> {
            
            vastaukset.delete(Integer.parseInt(req.queryParams("kysymyksenTunnus")));
            //System.out.println(req.queryParams("kysymyksenTunnus"));
            
            res.redirect("/" + Integer.parseInt(req.params(":id")));
            
            //Redirectaa nyt väärälle sivulle - ei enää
            
           return ""; 
        });
        
    }
    
}
