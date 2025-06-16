package dev.labintec.business;


import dev.labintec.business.ITransTypeService;

import dev.labintec.model.entities.Tramite;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author avila
 */
public class TransactionTypeService implements ITransTypeService{
    //private IRepoTrans repo;


    @Override
    public List<Tramite> getTramites() {
        List tramites = new ArrayList<>();
        return tramites;  // Devuelve la lista de trámites desde el repositorio
    }
}
