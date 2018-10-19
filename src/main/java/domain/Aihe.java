/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author iltal_000
 */
import java.util.*;

public class Aihe {
    
    private String nimi;
    private ArrayList<Kysymys> kysymykset;
    
    //Vähän keinotekoinen käsite, mutta helpottaa html-sivujen luontia
    
    public Aihe(String n) {
        this.nimi = n;
        this.kysymykset = new ArrayList<>();
    }
    
    public List<Kysymys> getKysymykset() {
        return kysymykset;
    }
    public void setKysymykset(ArrayList<Kysymys> uusi) {
        this.kysymykset = uusi;
    }
    
    @Override
    public String toString() {
        return nimi;
    }
    
}
