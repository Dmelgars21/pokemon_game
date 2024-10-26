package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Entrenador;
import org.grupo_games.pokemon_game.entities.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonDAO extends BaseDAO {
    public void crearPokemon(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO Pokemon (apodo, nivel, salud, entrenador_id, especie_id) VALUES (?, ?, ?)";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pokemon.getApodo());
            statement.setFloat(2, pokemon.getNivel());
            statement.setFloat(3, pokemon.getEntrenador().getId());
            statement.setInt(4, 0);
            statement.executeUpdate();
        }
    }

    public int obtenerEspecieId(String especie) throws SQLException {
        String sql = "SELECT id FROM Especie WHERE nombre = ?";
        int especieID = -1;

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, especie);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    especieID = resultSet.getInt("id");
                } else {
                    System.out.println("No se encontró la especie para " + especie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID de la especie: " + e.getMessage());
            throw e;
        }

        return especieID;
    }


    // Método para evolucionar un Pokémon
    public void evolucionarPokemon(Pokemon pokemon, String nuevaEspecie) throws SQLException {
        String sql = "UPDATE Pokemon SET especie_id = ? WHERE id = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int nuevaEspecieId = obtenerEspecieId(nuevaEspecie);
            statement.setInt(1, nuevaEspecieId);
            statement.setInt(2, pokemon.getId());
            statement.executeUpdate();
        }

        EvolucionDAO evolucionDAO = new EvolucionDAO();
        evolucionDAO.registrarEvolucion(pokemon, nuevaEspecie);
    }

    public void GuardarNivel(Pokemon pokemon) throws SQLException {
        String sql = "UPDATE RegistroDeCaptura SET nivel = ? WHERE pokemon_id = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Establecer los parámetros de la consulta
            statement.setFloat(1, pokemon.getNivel());
            statement.setInt(2, pokemon.getId());

            // Ejecutar la actualización
            int filasActualizadas = statement.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas

            if (filasActualizadas > 0) {
                System.out.println("Nivel del Pokémon actualizado correctamente.");
            } else {
                System.out.println("No se encontró el Pokémon con ID: " + pokemon.getId());
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el nivel del Pokémon: " + e.getMessage());
        }
    }


    public void ObtenerPokemonsAsociadosEntrenador(int entrenador_id, ArrayList<Pokemon> lista_pokemones) throws SQLException {
        String sql = "SELECT " +
                "   pk.id, " +
                "   pk.apodo, " +
                "   rdc.nivel, " +
                "   pk.salud, " +
                "   sp.nombre " +
                " FROM RegistroDeCaptura rdc " +
                " INNER JOIN Pokemon pk ON rdc.pokemon_id = pk.id " +
                " INNER JOIN Especie sp ON sp.id = pk.especie_id " +
                " WHERE rdc.entrenador_id = ? AND rdc.vinculado = 1";

        // Verificar que la lista no sea null
        if (lista_pokemones == null) {
            throw new IllegalArgumentException("La lista de Pokémon no puede ser nula.");
        }

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entrenador_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String apodo = resultSet.getString("apodo");
                    float nivel = resultSet.getFloat("nivel");
                    float salud = resultSet.getFloat("salud");
                    String especie = resultSet.getString("nombre");

                    // Crea un nuevo Pokémon con los datos del ResultSet
                    Pokemon pokemon = new Pokemon(id, apodo, especie, (int)nivel, salud);
                    lista_pokemones.add(pokemon); // Añade el Pokémon a la lista
                }
            }
        } catch (SQLException e) {
            // Propagar la excepción para que el llamador pueda manejarla
            throw e;
        }
    }



//    public String obtenerSiguienteEvolucion(String nombrePokemon) throws SQLException {
//        String sql = "INSERT INTO Pokemon (apodo, nivel, salud, entrenador_id, especie_id) VALUES (?, ?, ?)";
//        try (Connection connection = obtenerConexion();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, pokemon.getApodo());
//            statement.setInt(2, pokemon.getNivel());
//            statement.setFloat(3, pokemon.getEntrenador().getId());
//            statement.setInt(4, 0);
//            statement.executeUpdate();
//        }
//    }
}
