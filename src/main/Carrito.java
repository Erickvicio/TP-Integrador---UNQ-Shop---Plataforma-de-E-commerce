package main;

import java.util.ArrayList;

public class Carrito {
    
    // El ArrayList se muda para acá
    private ArrayList<DetallePedido> detallePedidos;

    public Carrito() {
        this.detallePedidos = new ArrayList<>();
    }

    // --- MÉTODOS DE AGREGAR ---
    
    
    private void actualizarDetalleExistente(DetallePedido detalle, ItemDeCatalogo item, int vecesAgregar) {
        int cantidadTotalAValidar = detalle.getCantidad() + vecesAgregar;
        

        detalle.verificarStockParaCantidad(cantidadTotalAValidar);
        
        detalle.incrementarCantidad(vecesAgregar); 
    }

    private void crearNuevoDetalle(ItemDeCatalogo item, int vecesAgregar) { 

        item.verificarStockSuficiente(vecesAgregar); 
         
        DetallePedido nuevoDetalle = new DetallePedido(vecesAgregar, item); 
        this.detallePedidos.add(nuevoDetalle); 
    } 

    public void quitarItem_veces(ItemDeCatalogo item, int cantidadAQuitar) { 
        this.validarItemNoNulo(item); 
        this.validarCantidad(cantidadAQuitar); 
        
        DetallePedido detalleExistente = this.buscarDetallePorItem(item); 
        this.validarExistencia(detalleExistente); 

        detalleExistente.verificarDisponibilidadParaQuitar(cantidadAQuitar); 

        this.procesarBajaDeDetalle(detalleExistente, cantidadAQuitar); 
    }

    public void agregarItem(ItemDeCatalogo item) { 
        this.agregarItem_veces(item, 1); 
    }
    
    public void agregarItem_veces(ItemDeCatalogo item, int vecesAgregar) {
        this.validarItemNoNulo(item); 
        this.validarCantidad(vecesAgregar); 
        DetallePedido detalleExistente = this.buscarDetallePorItem(item); 
        this.procesarDetallePedido(detalleExistente, item, vecesAgregar);
    } 

    private void procesarDetallePedido(DetallePedido detalleExistente, ItemDeCatalogo item, int vecesAgregar) {
        if (detalleExistente != null) { 
            this.actualizarDetalleExistente(detalleExistente, item, vecesAgregar); 
        } else { 
            this.crearNuevoDetalle(item, vecesAgregar); 
        }
    }
    
    public void decrementarStock() {
        this.detallePedidos.forEach(DetallePedido::decrementarStock);
    }

    public void incrementarStock() {
        this.detallePedidos.forEach(DetallePedido::incrementarStock);
    }

    // --- MÉTODOS DE QUITAR ---

    public void quitarItem(ItemDeCatalogo item) { 
        this.quitarItem_veces(item, 1); 
    } 
    

    private void procesarBajaDeDetalle(DetallePedido detalle, int cantidadAQuitar) { 
        if (cantidadAQuitar == detalle.getCantidad()) { 
            this.getDetallePedidos().remove(detalle); 
        } else { 
            detalle.decrementarCantidad(cantidadAQuitar); // <-- Delegado
        } 
    }

    // --- VALIDACIONES (UNIFICADAS EN RUNTIMEEXCEPTION) ---
    private void validarExistencia(DetallePedido detalle) {
        if (detalle == null) {
            throw new RuntimeException("El producto no existe en el pedido.");
        }
    }

    private void validarItemNoNulo(ItemDeCatalogo item) {
        if (item == null) {
            throw new RuntimeException("El ítem de catálogo no puede ser nulo.");
        }
    }

    private void validarCantidad(int cantidad) { 
        if (this.esCantidadInvalida(cantidad)) { 
            throw new RuntimeException("La cantidad a agregar debe ser mayor a 0. Pasaste: " + cantidad); 
        } 
    } 


    // --- MÉTODOS BOOLEANOS ---

    private boolean esCantidadInvalida(int cantidad) { 
        return cantidad <= 0; // 
    } 


    // --- GETTERS Y BUSCADORES DE CARRITO ---

    public ArrayList<DetallePedido> getDetallePedidos() { 
        return this.detallePedidos; 
    } 

    public DetallePedido buscarDetallePorItem(ItemDeCatalogo item) {
        return this.detallePedidos.stream()
                .filter(detalle -> detalle.perteneceAlItem(item)) 
                .findFirst()
                .orElse(null);
    }
     
    public float getPrecioTotal() { 
        return (float) this.detallePedidos.stream() 
                .mapToDouble(detalle -> detalle.getPrecio()) 
                .sum(); 
    } 
     
    public float getPesoTotal() { 
        return (float) this.detallePedidos.stream() 
                .mapToDouble(detalle -> detalle.getPeso()) 
                .sum(); 
    } 
}