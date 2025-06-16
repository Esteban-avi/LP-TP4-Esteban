/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.labintec.model;

import java.util.List;

/**
 *
 * @author avila
 */
public interface IRepo<T> {
    void create(T entity); //AGREGAR
    T read(int id);       //LEER POR ID
    List<T> readAll();     //LEER TODOS
    void update(T entity); //ACTUALIZAR
    void delete(int id);  //ELIMINAR
}
