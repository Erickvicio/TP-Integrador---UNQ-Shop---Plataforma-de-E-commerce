package main;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
	
	//Atributos
    private Map<ItemDeCatalogo, Integer> productos;
    private Estado estado;
    private Direccion dir;

    // Constructor
    public Pedido(Direccion dir) {
        this.dir = dir;
        this.productos = new HashMap<>(); 
        this.estadoInicial();
    }

    //Lógica Principal
    
    public void agregarItem(ItemDeCatalogo item) {
        this.agregarItem_veces(item,1);
    }
    
    public void agregarItem_veces(ItemDeCatalogo item, int vecesAgregar) {
        if (item == null) return;

        // Ejecuta la validación interna
        this.validarCantidad(vecesAgregar);
        this.validarStockDisponible(vecesAgregar,item);
        // Suma al valor existente o lo inicializa en 0 si no estaba, luego suma las veces indicadas
        this.productos.put(item, this.productos.getOrDefault(item, 0) + vecesAgregar);
    }
    


	public void quitarItem(ItemDeCatalogo item) {
        this.quitarItem_veces(item, 1);
    }

    public void quitarItem_veces(ItemDeCatalogo item, int cantidadAQuitar) {
        // 1. Validaciones obligatorias (Lanzan excepciones si algo está mal)
        this.validarParametros(item, cantidadAQuitar);
        this.validarExistencia(item);
        this.validarDisponibilidad(item, cantidadAQuitar);

        int cantidadActual = this.productos.get(item);

        // 2. Modificación o limpieza del mapa
        if (cantidadAQuitar == cantidadActual) {
            this.productos.remove(item);
        } else {
            this.productos.put(item, cantidadActual - cantidadAQuitar);
        }
    }

    
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
    
    public void rembolsaCostoYEnvio(){}
    public void rembolsaCosto() {}
    public void rembolsaEnvio() {}

    //Validaciones
    private void validarCantidad(int cantidad) {
        if (this.esCantidadInvalida(cantidad)) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a 0. Pasaste: " + cantidad);
        }
    }
    private void validarStockDisponible(int vecesAgregar, ItemDeCatalogo item) {
    	if (this.esStockInvalida(vecesAgregar,item)) {
            throw new IllegalArgumentException(vecesAgregar + "estas solicitando mas stock que el disponible ");
        }
	}
    
	private void validarParametros(ItemDeCatalogo item, int cantidadAQuitar) {
        if (this.esParametroInvalido(item, cantidadAQuitar)) {
            throw new IllegalArgumentException("Los parámetros de eliminación son inválidos. El ítem no puede ser nulo y la cantidad debe ser mayor a 0.");
        }
    }
    
    private void validarExistencia(ItemDeCatalogo item) {
        if (this.noExisteItem(item)) {
            throw new IllegalArgumentException("El producto " + item + " no existe en el pedido.");
        }
    }
    
    private void validarDisponibilidad(ItemDeCatalogo item, int cantidadAQuitar) {
        if (this.superaCantidadActual(item, cantidadAQuitar)) {
            int cantidadActual = this.productos.get(item);
            throw new IllegalArgumentException("No podés quitar " + cantidadAQuitar + 
                " unidades de " + item + ". Solo hay " + cantidadActual + " en el pedido.");
        }
    }

    //Booleanos
    private boolean esCantidadInvalida(int cantidad) {
        return cantidad <= 0;
    }
    
    private boolean esParametroInvalido(ItemDeCatalogo item, int cantidadAQuitar) {
        return item == null || cantidadAQuitar <= 0;
    }
    
    private boolean noExisteItem(ItemDeCatalogo item) {
        return !this.productos.containsKey(item);
    }
    
    private boolean superaCantidadActual(ItemDeCatalogo item, int cantidadAQuitar) {
        return cantidadAQuitar > this.productos.getOrDefault(item, 0);
    }
    
    private boolean esStockInvalida(int vecesAgregar, ItemDeCatalogo item) {
		return (vecesAgregar>=item.getStock());
	}

    // Getters
    public Map<ItemDeCatalogo, Integer> getProductos() {
        return this.productos;
    }
    
    public Estado getEstado() {
        return this.estado;
    }
    
    public Direccion getDir() {
        return this.dir;
    }
    
    public float getPrecio() {
        return (float) this.productos.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().precioFinal() * entry.getValue())
                .sum();
    }

    public float getPeso() {
        return (float) this.productos.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().peso() * entry.getValue())
                .sum();
    }
    // Setters
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    private void estadoInicial() {
        this.setEstado(new Borrador(this));
    }

}