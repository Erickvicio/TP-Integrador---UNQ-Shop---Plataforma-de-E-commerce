package main;


import java.util.HashMap;

public class Caja extends ItemDeCatalogo {
	
	HashMap<ItemDeCatalogo,Integer> items;
	
	public Caja(String nombre,String descripcion,int descuento) {
		super(nombre,descripcion,descuento);
		items= new HashMap<>();
	}
	
	public void agregarItem(ItemDeCatalogo item,int cantidad) {
		
			items.put(item,cantidad);
	}

	public void decrementarStock() {
		for (HashMap.Entry<ItemDeCatalogo, Integer> ic : this.items.entrySet()) {
			ic.getKey().decrementarStock(ic.getValue());
		}
	}
	
	public void decrementarStock(int cantidad) {
		for (HashMap.Entry<ItemDeCatalogo, Integer> ic : this.items.entrySet()) {
			ic.getKey().decrementarStock(ic.getValue()*cantidad);
		}
	}
	
	
	@Override
	public double precioBase() {
		// TODO Auto-generated method stub
		double resultado=items.entrySet().stream().mapToDouble(i ->i.getKey().precioFinal() * i.getValue()).sum();
		return resultado;
	}
	
	public double precioFinal() {
		double preciobase= this.precioBase();
		return preciobase - ((preciobase * descuento )/100);
	}

	@Override
	public void incrementarStock(int cantidad) {
		// TODO Auto-generated method stub
		for(HashMap.Entry<ItemDeCatalogo, Integer> ic : this.items.entrySet()) {
				int aumento= ic.getValue() * cantidad;
				ic.getKey().incrementarStock(aumento);

		}
		
	}

	

}
