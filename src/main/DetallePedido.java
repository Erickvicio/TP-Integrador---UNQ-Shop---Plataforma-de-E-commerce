package main;

public class DetallePedido {

    private int cantidad;
    private ItemDeCatalogo item;

    // Constructor para inicializar el renglón del pedido
    public DetallePedido(int cantidad, ItemDeCatalogo item) {
        this.cantidad = cantidad;
        this.item = item;
    }
    
    // --- MÉTODOS DE VALIDACIÓN Y CONTROL ---
    
    public void verificarStockParaCantidad(int cantidadTotalAValidar) {
        if (this.item == null) {
            throw new RuntimeException("El ítem de catálogo no puede ser nulo.");
        }
        // El detalle le delega la responsabilidad al ítem
        this.item.verificarStockSuficiente(cantidadTotalAValidar);
    }

    public void verificarDisponibilidadParaQuitar(int cantidadAQuitar) {
        if (this.superaCantidadActual(cantidadAQuitar)) {
            throw new RuntimeException("No podés quitar más unidades de las que tiene el pedido. Solicitado: " + cantidadAQuitar + " - En Pedido: " + this.cantidad);
        }
    }

    public boolean superaCantidadActual(int cantidadAQuitar) {
        return cantidadAQuitar > this.cantidad;
    }
    
    public boolean perteneceAlItem(ItemDeCatalogo unItem) {
        return this.item != null && this.item.equals(unItem);
    }
    
    // --- MÉTODOS DE STOCK (DELEGADOS DIRECTO A ITEM) ---
    
    public void decrementarStock() {
        this.item.decrementarStock(this.getCantidad());
    }

    public void incrementarStock() {
        this.item.incrementarStock(this.getCantidad());
    }
    
    // --- MÉTODOS DE CANTIDAD (DELEGADOS POR CARRITO) ---
    
    public void incrementarCantidad(int unidades) {
        this.cantidad += unidades;
    }

    public void decrementarCantidad(int unidades) {
        this.cantidad -= unidades;
    }

    // --- GETTERS, SETTERS Y CÁLCULOS ---
    
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