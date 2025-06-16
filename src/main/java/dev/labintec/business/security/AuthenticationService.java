/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.labintec.business.security;

import dev.labintec.model.IRepo;

import dev.labintec.model.RepositoryUser;
import dev.labintec.model.entities.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP Pavilion
 */
public class AuthenticationService implements IAuthService {
    //private IRepoUser repoUser; //Dependencia al repositorio de usuarios.
    private RepositoryUser repo;

    public AuthenticationService() {
    }
    
    
    public AuthenticationService(RepositoryUser repo) {
        this.repo = repo;
    }

    @Override
    public long signin(String username, String password) {
        Usuario user = null;

        try {
            String query = "SELECT * FROM Usuario WHERE username = ? AND contra = ?";
            PreparedStatement ps = repo.getCon().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // Si hay resultados, crea el usuario
                user = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("contra"));
                return user.getId();
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error en el inicio de sesión: " + ex.getMessage());
        }

        return -1; // Devuelve ID si encuentra el usuar
    }
}
