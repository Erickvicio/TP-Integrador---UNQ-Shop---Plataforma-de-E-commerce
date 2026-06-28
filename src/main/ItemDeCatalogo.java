package main;

public abstract class ItemDeCatalogo {
	String nombre;
	String descripcion;
	int descuento;
	String categoria;
	 
	public ItemDeCatalogo(String nombre,String descripcion,int descuento,String categoria) {
		if((nombre == ""||descripcion == "")|| categoria == "") {
			throw new DatoInvalido("el atrobuto nombre o descripcion no pueden estar vacios");
		}else {
			this.nombre=nombre;
			this.descripcion=descripcion;
			this.descuento=descuento;
			this.categoria=categoria;
		}
			
		
	}
	
	public String getCategoria() {
		return categoria;
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
	public String getNombre() {
		return this.nombre;
	}

}
