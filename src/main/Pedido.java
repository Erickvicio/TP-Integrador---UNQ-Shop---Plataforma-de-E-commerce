package main;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
    public Estado estado;
    private List<Item> productos;

    public Pedido() {
        this.productos = new ArrayList<>();
    }
    public void agregarItem(Item item) {
        this.productos.add(item);
    }

    public void quitarItem(Item item) {
        this.productos.remove(item);
    }

    public void decrementerStock() {

    }

    public void rembolsaCostoYEnvio() {

    }

    public void rembolsaCosto() {

    }

    public void rembolsaEnvio() {

    }

    public void incrementarStock() {

    }

    private void estadoInicial() {
    	

    }

    // Getter para la lista por si lo necesitás en los tests
    public List<Item> getProductos() {
        return this.productos;
    }
 // Getter para obtener el estado actual
    public Estado getEstado() {
        return this.estado;
    }

    // Setter para cambiar el estado del pedido
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
