package main;

public abstract class ItemDeCatalogo {
	String nombre;
	String descripcion;
	String categoria;
	int descuento;
	int stock;
	 
	public ItemDeCatalogo(String nombre,String descripcion,int descuento, String categoria) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.descuento=descuento;
		this.categoria = categoria;
	}
	
	public abstract double precioBase();
	public abstract double precioFinal();
	public abstract void incrementarStock(int cantidad);
	public abstract void decrementarStock(int cantidad);

	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public String getNombre() {return nombre;}

	public String getCategoria() {return categoria;}
	
	public boolean estaDisponible() {
		return stock > 0;
	}
	public int getStock() {return stock;} 
}
