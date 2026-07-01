package main;

public abstract class ItemDeCatalogo {

    String nombre;
    String descripcion;
    String category; // Atributo unificado
    int descuento;
    int stock; // Prioridad a llamarse stock al final
     
    // Constructor unificado que incluye todos los campos necesarios
    public ItemDeCatalogo(String nombre, String descripcion, int descuento, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.category = categoria;
        this.stock = 0; // Inicializado por defecto
    }
    
    
    
    // Métodos abstractos de precio y peso
    public abstract double precioBase();
    public abstract double precioFinal();
    protected abstract Integer peso();

    // Métodos de Stock (dejaron de ser abstractos y ahora tienen comportamiento)
    public void incrementarStock(int cantidad) {
        this.stock = this.stock + cantidad;
    }

    public boolean tieneStockSuficiente(int cantidadRequerida) {
        return this.stock >= cantidadRequerida;
    }

    // Modificamos tu método para que actúe como un guardián
    public void decrementarStock(int cantidad) {
        if (!this.tieneStockSuficiente(cantidad)) {
            throw new RuntimeException("Error crítico: No se puede decrementar stock por debajo de 0 para: " + this.nombre);
        }
        this.stock = this.stock - cantidad;
    }
    
    

    // Métodos de consulta (Queries / Getters & Setters)
    public boolean estaDisponible() {
        return this.stock > 0;
    }

    public int getStock() {
        return this.stock;
    } 

    public int getDescuento() {
        return this.descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCategoria() {
        return this.category;
    }


}

