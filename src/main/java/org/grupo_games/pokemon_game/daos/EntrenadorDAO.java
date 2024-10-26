package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Entrenador;
import org.grupo_games.pokemon_game.entities.Pokemon;
import org.grupo_games.pokemon_game.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntrenadorDAO extends BaseDAO {
    public void crearEntrenadorPorDefecto(String username, int usuarioId) throws SQLException {
        String sql = "INSERT INTO Entrenador (nombre, pueblo_origen, usuario_id) VALUES (?, ?, ?)";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "ASH");
            statement.setString(2, "Pueblo Paleta");
            statement.setInt(3, usuarioId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Entrenador obtenerEntrenadorId(int userId) throws SQLException {
        String sql = "SELECT e.id, e.nombre, e.pueblo_origen, u.username, u.password " +
                "FROM Entrenador e " +
                "JOIN Usuario u ON e.usuario_id = u.id " +
                "WHERE e.usuario_id = ?";

        Entrenador entrenador = null;

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            // Si hay un resultado, crea el objeto Entrenador
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String puebloOrigen = resultSet.getString("pueblo_origen");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                // Crea el objeto User con el ID y los datos obtenidos
                User user = new User(userId, username, password);
                entrenador = new Entrenador(id, nombre, puebloOrigen, user);
            }
        }

        return entrenador; // Devuelve null si no se encuentra
    }

    public boolean CapturarPokemon(Entrenador entrenador, Pokemon pokemon) throws SQLException {
        String sqlInsert = "INSERT INTO RegistroDeCaptura (entrenador_id, pokemon_id, vinculado) VALUES (?, ?, 1);";
        boolean capturaExitosa = false; // Inicializa como false

        try (Connection connection = obtenerConexion();
             PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {

            // Obtener los IDs del entrenador y el Pokémon
            int entrenadorId = entrenador.getId();
            int pokemonId = pokemon.getId();

            insertStatement.setInt(1, entrenadorId);
            insertStatement.setInt(2, pokemonId);

            int filasAfectadas = insertStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Captura registrada exitosamente.");
                capturaExitosa = true; // La captura fue exitosa
            } else {
                System.out.println("No se pudo registrar la captura.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return capturaExitosa; // Retorna el resultado
    }


}