package main;

public class DetallePedido {

    private int cantidad;
    private ItemDeCatalogo item;


    // Constructor para inicializar el renglón del pedido
    public DetallePedido(int cantidad, ItemDeCatalogo item) {
        this.cantidad = cantidad;
        this.item = item;
    }

    // Getters y Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ItemDeCatalogo getItem() {
        return item;
    }

    public void setItem(ItemDeCatalogo item) {
        this.item = item;
    }

    public double getPrecio() {
        return this.item.precioFinal() * this.cantidad;
    }
    public double getPeso() {
        return this.item.peso() * this.cantidad;
    }
}