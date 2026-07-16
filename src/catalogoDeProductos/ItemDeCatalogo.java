package catalogoDeProductos;

public abstract class ItemDeCatalogo {

    String nombre;
    String descripcion;
    String categoria; // Atributo unificado
    int descuento;
    int stock; // Prioridad a llamarse stock al final
 
     
    // Constructor unificado que incluye todos los campos necesarios
    public ItemDeCatalogo(String nombre, String descripcion, int descuento, String categoria) {
        this.nombre = nombre; 
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.categoria = categoria;
        this.stock = 0; // Inicializado por defecto 
    }
    
    // Métodos abstractos de precio y peso
    public abstract double precioBase();
    public abstract double precioFinal();
    public abstract double getPeso(); // Volvió a Integer objeto

//    // Métodos de Stock (dejaron de ser abstractos y ahora tienen comportamiento)
//    public abstract void incrementarStock(int cantidad); 
//    public abstract void decrementarStock(int cantidad);
    
    public void verificarStockSuficiente(int cantidadRequerida) {
        if (this.stock < cantidadRequerida) {
            throw new RuntimeException("Estás solicitando más stock del disponible para: " + this.nombre);
        }
    }

    // Métodos de consulta (Queries / Getters & Setters)
//    public abstract boolean estaDisponible(int cantidad);

    // PRUEBA
    public int getStock() {
        return this.stock;
    } 
    
    public void incrementarStock(int n) {
    	stock = stock + n;
    }
    
    public void decrementarStock(int n) {
    	stock = stock - n;
    }
    
    public abstract boolean estaDisponible();
    //*************************************************
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
        return this.categoria;
    }

    
 

}

