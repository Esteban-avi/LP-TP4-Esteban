/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.labintec.model;


import dev.labintec.database.DBConexion;
import dev.labintec.model.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
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
public class RepositoryUser implements IRepo<Usuario> {
    private Connection con;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUP");
    private EntityManager e; 

    public RepositoryUser(Connection con) {
        this.con = con;
    }
    
    
    public RepositoryUser(EntityManager e) {
        this.e = e;
    }
    
    
    @Override
    public void create(Usuario entity) {
        
        try {
            e.getTransaction().begin();

            e.persist(entity); // usamos el mismo objeto que recibimos como parametro, no hace falta crear otro

            e.getTransaction().commit(); // Confirma cambios en la BD
            System.out.println("Usuario creado exitosamente.");

        } catch (Exception ex) {
            if (e.getTransaction().isActive()) {
                e.getTransaction().rollback(); // Revierte si hubo error
            }
        System.out.println("Error al crear el usuario: " + ex.getMessage());

        }
    }
       
    
    
    @Override
    public Usuario read(int id) {
        
        Usuario usuario = null;
        try {
            usuario = e.find(Usuario.class, id);
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        
        return usuario;
       
    }

    @Override
    public List<Usuario> readAll() {
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            usuarios = e.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
            
//            for(Usuario U : usuarios){
//                System.out.println(U);
//            }
            
        } catch (Exception ex) {
            System.out.println("Error al obtener la lista de usuarios: " + ex.getMessage());
        }
        
        return usuarios;
    }

    @Override
    public void update(Usuario entity) {
        
        try {
            
            e.getTransaction().begin();
            e.merge(entity);  // Actualiza los datos del usuario
            e.getTransaction().commit();
            System.out.println("Usuario actualizado correctamente.");
            
        } catch (Exception ex) {
            if (e.getTransaction().isActive()) {
                e.getTransaction().rollback();
            }
            System.out.println("Error al actualizar el usuario: " + ex.getMessage());
        }
        
    }

    @Override
    public void delete(int id) {
        
        try {
            e.getTransaction().begin();
            Usuario usuario = e.find(Usuario.class, id);
            
            if (usuario != null) {
                e.remove(usuario);  // Elimina el usuario si existe
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró un usuario con ese ID.");
            }
            
            e.getTransaction().commit();
            
        } catch (Exception ex) {
            
            if (e.getTransaction().isActive()) {
                e.getTransaction().rollback();
            }
            System.out.println("Error al eliminar el usuario: " + ex.getMessage());
        }
}


    public Usuario readByUsername(String username) {
        Usuario usuario = null;
        
        try {
            usuario = e.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                   .setParameter("username", username)
                   .getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No se encontró un usuario con ese nombre.");
        } catch (Exception ex) {
            System.out.println("Error al buscar usuario por username: " + ex.getMessage());
        }
        
        return usuario;
    }

    public EntityManager getE() {
        return e;
    }

    public void setE(EntityManager e) {
        this.e = e;
    }
    

    
}
