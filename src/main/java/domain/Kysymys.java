package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iltal_000
 */
public class Kysymys {
    
    private Integer id;
    private String kurssi;
    private String aihe;
    private String teksti;
    
    public Kysymys(Integer i, String k, String a, String t) {
        this.id = i;
        this.kurssi = k;
        this.aihe = a;
        this.teksti = t;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getKurssi() {
        return kurssi;
    }
    
    public String getAihe() {
        return aihe;
    }
    
    public String getTeksti() {
        return teksti;
    }
    
}
