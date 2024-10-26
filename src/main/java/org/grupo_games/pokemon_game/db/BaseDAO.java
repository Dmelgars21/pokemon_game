package org.grupo_games.pokemon_game.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class BaseDAO {
    private String url;
    private String user;
    private String password;

    public BaseDAO() {
        cargarConfiguracion();
    }

    private void cargarConfiguracion() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config/db.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo db.properties");
                return;
            }
            // Cargar las propiedades desde el archivo
            properties.load(input);
            this.url = properties.getProperty("db.url");
            this.user = properties.getProperty("db.user");
            this.password = properties.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/pokemon_game", "root", "2103");
    }
}
