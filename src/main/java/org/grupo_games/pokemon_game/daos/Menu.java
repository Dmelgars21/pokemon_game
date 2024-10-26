/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.grupo_games.pokemon_game.daos;
import org.grupo_games.pokemon_game.entities.Mochila;
import org.grupo_games.pokemon_game.entities.Item;

import java.util.Scanner;

/**
 *
 * @author xepux
 */
public class Menu {
    Scanner scanner = new Scanner(System.in);
    boolean salir =false;
    private Mochila mochila;
    
    public Menu(int entrenadorId){
        this.mochila = new Mochila(entrenadorId);//inicializar mochila
    
    }
    public void mostrar_menu(){
       while(!salir){
            
           
             System.out.println("-------AVENTURA POKEMON--------");
             System.out.println("--1. EXPLORAR---Buscar Pokemon");
             System.out.println("--2. Batalla");
             System.out.println("--3. Ganar Experiencia");
             System.out.println("--4. Consultar Pokédex");
             System.out.println("--5. Gestionar Mochila");
             System.out.println("--6. Salir");
             System.out.println("--ELIJA UNA OPCION");
             int opcion = scanner.nextInt();
             scanner.nextLine();
             switch(opcion){
           
                case 1:
                  System.out.println("--1. Explorando Pokemon");
                break;
                case 2:
                  System.out.println("--2. Comenzando Batalla");
                break;
                case 3:
                  System.out.println("--3. Ganar Experiencia, campo para entrenar...");
                break;
                case 4:
                  System.out.println("--4. Cosultando Pokedex");
                break;
                case 5:
                  System.out.println("--5. Revisando Mochila");
                  mochila.gestionarMochila();
                 
                break;
                case 6:
                  System.out.println("--6. Saliendo");
                  salir=true;
                break;
                default:
                   System.out.println("OPCION NO VALIDA.");
                   
                break;
           
           
           
           
           

       }
       


    }
       scanner.close();
    
    
    
    
    }

    
    
}
