package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.EvolucionDAO;
import org.grupo_games.pokemon_game.daos.PiedraEvolutivaDAO;
import org.grupo_games.pokemon_game.daos.PokemonDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

public class Pokemon {
    private int id;
    private String apodo;
    private float nivel;
    private float salud;
    private String especie;
    private Entrenador entrenador;
    private float nivelParaEvolucionar = 10;

    private int NIVEL_EVOLUCION = 0;

    public Pokemon(int id, String apodo, String especie, int nivel, float salud) {
        this.id = id;
        this.apodo = apodo;
        this.especie = especie;
        this.nivel = nivel;
        this.salud = salud;
    }



    public Pokemon(String apodo, int nivel, float salud, Entrenador entrenador) {
        this.apodo = apodo;
        this.nivel = nivel;
        this.salud = salud;
        this.entrenador = entrenador;
    }

    public Pokemon(String apodo, int nivel, float salud) {
        this.apodo = apodo;
        this.nivel = nivel;
        this.salud = salud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecie(){
        return especie;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public float getSalud() {
        return salud;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public float getNivel() {
        return nivel;
    }

    public void setNivel(float nivel) {
        this.nivel = nivel;
    }

    public String getApodo() {
        return apodo;
    }

    // Método para evolucionar por piedra
    public void evolucionarPorPiedra() throws SQLException {
        // Obtener la instancia del manager de piedras evolutivas
        PiedraEvolutivaManager manager = PiedraEvolutivaManager.getInstance();
        ArrayList<PiedraEvolutiva> piedras = manager.getPiedrasEvolutivas();

        // Comprobar si hay piedras evolutivas disponibles
        if (piedras.isEmpty()) {
            System.out.println("No se posee piedras, no podrás evolucionar a tu Pokémon.");
            return;
        }

        // Mostrar las piedras evolutivas disponibles
        System.out.println("Piedras Evolutivas:");
        for (PiedraEvolutiva piedra : piedras) {
            System.out.println("Id: " + piedra.getId() + ", Nombre: " + piedra.getNombre());
        }

        // Obtener la piedra evolutiva que corresponde al Pokémon
        String nombrePiedraEvolutiva = new PiedraEvolutivaDAO().obtenerPiedraSegunPokemon(this.apodo);

        // Verificar si el Pokémon puede evolucionar con alguna piedra
        if (nombrePiedraEvolutiva == null) {
            System.out.println(this.apodo + " no tiene una piedra evolutiva asignada.");
            return;
        }

        // Comprobar si la piedra está en la lista
        PiedraEvolutiva piedraSeleccionada = piedras.stream()
                .filter(piedra -> piedra.getNombre().equalsIgnoreCase(nombrePiedraEvolutiva))
                .findFirst()
                .orElse(null);

        if (piedraSeleccionada == null) {
            System.out.println(this.apodo + " no puede evolucionar usando la piedra " + nombrePiedraEvolutiva);
            return;
        }

        // Llamar al método para evolucionar con la piedra seleccionada
        evolucionarConSeleccion(piedraSeleccionada.getNombre());
    }


    // Método para entrenar y verificar evolución por nivel
    public void entrenar() throws SQLException, InterruptedException  {
        Scanner scanner = new Scanner(System.in);
        float tiempoEsperaEnSegundos = 0.22f;
        boolean continuarEntrenando = true;
        int nivelesAlcanzados = 0;

        while (continuarEntrenando) {
            System.out.print("---------------- ENTRENANDO "+ apodo.toUpperCase() +" ------------------\n");
            Random random = new Random();
            float incremento = 0.01f + random.nextFloat() * 0.09f;
            nivel += incremento;
            System.out.println(apodo + " ha ganado " + incremento + " puntos de experiencia.");

            // Verificar si el Pokémon ya puede evolucionar
            if (nivel >= nivelParaEvolucionar) {
                evolucionarConSeleccion(null);
                return;
            } else {
                System.out.println("Experiencia actual: " + nivel + ". Aún necesita " + (nivelParaEvolucionar - nivel) + " para evolucionar.");
            }

            // Verificar si ha alcanzado 5 niveles completos desde la última vez que preguntamos
            int nivelesCompletosActuales = (int) nivel;
            if (nivelesCompletosActuales >= nivelesAlcanzados + 5) {
                System.out.print("Has alcanzado " + (nivelesAlcanzados + 5) + " niveles de experiencia. ¿Deseas continuar entrenando? (sí/no): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("no")) {
                    System.out.println("Has decidido detener el entrenamiento.");
                    continuarEntrenando = false;
                } else if (!respuesta.equals("sí")) {
                    System.out.println("Respuesta no válida. Continuando el entrenamiento por defecto.");
                }

                nivelesAlcanzados += 5;
            }
            Thread.sleep((long)(tiempoEsperaEnSegundos * 1000));
            System.out.print("------------------------------------------------------------ \n\n");

        }
    }

    public void actualizarNivelDB() throws SQLException {
        PokemonDAO pokemonDAO = new PokemonDAO();
        pokemonDAO.GuardarNivel(this);
    }

    // Método genérico para evolucionar con selección
    private void evolucionarConSeleccion(String piedraNombre) throws SQLException {
        EvolucionDAO evoDAO = new EvolucionDAO();
        ArrayList<String> especies = evoDAO.obtenerSiguientesEvoluciones(this.especie);

        if (especies.isEmpty()) {
            System.out.println(this.apodo + " no tiene evoluciones disponibles.");
            return;
        }

        String especieDestino = seleccionarEvolucion(especies);

        if (especieDestino != null) {
            System.out.println(this.apodo + " ha evolucionado a " + especieDestino + (piedraNombre != null ? " usando " + piedraNombre : "") + "!");
            PokemonDAO pokemonDAO = new PokemonDAO();
            pokemonDAO.evolucionarPokemon(this, especieDestino);
            this.especie = especieDestino;

            evoDAO.registrarEvolucion(this, especieDestino);
        } else {
            System.out.println("No se realizó la evolución.");
        }
    }

    // Método para seleccionar la especie de evolución (maneja una o múltiples opciones)
    private String seleccionarEvolucion(ArrayList<String> especies) {
        if (especies.size() == 1) {
            return especies.get(0);
        }

        System.out.println("Especies a las que puedes evolucionar:");
        for (int i = 0; i < especies.size(); i++) {
            System.out.println((i + 1) + ". " + especies.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecciona el número de la especie a la que deseas evolucionar: ");
        int seleccion = scanner.nextInt();

        if (seleccion > 0 && seleccion <= especies.size()) {
            return especies.get(seleccion - 1);
        } else {
            System.out.println("Selección inválida.");
            return null;
        }
    }

    @Override
    public String toString() {
        return  "Nombre: " + apodo + "\n" +
                "id: " + id + "\n" +
                "Especie: " + especie;
    }
}