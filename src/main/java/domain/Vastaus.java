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
public class Vastaus {
    
    private Integer id;
    private Integer kysymysId;
    private String teksti;
    private boolean oikein;
    
    public Vastaus(Integer i, Integer k, String t, boolean o) {
        
        this.id = i;
        this.kysymysId = k;
        this.teksti = t;
        this.oikein = o;
        
    }
    
    public Integer getId() {
        return id;
    }
    
    public Integer getKysymysId() {
        return kysymysId;
    }
    
    public String getTeksti() {
        return teksti;
    }
    
    public boolean getOikein() {
        return oikein;
    }
    
}
