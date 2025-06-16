/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.labintec.model;


import dev.labintec.database.DBConexion;
import dev.labintec.model.entities.Usuario;
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

    public RepositoryUser(Connection con) {
        this.con = con;
    }

    
    @Override
    public void create(Usuario entity) {
        
        try {
            
            if (readByUsername(entity.getUsername()) != null) {
                System.out.println("El nombre de usuario ya existe. Intente con otro.");
                
            }
            else{
                
                // Consulta SQL para insertar un nuevo usuario en la tabla Usuario
                 String query = "insert into Usuario (username, contra) values (?,?)";

                 // Se prepara la sentencia con la conexión activa
                 PreparedStatement ps = con.prepareStatement(query);

                 // Se establece el valor del primer parámetro: nombre de usuario
                 ps.setString(1, entity.getUsername());
                 // Se establece el valor del segundo parámetro: contraseña
                 ps.setString(2, entity.getPassword());

                 // Se ejecuta la sentencia de inserción
                 ps.executeUpdate();
                 // Se cierra el PreparedStatement
                 ps.close(); 
                 
                 System.out.println("Usuario creado.");
            }
          
            
        } catch (SQLException ex) {
            System.out.println("No se pudo crear la consulta. " + ex.getMessage());
        }
        
    }

    @Override
    public Usuario read(int id) {
        Usuario u = null; // Se inicializa el objeto usuario como null

        try {
            
            
            // Consulta SQL para obtener un usuario por su ID
            String query = "select * from usuario where id = ?";
                
            // Se prepara la sentencia con la conexión activa
            PreparedStatement psmt = con.prepareStatement(query);
            
            // Se asigna el valor del parámetro ID
            psmt.setInt(1, id);
            
            // Se ejecuta la consulta
            ResultSet rs = psmt.executeQuery();
            
            // Si hay un resultado, se crea el objeto Usuario con los datos obtenidos
            if (rs.next()) { 
                u = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("contra"));
                System.out.println("Usuario encotrado.");
                //System.out.println();
                System.out.println("ID: " + u.getId());
                //System.out.println();
                System.out.println("Username: " +u.getUsername());
            }else{
                System.out.println("Usuario no encontrado.");
            }
            
            // Se cierran los recursos
            rs.close();
            psmt.close();

            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base datos. " + ex.getMessage());
        }
        
        return u; // Retorna el usuario encontrado (o null si no existe)
      
    }

    @Override
    public List<Usuario> readAll() {
        List<Usuario> usuarios = new ArrayList<>(); // Lista donde se almacenarán todos los usuarios
        
        try {
            // Consulta SQL para seleccionar todos los usuarios
            String query = "select * from usuario";
            PreparedStatement psmt = con.prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            
            // Recorre cada fila del resultado y crea un objeto Usuario
            while(rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                // Nota: aquí no se recupera la contraseña por seguridad
                
                usuarios.add(u); // Agrega el usuario a la lista
            }
            psmt.close();
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base datos. " + ex.getMessage());
        }
        
        return usuarios; // Retorna la lista de usuarios
    }

    @Override
    public void update(Usuario entity) {
        try {
            // Consulta SQL para actualizar un usuario según su ID
            String query = "update Usuario set id=?, username=? where id = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setInt(1, entity.getId());
            psmt.setString(2, entity.getUsername());
            psmt.setInt(3, entity.getId());
            psmt.executeUpdate(); // Ejecuta la actualización
            psmt.close();   // Cierra el statement
        } catch (SQLException ex) {
            System.out.println("No se pudo crear la consulta. " + ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        
        try {
            Usuario u = this.read(id);
            
            if(u == null){
                System.out.println("No se encontro el usuario con ID: " + id);
                return;
            }
            else{
                
                // Consulta SQL para eliminar un usuario por su ID
                 String query = "delete from Usuario where id = ?";
                 PreparedStatement psmt = con.prepareStatement(query);
                 psmt.setInt(1, id);   // Se establece el ID del usuario a eliminar
                 psmt.executeUpdate(); // Ejecuta la eliminación directamente
                 psmt.close();         // Cierra el statement 
                 System.out.println("Usuario Eliminado.");
            }

    } catch (SQLException ex) {
        System.out.println("Error al eliminar usuario: " + ex.getMessage());
    }
        
    }

    public Usuario readByUsername(String username) {
        Usuario u = null; // Se inicializa el objeto usuario como null
        
        try {
            // Consulta SQL para obtener un usuario por su nombre de usuario
            String query = "select * from Usuario where username = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, username); // Se establece el nombre de usuario como parámetro
            ResultSet rs = psmt.executeQuery();
            
            // Si encuentra un resultado, lo carga en un objeto Usuario
            while(rs.next()) {
                u = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("contra"));
                
            }
            psmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }
    
    // Getter para obtener la conexión a la base de datos
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
}
