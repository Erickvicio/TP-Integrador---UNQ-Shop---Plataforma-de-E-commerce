package main; 

import java.util.ArrayList; 

public class Pedido { 
     
    // Atributos 
    private ArrayList<DetallePedido> detallePedidos; 
    private Estado estado; 
    private Direccion dir; 

    // Constructor 
    public Pedido(Direccion dir) { 
        this.dir = dir; 
        this.detallePedidos = new ArrayList<>(); 
        this.estadoInicial(); 
    } 

     
    public void agregarItem(ItemDeCatalogo item) { 
        this.agregarItem_veces(item, 1); 
    } 
     
    public void agregarItem_veces(ItemDeCatalogo item, int vecesAgregar) { 
        if (item == null) return; 

        this.validarCantidad(vecesAgregar); 

        DetallePedido detalleExistente = this.buscarDetallePorItem(item); 

        // Descomposición del IF en subtareas privadas
        if (detalleExistente != null) { 
            this.actualizarDetalleExistente(detalleExistente, item, vecesAgregar); 
        } else { 
            this.crearNuevoDetalle(item, vecesAgregar); 
        } 
    } 

    private void actualizarDetalleExistente(DetallePedido detalle, ItemDeCatalogo item, int vecesAgregar) { 
        int cantidadTotalAValidar = detalle.getCantidad() + vecesAgregar; 
         
        this.validarStockDisponible(cantidadTotalAValidar, item); 
         
        detalle.setCantidad(cantidadTotalAValidar); 
    } 

    private void crearNuevoDetalle(ItemDeCatalogo item, int vecesAgregar) { 
        this.validarStockDisponible(vecesAgregar, item); 
         
        DetallePedido nuevoDetalle = new DetallePedido(vecesAgregar, item); 
        this.detallePedidos.add(nuevoDetalle); 
    } 



    public void quitarItem(ItemDeCatalogo item) { 
        this.quitarItem_veces(item, 1); 
    } 

    public void quitarItem_veces(ItemDeCatalogo item, int cantidadAQuitar) { 
        this.validarParametros(item, cantidadAQuitar); 
        this.validarExistencia(item); 

        DetallePedido detalleExistente = this.buscarDetallePorItem(item); 
        
        this.validarDisponibilidadParaQuitar(detalleExistente, cantidadAQuitar); 

        // Descomposición del IF en una subtarea privada
        this.procesarBajaDeStock(detalleExistente, cantidadAQuitar); 
    } 

    private void procesarBajaDeStock(DetallePedido detalle, int cantidadAQuitar) { 
        if (cantidadAQuitar == detalle.getCantidad()) { 
            this.detallePedidos.remove(detalle); 
        } else { 
            int nuevaCantidad = detalle.getCantidad() - cantidadAQuitar; 
            detalle.setCantidad(nuevaCantidad); 
        } 
    } 

     
    public void decrementerStock() { 
        this.detallePedidos.forEach(detalle -> this.decrementarStockDeLinea(detalle)); 
    } 

    public void incrementarStock() { 
        this.detallePedidos.forEach(detalle -> this.incrementarStockDeLinea(detalle)); 
    } 

    private void decrementarStockDeLinea(DetallePedido detalle) { 
        detalle.getItem().decrementarStock(detalle.getCantidad()); 
    } 

    private void incrementarStockDeLinea(DetallePedido detalle) { 
        detalle.getItem().incrementarStock(detalle.getCantidad()); 
    } 
     
    public void rembolsaCostoYEnvio(){} 
    public void rembolsaCosto() {} 
    public void rembolsaEnvio() {} 



    private void validarCantidad(int cantidad) { 
        if (this.esCantidadInvalida(cantidad)) { 
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a 0. Pasaste: " + cantidad); 
        } 
    } 

    private void validarStockDisponible(int cantidadTotal, ItemDeCatalogo item) { 
        if (this.esStockInvalido(cantidadTotal, item)) { 
            throw new IllegalArgumentException("Estás solicitando más stock del disponible para: " + item.getNombre()); 
        } 
    } 
     
    private void validarParametros(ItemDeCatalogo item, int cantidadAQuitar) { 
        if (this.esParametroInvalido(item, cantidadAQuitar)) { 
            throw new IllegalArgumentException("Los parámetros de eliminación son inválidos. El ítem no puede ser nulo y la cantidad debe ser mayor a 0."); 
        } 
    } 
     
    private void validarExistencia(ItemDeCatalogo item) { 
        if (this.noExisteItem(item)) { 
            throw new IllegalArgumentException("El producto no existe en el pedido."); 
        } 
    } 
     
    private void validarDisponibilidadParaQuitar(DetallePedido detalle, int cantidadAQuitar) { 
        if (this.superaCantidadActual(detalle, cantidadAQuitar)) { 
            throw new RuntimeException("No podés quitar más unidades de las que tiene el pedido. Solicitado: " + cantidadAQuitar + " - En Pedido: " + detalle.getCantidad()); 
        } 
    } 



    private DetallePedido buscarDetallePorItem(ItemDeCatalogo item) { 
        return this.detallePedidos.stream() 
                .filter(detalle -> detalle.getItem().equals(item)) 
                .findFirst() 
                .orElse(null); 
    } 

    private boolean esCantidadInvalida(int cantidad) { 
        return cantidad <= 0; 
    } 
     
    private boolean esParametroInvalido(ItemDeCatalogo item, int cantidadAQuitar) { 
        return item == null || cantidadAQuitar <= 0; 
    } 
     
    private boolean noExisteItem(ItemDeCatalogo item) { 
        return this.buscarDetallePorItem(item) == null; 
    } 

    private boolean superaCantidadActual(DetallePedido detalle, int cantidadAQuitar) { 
        return detalle == null || cantidadAQuitar > detalle.getCantidad(); 
    } 

    private boolean esStockInvalido(int cantidadTotal, ItemDeCatalogo item) { 
        return cantidadTotal > item.getStock(); // Corregido: con '>' permite llevar el último artículo disponible
    } 



    public ArrayList<DetallePedido> getDetallePedido() { 
        return this.detallePedidos; 
    } 
     
    public Estado getEstado() { 
        return this.estado; 
    } 
     
    public Direccion getDir() { 
        return this.dir; 
    } 
     
    public float getPrecio() { 
        return (float) this.detallePedidos.stream() 
                .mapToDouble(detalle -> detalle.getPrecio()) 
                .sum(); 
    } 
     
    public float getPeso() { 
        return (float) this.detallePedidos.stream() 
                .mapToDouble(detalle -> detalle.getPeso()) 
                .sum(); 
    } 

    public void setEstado(Estado estado) { 
        this.estado = estado; 
    } 
     
    private void estadoInicial() { 
        this.setEstado(new Borrador(this)); 
    } 
}