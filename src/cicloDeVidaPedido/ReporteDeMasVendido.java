package cicloDeVidaPedido;

import java.util.ArrayList;

public class ReporteDeMasVendido implements Reporte{
    // Atributos
    private ArrayList<Venta> ventas;
    private double promedioPrecioPorCantidad; // O el nombre que prefieras para este dato

    // Constructor
    public ReporteDeMasVendido(ArrayList<Venta> ventasEnPeriodo, double promedio) {
        this.ventas = ventasEnPeriodo;
        this.promedioPrecioPorCantidad = promedio;
    }

    // Getters (Sin setters)
    public ArrayList<Venta> getVentas() {
        return this.ventas;
    }

    public double getPromedioPrecioPorCantidad() {
        return this.promedioPrecioPorCantidad;
    }

	@Override
	public void aceptar(FormatoVisitor f) {
		// TODO Auto-generated method stub
		f.visitarReporteDeMasVendido(this);
		
	}
}

