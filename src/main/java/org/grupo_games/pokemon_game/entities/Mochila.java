package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.MochilaDAO;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Mochila {
    //private String nombre;
    private ArrayList<Item>items;
    private int limite;
    private int mochilaId;

    public Mochila(int entrenadorId) {
        //this.nombre = nombre;
        this.items = new ArrayList<>();
        this.limite = 10;


        MochilaDAO mochiladb = new MochilaDAO();
        this.mochilaId = mochiladb.crearMochilaBD(entrenadorId, limite);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
   
    public void agregarItem(Item item) throws SQLException{
        if(items.size()<limite){
            items.add(item);
            System.out.println("Has añadido un "+ item.getNombre()+ " a la mochila!");
        
        
        }
        else{
            System.out.println("La mochila está llena!");
        
        }
    
    }
    public void usarItem(String nombreItem){
        for(Item item: items){
            
            if(item.getNombre().equals(nombreItem)){
            items.remove(item);
            System.out.println("Ha usado "+ nombreItem);
            return;
        
            }
        
        }
        System.out.println(nombreItem+ "  no está en tu mochila");
    
    }
     public void gestionarMochila() {
         Scanner scanner = new Scanner(System.in);
        System.out.println("-------GESTIÓN DE MOCHILA--------");
        System.out.println("1. Ver items en la mochila");
        System.out.println("2. Agregar item a la mochila");
        System.out.println("3. Usar item de la mochila");
        System.out.println("4. Volver al menú principal");

        int opcionMochila = scanner.nextInt();
        scanner.nextLine();

        switch (opcionMochila) {
            case 1:
                 System.out.println("Viendo ITEMS de mochila...");
                for (Item item : items) {
                    System.out.println("- " + item.getNombre() + " (Tipo: " + item.getTipo() + ")");
                }
                break;
            case 2:
                System.out.println("Agregando ITEMS de mochila...");
                System.out.println("Ingrese el nombre del item:");
                String nombreItem = scanner.nextLine();
                System.out.println("Ingrese el tipo del item:");
                int tipoItem = scanner.nextInt();
                scanner.nextLine();

                try {
                    agregarItem(new Item(nombreItem, tipoItem));
                } catch (SQLException e) {
                    System.out.println("Error al agregar item a la mochila: " + e.getMessage());
                }
                break;
            case 3:
                System.out.println("Ingrese el nombre del item a usar:");
                String itemUsar = scanner.nextLine();
                usarItem(itemUsar);
                break;
            case 4:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("OPCIÓN NO VÁLIDA");
        }
    }
    
    
}
