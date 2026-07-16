package cicloDeVidaPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import main.Carrito;

	

public class BaseDeReportes {
	
	ArrayList<Venta> ventas; 
	
	
	public void agregarReporte(Carrito car, LocalDate fecha) {
	
		Venta venta= new Venta(car, fecha);
		this.ventas.add(venta);
		
	}
	 
	public void generarReporte(LocalDate fechainc, LocalDate fechaFin){
		
		ArrayList<Venta> ventasEnPeriodo = this.ventas.stream()
			    .filter(v -> (v.getFecha().isAfter(fechainc) && v.getFecha().isBefore(fechaFin)))
			    .collect(Collectors.toCollection(() -> { return new ArrayList<Venta>(); }));
		
		
		Double cantidad = ventasEnPeriodo.stream().mapToDouble(n -> n.getCantidad()).sum();
		Double precio  = ventasEnPeriodo.stream().mapToDouble(n -> n.getPrecio()).sum();
		
		
		ReporteDeMasVendido reporte = new ReporteDeMasVendido(ventasEnPeriodo,(precio/cantidad));
		
	}
	


}


