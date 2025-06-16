/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.labintec.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avila
 */
public class DBConexion {
    private Connection connection;
    private static DBConexion instancia;

    private DBConexion() {
        createConnection();
    }
    
    public static DBConexion getInstancia(){
        
        if (instancia == null) {
            instancia = new DBConexion();
        }
        return instancia;
        
    }
    
    private void createConnection(){
        
        try {
            FileInputStream fis = new FileInputStream("src/main/java/dev/labintec/config/configuration.properties");
            Properties prop = new Properties();
            prop.load(fis);
            
            connection = DriverManager.getConnection(prop.getProperty("database"), prop);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado. " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo. " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("No se pudo conectar a la base de datos. " + ex.getMessage());
        }
       
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    // es necesario?????
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
