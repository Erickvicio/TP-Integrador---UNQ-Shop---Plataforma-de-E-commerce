package main;

import java.util.ArrayList;
import java.util.List;

import catalogoDeProductos.ItemDeCatalogo;

public class Catalogo {
    
    private String nombre;
    private int precio;
    private Categoria cat;
    // En Java usamos List y ArrayList (con mayúsculas)
    private ArrayList<ItemDeCatalogo> item;

    // Constructor para inicializar los atributos y la lista vacía
    public Catalogo(String nombre, int precio, Categoria cat) {
        this.nombre = nombre;
        this.precio = precio;
        this.cat = cat;
        this.item = new ArrayList<>(); 
    }

    // ==========================================
    // GETTERS & SETTERS
    // ==========================================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    public List<ItemDeCatalogo> getProducto() {
        return item;
    }

    public void agregarProducto(ItemDeCatalogo nuevoProducto) {
        this.item.add(nuevoProducto);
    }

    // ==========================================
    // MÉTODOS ADICIONALES (Basados en tu boceto)
    // ==========================================

    /* Si querés que devuelva el nombre de la categoría directamente
    public String getNombreCategoria() {
        return (cat != null) ? cat.getNombre() : "Sin categoría";
    }
*/
    // Podés cambiar la lógica de disponibilidad según los productos que tenga adentro
    public boolean estaDisponible() {
        return !item.isEmpty();
    }
}