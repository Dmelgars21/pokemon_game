
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
    
    public int crearMochilaBD(int entrenadorId, int limite) throws SQLException {
        int mochilaId = -1;
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemon", "user", "password");
        String query = "INSERT INTO Mochila (entrenador_id, limite) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, entrenadorId);
        ps.setInt(2, limite);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            mochilaId = rs.getInt(1);
        }
        conn.close();
        return mochilaId;
    }
    
    
    
    
}
