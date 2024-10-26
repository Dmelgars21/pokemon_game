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
public class Pokedex {
        private ArrayList<Pokemon> pokedex;

    public Pokedex() {
        this.pokedex = new ArrayList<>();
    }
    
    
    public void registrar_pokemon(Pokemon pokemon){
        pokedex.add(pokemon);
        System.out.println(pokemon.getEspecie()+ "ha sido registrado en la Pokedex");
    
    
    }
    public void mostrar_pokemon(){
        
        System.out.println("------POKEDEX----------");
        for(Pokemon pokemon: pokedex){
            System.out.println(pokemon.getEspecie()+ "Nivel: "+pokemon.getNivel()+".");
                
        }
    
    
    }
        
    
    
}
