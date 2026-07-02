package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Categoria;

class CategoriaTest {

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        // Inicializamos la instancia antes de cada test
        categoria = new Categoria();
    }

    @Test
    void testCreacionDeCategoria_NoEsNula() {
        // Verifica que el constructor por defecto funcione bien y cree el objeto
        assertNotNull(categoria, "La instancia de Categoria no debería ser nula");
    }
}