package cicloDeVidaPedido;

import java.time.LocalDate;

import main.Carrito;

public class Venta {
	private Carrito car;
	private LocalDate fecha;

 
	public Venta (Carrito car, LocalDate fecha) {
		
		this.car = car;
		this.fecha = fecha;
	}

	public LocalDate getFecha() {
	
		return this.fecha;
		
	}
	
	
	public int getCantidad() {
		
		return car.getCantidadTotal();
	
	}

	public float getPrecio() {
		
		return car.getPrecioTotal();
	
	}
}
