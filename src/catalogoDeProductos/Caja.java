package catalogoDeProductos;

import java.util.HashMap;

public class Caja extends ItemDeCatalogo {
	
	HashMap<ItemDeCatalogo,Integer> items;
	
	public Caja(String nombre,String descripcion,int descuento, String categoria) {
		super(nombre,descripcion,descuento, categoria); 
		items= new HashMap<>();
	}
	
	public Caja(String nombre,String descripcion,int descuento) {
		super(nombre,descripcion,descuento, null);
		items= new HashMap<>();
	}
	 
	public void agregarItem(ItemDeCatalogo item,int cantidad) {
			items.put(item,cantidad);
	}

//	public void decrementarStock() {
//		for (HashMap.Entry<ItemDeCatalogo, Integer> ic : this.items.entrySet()) {
//			ic.getKey().decrementarStock(ic.getValue());
//		} 
//	}
//	

	@Override
//	public void decrementarStock(int cantidad) {
//		for (HashMap.Entry<ItemDeCatalogo, Integer> ic : this.items.entrySet()) {
//			ic.getKey().decrementarStock(ic.getValue()*cantidad);
//		}
//	}
	
	public double precioBase() {
		double resultado=items.entrySet().stream().mapToDouble(i ->i.getKey().precioFinal() * i.getValue()).sum();
		return resultado;
	}
	
	public double precioFinal() {
		double preciobase= this.precioBase();
		return preciobase - ((preciobase * descuento )/100);
	}
 
//	@Override
//	public void incrementarStock(int cantidad) {
//		for(HashMap.Entry<ItemDeCatalogo, Integer> ic : this.items.entrySet()) {
//				int aumento= ic.getValue() * cantidad;
//				ic.getKey().incrementarStock(aumento);
//		}
//	}
//	
	
//	//PRUEBA
//	public boolean estaDisponible(int n) {
//		return items.entrySet().stream().allMatch(i -> i.getKey().estaDisponible(i.getValue() * n));
//	}
	
	@Override
	public boolean estaDisponible(){
		return this.stock > 0;
//				items.keySet().
//				stream().allMatch(i -> i.getStock() >= items.get(i));
//		return items.entrySet().stream().allMatch(i -> i.getKey().estaDisponible(i.getValue())); 
	} 

	@Override
	public double getPeso() {
		// TODO Auto-generated method stub
		return items.entrySet().stream()
	            .mapToDouble(i -> i.getKey().getPeso() * i.getValue())
	            .sum(); 
	} 


}