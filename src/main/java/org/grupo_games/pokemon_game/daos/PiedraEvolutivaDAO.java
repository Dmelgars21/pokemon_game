package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.PiedraEvolutiva;
import org.grupo_games.pokemon_game.entities.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PiedraEvolutivaDAO extends BaseDAO {

    public void obtenerPiedrasEvolutivas(ArrayList<PiedraEvolutiva> piedrasEvolutivas) {
        String sql = "SELECT id, nombre FROM PiedraEvolutiva;";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                piedrasEvolutivas.add(new PiedraEvolutiva(id, nombre));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las piedras evolutivas: " + e.getMessage());
        }
    }

    public int obtenerPiedraIdSegunNombre(String nombrePiedra) {
        String sql = "SELECT id FROM PiedraEvolutiva WHERE nombre = ?;";

        int piedraId = -1;

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombrePiedra);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    piedraId = resultSet.getInt("id");
                } else {
                    System.out.println("No se encontró la piedra con el nombre: " + nombrePiedra);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el ID de la piedra: " + e.getMessage());
        }

        return piedraId;
    }


    public ArrayList<String> obtenerTipoPokemonSegunNombrePiedra(String nombrePiedra) {
        String sql = "SELECT PE.id, PE.tipo_id, PE.nombre " +
                "FROM PiedraEvolutiva PE " +
                "INNER JOIN Tipo tp ON PE.tipo_id = tp.id " +
                "INNER JOIN Especie E ON tp.id = E.tipo_id " +
                "WHERE PE.nombre = ?;";

        ArrayList<String> nombresPiedra = new ArrayList<>();

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombrePiedra);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    nombresPiedra.add(nombre);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nombresPiedra;
    }

    public String obtenerPiedraSegunPokemon(String nombre_pokemon) {
        String sql = "SELECT pe.nombre as 'Piedra' " +
                "FROM Pokemon pk " +
                "INNER JOIN Especie es ON es.id = pk.especie_id " +
                "INNER JOIN Tipo tp ON es.tipo_id = tp.id " +
                "INNER JOIN PiedraEvolutiva pe ON pe.tipo_id = tp.id " +
                "WHERE pk.apodo = ?;";

        String nombrePiedra = null;

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombre_pokemon);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nombrePiedra = resultSet.getString("Piedra");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nombrePiedra; // Devuelve el nombre de la piedra
    }
}
