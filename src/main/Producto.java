package main;

import java.util.ArrayList;

public class Producto extends ItemDeCatalogo {
	Integer sku;
	String marca;
	double precio;
	double peso;
	ArrayList<Atributo> atributosExtra;
	int stock=0;
	
	public Producto(String nombre,String descripcion,int descuento,int sku,String marca,String categoria,double precio,double peso){
		super(nombre,descripcion,descuento,categoria);
		
		if(marca == "") {
			throw new DatoInvalido("el atributo categorias no puede estar vacio");
		}else if (((precio < 0)||(peso < 0)||(descuento < 0 || sku < 0))) {
			throw new DatoInvalido("los datos numericos deben ser correctos y mayores a 0");
		}else {
			this.sku=sku;
			this.marca=marca;
			this.precio=precio;
			this.peso=peso;
			this.atributosExtra=new ArrayList<Atributo>();
		}
	}

	
	@Override
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


	@Override
	public void incrementarStock(int cantidad) {
		// TODO Auto-generated method stub
		this.stock+= cantidad;
	}


	public ArrayList<Atributo> getAtributosExtra() {
		return atributosExtra;
	}


	@Override
	public void decrementarStock(int cantidad) {
		// TODO Auto-generated method stub
		if (this.stock >= cantidad) {
			this.stock-=cantidad;
		}
	}
	
	public int getStock() {
		return stock;
	}
	
}
