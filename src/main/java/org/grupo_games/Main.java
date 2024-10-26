package org.grupo_games;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;

import org.grupo_games.pokemon_game.daos.EntrenadorDAO;
import org.grupo_games.pokemon_game.daos.PokedexDAO;
import org.grupo_games.pokemon_game.daos.UserDAO;
import org.grupo_games.pokemon_game.entities.Pokemon;
import org.grupo_games.pokemon_game.entities.User;
import org.grupo_games.pokemon_game.entities.Entrenador;
//import org.grupo_games.pokemon_game.db.Db_local;
import org.grupo_games.pokemon_game.daos.Menu;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        //UserDAO userDAO = new UserDAO();
        //EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        PokedexDAO pokedexDAO = new PokedexDAO();
        //Db_local db = new Db_local();
        //Connection conexion = db.getConexion();
        int opcion;
        do{
            System.out.println("Bienvenido al juego Pokémon");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear usuario");
            System.out.println("3. Salir");
            System.out.println("--ELEGIR OPCION---");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    iniciarSesion(scanner);
                    break;
                case 2:
                    crearUsuario(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
        }        
        
        }while (opcion != 0);
    }

    public static void iniciarSesion(Scanner scanner){
    
            System.out.println("Ingrese su nombre de usuario:");
            String username = scanner.nextLine();
            UserDAO usuarioDAO = new UserDAO();
            Entrenador entrenador = usuarioDAO.validarUsuario(username);
            if (entrenador != null) {
            System.out.println("Bienvenido, " + entrenador.getNombre() + "!");
            // iniciar la navegación en el juego
        } else {
            System.out.println("Usuario no encontrado. Intente nuevamente.");
        }
            
    
    }
    public static void crearUsuario(Scanner scanner) {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();

        
        UserDAO usuarioDAO = new UserDAO();
        
        try{
            boolean usuarioCreado = usuarioDAO.crearUsuario(username);
            if (usuarioCreado) {
            
            System.out.println("Usuario creado exitosamente.");
            int usuarioId=usuarioDAO.getId(username);
            // Crear un entrenador por defecto (ASH)
            EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
            entrenadorDAO.crearEntrenadorPorDefecto(username, usuarioId); // Pasar userId
            System.out.println("Entrenador por defecto creado (ASH).");
            
            System.out.println("Entrenador por defecto creado (ASH).");
        } else {
            System.out.println("Error al crear el usuario. El nombre de usuario puede estar en uso.");
        }
        
        }catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al crear el usuario. Intente de nuevo.");
    }
        
        
        

        
        

       

        
    }
}
