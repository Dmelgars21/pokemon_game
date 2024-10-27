/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.EntrenadorDAO;
import org.grupo_games.pokemon_game.daos.PokedexDAO;
import org.grupo_games.pokemon_game.daos.PokemonDAO;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author xepux
 */
public class Menu {
    Scanner scanner = new Scanner(System.in);
    boolean salir = false;
    private Mochila mochila;
    private Entrenador session_entrenador;
    private Batalla batalla;

    public Menu(Entrenador entrenador) throws SQLException {
        this.session_entrenador = entrenador;
        this.mochila = new Mochila(entrenador.getId()); //inicializar mochila
    }

    public void mostrar_menu() throws SQLException {
        while (!salir) {

            System.out.println("-------AVENTURA POKEMON--------");
            System.out.println("--1. EXPLORAR---Buscar Pokemon");
            System.out.println("--2. Batalla");
            System.out.println("--3. Entrenar Pokemon");
            System.out.println("--4. Consultar Pokédex");
            System.out.println("--5. Gestionar Mochila");
            System.out.println("--6. Evolucionar Pokemon");
            System.out.println("--7. Salir");
            System.out.println("--ELIJA UNA OPCION");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {

                case 1:
                    System.out.println("--1. Explorando Pokemon");
                    break;
                case 2:
                    System.out.println("--2. Comenzando Batalla");
                    int entrenador_id2 = scanner.nextInt();
                    EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
                    entrenadorDAO.obtenerEntrenadorId(entrenador_id2);
                    Batalla batalla = new Batalla(session_entrenador, session_entrenador);
                    batalla.seleccionarPokemon(session_entrenador);
                    batalla.iniciarBatalla();
                    break;
                case 3:
                    System.out.println("--3. Ganar Experiencia, campo para entrenar...");
                    session_entrenador.entrenarPokemon();
                    break;
                case 4:
                    System.out.println("--4. Cosultando Pokedex");
                    PokedexDAO pokedex = new PokedexDAO();
                    pokedex.mostrarTodosLosPokemones();
                    break;
                case 5:
                    System.out.println("--5. Revisando Mochila");
                    mochila.gestionarMochila();
                    break;
                case 6:
                    System.out.println("--6. Evolucionar Pokemon");
                    Optional<Pokemon> pokemon_a_entrenar = session_entrenador.mostrarPokemonsDisponibles();
                    menuEvolucionarPokemon(pokemon_a_entrenar.get());
                    break;
                case 7:
                    System.out.println("--7. Saliendo");
                    salir = true;
                    break;
                default:
                    System.out.println("OPCION NO VALIDA.");

                    break;
            }
        }
        scanner.close();
    }

    public void menuEvolucionarPokemon(Pokemon pokemon) throws SQLException {
        System.out.println("¿Cómo deseas evolucionar a " + pokemon.getApodo() + "?");
        System.out.println("1. Por nivel");
        System.out.println("2. Por piedra evolutiva");
        System.out.println("3. Cancelar");

        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
//                poevolucionarPorNivel();
                break;
            case 2:
                pokemon.evolucionarPorPiedra();
                break;
            case 3:
                System.out.println("Evolución cancelada.");
                break;
            default:
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
                menuEvolucionarPokemon(pokemon);
                break;
        }
    }
}
