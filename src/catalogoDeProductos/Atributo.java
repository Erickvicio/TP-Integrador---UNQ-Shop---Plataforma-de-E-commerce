package catalogoDeProductos;

public class Atributo {
	String nombre;
	Object valor;

	public Atributo(String nombre,Object valor) {
		if((nombre == ""|| valor == null)) {
			throw new DatoInvalido("el atributo dinamico debe ser coherente");
		};
		this.nombre=nombre;
		this.valor=valor;
	}
	
	

}
