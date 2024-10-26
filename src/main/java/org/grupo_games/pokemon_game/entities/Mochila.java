package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.MochilaDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Mochila {
    //private String nombre;
    private ArrayList<Item> items;
    private int limite;
    private int mochilaId;
    private static Map<Integer, String> tipoItem = new HashMap<>();

    static {
        tipoItem.put(1, "Pokeball");
        tipoItem.put(2, "Piedra evolutiva");
    }

    public Mochila(int entrenadorId) {
        //this.nombre = nombre;
        this.items = new ArrayList<>();
        this.limite = 10;


        MochilaDAO mochiladb = new MochilaDAO();
        this.mochilaId = mochiladb.crearMochilaBD(entrenadorId, limite);
    }

    public static String getTipoItem(int key) {
        return tipoItem.get(key);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void agregarItem(Item item) throws SQLException {
        if (items.size() < limite) {
            items.add(item);
            System.out.println("Has añadido un " + item.getNombre() + " a la mochila!");


        } else {
            System.out.println("La mochila está llena!");

        }

    }

    public void usarItem(String nombreItem) {
        for (Item item : items) {

            if (item.getNombre().equals(nombreItem)) {
                items.remove(item);
                System.out.println("Ha usado " + nombreItem);
                return;

            }

        }
        System.out.println(nombreItem + "  no está en tu mochila");

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

                if (items.isEmpty()) {
                    System.out.println("No tienes items en la mochila");
                    return;
                }

                for (Item item : items) {
                    System.out.println("- " + item.getNombre() + " (Tipo: " + Mochila.getTipoItem(item.getTipo()) + ")");
                }
                break;
            case 2:
                System.out.println("Agregando ITEMS de mochila...");
                System.out.println("Ingrese el nombre del item:");
                String nombreItem = scanner.nextLine();
                System.out.println("Ingrese el codigo del tipo de item:");
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
