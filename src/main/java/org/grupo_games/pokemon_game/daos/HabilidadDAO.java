//package org.grupo_games.pokemon_game.daos;
//
//import org.grupo_games.pokemon_game.db.BaseDAO;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HabilidadDAO extends BaseDAO {
//
//    // Metodo para insertar una nueva habilidad
//    public void insertarHabilidad(Habilidad habilidad) throws SQLException {
//        String query = "INSERT INTO habilidad (nombre, tipo, poder) VALUES (?, ?, ?)";
//        try (Connection connection = obtenerConexion();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, habilidad.getNombre());
//            statement.setString(2, habilidad.getTipo());
//            statement.setInt(3, habilidad.getPoder());
//            statement.executeUpdate();
//        }
//    }
//
//    // Metodo para obtener una habilidad por su ID
//    public Habilidad obtenerHabilidadPorId(int id) throws SQLException {
//        String query = "SELECT * FROM habilidad WHERE id = ?";
//        try (Connection connection = obtenerConexion();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, id);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    String nombre = resultSet.getString("nombre");
//                    String tipo = resultSet.getString("tipo");
//                    int poder = resultSet.getInt("poder");
//                    return new Habilidad(nombre, tipo, poder);
//                }
//            }
//        }
//        return null;  // Si no se encuentra la habilidad
//    }
//
//    // Metodo para obtener todas las habilidades
//    public List<Habilidad> obtenerTodasLasHabilidades() throws SQLException {
//        List<Habilidad> habilidades = new ArrayList<>();
//        String query = "SELECT * FROM habilidad";
//        try (Connection connection = obtenerConexion();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//            while (resultSet.next()) {
//                String nombre = resultSet.getString("nombre");
//                String tipo = resultSet.getString("tipo");
//                int poder = resultSet.getInt("poder");
//                Habilidad habilidad = new Habilidad(nombre, tipo, poder);
//                habilidades.add(habilidad);
//            }
//        }
//        return habilidades;
//    }
//
//    // Metodo para actualizar una habilidad existente
//    public void actualizarHabilidad(Habilidad habilidad, int id) throws SQLException {
//        String query = "UPDATE habilidad SET nombre = ?, tipo = ?, poder = ? WHERE id = ?";
//        try (Connection connection = obtenerConexion();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, habilidad.getNombre());
//            statement.setString(2, habilidad.getTipo());
//            statement.setInt(3, habilidad.getPoder());
//            statement.setInt(4, id);
//            statement.executeUpdate();
//        }
//    }
//
//    // Metodo para eliminar una habilidad por su ID
//    public void eliminarHabilidad(int id) throws SQLException {
//        String query = "DELETE FROM habilidad WHERE id = ?";
//        try (Connection connection = obtenerConexion();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        }
//    }
//}
