package org.grupo_games.pokemon_game.entities;


public class User {
    private int id;
    private String username;
    private String password;
    private Entrenador entrenador;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }
}

