/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.labintec.business.security;

import dev.labintec.model.IRepo;

import dev.labintec.model.RepositoryUser;
import dev.labintec.model.entities.Usuario;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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
        
        try {
            TypedQuery<Usuario> query = repo.getE().createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            Usuario user = query.getSingleResult();
            
            return user.getId();  // Devuelve el ID si encuentra el usuario

        } catch (NoResultException ex) {
            System.out.println("Usuario o contraseña incorrectos.");
        } catch (Exception ex) {
            System.out.println("Error en el inicio de sesión: " + ex.getMessage());
        }

        return -1; // Si no encuentra nada o hay error
}

}
