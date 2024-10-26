package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.PiedraEvolutivaDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PiedraEvolutivaManager {
    private static PiedraEvolutivaManager instance;
    private ArrayList<PiedraEvolutiva> piedrasEvolutivas;

    private PiedraEvolutivaManager() {
        piedrasEvolutivas = new ArrayList<>();
        cargarPiedrasEvolutivas();
    }

    public static PiedraEvolutivaManager getInstance() {
        if (instance == null) {
            instance = new PiedraEvolutivaManager();
        }
        return instance;
    }

    private void cargarPiedrasEvolutivas() {
        PiedraEvolutivaDAO daoPiedraEvolutiva = new PiedraEvolutivaDAO();
        daoPiedraEvolutiva.obtenerPiedrasEvolutivas(piedrasEvolutivas);
    }

    public ArrayList<PiedraEvolutiva> getPiedrasEvolutivas() {
        return piedrasEvolutivas;
    }
}
