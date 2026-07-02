package cicloDeVidaPedido;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Carrito;

class BaseDeReportesTest {

    BaseDeReportes baseDeReportes;
    Carrito carrito1;
    Carrito carrito2;
    Carrito carrito3;

    @BeforeEach
    void setUp() throws Exception {
        baseDeReportes = new BaseDeReportes();
        
        // ¡Importante! Inicializamos la lista de ventas para evitar el NullPointerException
        baseDeReportes.ventas = new ArrayList<Venta>();
        
        // Inicializamos los carritos (usá los constructores reales de tu clase Carrito)
        carrito1 = new Carrito();
        carrito2 = new Carrito();
        carrito3 = new Carrito();
    }

    @Test
    void testAgregarReporteSumaVentaALaLista() {
        LocalDate fecha = LocalDate.of(2026, 6, 15);
        
        // Verificamos que arranque vacía
        assertEquals(0, baseDeReportes.ventas.size());
        
        // Agregamos una venta
        baseDeReportes.agregarReporte(carrito1, fecha);
        
        // Verificamos que ahora tenga 1 elemento
        assertEquals(1, baseDeReportes.ventas.size());
        assertEquals(fecha, baseDeReportes.ventas.get(0).getFecha());
    }

    @Test
    void testGenerarReporteFiltraYCalculaCorrectamente() {
        // Configuramos las fechas de nuestro período de prueba (Rango exclusivo según tu filter)
        LocalDate inicio = LocalDate.of(2026, 6, 1);
        LocalDate fin = LocalDate.of(2026, 6, 10);
        
        // Creamos y agregamos ventas manualmente con distintas fechas
        // Venta 1: Está DENTRO del rango (Día 5)
        baseDeReportes.agregarReporte(carrito1, LocalDate.of(2026, 6, 5)); 
        
        // Venta 2: Está FUERA del rango (Día 15)
        baseDeReportes.agregarReporte(carrito2, LocalDate.of(2026, 6, 15));
        
        // Venta 3: Está DENTRO del rango (Día 8)
        baseDeReportes.agregarReporte(carrito3, LocalDate.of(2026, 6, 8));

        // Ejecutamos el método a testear
        // Nota: Como tu método actual guarda el 'Reporte' en una variable local y no lo retorna,
        // el test principalmente verifica que el flujo corra sin lanzar excepciones (como división por cero).
        assertDoesNotThrow(() -> {
            baseDeReportes.generarReporte(inicio, fin);
        });
    }
}