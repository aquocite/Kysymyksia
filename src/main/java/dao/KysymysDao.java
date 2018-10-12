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
import domain.Kysymys;
import java.sql.*;
import java.util.*;

public class KysymysDao {
    
    private Database db;
    
    public KysymysDao(Database db) {
        this.db = db;
    }
    
    public Kysymys findOne(Integer key) throws SQLException {
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Kysymys WHERE id = ?");
        s.setInt(1, key);
        
        ResultSet r = s.executeQuery();
        
        if(!r.next()) {
            return null;
        }
        
        Kysymys k = new Kysymys(r.getInt("id"), r.getString("kurssi"), r.getString("aihe"), r.getString("teksti"));
        
        s.close();
        r.close();
        c.close();
        
        return k;
        
    }
    
    public List<Kysymys> findAll() throws SQLException {
        
        ArrayList<Kysymys> l = new ArrayList<>();
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Asiakas");
        
        
        ResultSet rs = s.executeQuery();
        
        while(rs.next()) {
        
            Kysymys k = new Kysymys(rs.getInt("id"), rs.getString("kurssi"), rs.getString("aihe"), rs.getString("teksti"));

            l.add(k);
        
        }
        
        s.close();
        rs.close();
        c.close();
        
        return l;
    }
    
    public List<Kysymys> findWithKurssi(String kurssi) throws SQLException {
        
        ArrayList<Kysymys> l = new ArrayList<>();
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Asiakas WHERE kurssi = ?");
        s.setString(1, kurssi);       
        
        ResultSet rs = s.executeQuery();
        
        while(rs.next()) {
        
            Kysymys k = new Kysymys(rs.getInt("id"), rs.getString("kurssi"), rs.getString("aihe"), rs.getString("teksti"));

            l.add(k);
        
        }
        
        s.close();
        rs.close();
        c.close();
        
        return l;
        
    }
    
    public List<Kysymys> findWithAihe(String aihe) throws SQLException {
        
        ArrayList<Kysymys> l = new ArrayList<>();
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("SELECT * FROM Asiakas WHERE kurssi = ?");
        s.setString(1, aihe);       
        
        ResultSet rs = s.executeQuery();
        
        while(rs.next()) {
        
            Kysymys k = new Kysymys(rs.getInt("id"), rs.getString("kurssi"), rs.getString("aihe"), rs.getString("teksti"));

            l.add(k);
        
        }
        
        s.close();
        rs.close();
        c.close();
        
        return l;
        
    }
    
    public Kysymys save(Kysymys k) throws SQLException {
        
        Connection c = db.getConnection();
        
        PreparedStatement s = c.prepareStatement("INSERT INTO Kysymys"
                + " (kurssi, aihe, teksti) VALUES (?, ?, ?)");
        s.setString(1, k.getKurssi());
        s.setString(2, k.getAihe());
        s.setString(3, k.getTeksti());
        
        s.executeUpdate();
        
        s.close();
        c.close();
        
        return k;
        
    }
    
    public void delete(Integer key) throws SQLException {
        Connection c = db.getConnection();
        PreparedStatement stmt = c.prepareStatement("DELETE FROM Kysymys WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        c.close();
    }
    
}
