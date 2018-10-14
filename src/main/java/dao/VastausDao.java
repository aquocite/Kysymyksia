/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author iltal_000
 */
import database.Database;
import domain.Vastaus;
import java.sql.*;
import java.util.*;

public class VastausDao {
    
    private Database db;
    
    public VastausDao(Database d) {
        
        this.db = d;
        
    }
    
    public Vastaus findOne(Integer key) throws SQLException {
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Vastaus WHERE id = ?");
        s.setInt(1, key);
        
        ResultSet r = s.executeQuery();
        
        if(!r.next()) {
            return null;
        }
        
        Vastaus v = new Vastaus(r.getInt("id"), r.getInt("kysymysID"), r.getString("teksti"), r.getBoolean("oikein"));
        
        s.close();
        r.close();
        c.close();
        
        return v;
        
    }
    
    public List<Vastaus> findAll() throws SQLException {
        
        ArrayList<Vastaus> l = new ArrayList<>();
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Vastaus");
        
        
        ResultSet r = s.executeQuery();
        
        while(r.next()) {
        
            Vastaus v = new Vastaus(r.getInt("id"), r.getInt("kysymysID"), r.getString("teksti"), r.getBoolean("oikein"));

            l.add(v);
        
        }
        
        s.close();
        r.close();
        c.close();
        
        return l;
        
    }
    
    public List<Vastaus> findWithAihe(String aihe) throws SQLException {
        
        ArrayList<Vastaus> l = new ArrayList<>();
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Vastaus WHERE aihe = ?");
        s.setString(1, aihe);
        
        
        ResultSet r = s.executeQuery();
        
        while(r.next()) {
        
            Vastaus v = new Vastaus(r.getInt("id"), r.getInt("kysymysID"), r.getString("teksti"), r.getBoolean("oikein"));

            l.add(v);
        
        }
        
        s.close();
        r.close();
        c.close();
        
        return l;
        
    }
    
    public List<Vastaus> findWithKysymys(Integer kysymysKey) throws SQLException {
        
        ArrayList<Vastaus> l = new ArrayList<>();
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Vastaus WHERE kysymysID = ?");
        s.setInt(1, kysymysKey);
        
        
        ResultSet r = s.executeQuery();
        
        while(r.next()) {
        
            Vastaus v = new Vastaus(r.getInt("id"), r.getInt("kysymysID"), r.getString("teksti"), r.getBoolean("oikein"));

            l.add(v);
        
        }
        
        s.close();
        r.close();
        c.close();
        
        return l;
        
    }
    
    public Vastaus save(Vastaus v) throws SQLException {
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("INSERT INTO Vastaus"
                + " (kysymysID, teksti, oikein) VALUES (?, ?, ?)");
        s.setInt(1, v.getKysymysId());
        s.setString(2, v.getTeksti());
        s.setBoolean(3, v.getOikein());
        
        s.executeUpdate();
        
        s.close();
        c.close();
        
        return v;
        
    }
    
    public void delete(Integer key) throws SQLException {
        Connection c = db.getConnection();
        PreparedStatement stmt = c.prepareStatement("DELETE FROM Vastaus WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        c.close();
    }
    
}
