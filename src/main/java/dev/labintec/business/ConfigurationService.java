
import dev.labintec.business.IConfService;

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
public class ConfigurationService implements IConfService{
    
    // Referencia al repositorio que maneja la persistencia (lectura/escritura) en JSON
    //private IRepoTrans repo;

    // Constructor que recibe el repositorio como dependencia

    
    //Agrega un trámite utilizando el repositorio.
    @Override
    public void registrarTramite(Tramite tramite) {
//        List<Tramite> tramites = repo.obtenerTodos();
//
//        boolean existe = false;
//        for (Tramite t : tramites) {
//            if (t.getId() == tramite.getId()) {
//                existe = true;
//                break;
//            }
//        }
//
//        if (!existe) {
//            repo.agregar(tramite);
//            System.out.println("Trámite registrado correctamente.");
//        } else {
//            System.out.println("Ya existe un trámite con ID " + tramite.getId());
//        }
    }
    
    //Busca y devuelve un trámite según su ID.
    @Override
    public Tramite obtenerTramitePorId(int id) {
        Tramite t = new Tramite();
        return t;
    }
    
    //Cambia el estado de un trámite si existe y guarda la modificación en el archivo.
    @Override
    public void actualizarEstadoTramite(int id, String nuevoEstado) {
//        // Obtiene el tramite
//        Tramite tramite = repo.buscarPorId(id);
//        
//        // Busca el trámite y lo modifica si existe
//        if (tramite != null) {
//            tramite.setEstado(nuevoEstado);
//            repo.actualizarPorID(id, tramite);
//        }
    }

    @Override
    public List<Tramite> listarTramites() {
        List tramites = new ArrayList<>();
        return tramites;
    }
    
}
