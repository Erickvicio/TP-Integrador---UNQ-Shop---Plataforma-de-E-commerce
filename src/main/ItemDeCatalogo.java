package main;

public abstract class ItemDeCatalogo {
	String nombre;
	String descripcion;
	int descuento;
	 
	public ItemDeCatalogo(String nombre,String descripcion,int descuento) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.descuento=descuento;
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

	

}
