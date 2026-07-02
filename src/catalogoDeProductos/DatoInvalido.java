package catalogoDeProductos;

public class DatoInvalido extends RuntimeException {
	String mensaje;
	
	public DatoInvalido(String mensaje) {
		super(mensaje);
		
	}
	
}

/*
 * Case explicitamente creada con el fin de testear
 * errores construccion de objetos con datos erroneos
 */