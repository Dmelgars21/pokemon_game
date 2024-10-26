package org.grupo_games.pokemon_game.entities;


import java.sql.SQLException;
import java.util.Random;
import org.grupo_games.pokemon_game.entities.*;
import org.grupo_games.pokemon_game.entities.Entrenador;

public class Batalla {
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private boolean finalizada;
    private Entrenador turnoActual;

    // Constructor que recibe dos entrenadores
    public Batalla(Entrenador entrenador1, Entrenador entrenador2) {
        this.entrenador1 = entrenador1;
        this.entrenador2 = entrenador2;
        this.turnoActual = entrenador1;  // El primer turno es del entrenador 1
        this.finalizada = false;
    }

    // Metodo para iniciar la batalla

    public void iniciarBatalla() {
        System.out.println("¡La batalla ha comenzado entre " + entrenador1.getNombre() + " y " + entrenador2.getNombre() + "!");

        // Selecciona el primer Pokémon de cada entrenador
        pokemon1 = seleccionarPokemon(entrenador1);
        pokemon2 = seleccionarPokemon(entrenador2);

        // Bucle de batalla, alterna turnos mientras la batalla no haya terminado
        while (!finalizada) {
            if (turnoActual == entrenador1) {
                turno(entrenador1, pokemon1, pokemon2);
                turnoActual = entrenador2;  // Cambiar turno al entrenador 2
            } else {
                turno(entrenador2, pokemon2, pokemon1);
                turnoActual = entrenador1;  // Cambiar turno al entrenador 1
            }
        }
    }

    // Metodo para seleccionar un Pokémon del entrenador (aquí se selecciona el primero de la lista)
    private Pokemon seleccionarPokemon(Entrenador entrenador) {
        System.out.println(entrenador.getNombre() + " selecciona su primer Pokémon.");
        return entrenador.getPokemonId(0);  // Puedes cambiar la lógica para seleccionar Pokémon
    }

    // Metodo que gestiona el turno de un entrenador
    private void turno(Entrenador entrenador, Pokemon pokemonActivo, Pokemon pokemonOponente) {
        System.out.println(entrenador.getNombre() + " toma su turno.");

        // Selecciona una habilidad del Pokémon de manera aleatoria
        Habilidad habilidadSeleccionada = seleccionarHabilidad(pokemonActivo);

        // Aplica la habilidad al Pokémon oponente
        aplicarHabilidad(pokemonActivo, habilidadSeleccionada, pokemonOponente);

        // Verifica si el Pokémon oponente ha sido derrotado
        if (pokemonOponente.getSalud() <= 0) {
            System.out.println(pokemonOponente.getApodo() + " ha sido derrotado!");
            finalizarBatalla(entrenador);
        }
    }

    // Metodo que selecciona una habilidad de forma aleatoria (esto se puede mejorar)
    private Habilidad seleccionarHabilidad(Pokemon pokemon) {
        Random rand = new Random();
        // Aquí asumimos que el Pokémon tiene una lista de habilidades, seleccionamos una aleatoria
        int indiceHabilidad = rand.nextInt(pokemon.getHabilidades().size());
        Habilidad habilidadSeleccionada = pokemon.getHabilidades().get(indiceHabilidad);
        System.out.println(pokemon.getApodo() + " usa " + habilidadSeleccionada.getNombre());
        return habilidadSeleccionada;
    }

    //Metodo que aplica la habilidad seleccionada al Pokémon oponente
    private void aplicarHabilidad(Pokemon atacante, Habilidad habilidad, Pokemon oponente) {
        // Aplica daño basado en el poder de la habilidad
        oponente.recibirDano(habilidad.getPoder());
        System.out.println(oponente.getApodo() + " ha recibido " + habilidad.getPoder() + " puntos de daño.");
    }

    // Metodo para finalizar la batalla cuando uno de los Pokémon es derrotado
    private void finalizarBatalla(Entrenador ganador) {
        System.out.println("La batalla ha terminado. ¡" + ganador.getNombre() + " es el ganador!");
        finalizada = true;  //Termina la batalla
    }
}
