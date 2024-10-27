public class Habilidad {
    private int id;
    private String nombre;
    private String tipo;  // Ejemplo: "Ataque", "Defensa", "Curación", etc.
    private int poder;
    private Pokemon pokemon;  // Relación con la clase Pokemon, si es necesario que un Pokémon tenga habilidades específicas

    // Constructor
    public Habilidad(String nombre, String tipo, int poder) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.poder = poder;
    }

    public Habilidad(int id, String nombre, String tipo, int poder) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.poder = poder;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "Habilidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", poder=" + poder +
                '}';
    }
}
