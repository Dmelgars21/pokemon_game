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
        String sql = "SELECT * FROM Pokemon";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Lista de todos los Pokémon:");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String apodo = resultSet.getString("apodo");
                int nivel = resultSet.getInt("nivel");
                int salud = resultSet.getInt("salud");

                // Muestra cada Pokémon en la consola
                System.out.println("----------------------------");
                System.out.println("ID: " + id + "\nNombre: " + apodo + "\nNivel: " + nivel + "\nSalud: " + salud);
            }
        }
    }
    // Método para obtener todos los Pokémon de un Entrenador específico
}
