package main;

import java.util.ArrayList;
import java.util.List;

import catalogoDeProductos.ItemDeCatalogo;

public class Catalogo {
    
    private String nombre;
    private int precio;
    private Categoria cat;

    private ArrayList<ItemDeCatalogo> item;

    
    public Catalogo(String nombre, int precio, Categoria cat) {
        this.nombre = nombre;
        this.precio = precio;
        this.cat = cat;
        this.item = new ArrayList<>(); 
    }



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

    
    public boolean estaDisponible() {
        return !item.isEmpty();
    }
}
