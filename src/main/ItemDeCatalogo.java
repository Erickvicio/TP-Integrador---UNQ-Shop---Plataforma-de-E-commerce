package main;

public abstract class ItemDeCatalogo {
	String nombre;
	String descripcion;
	int descuento;
	int cantidad;
	 
	public ItemDeCatalogo(String nombre,String descripcion,int descuento) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.descuento=descuento;
	}
	
	public abstract double precioBase();
	public abstract double precioFinal();
	public void incrementarStock(int cantidad) {
		this.cantidad = this.cantidad + cantidad;
	}
	public void decrementarStock(int cantidad) {
		this.cantidad = this.cantidad - cantidad;
	}
	public int getCantidad(){
		return cantidad;
	}

	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	protected abstract Integer peso();

	

}
