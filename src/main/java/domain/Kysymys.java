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
    private Kurssi kurssi;
    private Aihe aihe;
    private String teksti;
    
    public Kysymys(Integer i, Kurssi k, Aihe a, String t) {
        this.id = i;
        this.kurssi = k;
        this.aihe = a;
        this.teksti = t;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Kurssi getKurssi() {
        return kurssi;
    }
    
    public Aihe getAihe() {
        return aihe;
    }
    
    public String getTeksti() {
        return teksti;
    }
    
    @Override
    public String toString() {
        return kurssi + ", " + aihe + ", " + teksti; 
    }
    
}
