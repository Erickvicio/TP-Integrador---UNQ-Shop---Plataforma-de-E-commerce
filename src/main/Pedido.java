package main;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	
	float getPeso() {
		return 0;
	}

	Direccion getDir() {
		return null;
	}

	float getPrecio() {
		return 0;
	}
    public Estado estado;
    private List<Item> productos;

    public Pedido() {
        this.productos = new ArrayList<>();
        // ¡ESTA LÍNEA ES CLAVE! Si no la ponés acá, el método jamás se ejecuta
        this.estadoInicial(); 
    }

    public void estadoInicial() {
        this.setEstado(new Borrador(this));
    }

    public void agregarItem(Item item) {
        this.productos.add(item);
    }

    public void quitarItem(Item item) {
        this.productos.remove(item);
    }

    public void decrementerStock() {}
    public void rembolsaCostoYEnvio() {}
    public void rembolsaCosto() {}
    public void rembolsaEnvio() {}
    public void incrementarStock() {}

    public List<Item> getProductos() {
        return this.productos;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}