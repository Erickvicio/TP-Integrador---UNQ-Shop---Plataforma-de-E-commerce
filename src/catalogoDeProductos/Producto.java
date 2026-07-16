package catalogoDeProductos;

import java.util.ArrayList;

public class Producto extends ItemDeCatalogo {
	Integer sku;
	String marca;
	String categoria;
	double precio;
	double peso;
//	int stock;
	
	ArrayList<Atributo> atributosExtra; 
	
	public Producto(String nombre,String descripcion,int descuento,int sku,String marca,String categoria,double precio,double peso){
		super(nombre,descripcion,descuento, categoria);
		
		if((nombre == ""||descripcion == "")||(marca == "" || categoria == "")) {
			throw new DatoInvalido("el atrobuto nombre o descripcion no pueden estar vacios");
		};
		if(((precio < 0)||(peso < 0)||(descuento < 0 || sku < 0))) {
			throw new DatoInvalido("los datos numericos deben ser correctos y mayores a 0");
		};
		
		
		this.sku=sku; 
		this.marca=marca;
		this.precio=precio;
		this.peso=peso;
		this.atributosExtra=new ArrayList<Atributo>();
	}


	public double precioBase() {
		// TODO Auto-generated method stub
		return precio;
	}
	
	public double precioFinal() {
		// TODO Auto-generated method stub
		return precio - (precio * descuento )/100;
	}
	
	public void agregarAtributo(String nombre,Object valor) {
		Atributo a=new Atributo(nombre,valor);
		atributosExtra.add(a);
	}


//	@Override
//	public void incrementarStock(int cantidad) {
//		this.stock += cantidad; 
//	}


	public ArrayList<Atributo> getAtributosExtra() {
		return atributosExtra;
	}


//	@Override
//	public void decrementarStock(int cantidad) {
//		if (this.stock >= cantidad) {
//			this.stock-=cantidad;
//		}
//	}


	public double getPeso() {
		return peso;
	}


	@Override
	public boolean estaDisponible() {
		return stock > 0;
	}



//	public boolean estaDisponible(int cantidad){
//		return this.stock >= cantidad; 
//	}
//
//	public int getStock() {
//		return stock;
//	}

	
}