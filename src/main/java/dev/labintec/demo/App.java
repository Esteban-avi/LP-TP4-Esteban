/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package dev.labintec.demo;

import dev.labintec.business.security.AuthenticationService;
import dev.labintec.business.security.IAuthService;
import dev.labintec.database.DBConexion;
import dev.labintec.model.RepositoryTransaction;
import dev.labintec.model.RepositoryUser;
import dev.labintec.model.entities.Tramite;
import dev.labintec.model.entities.Usuario;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author avila
=======

/**
 *
 * @author HP Pavilion
>>>>>>> origin/Guevara
 */

public class App {

    public static void main(String[] args) {
        
        // Conexión a la base de datos
        DBConexion cone = DBConexion.getInstancia();
        Connection c = cone.getConnection();
        System.out.println("Conexion: " + c);
        
        // Repositorio
        RepositoryUser CRUDuser = new RepositoryUser(c);
        int opcion;
        boolean salir = false;
        Scanner scanner = new Scanner(System.in);
        
        while(salir == false) {
        
        System.out.println("\n------MENU DE USUARIOS------");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar todos los usuarios");
        System.out.println("3. Buscar usuario por ID");
        System.out.println("4. Buscar usuario por username");
        System.out.println("5. Modificar username de un usuario");
        System.out.println("6. Eliminar usuario por id");
        System.out.println("7. Iniciar sesion (login)");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
        
        
        
        opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        
        switch (opcion) {
            case 1:
                System.out.print("Ingrese username: ");
                String username = scanner.nextLine();
                System.out.print("Ingrese contraseña: ");
                String contra = scanner.nextLine();
                Usuario nuevo = new Usuario(username, contra);
                CRUDuser.create(nuevo);
                break;



            case 2:
               List<Usuario> usuarios = CRUDuser.readAll();
               
               for (Usuario u : usuarios) {
                   System.out.println(u);
               }
               break;
            
            case 3:
                System.out.print("Ingrese ID a buscar: ");
                int idBuscar = scanner.nextInt();
                Usuario usuarioID = CRUDuser.read(idBuscar);
               break;

               
            case 4:
                System.out.print("Ingrese username a buscar: ");
                String buscarUsername = scanner.nextLine();
                Usuario porUsername = CRUDuser.readByUsername(buscarUsername);
                if (porUsername != null) {
                  System.out.println("Encontrado: " + porUsername);
                } else {
                  System.out.println("Usuario no encontrado.");
                }
                break;
                
            case 5:
                System.out.print("Ingrese ID del usuario a modificar: ");
                int idModificar = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                Usuario usuarioModificar = CRUDuser.read(idModificar);
                if (usuarioModificar != null) {
                  System.out.print("Nuevo username: ");
                  String nuevoUsername = scanner.nextLine();
                  usuarioModificar.setUsername(nuevoUsername);
                  CRUDuser.update(usuarioModificar);
                  System.out.println("Usuario modificado.");
                } else {
                  System.out.println("Usuario no encontrado.");
                }
                break;
                
            case 6:
                System.out.print("Ingrese ID del usuario a eliminar: ");
                int idEliminar = scanner.nextInt();
                CRUDuser.delete(idEliminar);
                break;
                
            case 7:
                AuthenticationService au = new AuthenticationService(CRUDuser);
                System.out.print("Username: ");
                String loginUser = scanner.nextLine();
                System.out.print("Contraseña: ");
                String loginPass = scanner.nextLine();
                long userid = au.signin(loginUser, loginPass);
                if (userid != -1) {
                   System.out.println("Inicio de sesion exitoso. ID: " + userid);
                } else {
                    System.out.println("Credenciales incorrectas.");
                }
                break;
                
            case 0:
                salir = true;
                System.out.println("Saliendo del programa.");
                break;
                
            default:
                System.out.println("Opcion invalida.");
        }
        } 
        scanner.close();
        System.out.println("");
        
//        // Crear usuarios
//        Usuario user1 = new Usuario("esteban", "1234");
//        Usuario user2 = new Usuario("mica", "4321");
//        
//        CRUDuser.create(user1);
//        CRUDuser.create(user2);
//        
//        List<Usuario> usuarios = CRUDuser.readAll();
//        
//        for (Usuario u : usuarios) {
//            System.out.println(u);
//        }
//        
//        user1 = CRUDuser.readByUsername(user1.getUsername());
//        System.out.println(user1);
//        
//        user2 = CRUDuser.read(user2.getId());
//        System.out.println(user2);
//        
//        CRUDuser.delete(user2.getId());
//        
        

        
//        AuthenticationService au = new AuthenticationService(CRUDuser);
//        long userid = au.signin(user1.getUsername(), user1.getPassword());
//        System.out.println("ID del usuario: " + userid);
        
//        RepositoryTransaction CRUDtramite = new RepositoryTransaction(c);
//        Tramite t1 = new Tramite("Constancia de alumno regular", "Aprobado", "Tramite para alumno");
//        Tramite t2 = new Tramite("Inscripcion a mesas de examen", "Rechazado", "Tramite para alumno");
//        
//        CRUDtramite.create(t1);
//        CRUDtramite.create(t2);
//        
//        List<Tramite> tramites = CRUDtramite.readAll();
//        for (Tramite t : tramites){
//            System.out.println(t);
//        }
        
    }
}
