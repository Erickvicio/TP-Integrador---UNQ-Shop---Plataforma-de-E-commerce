package cicloDeVidaPedido;

import java.util.ArrayList;

public interface Reporte {

	public ArrayList<Venta> getVentas();
	public double getPromedioPrecioPorCantidad();
	public void aceptar(FormatoVisitor f);

}
