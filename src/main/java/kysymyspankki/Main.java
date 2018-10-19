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
import java.util.*;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import dao.*;
import domain.*;
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
            
            ArrayList<Kurssi> kurssit = new ArrayList<>();
            ArrayList<String> nimet = new ArrayList<>();
            
            for(Kysymys k : kysymykset.findAll()) {
                
                if(!nimet.contains(k.getKurssi().toString())) {
                    kurssit.add(k.getKurssi());
                    nimet.add(k.getKurssi().toString());
                }
                
            }
            
            for(Kurssi k : kurssit) {
                for(Kysymys ky : kysymykset.findAll()) {
                    if(ky.getKurssi().toString().equals(k.toString())) {
                        k.addKysymys(ky);
                        k.addAihe(ky.getAihe());
                    }
                }
            }
            
            map.put("kurssit", kurssit);
            

            return new ModelAndView(map, "kysymys");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/lisaa", (req, res) -> {
            
            Kysymys k = new Kysymys(-1, new Kurssi(req.queryParams("kurssi")), 
                    new Aihe(req.queryParams("aihe")), req.queryParams("teksti"));
            
            if(k.getKurssi().toString().isEmpty() || k.getAihe().toString().isEmpty() || k.getTeksti().isEmpty()) {
                
                res.redirect("/kysymysvirhe");
                return "";
                
            }
            
            kysymykset.save(k);
            
            res.redirect("/");
            
            return "";
            
        });
        
        Spark.post("/poista", (req, res) -> {
            
            kysymykset.delete(Integer.parseInt(req.queryParams("tunnus")));
            
            res.redirect("/");
            
            return "";
        });
        
        Spark.get("/kysymysvirhe", (req, res) -> {
            
            HashMap map = new HashMap<>();
            
            ArrayList<Kurssi> kurssit = new ArrayList<>();
            ArrayList<String> nimet = new ArrayList<>();
            
            for(Kysymys k : kysymykset.findAll()) {
                
                if(!nimet.contains(k.getKurssi().toString())) {
                    kurssit.add(k.getKurssi());
                    nimet.add(k.getKurssi().toString());
                }
                
            }
            
            for(Kurssi k : kurssit) {
                for(Kysymys ky : kysymykset.findAll()) {
                    if(ky.getKurssi().toString().equals(k.toString())) {
                        k.addKysymys(ky);
                        k.addAihe(ky.getAihe());
                    }
                }
            }
            
            map.put("kurssit", kurssit);

            return new ModelAndView(map, "virhekysymys");
            
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("vastaukset", vastaukset.findWithKysymys(Integer.parseInt(req.params(":id"))));
            map.put("kysymys", "/lisaavastaus/" + Integer.parseInt(req.params(":id")));
            map.put("kysymysteksti", kysymykset.findOne(Integer.parseInt(req.params(":id"))).getTeksti());
            
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
            
            if(req.queryParams("teksti").isEmpty()) {
                
                res.redirect("/virhe/" + Integer.parseInt(req.params(":id")));
                
                return "";
                
            }
            
            vastaukset.save(v);
            
            res.redirect("/" + Integer.parseInt(req.params(":id")));            
            
            //System.out.println(req.queryParams("oikein"));
            
            return "";
            
        });
        
        Spark.get("/virhe/:id", (req, res) -> {
            
            HashMap map = new HashMap<>();
            
            map.put("vastaukset", vastaukset.findWithKysymys(Integer.parseInt(req.params(":id"))));
            map.put("kysymys", "/lisaavastaus/" + Integer.parseInt(req.params(":id")));
            map.put("kysymysteksti", kysymykset.findOne(Integer.parseInt(req.params(":id"))).getTeksti());
            
            //System.out.println(req.queryParams(":id"));

            return new ModelAndView(map, "virhevastaus");
            
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/poistavastaus/:id", (req, res) -> {
            
            vastaukset.delete(Integer.parseInt(req.queryParams("tunnus")));
            //System.out.println(req.queryParams("kysymyksenTunnus"));
            
            res.redirect("/" + Integer.parseInt(req.params(":id")));
            
            //Redirectaa nyt väärälle sivulle - ei enää
            
           return ""; 
        });
        
    }
    
}
