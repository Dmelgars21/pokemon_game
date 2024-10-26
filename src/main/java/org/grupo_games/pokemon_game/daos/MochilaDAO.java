
package org.grupo_games.pokemon_game.daos;
import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.db.DatabaseConnection;
import org.grupo_games.pokemon_game.entities.Mochila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MochilaDAO extends BaseDAO {

    public int crearMochilaBD(int entrenadorId, int limite) {
        int mochilaId = -1;
        String query = "INSERT INTO Mochila (entrenador_id, limite) VALUES (?, ?)";

        try (Connection connection = obtenerConexion();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, entrenadorId);
            ps.setInt(2, limite);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    mochilaId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la mochila en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return mochilaId;
    }


}
