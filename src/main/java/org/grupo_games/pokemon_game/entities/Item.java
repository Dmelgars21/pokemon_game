/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.grupo_games.pokemon_game.entities;
import java.util.ArrayList;

/**
 *
 * @author xepux
 */
public class Item {
    private String nombre;
    private int tipo;

    public Item(String nombre, int tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }
    
    
    
}
