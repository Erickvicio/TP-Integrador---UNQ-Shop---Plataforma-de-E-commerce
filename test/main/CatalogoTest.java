package main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import catalogoDeProductos.ItemDeCatalogo;
import main.Catalogo;
import main.Categoria;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogoTest {

    private Catalogo catalogo;
    private Categoria categoriaMock;
    private ItemDeCatalogo itemMock1;
    private ItemDeCatalogo itemMock2;

    private final String NOMBRE_INICIAL = "Catálogo UNQ";
    private final int PRECIO_INICIAL = 1500;

    @BeforeEach
    void setUp() {
        // Creamos las dependencias mockeadas
        categoriaMock = mock(Categoria.class);
        itemMock1 = mock(ItemDeCatalogo.class);
        itemMock2 = mock(ItemDeCatalogo.class);

        // Inicializamos el objeto bajo prueba (SUT)
        catalogo = new Catalogo(NOMBRE_INICIAL, PRECIO_INICIAL, categoriaMock);
    }

    // --- TESTS DEL CONSTRUCTOR E INICIALIZACIÓN ---

    @Test
    void testConstructor_InicializaAtributosYListaVacia() {
        assertEquals(NOMBRE_INICIAL, catalogo.getNombre());
        assertEquals(PRECIO_INICIAL, catalogo.getPrecio());
        assertEquals(categoriaMock, catalogo.getCat());
        
        // La lista de ítems debe arrancar vacía pero no nula
        assertNotNull(catalogo.getProducto());
        assertTrue(catalogo.getProducto().isEmpty());
    }

    // --- TESTS DE MANEJO DE PRODUCTOS ---

    @Test
    void testAgregarProducto_AgregaUnItemALaLista() {
        catalogo.agregarProducto(itemMock1);
        
        List<ItemDeCatalogo> productos = catalogo.getProducto();
        
        assertEquals(1, productos.size());
        assertTrue(productos.contains(itemMock1));
    }

    @Test
    void testAgregarProducto_AgregaMultiplesItems() {
        catalogo.agregarProducto(itemMock1);
        catalogo.agregarProducto(itemMock2);
        
        List<ItemDeCatalogo> productos = catalogo.getProducto();
        
        assertEquals(2, productos.size());
        assertEquals(itemMock1, productos.get(0));
        assertEquals(itemMock2, productos.get(1));
    }

    // --- TESTS DE LÓGICA DE NEGOCIO (estaDisponible) ---

    @Test
    void testEstaDisponible_SinProductos_RetornaFalse() {
        // Como arranca vacía, no debería estar disponible
        assertFalse(catalogo.estaDisponible());
    }

    @Test
    void testEstaDisponible_ConAlMenosUnProducto_RetornaTrue() {
        catalogo.agregarProducto(itemMock1);
        
        // Al tener un elemento, pasa a estar disponible
        assertTrue(catalogo.estaDisponible());
    }

    // --- TESTS DE GETTERS Y SETTERS (Para asegurar 100% Coverage) ---

    @Test
    void testSetNombre() {
        catalogo.setNombre("Nuevo Nombre Catálogo");
        assertEquals("Nuevo Nombre Catálogo", catalogo.getNombre());
    }

    @Test
    void testSetPrecio() {
        catalogo.setPrecio(2500);
        assertEquals(2500, catalogo.getPrecio());
    }

    @Test
    void testSetCat() {
        Categoria nuevaCategoriaMock = mock(Categoria.class);
        catalogo.setCat(nuevaCategoriaMock);
        assertEquals(nuevaCategoriaMock, catalogo.getCat());
    }
}