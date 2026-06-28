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
     * Agrega los productos del mapa recibido al mapa actual.
     * Si ya existían, suma las cantidades.
     */
    public void agregarItem(Map<ItemDeCatalogo, Integer> nuevosProductos) {
        if (nuevosProductos == null) return;

        nuevosProductos.entrySet().stream().forEach(entry -> {
            ItemDeCatalogo item = entry.getKey();
            Integer cantidadASumar = entry.getValue();
            
            this.productos.put(item, this.productos.getOrDefault(item, 0) + cantidadASumar);
        });
    }

    /**
     * Quita las cantidades indicadas en el mapa recibido.
     * Lanza excepciones específicas si el ítem no existe o si se quiere quitar de más.
     */
    public void quitarItem(Map<ItemDeCatalogo, Integer> productosAQuitar) {
        if (productosAQuitar == null) return;

        productosAQuitar.entrySet().stream().forEach(entry -> {
            ItemDeCatalogo item = entry.getKey();
            Integer cantidadAQuitar = entry.getValue();

            if (!this.productos.containsKey(item)) {
                throw new IllegalArgumentException("El producto " + item + " no existe en el pedido.");
            }

            int cantidadActual = this.productos.get(item);

            if (cantidadAQuitar > cantidadActual) {
                throw new IllegalArgumentException("No podés quitar " + cantidadAQuitar + 
                    " unidades de " + item + ". Solo hay " + cantidadActual + " en el pedido.");
            }

            if (cantidadAQuitar == cantidadActual) {
                this.productos.remove(item);
            } else {
                this.productos.put(item, cantidadActual - cantidadAQuitar);
            }
        });
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

    
    public void rembolsaCostoYEnvio() {}
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