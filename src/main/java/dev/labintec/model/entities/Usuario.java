/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.labintec.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author HP Pavilion
 */
@Entity
@Table(name="Usuario")

public class Usuario { //Se convierte en una entidad JPA
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   
   @Column(name="username") // se usa esta anotación cuando difieren los nombres 
   private String username;
   
   @Column(name="contra")
   private String password;

    public Usuario() {
    }

    public Usuario(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId(){
     return this.id;
    }
    public void setId(int id){
     this.id=id;
    }

   public String getUsername(){
      return this.username;
   }
   public void setUsername(String username){
      this.username= username;
   }

   public String getPassword(){
      return this.password;
   }
   public void setPassword(String password){
      this.password=password;
   }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }
   
   
}
