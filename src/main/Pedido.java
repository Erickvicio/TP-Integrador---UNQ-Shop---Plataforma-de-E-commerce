package main;

import java.util.HashMap;
import java.util.Map;

public class Pedido {

    private Map<ItemDeCatalogo, Integer> productos;
    private Estado estado;
    private Direccion dir; // Atributo para la dirección agregado

    // Constructor unificado que recibe la dirección obligatoriamente al crearse
    public Pedido(Direccion dir) {
        this.dir = dir;
        this.productos = new HashMap<>(); 
        this.estadoInicial(); 
    }

    public void estadoInicial() {
        this.setEstado(new Borrador(this));
    }

    /**
     * Agrega una sola unidad de un ítem al mapa.
     * Si ya existía, suma 1 a la cantidad actual.
     */
    public void agregarItem(ItemDeCatalogo item) {
        this.agregarItem_veces(item, 1);
    }

    /**
     * Agrega un ítem al mapa la cantidad de veces especificada.
     * Si ya existía, suma la nueva cantidad a la actual.
     */
    public void agregarItem_veces(ItemDeCatalogo item, int vecesAgregar) {
        if (item == null || vecesAgregar <= 0) return;

        // Suma al valor existente o lo inicializa en 0 si no estaba, luego suma las veces indicadas
        this.productos.put(item, this.productos.getOrDefault(item, 0) + vecesAgregar);
    }

    /**
     * Quita una sola unidad de un ítem del mapa.
     * Lanza excepciones si el ítem no existe o si ya no quedan unidades.
     */
    public void quitarItem(ItemDeCatalogo item) {
        this.quitarItem_veces(item, 1);
    }

    /**
     * Quita un ítem del mapa la cantidad de veces especificada.
     * Maneja las excepciones correspondientes si no existe o si se quita de más.
     */
    public void quitarItem_veces(ItemDeCatalogo item, int cantidadAQuitar) {
        if (item == null || cantidadAQuitar <= 0) return;

        // 1. Excepción si el producto no existe en el pedido
        if (!this.productos.containsKey(item)) {
            throw new IllegalArgumentException("El producto " + item + " no existe en el pedido.");
        }

        int cantidadActual = this.productos.get(item);

        // 2. Excepción si se quiere quitar más de lo que hay guardado
        if (cantidadAQuitar > cantidadActual) {
            throw new IllegalArgumentException("No podés quitar " + cantidadAQuitar + 
                " unidades de " + item + ". Solo hay " + cantidadActual + " en el pedido.");
        }

        // 3. Modificación o limpieza del mapa
        if (cantidadAQuitar == cantidadActual) {
            // Si sacamos exactamente las que había, removemos la clave para limpiar el mapa
            this.productos.remove(item);
        } else {
            // Si quedan unidades, actualizamos el mapa con el resto
            this.productos.put(item, cantidadActual - cantidadAQuitar);
        }
    }
    

    // Calcula el precio total recorriendo el mapa multiplicando precioUnitario * cantidad
    public float getPrecio() {
        return (float) this.productos.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().precioFinal() * entry.getValue())
                .sum();
    }

    // Calcula el peso total recorriendo el mapa multiplicando pesoUnitario * cantidad
    public float getPeso() {
        return (float) this.productos.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().peso() * entry.getValue())
                .sum();
    }

    // Getter de la dirección seteada en el constructor
    public Direccion getDir() {
        return this.dir;
    }

    
    public void rembolsaCostoYEnvio(){}
    public void rembolsaCosto() {}
    public void rembolsaEnvio() {}
    
    public void decrementerStock() {
        this.productos.entrySet().stream().forEach(entry -> {
            entry.getKey().decrementarStock(entry.getValue());
        });
    }

    public void incrementarStock() {
        this.productos.entrySet().stream().forEach(entry -> {
            entry.getKey().incrementarStock(entry.getValue());
        });
    }

    public Map<ItemDeCatalogo, Integer> getProductos() {
        return this.productos;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}