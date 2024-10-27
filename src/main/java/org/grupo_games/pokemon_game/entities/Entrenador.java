package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.PokemonDAO;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Entrenador {
    private int id;
    private String nombre;
     String pueblo_origen;
    private User user;
    private ArrayList<Pokemon> pokemones;
    private Mochila mochila;
    private Scanner scanner;

    public Entrenador(int id, String nombre, String pueblo_origen, User user) throws SQLException {
        this.id = id;
        this.nombre = nombre;
        this.pueblo_origen = pueblo_origen;
        this.user = user;
        this.pokemones = new ArrayList<>();
        this.mochila = new Mochila(user.getId());//MOCHILA CON limite de 10 objetos
        this.scanner = new Scanner(System.in);
    }

    public Entrenador(String nombre, String pueblo_origen, User user) throws SQLException {
        this.nombre = nombre;
        this.pueblo_origen = pueblo_origen;
        this.user = user;
        this.pokemones = new ArrayList<>();
        this.mochila = new Mochila(user.getId());//MOCHILA CON limite de 10 objetos
        this.scanner = new Scanner(System.in);
    }


    public String getNombre() {
        return nombre;
    }

    public String getPuebloOrigen() {
        return pueblo_origen;

    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return user.getId();
    }

    public Pokemon getPokemonId(int id) {
        return this.pokemones.get(id);
    }

    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public void setPuebloOrigen(String pueblo_origen) {
        this.pueblo_origen = pueblo_origen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void agregarNuevoPokemon(Pokemon pokemon) {
        pokemones.add(pokemon);

        System.out.println("Se ha agregado un nuevo pokemon " + pokemon.getApodo());
    }

    public Optional<Pokemon> mostrarPokemonsDisponibles() {
        if (pokemones.isEmpty()) {
            System.out.println("No tiene pokemones disponibles, ve y captura algunos!!");
            return Optional.empty(); // Retornar un Optional vacío
        }

        System.out.println("---------------- POKEMONES ---------------------\n");
        pokemones.forEach(pokemon -> {
            System.out.println("ID del pokemon: " + pokemon.getId() + ", Nivel de pokemon: " + pokemon.getNivel() + " PTS");
        });

        System.out.println("Ingresa el ID del pokemon que quieres entrenar");
        int pokemon_id;

        // Manejo de entrada inválida
        while (true) {
            if (scanner.hasNextInt()) {
                pokemon_id = scanner.nextInt();
                break; // Salir del bucle si se recibe un entero
            } else {
                System.out.println("Por favor, ingresa un ID válido.");
                scanner.next(); // Limpiar la entrada incorrecta
            }
        }

        // Filtrar el Pokémon seleccionado
        Optional<Pokemon> pokemon_a_entrenar = pokemones.stream()
                .filter(pokemon -> pokemon.getId() == pokemon_id)
                .findFirst();

        return pokemon_a_entrenar;
    }

    public void entrenarPokemon() throws SQLException {
        Optional<Pokemon> pokemon_a_entrenar = mostrarPokemonsDisponibles();

        if (pokemon_a_entrenar.isPresent()) {
            System.out.println("Pokémon seleccionado: " + pokemon_a_entrenar.get().getApodo());
        } else {
            System.out.println("Pokémon no encontrado.");
            return;
        }

        boolean seguir_entrenando = true;
        while (seguir_entrenando) {
            System.out.println("Entrenando Pokémon...");
            float nivel_actual = pokemon_a_entrenar.get().getNivel();

            // Simular el aumento de nivel (puedes personalizar cómo aumenta el nivel)
            pokemon_a_entrenar.get().setNivel(nivel_actual + 1);

            // Verificar si el nivel alcanzó un múltiplo de 5
            if ((int) pokemon_a_entrenar.get().getNivel() % 5 == 0) {
                System.out.println("El Pokémon ha alcanzado el nivel " + pokemon_a_entrenar.get().getNivel() + ".");
                System.out.println("¿Quieres seguir entrenando? (s/n)");
                String respuesta = scanner.next();
                seguir_entrenando = respuesta.equalsIgnoreCase("s");
            }
        }

        System.out.println("Entrenamiento finalizado para " + pokemon_a_entrenar.get().getApodo());
        pokemon_a_entrenar.get().actualizarNivelDB();
    }


    public void ObtenerPokemonesCapturador() throws SQLException {
        PokemonDAO pokemonDAO = new PokemonDAO();
        System.out.println("MI ID ES: " + this.id);
        this.pokemones = new ArrayList<>();
        pokemonDAO.ObtenerPokemonsAsociadosEntrenador(this.id, this.pokemones);
    }
}
