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

public class Kurssi {
    
    private String nimi;
    private ArrayList<Aihe> aiheet;
    private HashMap<String, ArrayList<Kysymys>> kysymykset;
    
    public Kurssi(String n) {
        this.nimi = n;
        this.aiheet = new ArrayList<>();
        this.kysymykset = new HashMap<>();
    }
    
    public List<Aihe> getAiheet() {
        return aiheet;
    }
    
    public void setAiheet(ArrayList<Aihe> uusi) {
        this.aiheet = uusi;
    }
    
    public boolean contains(Aihe a) {
        for(Aihe aihe : this.aiheet) {
            if(aihe.toString().equals(a.toString())) {
                return true;
            }
        }
        return false;
    }
    
    public void addAihe(Aihe uusi) {
        if(this.contains(uusi)) {
            return;
        }
        this.aiheet.add(uusi);
    }
    
    public void addKysymys(Kysymys k) {
        String aihe = k.getAihe().toString();
        this.kysymykset.put(aihe, kysymykset.getOrDefault(aihe, new ArrayList<>()));
        kysymykset.get(aihe).add(k);
    }
    
    public ArrayList<Kysymys> getKysymyksetWithAihe(String aihe) {
        
        return this.kysymykset.get(aihe);
        
    }
    
    @Override
    public String toString() {
        return nimi;
    }
    
}
