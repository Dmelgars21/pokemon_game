package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.EvolucionDAO;
import org.grupo_games.pokemon_game.daos.PiedraEvolutivaDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PiedraEvolutiva extends Objeto {
    private int id;
    private ArrayList<String> tipoPokemon;

    public PiedraEvolutiva(int id, String nombre) {
        super(nombre, "");
        this.id = id;
        tipoPokemon = new ArrayList<>();

        PiedraEvolutivaDAO piedraEvolutivaDAO = new PiedraEvolutivaDAO();
        this.id = piedraEvolutivaDAO.obtenerPiedraIdSegunNombre(nombre);
        this.tipoPokemon = piedraEvolutivaDAO.obtenerTipoPokemonSegunNombrePiedra(nombre);
    }

//    public PiedraEvolutiva(String nombre, String descripcion) {
//        super(nombre, descripcion);
//        tipoPokemon = new ArrayList<>();
//
//        PiedraEvolutivaDAO piedraEvolutivaDAO = new PiedraEvolutivaDAO();
//        this.id = piedraEvolutivaDAO.obtenerPiedraIdSegunNombre(nombre);
//        this.tipoPokemon = piedraEvolutivaDAO.obtenerTipoPokemonSegunNombrePiedra(nombre);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getTipoPokemon() {
        return tipoPokemon;
    }

    public void setTipoPokemon(String tipoPokemon) {
        this.tipoPokemon.add(tipoPokemon);
    }

    // Método para usar la piedra y hacer que un Pokémon evolucione
    public void usar() {
        System.out.println("Usando " + getNombre() + " para evolucionar un Pokémon.");
    }

    private boolean pokemonPuedeEvolucionar(Pokemon pokemon) {
        EvolucionDAO evolucionDAO = new EvolucionDAO();

        ArrayList<String> evoluciones = evolucionDAO.obtenerSiguientesEvoluciones(pokemon.getEspecie());
        if (evoluciones.contains(pokemon.getEspecie())) {
            return true;
        }
        return false;
    }
}
