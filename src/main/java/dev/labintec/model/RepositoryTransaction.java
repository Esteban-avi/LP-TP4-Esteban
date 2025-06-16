/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.labintec.model;


import dev.labintec.model.entities.Tramite;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP Pavilion
 */
public class RepositoryTransaction implements IRepo<Tramite> {
    private Connection con;

    public RepositoryTransaction(Connection con) {
        this.con = con;
    }
    
    
    @Override
    public void create(Tramite entity) {
        
        try {
            String query = "insert into Tramite (tipo, estado, descripcion) values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, entity.getTipo());
            ps.setString(2, entity.getEstado());
            ps.setString(3, entity.getDescripcion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear la consulta. " + ex.getMessage());
        }
        
    }

    @Override
    public Tramite read(int id) {
        Tramite t = null;
        
        try {
            String query = "select * from Tramite where id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) { 
                t = new Tramite(rs.getInt("id"), rs.getString("tipo"), rs.getString("estado"), rs.getString("descripcion"));
                
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base datos. " + ex.getMessage());
        }
        
        
        
        return t;
    }

    @Override
    public List<Tramite> readAll() {
        List<Tramite> tramites = new ArrayList<>();
        
        try {
            String query = "select * from Tramite";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Tramite t = new Tramite();
                t.setId(rs.getInt("id"));
                t.setTipo(rs.getString("tipo"));
                t.setEstado(rs.getString("estado"));
                t.setDescripcion(rs.getString("descripcion"));
                
                tramites.add(t);
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base datos. " + ex.getMessage());
        }
        
        
        return tramites;
    }

    @Override
    public void update(Tramite entity) {
        
        try {
            String query = "update Tramite set id=?, tipo=?, estado=?, descripcion=? where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getTipo());
            ps.setString(3, entity.getEstado());
            ps.setString(4, entity.getDescripcion());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear la consulta. " + ex.getMessage());
        }
        
    }

    @Override
    public void delete(int id) {
        
        try {
            String query = "delete from Tramite where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar tramite: " + ex.getMessage());
        }
    }

}
