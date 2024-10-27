package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Habilidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HabilidadDAO extends BaseDAO {

    // Método para agregar una nueva habilidad a la base de datos


    // Método para actualizar una habilidad existente


    // Método para eliminar una habilidad por su ID


    // Método para mostrar todas las habilidades con sus tipos
    public void mostrarTodasLasHabilidades() throws SQLException {
        String sql = "SELECT h.nombre AS 'Habilidad', " +
                "th.nombre AS 'Tipo' " +
                "FROM Habilidad h " +
                "INNER JOIN TipoHabilidad th ON h.tipo_habilidad_id = th.id;";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Lista de todas las habilidades:");

            while (resultSet.next()) {
                String nombreHabilidad = resultSet.getString("Habilidad");
                String tipoHabilidad = resultSet.getString("Tipo");

                // Muestra cada habilidad en la consola
                System.out.println("----------------------------");
                System.out.println("Habilidad: " + nombreHabilidad);
                System.out.println("Tipo: " + tipoHabilidad);
            }
        }
    }
}


