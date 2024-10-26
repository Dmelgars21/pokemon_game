package org.grupo_games;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.grupo_games.pokemon_game.daos.EntrenadorDAO;
import org.grupo_games.pokemon_game.daos.PiedraEvolutivaDAO;
import org.grupo_games.pokemon_game.daos.PokedexDAO;
import org.grupo_games.pokemon_game.daos.UserDAO;
import org.grupo_games.pokemon_game.entities.*;
//import org.grupo_games.pokemon_game.db.Db_local;


public class Main {
    private static ArrayList<PiedraEvolutiva> piedras_evolutivas = new ArrayList<PiedraEvolutiva>();

    public static void main(String[] args) throws SQLException {
        PiedraEvolutivaManager manager = PiedraEvolutivaManager.getInstance();
        ArrayList<PiedraEvolutiva> piedras = manager.getPiedrasEvolutivas();

        Scanner scanner = new Scanner(System.in);

        //EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        PokedexDAO pokedexDAO = new PokedexDAO();
        //Db_local db = new Db_local();
        //Connection conexion = db.getConexion();
        int opcion;
        do {
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

        } while (opcion != 0);
    }

    public static void iniciarSesion(Scanner scanner) throws SQLException {

        System.out.println("Ingrese su nombre de usuario:");
        String username = scanner.nextLine();

        System.out.println("Ingrese su contraseña de usuario:");
        String passw = scanner.nextLine();

        UserDAO usuarioDAO = new UserDAO();
        Entrenador entrenador = usuarioDAO.validarUsuario(username, passw);
        entrenador.ObtenerPokemonesCapturador();
        if (entrenador != null) {
            System.out.println("Bienvenido, " + entrenador.getNombre() + "!");
            // iniciar la navegación en el juego
            Menu menu = new Menu(entrenador);
            menu.mostrar_menu();
        } else {
            System.out.println("Usuario no encontrado. Intente nuevamente.");
        }


    }

    public static void crearUsuario(Scanner scanner) {
        UserDAO userDAO = new UserDAO();

        System.out.println("Ingrese un nombre de usuario:");
        String username = scanner.nextLine();

        System.out.println("Ingrese una contraseña:");
        String password = scanner.nextLine();


        User user = new User(username, password);

        try {
            boolean usuarioCreado = userDAO.crearUsuario(user);
            if (usuarioCreado) {

                System.out.println("Usuaario credo exitosamente.");
                int usuarioId = userDAO.getId(user);
                // Crear un entrenador por defecto (ASH)
                EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
                entrenadorDAO.crearEntrenadorPorDefecto(username, usuarioId); // Pasar userId
                System.out.println("Entrenador por defecto creado (ASH).");

                System.out.println("Entrenador por defecto creado (ASH).");
            } else {
                System.out.println("Error al crear el usuario. El nombre de usuario puede estar en uso.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear el usuario. Intente de nuevo.");
        }
    }
}
