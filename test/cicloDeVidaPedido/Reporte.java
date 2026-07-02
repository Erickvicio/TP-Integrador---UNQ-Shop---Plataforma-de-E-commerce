package cicloDeVidaPedido;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReporteTest {

    ArrayList<Venta> listaDeVentasMock;
    Venta venta1;
    Venta venta2;

    @BeforeEach
    void setUp() throws Exception {
        // Inicializamos la lista y algunos elementos de prueba de tipo Venta
        listaDeVentasMock = new ArrayList<Venta>();
        
        // Asumiendo la existencia del constructor de Venta
        venta1 = new Venta(null, null); 
        venta2 = new Venta(null, null);
        
        listaDeVentasMock.add(venta1);
        listaDeVentasMock.add(venta2);
    }

    @Test
    void testConstructorYGetters() {
        double promedioEsperado = 150.50;

        // Creamos la instancia de Reporte pasando los datos de prueba
        Reporte reporte = new Reporte(listaDeVentasMock, promedioEsperado);

        // 1. Verificamos que la lista recuperada sea exactamente la misma que pasamos
        assertNotNull(reporte.getVentas());
        assertEquals(2, reporte.getVentas().size());
        assertTrue(reporte.getVentas().contains(venta1));
        assertTrue(reporte.getVentas().contains(venta2));

        // 2. Verificamos que el promedio se guarde y recupere de forma correcta
        // Usamos el delta de tolerancia de 0.001 por ser un tipo double
        assertEquals(promedioEsperado, reporte.getPromedioPrecioPorCantidad(), 0.001);
    }
}
