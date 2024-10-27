package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokedexDAO extends BaseDAO {

    // Método para agregar un nuevo Pokémon a la base de datos


    // Método para actualizar un Pokémon existente


    // Método para eliminar un Pokémon por su ID

    public void mostrarTodosLosPokemones() throws SQLException {
        String sql = "SELECT es.nombre AS 'Especie', " +
                "tp.nombre AS 'Tipo' " +
                "FROM Especie es " +
                "INNER JOIN Tipo tp ON es.tipo_id = tp.id;";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Lista de todos los Pokémon:");

            while (resultSet.next()) {
                String nombre = resultSet.getString("Especie");
                String tipo = resultSet.getString("Tipo");

                // Muestra cada Pokémon en la consola
                System.out.println("----------------------------");
                System.out.println("Nombre: " + nombre + "\nTipo: " + tipo);
            }
        }
    }
    // Método para obtener todos los Pokémon de un Entrenador específico
}
