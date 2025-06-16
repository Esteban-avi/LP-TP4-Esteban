package dev.labintec.business;


import dev.labintec.model.entities.Tramite;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avila
 */
public interface ITransTypeService {
    List<Tramite> getTramites();  // En lugar de tipos de transacción, devolvemos trámites
}
